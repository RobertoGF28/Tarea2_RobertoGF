/**
* Clase NumeroController.java
*
* @author Roberto Garcia Fernandez
* @version 1.0
*/


package controlador;

import dao.ArtistasDAO;
import dao.NumeroDAO;
import modelo.Artista;
import modelo.Numero;

public class NumeroController {
	private NumeroDAO dao = new NumeroDAO();
    private ArtistasDAO artistaDAO = new ArtistasDAO();


    public boolean crearNumero(int orden, String nombre, double duracion, Long idEspectaculo, Long idArtista) {

     
        if (orden <= 0) {
            System.err.println("El orden debe ser mayor que 0.");
            return false;
        }
        if (duracion <= 0) {
            System.err.println("La duración debe ser mayor que 0.");
            return false;
        }

        
        Artista art = artistaDAO.buscarPorIdArt(idArtista);
        if (art == null) {
            System.err.println("El artista no existe.");
            return false;
        }

        Numero n = new Numero(null, orden, nombre, duracion);

        Long id = dao.insertarNumero(n, idEspectaculo, idArtista);

        return id != null;
    }


    public boolean modificarNumero(Long idNumero, int nuevoOrden, String nuevoNombre,
                                   double nuevaDuracion, Long nuevoIdArtista) {

      
        if (nuevoOrden <= 0) {
            System.err.println("El orden debe ser mayor que 0.");
            return false;
        }
        if (nuevaDuracion <= 0) {
            System.err.println("La duración debe ser mayor que 0.");
            return false;
        }

      
        Artista art = artistaDAO.buscarPorIdArt(nuevoIdArtista);
        if (art == null) {
            System.err.println("El artista no existe.");
            return false;
        }

        Numero n = new Numero(idNumero, nuevoOrden, nuevoNombre, nuevaDuracion);

        return dao.actualizarNumero(n, nuevoIdArtista);
    }

}
