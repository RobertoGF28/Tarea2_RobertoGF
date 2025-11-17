package util;

import java.sql.*;
import java.util.Properties;

public class ConexDB {
	
	Connection conexion = null;
	Properties p = new Properties();
	Statement stmt = null;
	ResultSet resultado = null;{
	
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = p.getProperty("db.url");
			conexion=DriverManager.getConnection(url, "root", "");
			
			
			
			
			
		}catch(Exception e) {
			
		}
	
	}
}
