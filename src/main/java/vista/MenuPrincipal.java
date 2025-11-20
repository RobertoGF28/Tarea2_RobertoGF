/**
* Clase MenuPrincipal.java
*
* @author Roberto Garcia Fernandez
* @version 1.0
*/


package vista;

import java.util.Scanner;

import controlador.LoginController;
import dao.EspectaculoDAO;
import modelo.Espectaculo;
import modelo.Perfiles;

public class MenuPrincipal {
	

    private Scanner sc = new Scanner(System.in);
    private LoginController login = new LoginController();
    private EspectaculoDAO eDAO = new EspectaculoDAO();

    public void start() {
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

        Perfiles perfil = login.login(user, password);

        if (perfil == null) {
            System.out.println("Credenciales incorrectas.");
            return;
        }

        switch (perfil) {
        	case ADMIN: new MenuAdmin().mostrar();
        	break;
        	case COORDINACION: new MenuCoord().mostrar();
        	break;
        	case ARTISTA: new MenuArtista().mostrar();
        	break;
        }
    }
	
}
