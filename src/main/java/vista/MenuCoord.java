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
import controlador.NumeroController;
import dao.ArtistasDAO;
import dao.EspectaculoDAO;
import dao.NumeroDAO;
import modelo.Artista;
import modelo.Coordinacion;
import modelo.Espectaculo;
import modelo.Numero;
import modelo.Perfiles;

public class MenuCoord {
	 private Scanner sc = new Scanner(System.in);
	 private Coordinacion coordinacion;

	    public MenuCoord(Coordinacion coordinacion) {
	        this.coordinacion = coordinacion;
	    }

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
		                    crearEspectaculoCoordinador(this.coordinacion.getId());
		                    break;

		                case 3:
		                    modificarEspectaculoCoord();
		                    break;
		                    
		                case 4:
		                   crearNumero();
		                    break;
		                    
		                case 5:
		                    modificarNumero();
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
	    private void verEspectaculoDetalle() {
	    	sc.nextLine();

	        EspectaculoDAO edao = new EspectaculoDAO();
	        NumeroDAO ndao = new NumeroDAO();

	        System.out.println("\n=== LISTA DE ESPECTÁCULOS ===");

	        List<Espectaculo> espectaculos = edao.listarTodos();

	        if (espectaculos.isEmpty()) {
	            System.out.println("No hay espectáculos registrados.");
	            return;
	        }

	        
	        for (int i = 0; i < espectaculos.size(); i++) {
	            Espectaculo e = espectaculos.get(i);
	            System.out.println(e.getIdEsp() + " - " + e.getNombre());
	        }

	        System.out.print("\nSeleccione ID del espectáculo: ");
	        Long idEsp = Long.parseLong(sc.nextLine());

	        Espectaculo esp = edao.buscarPorId(idEsp);
	        if (esp == null) {
	            System.err.println("Espectáculo no encontrado.");
	            return;
	        }

	        System.out.println("\n=== NÚMEROS DEL ESPECTÁCULO ===");
	        System.out.println("Espectáculo: " + esp.getNombre() + "\n");

	        List<Numero> numeros = ndao.listarPorEspectaculo(idEsp);

	        if (numeros.isEmpty()) {
	            System.out.println("Este espectáculo no tiene números registrados.");
	            return;
	        }

	        for (int i = 0; i < numeros.size(); i++) {

	            Numero n = numeros.get(i);

	            System.out.println("-----------------------------");
	            System.out.println("Orden: " + n.getOrden());
	            System.out.println("Nombre: " + n.getNombre());
	            System.out.println("Duración: " + n.getDuracion() + " min");
	        }

	        System.out.println("-----------------------------");
	    }
	    
	    private void crearNumero() {
	    	sc.nextLine();
	        NumeroController nc = new NumeroController();
	        ArtistasDAO artistaDAO = new ArtistasDAO();

	        System.out.println("\n=== CREAR NÚMERO ===");

	        System.out.print("ID del espectáculo: ");
	        Long idEsp = Long.parseLong(sc.nextLine());

	        System.out.print("Orden: ");
	        int orden = Integer.parseInt(sc.nextLine());

	        System.out.print("Nombre del número: ");
	        String nombre = sc.nextLine();

	        System.out.print("Duración (minutos): ");
	        double dur = Double.parseDouble(sc.nextLine());

	        System.out.println("Artistas disponibles:");
	        List<Artista> artes = artistaDAO.listar();

	        for (int i = 0; i < artes.size(); i++) {
	            Artista a = artes.get(i);
	            System.out.println(a.getIdArt() + " - " + a.getNombre());
	        }

	        System.out.print("ID del artista: ");
	        Long idArt = Long.parseLong(sc.nextLine());

	        boolean ok = nc.crearNumero(orden, nombre, dur, idEsp, idArt);

	        if (ok) System.out.println("Número creado correctamente.");
	        else System.err.println("No se pudo crear el número.");
	    }
	    
	    private void modificarNumero() {
	    	sc.nextLine();
	    	NumeroController nc = new NumeroController();
	        ArtistasDAO artistaDAO = new ArtistasDAO();

	        System.out.println("\n=== MODIFICAR NÚMERO ===");

	        System.out.print("ID del número a modificar: ");
	        Long idNum = Long.parseLong(sc.nextLine());

	        System.out.print("Nuevo orden: ");
	        int orden = Integer.parseInt(sc.nextLine());

	        System.out.print("Nuevo nombre: ");
	        String nombre = sc.nextLine();

	        System.out.print("Nueva duración (minutos): ");
	        double dur = Double.parseDouble(sc.nextLine());

	        System.out.println("Artistas disponibles:");
	        List<Artista> artes = artistaDAO.listar();

	        for (int i = 0; i < artes.size(); i++) {
	            Artista a = artes.get(i);
	            System.out.println(a.getIdArt() + " - " + a.getNombre());
	        }

	        System.out.print("Nuevo ID de artista: ");
	        Long idArt = Long.parseLong(sc.nextLine());

	        boolean ok = nc.modificarNumero(idNum, orden, nombre, dur, idArt);

	        if (ok) System.out.println("Número modificado correctamente.");
	        else System.err.println("No se pudo modificar el número.");
	    }
}
