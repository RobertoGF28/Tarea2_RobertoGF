/**
* Clase MenuCoord.java
*
* @author Roberto Garcia Fernandez
* @version 1.0
*/


package vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import controlador.EspectaculoController;
import dao.EspectaculoDAO;
import modelo.Espectaculo;
import modelo.Perfiles;

public class MenuCoord {
	 private Scanner sc = new Scanner(System.in);

	    public void mostrar() {
	    	 int opcion;

	        do {
	        	 System.out.println("=== MENU COORDINADOR ===");
		            System.out.println("1) Ver Espectaculos");
		            System.out.println("2) Crear Espectaculos");
		            System.out.println("3) Modificar Espectaculos");
		            System.out.println("4) Crear Numeros");
		            System.out.println("5) Modificar Numeros");
		            System.out.println("0) Volver");

		            opcion = sc.nextInt();

		            switch (opcion) {
		                case 1:
		                    
		                    break;

		                case 2:
		                    crearEspectaculoCoordinador(null);
		                    break;

		                case 3:
		                    modificarEspectaculoCoord();
		                    break;
		                    
		                case 4:
		                   
		                    break;
		                    
		                case 5:
		                    
		                    break;

		                case 0:
		                    System.out.println("Volviendo al menu principal...");
		                    break;

		                default:
		                    System.out.println("Opción inválida.");
		            }
		        
			} while (opcion!=0); 
	           
	    }
	    
	    private void crearEspectaculoCoordinador(Long idPersonaCoord) {
	    	sc.nextLine();
	    	EspectaculoController ec=new EspectaculoController();
	        System.out.println("\n=== CREAR ESPECTÁCULO ===");

	        System.out.print("Nombre del espectáculo: ");
	        String nombre = sc.nextLine();

	        System.out.print("Fecha inicio (DD-MM-YYYY): ");
	        String fIni = sc.nextLine();

	        System.out.print("Fecha fin (DD-MM-YYYY): ");
	        String fFin = sc.nextLine();

	        boolean ok = ec.crearEspectaculo(
	                nombre, fIni, fFin,
	                Perfiles.COORDINACION,
	                idPersonaCoord,
	                null
	        );

	        if (ok) System.out.println("Espectáculo creado.");
	    }
	    
	    private void modificarEspectaculoCoord() {
	    	sc.nextLine();

    	    System.out.println("\n=== MODIFICAR ESPECTÁCULO ===");

    	    EspectaculoDAO dao = new EspectaculoDAO();
    	    List<Espectaculo> lista = dao.listarTodos();

    	    if (lista.isEmpty()) {
    	        System.out.println("No hay espectáculos registrados.");
    	        return;
    	    }

    	    System.out.println("Espectáculos disponibles:");
    	    for (int i = 0; i < lista.size(); i++) {
    	        Espectaculo e = lista.get(i);
    	        System.out.println(e.getIdEsp() + " - " + e.getNombre());
    	    }

    	    System.out.print("Ingrese ID del espectáculo a modificar: ");
    	    Long idEsp = Long.parseLong(sc.nextLine());

    	    Espectaculo esp = dao.buscarPorId(idEsp);
    	    if (esp == null) {
    	        System.err.println("No existe un espectáculo con ese ID.");
    	        return;
    	    }


    	    System.out.print("Nuevo nombre (" + esp.getNombre() + "): ");
    	    String nuevoNombre = sc.nextLine();
    	    if (nuevoNombre.isBlank()) nuevoNombre = esp.getNombre();


    	    LocalDate nuevaIni = null;
    	    boolean fechaIniOk = false;

    	    while (!fechaIniOk) {
    	        System.out.print("Nueva fecha de inicio (DD-MM-YYYY): ");
    	        String texto = sc.nextLine();

    	        try {
    	            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    	            nuevaIni = LocalDate.parse(texto, fmt);
    	            fechaIniOk = true;
    	        } catch (Exception e) {
    	            System.err.println("Formato incorrecto, use DD-MM-YYYY.");
    	        }
    	    }

    	 
    	    LocalDate nuevaFin = null;
    	    boolean fechaFinOk = false;

    	    while (!fechaFinOk) {
    	        System.out.print("Nueva fecha de fin (DD-MM-YYYY): ");
    	        String texto = sc.nextLine();

    	        try {
    	            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    	            nuevaFin = LocalDate.parse(texto, fmt);
    	            fechaFinOk = true;
    	        } catch (Exception e) {
    	            System.err.println("Formato incorrecto, use DD-MM-YYYY.");
    	        }
    	    }

    	    if (nuevaFin.isBefore(nuevaIni)) {
    	        System.err.println("La fecha de fin no puede ser anterior a la fecha de inicio.");
    	        return;
    	    }

    	    LocalDate limite = LocalDate.now().plusYears(1);
    	    if (nuevaIni.isAfter(limite) || nuevaFin.isAfter(limite)) {
    	        System.err.println("Las fechas no pueden sobrepasar 1 año desde hoy.");
    	        return;
    	    }
    	    
    	    EspectaculoController ec=new EspectaculoController();
    	    boolean ok = ec.modificarEspectaculo(
    	            idEsp,
    	            nuevoNombre,
    	            nuevaIni,
    	            nuevaFin
    	          
    	    );

    	    if (ok) System.out.println("Espectáculo modificado correctamente.");
    	    else    System.err.println("Error al modificar el espectáculo.");
    
 }
}
