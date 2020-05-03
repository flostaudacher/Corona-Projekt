import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Visualisierung extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method 
		init(primaryStage);
	}
	// hier wird ein Liniendiagramm erstellt.
	private void init(Stage primaryStage) {
		HBox root = new HBox();
		Scene scene = new Scene(root, 1000, 700);

		NumberAxis xAxis= new NumberAxis();
		xAxis.setLabel("Ortsgröße/ Einwohnerzahl"); // Beschriftung der x Achse 

		NumberAxis yAxis= new NumberAxis();
		yAxis.setLabel("Migartionsanteil des Ortes in % %"); // Beschriftung der y Achse 

		LineChart<Number, Number> lineChart = new LineChart<Number,Number>(xAxis,yAxis);
		lineChart.setTitle("Kausalitätsdarstellung");

		XYChart.Series<Number, Number> data = new XYChart.Series<>();

		// ab hier wird die befüllung des Diagrammes Durchgeführt
		Import.setup();
		for (int Rowc = 1; Rowc < Import.anzahlZeilen/100; Rowc=Rowc+5) {
			float populationAVG=0;
			float foreignPopulation=0;
			int timeForAVG=10;
			for (int runV= 0;  runV <= timeForAVG; runV++) {
				populationAVG=populationAVG+getWert(Import.arr[Rowc][8]);
			}
			populationAVG=populationAVG/timeForAVG;
			for (int runV= 0;  runV <= timeForAVG; runV++) {
				foreignPopulation=foreignPopulation+getWert(Import.arr[Rowc][10]);
			}
			foreignPopulation=foreignPopulation/timeForAVG;
			Number NumPop=(populationAVG);
			Number percent=(foreignPopulation/populationAVG)*100;
			data.getData().add(new XYChart.Data<Number, Number>(NumPop,percent));
		}
		lineChart.getData().add(data);
		root.getChildren().add(lineChart);
		primaryStage.setTitle("Linechart of my Corona Projekt");
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	private int getWert(String string) {
		int Wert;
		Wert = Integer.parseInt(string);
		return Wert;
	}
	public static void main(String[] args) {
		launch(args);
	}
}
