/**
* Clase MenuAdmin.java
*
* @author Roberto Garcia Fernandez
* @version 1.0
*/


package vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;

import controlador.EspectaculoController;
import controlador.RegistroController;
import dao.CoordinacionDAO;
import dao.EspectaculoDAO;
import dao.NumeroDAO;
import modelo.Coordinacion;
import modelo.Especialidades;
import modelo.Espectaculo;
import modelo.Numero;
import modelo.Perfiles;
import util.XMLReader;

public class MenuAdmin {
	 private Scanner sc = new Scanner(System.in);
	 

	    public void mostrar() {
	        int opcion;
	            
	            do {
	            	System.out.println("=== MENU ADMINISTRADOR ===");
		            System.out.println("1) Ver espectaculos");
		            System.out.println("2) Crear usuarios");
		            System.out.println("3) Modificar Usuarios");
		            System.out.println("4) Crear Espectaculo");
		            System.out.println("5) Modificar Espectaculo");
		            System.out.println("6) Crear Numero");
		            System.out.println("7) Modificar Numero");
		            System.out.println("0) Volver");
		            
		            opcion= sc.nextInt();
	            	
	            	switch (opcion) {
	                case 1:
	                    verEspectaculoDetalle();
	                    break;

	                case 2:
	                    registrarUsuario();
	                    break;

	                case 3:
	                    modificarUsuario();
	                    break;
	                case 4:
	                	crearEspectaculoAdmin();
	                	break;
	                case 5:
	                	modificarEspectaculoAdmin();
	                	break;
	                case 6:
	                	break;
	                case 7:
	                	break;
	                case 0:
	                    System.out.println("Volviendo al menu principal...");
	                    break;

	                default:
	                    System.out.println("Opción inválida.");
	            	}
	            	
				} while (opcion!=0);
	            
	            
	        }
	    
	    
	    private void registrarUsuario() {
	    	sc.nextLine();
	    	RegistroController rc = new RegistroController();
	        System.out.println("\n=== Registro de usuario ===");

	        System.out.print("Nombre de usuario (login): ");
	        String username = sc.nextLine();       

	        System.out.print("Nombre real: ");
	        String nombreReal = sc.nextLine();     

	        String email;
	        do {
	            System.out.print("Nuevo email: ");
	            email = sc.nextLine();

	            if (!rc.emailValido(email)) {
	                System.err.println("❌ Formato de email incorrecto.");
	            }

	        } while (!rc.emailValido(email));

	        
	        String idPais;
	        String nombrePais;

	        do {
	            System.out.print("Introduce el ID del país: ");
	            idPais = sc.nextLine().trim().toUpperCase();

	            nombrePais = XMLReader.getNombrePais(idPais);

	            if (nombrePais == null)
	                System.err.println("País no encontrado. Intente otra vez.");

	        } while (nombrePais == null);

	        System.out.println("País seleccionado: " + nombrePais);

	        System.out.print("Password: ");
	        String password = sc.nextLine();

	        System.out.println("Tipo de usuario:");
	        System.out.println("1. Coordinador");
	        System.out.println("2. Artista");
	        int tipo = Integer.parseInt(sc.nextLine());

	        Perfiles perfil = (tipo == 1 ? Perfiles.COORDINACION : Perfiles.ARTISTA);

	        boolean esSenior = false;
	        LocalDate fechaSenior = null;
	        String apodo = null;
	        Especialidades[] especialidades = null;

	        if (perfil == Perfiles.COORDINACION) {
	            System.out.print("¿Es senior? (true/false): ");
	            esSenior = Boolean.parseBoolean(sc.nextLine());
	            if (esSenior) {
	                LocalDate fecha = null;

	                do {
	                    System.out.print("Fecha senior (DD-MM-YYYY): ");
	                    String input = sc.nextLine();

	                    fecha = rc.parsearFecha(input);

	                    if (fecha == null) {
	                        System.err.println("Formato incorrecto. Use DD-MM-YYYY.");
	                    } else if (fecha.isAfter(LocalDate.now())) {
	                        System.err.println("La fecha no puede ser posterior a hoy.");
	                        fecha = null;
	                    }

	                } while (fecha == null);

	                fechaSenior = fecha;
	            }
	        }

	        if (perfil == Perfiles.ARTISTA) {
	            System.out.print("Apodo artístico: ");
	            apodo = sc.nextLine();

	            System.out.println("Especialidades disponibles:");
	            for (Especialidades e : Especialidades.values())
	                System.out.println("- " + e.name());

	            System.out.print("Especialidad principal: ");
	            especialidades = new Especialidades[]{Especialidades.valueOf(sc.nextLine().toUpperCase())};
	        }

	        boolean ok = rc.registrarUsuario(
	                username,
	                nombreReal,
	                email,
	                idPais,
	                password,
	                perfil,
	                esSenior,
	                fechaSenior,
	                apodo,
	                especialidades
	        );

	        if (ok)
	            System.out.println("✔ Usuario registrado correctamente.");
	        else
	            System.err.println("❌ Error al registrar usuario.");
	    }
	    
