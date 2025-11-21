/**
* Clase MenuPrincipal.java
*
* @author Roberto Garcia Fernandez
* @version 1.0
*/


package vista;

import java.util.Scanner;

import controlador.LoginController;
import dao.ArtistasDAO;
import dao.CoordinacionDAO;
import dao.EspectaculoDAO;
import dao.PersonaDAO;
import modelo.Artista;
import modelo.Coordinacion;
import modelo.Credenciales;
import modelo.Espectaculo;
import modelo.Perfiles;
import modelo.Persona;

public class MenuPrincipal {
	

    private Scanner sc = new Scanner(System.in);
    private LoginController login = new LoginController();
    private EspectaculoDAO eDAO = new EspectaculoDAO();

    public void mostrar() {
        int opcion;
    	do {
    		   menuPrincipal();
               opcion=sc.nextInt();

               switch (opcion) {
                   case 1:
                       verEspectaculos();
                       break;

                   case 2:
                       iniciarSesion();
                       break;

                   case 0:
                       System.out.println("Saliendo de la aplicacion...");
                       break;

                   default:
                       System.out.println("Opción invalida, intentelo de nuevo.");
               }
		} while (opcion!=0);
        
         
    }
    

    private void menuPrincipal() {
        System.out.println("==== Circo ====");
        System.out.println("1) Ver espectáculos");
        System.out.println("2) Iniciar sesión");
        System.out.println("0) Salir");
        
    }

    private void verEspectaculos() {
        
    	
      System.out.println(eDAO.listarTodos());
    }

    private void iniciarSesion() {

        sc.nextLine();
        System.out.print("Nombre de Usuario: ");
        String user = sc.nextLine();

        System.out.print("Contraseña: ");
        String password = sc.nextLine();

        Credenciales cred = login.login(user, password);

        if (cred == null) {
            System.out.println("Credenciales incorrectas.");
            return;
        }

        
        if (cred.getPerfil() == Perfiles.ADMIN) {
            new MenuAdmin().mostrar();
            return;
        }

     
        PersonaDAO pdao = new PersonaDAO();
        Persona persona = pdao.buscarPorIdCredenciales(cred.getId());

        if (persona == null) {
            System.err.println("ERROR: No hay persona asociada a estas credenciales.");
            return;
        }

 
        switch (cred.getPerfil()) {

            case ARTISTA:
                ArtistasDAO adao = new ArtistasDAO();
                Artista artista = adao.buscarPorIdPersona(persona.getId());

                if (artista == null) {
                    System.err.println("ERROR: No existe registro de artista asociado.");
                    return;
                }

                new MenuArtista(artista).mostrar();
                break;


            case COORDINACION:
                CoordinacionDAO cdao = new CoordinacionDAO();
                Coordinacion coord = cdao.buscarPorIdPersona(persona.getId());

                if (coord == null) {
                    System.err.println("ERROR: No existe registro de coordinador asociado.");
                    return;
                }

                new MenuCoord(coord).mostrar();
                break;
        }
    }
	
}
