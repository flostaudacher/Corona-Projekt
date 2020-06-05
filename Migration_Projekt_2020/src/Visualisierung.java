import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
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
		Scene scene = new Scene(root, 700, 700);

		LogarithmicNumberAxis xAxis= new LogarithmicNumberAxis();
		xAxis.setLabel("Ortsgröße/ Einwohnerzahl"); // Beschriftung der x Achse 

		NumberAxis yAxis= new NumberAxis();
		yAxis.setLabel("Migartionsanteil des Ortes in  %"); // Beschriftung der y Achse 
		
		AreaChart<Number, Number> areaChart = new AreaChart<Number,Number>(xAxis,yAxis);
		areaChart.setTitle("Kausalitätsdarstellung");

		XYChart.Series<Number, Number> data = new XYChart.Series<>();
		
		data.setName("Werte des % Ausländeranteils bei jeweiliger Bevölkerung in Niederösterreich");
		// ab hier wird die befüllung des Diagrammes Durchgeführt
		Import.setup();
		int stepsAVG=9;
		data.getData().add(new XYChart.Data<Number, Number>(getWert(Import.arr[1][8]),getWert(Import.arr[1][10]))); // erster Wert aus der Tabelle
		for (int Rowc = 1; Rowc < Import.anzahlZeilen + 1; Rowc=Rowc+5) {								// hier wird noch eine Lösung gesucht den Grahpen aussagekräftiger zu machen
			float populationAVG=0;
			float foreignPopulation=0;
			for (int runV= 0;  runV <= stepsAVG; runV++) {
				populationAVG=populationAVG+getWert(Import.arr[Rowc][8]);
			}
			populationAVG=populationAVG/stepsAVG;
			for (int runV= 0;  runV <= stepsAVG; runV++) {
				foreignPopulation=foreignPopulation+getWert(Import.arr[Rowc][10]);
			}
			foreignPopulation=foreignPopulation/stepsAVG;
			Number NumPop=(populationAVG);
			Number percent=(foreignPopulation/populationAVG)*100;
			data.getData().add(new XYChart.Data<Number, Number>(NumPop,percent));
		}
		
		areaChart.setCreateSymbols(false);
		
		areaChart.getData().add(data);
		root.getChildren().add(areaChart);
		primaryStage.setTitle("Areachart of my Corona Projekt");
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
	public void setupChartoption() {
		
	}
	
}
