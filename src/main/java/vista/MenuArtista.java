/**
* Clase MenuArtista.java
*
* @author Roberto Garcia Fernandez
* @version 1.0
*/


package vista;

import java.util.Scanner;

public class MenuArtista {
    private Scanner sc = new Scanner(System.in);

    public void mostrar() {
        boolean running = true;

        while (running) {
            System.out.println("=== MENU ARTISTA ===");
            System.out.println("1) Ver mis números");
            System.out.println("2) Ver mis espectáculos");
            System.out.println("0) Volver");

            System.out.print("Elige: ");
            String op = sc.nextLine();

            switch (op) {
                case "1":
                    System.out.println("Mostrando números del artista...");
                    break;

                case "2":
                    System.out.println("Mostrando espectáculos del artista...");
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
