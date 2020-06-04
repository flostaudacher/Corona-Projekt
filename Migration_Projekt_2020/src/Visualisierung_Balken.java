import java.util.Arrays;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.stage.Stage;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Series;

public class Visualisierung_Balken extends Application {
	public static int groupArr[] = {500, 1000, 5000, 12500, 25000, 45000};
	public static float WertArr[] = new float [6];
	//Defining the 
	@Override
	public void start(Stage primaryStage) {    
		// TODO Auto-generated method 
		init(primaryStage);
	}
	// hier wird ein Liniendiagramm erstellt.
	private void init(Stage primaryStage) {
		//Defining the groups

		CategoryAxis xAxis = new CategoryAxis();  
		xAxis.setCategories(FXCollections.<String>
		observableArrayList(Arrays.asList("GROUP 1", "GROUP 2", "GROUP 3", "GROUP 4", "GROUP 5", "GROUP 6")));
		xAxis.setLabel("Anteil in den Verschiedenen Jahren");

		NumberAxis yAxis = new NumberAxis();
		yAxis.setLabel("Anteil des Migrationsantiel in %");

		//Creating the Bar chart
		BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis); 
		barChart.setTitle("Werte des % Ausländeranteils bei jeweiliger Bevölkerung in Niederösterreich");
		
		
		
		Import.setup();
		//Prepare XYChart.Series objects by setting data    
		XYChart.Series<String, Number> series1 = new XYChart.Series<>();	
		XYChart.Series<String, Number> series2 = new XYChart.Series<>();
		XYChart.Series<String, Number> series3 = new XYChart.Series<>();
		XYChart.Series<String, Number> series4 = new XYChart.Series<>();		// Erstellen der Verschiedenen Charts für die Jahre 
		XYChart.Series<String, Number> series5 = new XYChart.Series<>();
		XYChart.Series<String, Number> series6 = new XYChart.Series<>();
		XYChart.Series<String, Number> series7 = new XYChart.Series<>();
		XYChart.Series<String, Number> series8 = new XYChart.Series<>();
		create(series1, 2012);
		create(series2, 2013);
		create(series3, 2014);
		create(series4, 2015);
		create(series5, 2016);
		create(series6, 2017);
		create(series7, 2018);
		create(series8, 2019);
		//Setting the data to bar chart       
		barChart.getData().addAll(series1, series2, series3, series4, series5, series6, series7, series8);		//darstellen der Charts 
		//Creating a Group object 
		Group root = new Group(barChart);
		//Creating a scene object
		Scene scene = new Scene(root, 1000, 700);

		barChart.setCategoryGap(20);
		primaryStage.setTitle("Balkendiagramm meiner Corona Arbeit");
		primaryStage.setScene(scene);
		primaryStage.show();       
		
	}
	private void create(Series<String, Number> s, int sy) {
		s.setName(""+sy);
		getWertPerYear(sy);
		for (int i = 0; i < 6 ;  i++) {
			s.getData().add(new XYChart.Data<>("GROUP "+(i+1), WertArr[i])); //erstellen und eintragen der Daten
		}
		reset();
	}
	private void reset() {
		for(int i = 0; i<WertArr.length; i++) {
			WertArr[i]=0;
		}
	}
	private void getWertPerYear(int year) {
		int arr[] = new int [6];
		for (int Rowc = 1; Rowc < Import.anzahlZeilen; Rowc++) {
			if (getWert(Import.arr[Rowc][11]) == year) 
			{
				if (getWert(Import.arr[Rowc][5]) < groupArr[0]) {
					arr[0]++;
					WertArr[0] = WertArr[0] + (getWert(Import.arr[Rowc][10])/getWert(Import.arr[Rowc][5]));
				}
				for (int x  = 1; x < 6; x++ ) {
					if (getWert(Import.arr[Rowc][5]) > groupArr[x-1] && getWert(Import.arr[Rowc][5]) < groupArr[x]) {
						arr[x]++;
						WertArr[x] = WertArr[x] + (getWert(Import.arr[Rowc][10])/getWert(Import.arr[Rowc][5]));
					}
				}
			}	
		}
		for (int i = 0 ; i < 6; i++) {
			WertArr[i] = WertArr[i]/arr[i]*100;
		}
	}
	public static void main(String args[]){
		launch(args);
	}
	private float getWert(String string) {
		int Wert;
		Wert = Integer.parseInt(string);
		float Wertx=Wert;
		return Wertx;
	}
}




