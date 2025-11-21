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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.Numero;
import util.ConexDB;

public class NumeroDAO {
	
	public Long insertarNumero(Numero n, Long idEspectaculo, Long idArtista) {

        String sql = "INSERT INTO numero (orden, nombre, duracion, id_espectaculo, id_artista) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = ConexDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, n.getOrden());
            ps.setString(2, n.getNombre());
            ps.setDouble(3, n.getDuracion());
            ps.setLong(4, idEspectaculo);
            ps.setLong(5, idArtista);

            int rows = ps.executeUpdate();
            if (rows == 0) return null;

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    long id = keys.getLong(1);
                    n.setIdNum(id);
                    return id;
                }
            }

        } catch (SQLException e) {
            System.err.println("ERROR NumeroDAO.insertarNumero: " + e.getMessage());
        }

        return null;
    }

 
    public boolean actualizarNumero(Numero n, Long idArtista) {

        String sql = "UPDATE numero SET orden = ?, nombre = ?, duracion = ?, id_artista = ? WHERE id = ?";

        try (Connection conn = ConexDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, n.getOrden());
            ps.setString(2, n.getNombre());
            ps.setDouble(3, n.getDuracion());
            ps.setLong(4, idArtista);
            ps.setLong(5, n.getIdNum());

            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            System.err.println("ERROR NumeroDAO.actualizarNumero: " + e.getMessage());
        }

        return false;
    }
	
	
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
	 
	 public List<Numero> listarPorArtista(Long idArtista) {

		    List<Numero> lista = new ArrayList<>();

		    String sql = "SELECT id, orden, nombre, duracion, id_espectaculo " +
		                 "FROM numero WHERE id_artista = ? ORDER BY id_espectaculo, orden";

		    try (Connection conn = ConexDB.getConnection();
		         PreparedStatement ps = conn.prepareStatement(sql)) {

		        ps.setLong(1, idArtista);

		        try (ResultSet rs = ps.executeQuery()) {

		            while (rs.next()) {
		                Numero n = new Numero(
		                        rs.getLong("id"),
		                        rs.getInt("orden"),
		                        rs.getString("nombre"),
		                        rs.getDouble("duracion")
		                );


		                long idEsp = rs.getLong("id_espectaculo");
		                numeroEspectaculo.put(n.getIdNum(), idEsp);

		                lista.add(n);
		            }
		        }

		    } catch (SQLException e) {
		        System.err.println("Error en NumeroDAO.listarPorArtista: " + e.getMessage());
		    }

		    return lista;
		}


		private static Map<Long, Long> numeroEspectaculo = new HashMap<>();

		public Long obtenerIdEspectaculoDeNumero(Long idNumero) {
		    return numeroEspectaculo.get(idNumero);
		}
}
