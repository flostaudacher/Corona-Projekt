import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

public class Import {
 public static String [][] arr;
 public static int anzahlZeilen=4586;
 public static int anzahlSpalten=12;
 public static String filelocation= "E:\\Migration_data\\\\noe_pop_migration_background_2012_2019_lau2.dat";
 public static String InputLine = "";
	
	static void setup() {
		arr = new String [anzahlZeilen][anzahlSpalten];
		
		Scanner sc= null;
		int Rowc = 0;
		System.out.println("Array wird angelegt");
		
		try 
		
		{
			sc= new Scanner (new BufferedReader(new FileReader(filelocation))); // file wird angelegt
			
			while (sc.hasNextLine()) {
				InputLine= sc.nextLine();
				String[] inArr = InputLine.split(";");
				for (int x = 0; x < inArr.length; x++) {
					arr[Rowc][x]=inArr[x];
				}
				Rowc++;
			}
		}catch (Exception e) {
			System.out.println(e);
		}
		
		
	}
	public static void printArray() {
		System.out.println("Array wird ausgegeben");
		for (int Rowc = 0; Rowc < anzahlZeilen; Rowc++) {
			for (int Colc = 0; Colc < anzahlSpalten; Colc++) {
				System.out.println(arr[Rowc][Colc]);
			}
			System.out.println();
		}
		return;
	}
	static void getNumOfRowCol() {
		Scanner sc= null;
		System.out.println("Anzahl an Zeilen und Spalten werden hier berechnet");
		try {
			sc = new Scanner(new BufferedReader(new FileReader(filelocation)));
			sc.useDelimiter(";");
			while (sc.hasNextLine()) {
				InputLine=sc.nextLine();
				String[] inArr = InputLine.split(";");
				anzahlZeilen++;
				anzahlSpalten = inArr.length;
			}
		} catch (Exception e ) {
			System.out.println(e);
		}
		
		
	}
}
