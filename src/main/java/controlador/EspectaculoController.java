/**
* Clase EspectaculoController.java
*
* @author Roberto Garcia Fernandez
* @version 1.0
*/


package controlador;

import dao.CoordinacionDAO;
import dao.EspectaculoDAO;
import modelo.Coordinacion;
import modelo.Espectaculo;
import modelo.Perfiles;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class EspectaculoController {

    private final EspectaculoDAO espectaculoDAO = new EspectaculoDAO();
    private final CoordinacionDAO coordDAO = new CoordinacionDAO();

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    public LocalDate parsearFecha(String f) {
        try {
            return LocalDate.parse(f, formatter);
        } catch (Exception e) {
            return null;
        }
    }

    public boolean crearEspectaculo(String nombre, String fechaIniStr, String fechaFinStr,
                                    Perfiles perfilUsuario,
                                    Long idCoordinadorUsuario,
                                    Long idCoordinadorElegido) {

        LocalDate inicio = parsearFecha(fechaIniStr);
        LocalDate fin = parsearFecha(fechaFinStr);

        if (inicio == null || fin == null) {
            System.err.println("Formato de fecha inválido. Use DD-MM-YYYY.");
            return false;
        }

        if (fin.isBefore(inicio)) {
            System.err.println("La fecha de fin no puede ser anterior a la de inicio.");
            return false;
        }

        LocalDate limite = LocalDate.now().plusYears(1);

        if (inicio.isAfter(limite) || fin.isAfter(limite)) {
            System.err.println("Las fechas no pueden superar un año de vigencia.");
            return false;
        }


        Coordinacion coordinador;

        if (perfilUsuario == Perfiles.COORDINACION) {
            coordinador = coordDAO.buscarPorIdPersona(idCoordinadorUsuario);
        } else {
            
            coordinador = coordDAO.buscarPorIdCoord(idCoordinadorElegido);
        }

        if (coordinador == null) {
            System.err.println("No se encontró el coordinador.");
            return false;
        }

        
        Espectaculo esp = new Espectaculo(null, nombre, inicio, fin, coordinador);

        Long id = espectaculoDAO.insertar(esp);

        if (id == null) {
            System.err.println("No se pudo crear el espectáculo.");
            return false;
        }

        return true;
    }
    
    public boolean modificarEspectaculo(Long idEspectaculo,
            String nuevoNombre,
            LocalDate nuevaFechaIni,
            LocalDate nuevaFechaFin
            ) {

				EspectaculoDAO dao = new EspectaculoDAO();
				Espectaculo esp = dao.buscarPorId(idEspectaculo);
				
				if (esp == null) {
				System.err.println("No se encontró el espectáculo con ID " + idEspectaculo);
				return false;
				}
				
				// VALIDACIONES
				if (nuevaFechaFin.isBefore(nuevaFechaIni)) {
				System.err.println("La fecha de fin no puede ser anterior a la de inicio.");
				return false;
				}
				
				LocalDate limite = LocalDate.now().plusYears(1);
				if (nuevaFechaIni.isAfter(limite) || nuevaFechaFin.isAfter(limite)) {
				System.err.println("Las fechas no pueden superar un año desde hoy.");
				return false;
				}
				
			
				esp.setNombre(nuevoNombre);
				
				
				esp.setFechaInicio(nuevaFechaIni);
				esp.setFechaFin(nuevaFechaFin);
				
				

				return dao.actualizar(esp);
				}
    
}
