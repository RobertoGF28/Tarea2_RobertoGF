package util;


import java.sql.*;
import java.util.Properties;



public class ConexDB {

	static Properties p= new Properties();
	
	
	
    private static final String URL = "jdbc:mysql://localhost:3306/circo_robertogarcia"; 
    private static final String USER = "root";
    private static final String PASSWORD = "";
    

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";


    public static Connection getConnection() {
        Connection connection = null;
        try {
        	
        

            Class.forName(DRIVER);
            
            System.out.println("Intentando conectar a la base de datos...");
            

            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            
            System.out.println("Conexion exitosa a la base de datos.");
            
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontro el driver JDBC de MySQL.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Error: Fallo al conectar con la base de datos.");
            System.err.println("Codigo de error SQL: " + e.getSQLState());
            e.printStackTrace();
        }
        return connection;
    }


    public static void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Conexion cerrada.");
            }
        } catch (SQLException e) {
            System.err.println("Error al intentar cerrar la conexion.");
            e.printStackTrace();
        }
    }
}
