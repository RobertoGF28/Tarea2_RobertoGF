/**
* Clase MenuCoord.java
*
* @author Roberto Garcia Fernandez
* @version 1.0
*/


package vista;

import java.util.Scanner;

public class MenuCoord {
	 private Scanner sc = new Scanner(System.in);

	    public void mostrar() {
	        boolean running = true;

	        while (running) {
	            System.out.println("=== MENU COORDINADOR ===");
	            System.out.println("1) Ver mis espectáculos");
	            System.out.println("2) Crear números");
	            System.out.println("3) Asignar artistas a números");
	            System.out.println("0) Volver");

	            System.out.print("Elige: ");
	            String op = sc.nextLine();

	            switch (op) {
	                case "1":
	                    System.out.println("Mostrando espectáculos coordinados...");
	                    break;

	                case "2":
	                    System.out.println("Creando números...");
	                    break;

	                case "3":
	                    System.out.println("Asignando artista a número...");
	                    break;

	                case "0":
	                    running = false;
	                    break;

	                default:
	                    System.out.println("Opción inválida.");
	            }
	        }
	    }
}
