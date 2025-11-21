/**
* Clase MenuAdmin.java
*
* @author Roberto Garcia Fernandez
* @version 1.0
*/


package vista;

import java.time.LocalDate;
import java.util.Map.Entry;
import java.util.Scanner;

import controlador.RegistroController;
import modelo.Especialidades;
import modelo.Perfiles;
import util.XMLReader;

public class MenuAdmin {
	 private Scanner sc = new Scanner(System.in);
	 

	    public void mostrar() {
	        int opcion;
	            
	            do {
	            	System.out.println("=== MENU ADMINISTRADOR ===");
		            System.out.println("1) Crear espectáculos");
		            System.out.println("2) Crear usuarios");
		            System.out.println("3) Modificar Usuarios");
		            System.out.println("0) Volver");
		            
		            opcion= sc.nextInt();
	            	
	            	switch (opcion) {
	                case 1:
	                    System.out.println("Crear espectáculo...");
	                    break;

	                case 2:
	                    registrarUsuario();
	                    break;

	                case 3:
	                    modificarUsuario();
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

	        System.out.print("Email: ");
	        String email = sc.nextLine();

	        // PAÍS DESDE XML
	        String idPais;
	        String nombrePais;

	        do {
	            System.out.print("Introduce el ID del país (ver opción 3): ");
	            idPais = sc.nextLine().trim().toUpperCase();

	            nombrePais = XMLReader.getNombrePais(idPais);

	            if (nombrePais == null)
	                System.err.println("❌ País no encontrado. Intente otra vez.");

	        } while (nombrePais == null);

	        System.out.println("✔ País seleccionado: " + nombrePais);

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
	                System.out.print("Fecha senior (YYYY-MM-DD): ");
	                fechaSenior = LocalDate.parse(sc.nextLine());
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
	                System.err.println("❌ El país no existe en paises.xml. Inténtalo.");
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

	    
	    }

