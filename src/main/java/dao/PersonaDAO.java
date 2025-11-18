package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import modelo.Persona;
import util.ConexDB;

public class PersonaDAO {

	ConexDB conexDB=new ConexDB();
	Persona persona=null;
	
	public void getPersonas() {
		
		
		try {
			PreparedStatement ps = ConexDB.getConnection().prepareStatement("select * from persona");
			ResultSet rs=ps.executeQuery();
			
			rs.next();
			persona= new Persona(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4));
			System.out.println(persona.toString());
			ps.close();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	
	
}
