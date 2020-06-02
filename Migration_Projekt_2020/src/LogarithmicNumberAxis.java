import com.sun.javafx.charts.ChartLayoutAnimator;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.chart.ValueAxis;
import javafx.util.Duration;

public class LogarithmicNumberAxis extends ValueAxis<Number> {

    private Object currentAnimationID;
    private final ChartLayoutAnimator animator = new ChartLayoutAnimator(this);    
    private final DoubleProperty logUpperBound = new SimpleDoubleProperty();
    private final DoubleProperty logLowerBound = new SimpleDoubleProperty();
    public LogarithmicNumberAxis() {
        super(500, 50000);
        bindLogBoundsToDefaultBounds();
    }

    public LogarithmicNumberAxis(double lowerBound, double upperBound) {
        super(lowerBound, upperBound);
        validateBounds(lowerBound, upperBound);
        bindLogBoundsToDefaultBounds();
    }

    public void setLogarithmizedUpperBound(double d) {
        double nd = Math.pow(2, Math.ceil(Math.log(d)));
        setUpperBound(nd == d ? nd * 2 : nd);
    }
    private void bindLogBoundsToDefaultBounds() {
        logLowerBound.bind(new DoubleBinding() {
            {
                super.bind(lowerBoundProperty());
            }

            @Override
            protected double computeValue() {
                return Math.log(lowerBoundProperty().get());
            }
        });
        logUpperBound.bind(new DoubleBinding() {
            {
                super.bind(upperBoundProperty());
            }

            @Override
            protected double computeValue() {
                return Math.log(upperBoundProperty().get());
            }
        });
    }
    private void validateBounds(double lowerBound, double upperBound) throws IllegalLogarithmicRangeException {
        if (lowerBound < 0 || upperBound < 0 || lowerBound > upperBound) {
            throw new IllegalLogarithmicRangeException(
                    "The logarithmic range should be in [0,Double.MAX_VALUE] and the lowerBound should be less than the upperBound");
        }
    }
    @Override
    protected List<Number> calculateMinorTickMarks() {
        List<Number> minorTickMarksPositions = new ArrayList<>();
        return minorTickMarksPositions;
    }
    @Override
    protected List<Number> calculateTickValues(double length, Object range) {
        LinkedList<Number> tickPositions = new LinkedList<>();
        if (range != null) {
            double lowerBound = ((double[]) range)[0];
            double upperBound = ((double[]) range)[1];

            for (double i = Math.log(lowerBound); i <= Math.log(upperBound); i++) {
                tickPositions.add(Math.pow(2, i));
            }

            if (!tickPositions.isEmpty()) {
                if (tickPositions.getLast().doubleValue() != upperBound) {
                    tickPositions.add(upperBound);
                }
            }
        }

        return tickPositions;
    }


    @Override
    protected double[] getRange() {
        return new double[]{
            getLowerBound(),
            getUpperBound()
        };
    }

    @Override
    protected String getTickMarkLabel(Number value) {
        NumberFormat formatter = NumberFormat.getInstance();
        formatter.setMaximumIntegerDigits(10);
        formatter.setMinimumIntegerDigits(1);
        return formatter.format(value);
    }
    @Override
    protected void setRange(Object range, boolean animate) {
        if (range != null) {
            final double[] rangeProps = (double[]) range;
            final double lowerBound = rangeProps[0];
            final double upperBound = rangeProps[1];

            final double oldLowerBound = getLowerBound();
            setLowerBound(lowerBound);
            setUpperBound(upperBound);
            if (animate) {
                animator.stop(currentAnimationID);
                currentAnimationID = animator.animate(
                        new KeyFrame(Duration.ZERO,
                                new KeyValue(currentLowerBound, oldLowerBound)
                        ),
                        new KeyFrame(Duration.millis(700),
                                new KeyValue(currentLowerBound, lowerBound)
                        )
                );
            } else {
                currentLowerBound.set(lowerBound);
            }
        }
    }
    @Override
    public Number getValueForDisplay(double displayPosition) {
        double delta = logUpperBound.get() - logLowerBound.get();
        if (getSide().isVertical()) {
            return Math.pow(2, (((displayPosition - getHeight()) / -getHeight()) * delta) + logLowerBound.get());
        } else {
            return Math.pow(2, (((displayPosition / getWidth()) * delta) + logLowerBound.get()));
        }
    }

    @Override
    public double getDisplayPosition(Number value) {
        double delta = logUpperBound.get() - logLowerBound.get();
        double deltaV = Math.log(value.doubleValue()) - logLowerBound.get();
        if (getSide().isVertical()) {
            return (1. - ((deltaV) / delta)) * getHeight();
        } else {
            return ((deltaV) / delta) * getWidth();
        }
    }
    public class IllegalLogarithmicRangeException extends RuntimeException {
        public IllegalLogarithmicRangeException(String message) {
            super(message);
        }
    }
}