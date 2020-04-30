import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

public class Import {
	public static Scanner sc;
	
	public void importData () {
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		try {

			Reader  csvFile = new FileReader("E:\\Migration_data\\noe_pop_migration_background_2012_2019_lau2.dat");
			br = new BufferedReader(csvFile);
			try{
				while ((line = br.readLine()) != null) {
					System.out.println(line);
				}
			}
			finally {
				if(br != null)
					br.close();
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
