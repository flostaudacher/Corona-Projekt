import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class sqlConnection {
	private final String url ="jdbc:mysql://localhost:3306/";
	private String user = "root";
	private String password = "13456stau";
	private String db = "java";
	private String command;
	public static String ergStr = "";
	public sqlConnection (String s) {
		this.setCommand(" SELECT * FROM mainexamples WHERE name LIKE '%" +s+"%'");
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();

			Connection con = DriverManager.getConnection(this.getUrl(), this.getUser(), this.getPassword());
			
			Statement stt= con.createStatement();
			stt.execute("USE"+" "+this.getDb());
			
			ResultSet res = stt.executeQuery(this.getCommand());
			
			
			while (res.next()) {
				ergStr = ergStr.concat("\n"+res.getString("ID") + " "+ res.getString("Name")+ "\n");
			}
			
			ausgeben1();
			res.close();
			stt.close();
			con.close();
		}
		catch (Exception e){
			System.out.println("Error");
		}
	}

	public static void ausgeben1() {
		// TODO Auto-generated method stub
		System.out.println(ergStr);
	}

	static void ausgeben() {
		// TODO Auto-generated method stub
		System.out.println(ergStr);
	}

	private String getCommand() {
		return command;
	}

	private void setCommand(String command) {
		this.command=command;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDb() {
		return db;
	}

	public void setDb(String db) {
		this.db = db;
	}

	public String getUrl() {
		return url;
	}

} 