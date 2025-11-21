/**
* Clase EspectaculoDAO.java
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import modelo.Coordinacion;
import modelo.Espectaculo;
import util.ConexDB;

public class EspectaculoDAO {
	public Long insertar(Espectaculo e) {

        String sql = "INSERT INTO espectaculo (nombre, fechaini, fechafin, id_coordinador) VALUES (?, ?, ?, ?)";

        try (Connection conn = ConexDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, e.getNombre());
            ps.setDate(2, Date.valueOf(e.getFechaInicio()));
            ps.setDate(3, Date.valueOf(e.getFechaFin()));

            // IMPORTANTE: usar id_Coord, NO getCoordinador()
            ps.setLong(4, e.getId_Coord());

            int rows = ps.executeUpdate();
            if (rows == 0) return null;

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    long id = keys.getLong(1);
                    e.setIdEsp(id);
                    return id;
                }
            }

        } catch (SQLException ex) {
            System.err.println("ERROR EspectaculoDAO.insertar: " + ex.getMessage());
        }

        return null;
    }


    public boolean actualizar(Espectaculo e) {

        String sql = "UPDATE espectaculo SET nombre = ?, fechaini = ?, fechafin = ?, id_coordinador = ? WHERE id = ?";

        try (Connection conn = ConexDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, e.getNombre());
            ps.setDate(2, Date.valueOf(e.getFechaInicio()));
            ps.setDate(3, Date.valueOf(e.getFechaFin()));

            // IMPORTANTE: usar id_Coord
            ps.setLong(4, e.getId_Coord());

            ps.setLong(5, e.getIdEsp());

            return ps.executeUpdate() == 1;

        } catch (SQLException ex) {
            System.out.println("Error al actualizar espectáculo: " + ex.getMessage());
        }
        return false;
    }


    public boolean eliminar(long id) {

        String sql = "DELETE FROM espectaculo WHERE id = ?";

        try (Connection conn = ConexDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            return ps.executeUpdate() == 1;

        } catch (SQLException ex) {
            System.out.println("Error al eliminar espectáculo: " + ex.getMessage());
        }
        return false;
    }


    public Espectaculo buscarPorId(long id) {

        String sql = "SELECT * FROM espectaculo WHERE id = ?";

        try (Connection conn = ConexDB.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapEspectaculo(rs);
            }

        } catch (SQLException ex) {
            System.out.println("Error en buscarPorId: " + ex.getMessage());
        }
        return null;
    }


    public Espectaculo buscarPorNombre(String nombre) {

        String sql = "SELECT * FROM espectaculo WHERE nombre = ?";

        try (Connection con = ConexDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return mapEspectaculo(rs);
            }

        } catch (SQLException ex) {
            System.out.println("Error en buscarPorNombre: " + ex.getMessage());
        }
        return null;
    }

    public List<Espectaculo> listarTodos() {

        List<Espectaculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM espectaculo ORDER BY fechaini";

        try (Connection con = ConexDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapEspectaculo(rs));
            }

        } catch (SQLException ex) {
            System.out.println("Error listando espectáculos: " + ex.getMessage());
        }

        return lista;
    }


    public boolean existeNombre(String nombre) {

        String sql = "SELECT 1 FROM espectaculo WHERE nombre = ?";

        try (Connection con = ConexDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }

        } catch (SQLException ex) {
            System.out.println("Error en existeNombre: " + ex.getMessage());
        }
        return false;
    }

    private Espectaculo mapEspectaculo(ResultSet rs) throws SQLException {

        Long id = rs.getLong("id");
        String nombre = rs.getString("nombre");
        LocalDate fechaini = rs.getDate("fechaini").toLocalDate();
        LocalDate fechafin = rs.getDate("fechafin").toLocalDate();
        Long idCoord = rs.getLong("id_coordinador");

        CoordinacionDAO cdao = new CoordinacionDAO();
        Coordinacion coord = cdao.buscarPorIdCoord(idCoord);

        // ÚNICO CONSTRUCTOR CORRECTO
        Espectaculo e = new Espectaculo(id, nombre, fechaini, fechafin, coord);

        return e;
    }

}
