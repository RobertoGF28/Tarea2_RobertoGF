/**
* Clase CoordinacionDAO.java
*
* @author Roberto Garcia Fernandez
* @version 1.0
*/


package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import modelo.Coordinacion;
import util.ConexDB;

public class CoordinacionDAO {
    public Long insertar(Coordinacion c) {
        String sql = "INSERT INTO coordinacion (senior, fechasenior, Id_Persona) VALUES (?, ?, ?)";

        try (Connection conn = ConexDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setBoolean(1, c.isSenior());

            if (c.getFechasenior() != null) {
                ps.setDate(2, Date.valueOf(c.getFechasenior()));
            } else {
                ps.setNull(2, Types.DATE);
            }

            ps.setLong(3, c.getId());  // ← PERSONA YA TIENE ID

            int rows = ps.executeUpdate();
            if (rows == 0) return null;

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    long idCoord = keys.getLong(1);
                    c.setIdCoord(idCoord);
                    return idCoord;
                }
            }

        } catch (SQLException e) {
            System.out.println("Error en CoordinacionDAO.insertar: " + e.getMessage());
        }

        return null;
    }



    public Coordinacion buscarPorIdCoord(long idCoord) {

        String sql =("SELECT c.idCoord, c.senior, c.fechasenior, " +
                "p.ID, p.email, p.nombre, p.nacionalidad, p.id_credenciales " +
                "FROM coordinacion c " +
                "INNER JOIN persona p ON c.Id_Persona = p.ID " +
                "WHERE c.idCoord = ?");

            
        try (Connection conn = ConexDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, idCoord);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapCoordinador(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error en CoordinacionDAO.buscarPorIdCoord: " + e.getMessage());
        }

        return null;
    }



    public Coordinacion buscarPorIdPersona(long idPersona) {

        String sql =
            "SELECT c.idCoord, c.senior, c.fechasenior, " +
            "p.ID, p.email, p.nombre, p.nacionalidad, p.id_credenciales " +
            "FROM coordinacion c " +
            "INNER JOIN persona p ON c.Id_Persona = p.ID " +
            "WHERE p.ID = ?";

        try (Connection conn = ConexDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, idPersona);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return mapCoordinador(rs);
                }
            }

        } catch (SQLException e) {
            System.out.println("Error en CoordinacionDAO.buscarPorIdPersona: " + e.getMessage());
        }

        return null;
    }




    public List<Coordinacion> listar() {

        List<Coordinacion> lista = new ArrayList<>();

        String sql =
            "SELECT c.idCoord, c.senior, c.fechasenior, " +
            "p.ID, p.email, p.nombre, p.nacionalidad, p.id_credenciales " +
            "FROM coordinacion c " +
            "INNER JOIN persona p ON c.Id_Persona = p.ID";

        try (Connection conn = ConexDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapCoordinador(rs));
            }

        } catch (SQLException e) {
            System.out.println("Error en CoordinacionDAO.listar: " + e.getMessage());
        }

        return lista;
    }


    public boolean eliminar(long idCoord) {

        String sql = "DELETE FROM coordinacion WHERE idCoord = ?";

        try (Connection con = ConexDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setLong(1, idCoord);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("Error en CoordinacionDAO.eliminar: " + e.getMessage());
        }

        return false;
    }


    private Coordinacion mapCoordinador(ResultSet rs) throws SQLException {

        LocalDate fechaSenior =
            rs.getDate("fechasenior") != null ?
            rs.getDate("fechasenior").toLocalDate() : null;

        return new Coordinacion(
            rs.getLong("ID"),
            rs.getString("email"),
            rs.getString("nombre"),
            rs.getString("nacionalidad"),
            rs.getLong("id_credenciales"),
            rs.getLong("idCoord"),
            rs.getBoolean("senior"),
            fechaSenior
        );
    }
    
}
