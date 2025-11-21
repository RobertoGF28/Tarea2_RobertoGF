package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import modelo.Credenciales;
import modelo.Perfiles;
import util.ConexDB;

public class CredencialesDAO {
	public Long agregar(Credenciales c) {
	    String sql = "INSERT INTO credenciales (nombre, password, perfil) VALUES (?, ?, ?)";

	    try (Connection conn = ConexDB.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

	        ps.setString(1, c.getNombre());
	        ps.setString(2, c.getPassword());
	        ps.setString(3, c.getPerfil().name());

	        int rows = ps.executeUpdate();
	        if (rows == 0) return null;

	        try (ResultSet keys = ps.getGeneratedKeys()) {
	            if (keys.next()) {
	                long id = keys.getLong(1);
	                c.setId(id);
	                return id;
	            }
	        }

	    } catch (SQLException e) {
	        System.err.println("ERROR CredencialesDAO.agregar: " + e.getMessage());
	    }

	    return null;
	}

	 
	    public Credenciales obtenerPorNombre(String nombre) {
	        String sql = "SELECT ID, nombre, password, perfil FROM credenciales WHERE nombre = ?";
	        try (Connection con = ConexDB.getConnection();
	             PreparedStatement ps = con.prepareStatement(sql)) {

	            ps.setString(1, nombre);
	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                    Credenciales c = new Credenciales(null, null, null, null);
	                    c.setId(rs.getLong("ID"));
	                    c.setNombre(rs.getString("nombre"));
	                    c.setPassword(rs.getString("password"));
	                    c.setPerfil(Perfiles.valueOf(rs.getString("perfil")));
	                    return c;
	                }
	            }
	        } catch (SQLException e) {
	            System.out.println("Error al acceder al sql: " + e.getMessage());
	        }
	        return null;
	    }

	    
	    public boolean login(String nombre, String password) {
	        Credenciales c = obtenerPorNombre(nombre);
	        if (c == null) return false;          
	        return c.getPassword().equals(password);
	    }


	    public boolean existeNombre(String nombre) {
	        String sql = "SELECT 1 FROM credenciales WHERE nombre = ?";
	        try (Connection conn = ConexDB.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {

	            ps.setString(1, nombre);
	            try (ResultSet rs = ps.executeQuery()) {
	                return rs.next();
	            }
	        } catch (SQLException e) {
	            System.err.println("Error en CredencialesDAO.existeNombre: " + e.getMessage());
	            return false;
	        }
	    }
}
