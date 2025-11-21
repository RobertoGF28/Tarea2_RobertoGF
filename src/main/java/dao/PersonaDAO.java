package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import modelo.Persona;
import util.ConexDB;

public class PersonaDAO {
	
	public Long insertar(Persona p) {
        String sql = "INSERT INTO persona (email, nombre, nacionalidad, id_credenciales) VALUES (?, ?, ?, ?)";
        try (Connection conn = ConexDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, p.getEmail());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getNacionalidad());
            if (p.getId_credenciales() == null) ps.setNull(4, Types.BIGINT);
            else ps.setLong(4, p.getId_credenciales());

            int rows = ps.executeUpdate();
            if (rows == 0) return null;
            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    long id = keys.getLong(1);
                    p.setId(id);
                    return id;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error en PersonaDAO.insertar: " + e.getMessage());
        }
        return null;
    }

    public boolean existeEmail(String email) {
        String sql = "SELECT 1 FROM persona WHERE email = ?";
        try (Connection conn = ConexDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            
            
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            System.err.println("Error en PersonaDAO.existeEmail: " + e.getMessage());
        }
        return false;
    }
    public boolean actualizar(Persona p) {
        String sql = "UPDATE persona SET email=?, nombre=?, nacionalidad=? WHERE ID=?";

        try (Connection conn = ConexDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, p.getEmail());
            ps.setString(2, p.getNombre());
            ps.setString(3, p.getNacionalidad());
            ps.setLong(4, p.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("ERROR en PersonaDAO.actualizar: " + e.getMessage());
        }

        return false;
    }
    public Persona buscarPorIdCredenciales(long idCred) {

        String sql = "SELECT * FROM persona WHERE id_credenciales = ?";

        try (Connection conn = ConexDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, idCred);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Persona(
                    rs.getLong("ID"),
                    rs.getString("email"),
                    rs.getString("nombre"),
                    rs.getString("nacionalidad"),
                    rs.getLong("id_credenciales")
                );
            }

        } catch (SQLException e) {
            System.err.println("ERROR buscarPorIdCredenciales: " + e.getMessage());
        }

        return null;
    }

}



		

	
	
	