	    private void modificarUsuario() {
	    	sc.nextLine();
	    	RegistroController rc = new RegistroController();
	        System.out.println("\n=== Modificar usuario ===");

	        System.out.print("ID de persona: ");
	        long id = Long.parseLong(sc.nextLine());

	        System.out.print("Nuevo email: ");
	        String email = sc.nextLine();

	        System.out.print("Nuevo nombre: ");
	        String nombre = sc.nextLine();

	      
	        String idPais;
	        String nombrePais;

	        do {
	            System.out.print("Introduce el ID del país (ver opción 3): ");
	            idPais = sc.nextLine().trim().toUpperCase();

	            nombrePais = XMLReader.getNombrePais(idPais);

	            if (nombrePais == null) {
	                System.err.println("El país no existe en paises.xml. Inténtalo.");
	            }

	        } while (nombrePais == null);

	        System.out.println("✔ País seleccionado: " + nombrePais);

	        boolean ok = rc.modificarPersona(id, email, nombre, nombrePais);

	        if (ok) {
	            System.out.println("Usuario modificado con éxito.");
	        } else {
	            System.out.println("No se pudo modificar el usuario.");
	        }
	    }


	    private void mostrarPaises() {
	    	
	    	
	        System.out.println("\n=== LISTA DE PAÍSES DEL XML ===");

	        for (Entry<String, String> entry : XMLReader.getPaises().entrySet()) {
	            System.out.println(entry.getKey() + entry.getValue());
	        }

	        System.out.println("===============================");
	    }
	    
	    private void crearEspectaculoAdmin() {
	    	sc.nextLine();
	    	EspectaculoController ec=new EspectaculoController();
	    	CoordinacionDAO cDao=new CoordinacionDAO();
	    	System.out.println("\n=== CREAR ESPECTÁCULO (ADMIN) ===");

	    	System.out.print("Nombre del espectáculo: ");
	    	String nombre = sc.nextLine();

	    	System.out.print("Fecha inicio (DD-MM-YYYY): ");
	    	String fIni = sc.nextLine();

	    	System.out.print("Fecha fin (DD-MM-YYYY): ");
	    	String fFin = sc.nextLine();

	    	System.out.println("Coordinadores disponibles:");

	    	List<Coordinacion> listaCoordinadores = cDao.listar();

	    	for (int i = 0; i < listaCoordinadores.size(); i++) {
	    	    Coordinacion c = listaCoordinadores.get(i);
	    	    System.out.println(c.getIdCoord() + " - " + c.getNombre());
	    	}

	    	System.out.print("ID del coordinador que lo dirigirá: ");
	    	Long idCoord = Long.parseLong(sc.nextLine());

	    	boolean ok = ec.crearEspectaculo(
	    	        nombre,
	    	        fIni,
	    	        fFin,
	    	        Perfiles.ADMIN,
	    	        null,
	    	        idCoord
	    	);

	    	if (ok) {
	    	    System.out.println("Espectáculo creado.");
	    	} else {
	    	    System.err.println("No se pudo crear el espectáculo.");
	    	}
	    }
	    
	    private void modificarEspectaculoAdmin() {
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

}


