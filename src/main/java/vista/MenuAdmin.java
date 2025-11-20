/**
* Clase MenuAdmin.java
*
* @author Roberto Garcia Fernandez
* @version 1.0
*/


package vista;

import java.util.Scanner;

public class MenuAdmin {
	 private Scanner sc = new Scanner(System.in);

	    public void mostrar() {
	        int opcion;
	            
	            do {
	            	System.out.println("=== MENU ADMINISTRADOR ===");
		            System.out.println("1) Crear espectáculos");
		            System.out.println("2) rear usuarios");
		            System.out.println("3) Listar usuarios");
		            System.out.println("0) Volver");
		            
		            opcion= sc.nextInt();
	            	
	            	switch (opcion) {
	                case 1:
	                    System.out.println("Crear espectáculo...");
	                    break;

	                case 2:
	                    System.out.println("Crear usuario...");
	                    break;

	                case 3:
	                    System.out.println("Listando usuarios...");
	                    break;

	                case 0:
	                    System.out.println("Volviendo al menu principal...");
	                    break;

	                default:
	                    System.out.println("Opción inválida.");
	            	}
	            	
				} while (opcion!=0);
	            

	            
	        }
	    }

