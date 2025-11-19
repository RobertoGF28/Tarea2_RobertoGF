package util;


import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;



public class ConexDB {

	   private static String url;
	    private static String user;
	    private static String password;

	   

	    private static void loadProperties() {
	        try (InputStream input = ConexDB.class.getClassLoader()
	                .getResourceAsStream("application.properties")) {

	            if (input == null) {
	                System.out.println("No se encontro application.properties");
	            }

	            Properties p = new Properties();
	            p.load(input);

	            url = p.getProperty("db.url");
	            user = p.getProperty("db.user");
	            password = p.getProperty("db.password");

	        } catch (IOException e) {
	            System.out.println("Error al cargar application.properties: " + e.getLocalizedMessage());
	        }
	    }

	    public static Connection getConnection() throws SQLException {
	    	loadProperties();
	    	
	        return DriverManager.getConnection(url, user, password);
	    }  
    }

