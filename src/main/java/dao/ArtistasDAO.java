/**
* Clase ArtistasDAO.java
*
* @author Roberto Garcia Fernandez
* @version 1.0
*/


package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import modelo.Artista;
import modelo.Especialidades;
import util.ConexDB;


public class ArtistasDAO {
    public Long insertar(Artista a) {

        String sql = "INSERT INTO artista (apodo, especialidades, Id_Persona) VALUES (?, ?, ?)";

        try (Connection conn = ConexDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, a.getApodoString());

            
            if (a.getEspecialidad() == null || a.getEspecialidad().length == 0) {
                throw new SQLException("El artista debe tener al menos una especialidad.");
            }

            ps.setString(2, a.getEspecialidad()[0].name());
            ps.setLong(3, a.getId());  
            int filas = ps.executeUpdate();
            if (filas == 0) return null;

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    long idArt = keys.getLong(1);
                    a.setIdArt(idArt);
                    return idArt;
                }
            }

        } catch (SQLException e) {
            System.err.println("ERROR en ArtistaDAO.insertar: " + e.getMessage());
        }

        return null;
    }


    public Artista buscarPorIdArt(long idArt) {

        String sql = """
                SELECT a.idArt, a.apodo, a.especialidades,
                       p.ID, p.email, p.nombre, p.nacionalidad, p.id_credenciales
                FROM artista a
                INNER JOIN persona p ON a.Id_Persona = p.ID
                WHERE a.idArt = ?
                """;

        try (Connection conn = ConexDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, idArt);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapArtista(rs);
            }

        } catch (SQLException e) {
            System.err.println("ERROR en ArtistaDAO.buscarPorIdArt: " + e.getMessage());
        }

        return null;
    }


    public Artista buscarPorIdPersona(long idPersona) {

        String sql = """
                SELECT a.idArt, a.apodo, a.especialidades,
                       p.ID, p.email, p.nombre, p.nacionalidad, p.id_credenciales
                FROM artista a
                INNER JOIN persona p ON a.Id_Persona = p.ID
                WHERE p.ID = ?
                """;

        try (Connection conn = ConexDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, idPersona);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapArtista(rs);
            }

        } catch (SQLException e) {
            System.err.println("ERROR en ArtistaDAO.buscarPorIdPersona: " + e.getMessage());
        }

        return null;
    }



    public List<Artista> listar() {

        List<Artista> lista = new ArrayList<>();

        String sql = """
                SELECT a.idArt, a.apodo, a.especialidades,
                       p.ID, p.email, p.nombre, p.nacionalidad, p.id_credenciales
                FROM artista a
                INNER JOIN persona p ON a.Id_Persona = p.ID
                """;

        try (Connection conn = ConexDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapArtista(rs));
            }

        } catch (SQLException e) {
            System.err.println("ERROR en ArtistaDAO.listar: " + e.getMessage());
        }

        return lista;
    }


    public boolean eliminar(long idArt) {
        String sql = "DELETE FROM artista WHERE idArt = ?";

        try (Connection conn = ConexDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, idArt);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("ERROR en ArtistaDAO.eliminar: " + e.getMessage());
        }

        return false;
    }


    private Artista mapArtista(ResultSet rs) throws SQLException {

        String especStr = rs.getString("especialidades");
        Especialidades[] especialidades = { Especialidades.valueOf(especStr) };

        return new Artista(
                rs.getLong("ID"),                
                rs.getString("email"),
                rs.getString("nombre"),
                rs.getString("nacionalidad"),
                rs.getLong("id_credenciales"),   
                rs.getLong("idArt"),            
                rs.getString("apodo"),
                especialidades
        );
    }
}
