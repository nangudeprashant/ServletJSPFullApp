package databaseUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MySQLUtil {
	private  static String databaseURL = "jdbc:mysql://localhost:3306/test";
    private static Connection conn = null;
	public static Connection getConnetion() throws SQLException{
	        Properties props = new Properties();
	        props.put("user", "root");
	        props.put("password", "root");
	        try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
	        conn = DriverManager.getConnection(databaseURL, props);
	        if (conn != null) {
	            System.out.println("Connected to the database");
	        }
	        else {
	        	throw new SQLException();
	        }
	        return conn;
	}
}
