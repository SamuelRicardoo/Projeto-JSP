package connection;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexão {
	//BANCO OFICIAL: cursojsp
	private static String url = "jdbc:mysql://localhost:3306/cursojsp?autoReconnect=true";
	private static String password = "admin";
	private static String user = "root";
	
	private static Connection con = null;
	
	
	public Conexão() {
		conectar();
	}
	
	
	static {
		conectar();
	}
	
	
	private static void conectar() {
		
		try {
			if(con == null) {
				Class.forName("com.mysql.cj.jdbc.Driver");
				con = DriverManager.getConnection(url,user,password);
				con.setAutoCommit(false);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection() {
		return con;
	}
	
	
	
	

}
