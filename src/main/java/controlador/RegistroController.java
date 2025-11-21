/**
* Clase RegistroController.java
*
* @author Roberto Garcia Fernandez
* @version 1.0
*/


package controlador;

import java.time.LocalDate;

import dao.ArtistasDAO;
import dao.CoordinacionDAO;
import dao.CredencialesDAO;
import dao.PersonaDAO;

import modelo.Artista;
import modelo.Coordinacion;
import modelo.Credenciales;
import modelo.Especialidades;
import modelo.Perfiles;
import modelo.Persona;
import util.XMLReader;

public class RegistroController {
	 private final CredencialesDAO credDAO = new CredencialesDAO();
	    private final PersonaDAO personaDAO = new PersonaDAO();
	    private final CoordinacionDAO coordDAO = new CoordinacionDAO();
	    private final ArtistasDAO artistaDAO = new ArtistasDAO();

	    public boolean registrarUsuario(
	            String username,       
	            String nombreReal,     
	            String email,
	            String idPais,
	            String password,
	            Perfiles perfil,
	            boolean esSenior,
	            LocalDate fechaSenior,
	            String apodo,
	            Especialidades[] especialidades
	    ) {

	      
	        if (!XMLReader.existePais(idPais)) {
	            System.out.println("El país con ID '" + idPais + "' no existe.");
	            return false;
	        }

	        String nombrePais = XMLReader.getNombrePais(idPais);

	        if (perfil == Perfiles.ADMIN) {
	            System.out.println("No está permitido registrar administradores.");
	            return false;
	        }

	        if (perfil == Perfiles.COORDINACION && esSenior && fechaSenior != null) {
	            if (fechaSenior.isAfter(LocalDate.now())) {
	                System.out.println("La fecha de senior no puede ser futura.");
	                return false;
	            }
	        }

	       
	        Credenciales cred = new Credenciales(
	                null,
	                username,   
	                password,
	                perfil
	        );

	        Long idCred = credDAO.agregar(cred);
	        if (idCred == null) {
	            System.out.println("Error insertando credenciales.");
	            return false;
	        }

	       
	        Persona persona = new Persona(
	                0,
	                email,
	                nombreReal,  
	                nombrePais,
	                idCred
	        );

	        Long idPersona = personaDAO.insertar(persona);
	        if (idPersona == null) {
	            System.err.println("Error insertando persona.");
	            return false;
	        }

	      
	        if (perfil == Perfiles.COORDINACION) {
	            Coordinacion c = new Coordinacion(
	                    idPersona,
	                    email,
	                    nombreReal,
	                    nombrePais,
	                    idCred,
	                    null,
	                    esSenior,
	                    fechaSenior
	            );

	            return coordDAO.insertar(c) != null;
	        }

	       
	        if (perfil == Perfiles.ARTISTA) {
	        	Artista a = new Artista(
	        	        idPersona,
	        	        email,
	        	        nombreReal,
	        	        nombrePais,
	        	        idCred,     
	        	        null,       
	        	        apodo,
	        	        especialidades
	        	);
	            return artistaDAO.insertar(a) != null;
	        }

	        return false;
	    }
	    
	    
	    public boolean modificarPersona(long idPersona, String nuevoEmail, String nuevoNombre, String nuevaNacionalidad) {
	        Persona persona = new Persona(idPersona, nuevoEmail, nuevoNombre, nuevaNacionalidad, 0);

	        return personaDAO.actualizar(persona);
	    }
}