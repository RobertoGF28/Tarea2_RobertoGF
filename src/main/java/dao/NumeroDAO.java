/**
* Clase NumeroDAO.java
*
* @author Roberto Garcia Fernandez
* @version 1.0
*/


package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import modelo.Numero;
import util.ConexDB;

public class NumeroDAO {
	 public List<Numero> listarPorEspectaculo(Long idEspectaculo) {

	        List<Numero> lista = new ArrayList<>();

	        String sql = "SELECT id, orden, nombre, duracion FROM numero WHERE id_espectaculo = ? ORDER BY orden";

	        try (Connection conn = ConexDB.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {

	            ps.setLong(1, idEspectaculo);

	            try (ResultSet rs = ps.executeQuery()) {
	                while (rs.next()) {

	                    Numero n = new Numero(
	                            rs.getLong("id"),
	                            rs.getInt("orden"),
	                            rs.getString("nombre"),
	                            rs.getDouble("duracion")
	                    );

	                    lista.add(n);
	                }
	            }

	        } catch (SQLException e) {
	            System.err.println("Error en NumeroDAO.listarPorEspectaculo: " + e.getMessage());
	        }

	        return lista;
	    }
}
