package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Credenciales;

import util.ConexDB;

public class CredencialesDAO {
	ConexDB conexDB=new ConexDB();
	Credenciales c=null;
	
	public void getPersonas() {
		
		
		try {
			PreparedStatement ps = ConexDB.getConnection().prepareStatement("select * from persona");
			ResultSet rs=ps.executeQuery();
			
			rs.next();
			c= new Credenciales();
			System.out.println(c.toString());
			ps.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}
}
