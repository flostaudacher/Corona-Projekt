import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Scanner;

public class Migration_Main {
	static Scanner sc;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s;
		BufferedReader br = null;
		try {
			Reader fileReader = new FileReader("E:\\Migration_data\\noe_pop_migration_background_2012_2019_lau2.dat");
			br = new BufferedReader(fileReader);
			try {
				while ((s = br.readLine()) != null ) {
					System.out.println(s);
				}
			}
			finally { 
				if (br != null) {
					br.close();
				}
			}
		}
		catch( IOException io) {
			System.out.println(io.getMessage());
		}
	}
}
