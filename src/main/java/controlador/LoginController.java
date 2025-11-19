package controlador;

import java.util.Scanner;

import dao.CredencialesDAO;
import modelo.Credenciales;
import modelo.Perfiles;

public class LoginController {

	

    private final Scanner sc = new Scanner(System.in);
    private final CredencialesDAO credDAO = new CredencialesDAO();

    public void lanzar() {
        boolean corriendo = true;
        while (corriendo) {
            showMenu();
            String opt = sc.nextLine().trim();
            switch (opt) {
                case "1": registrar();
                break;
                case "2": login(); 
                break;
                case "0": corriendo = false; 
                break;
                default: System.out.println("Opción inválida.");
            }
        }
    }

    private void showMenu() {
        System.out.println("\n=== CIRCO - MENU ===");
        System.out.println("1) Registrar credenciales (nombre/password/perfil)");
        System.out.println("2) Login");
        System.out.println("0) Salir");
        System.out.print("Elige: ");
    }

    private void registrar() {
        System.out.print("Nombre (usuario): ");
        String nombre = sc.nextLine().trim();
        if (nombre.isEmpty()) { 
        	System.out.println("Nombre vacío."); 
        	return; 
        	}
        if (credDAO.existeNombre(nombre)) { 
        	System.out.println("Usuario ya existe.");
        	return;
        	}

        System.out.print("Password: ");
        String pass = sc.nextLine().trim();
        if (pass.length() < 3) { 
        	System.out.println("Password demasiado corta (mín 3)."); 
        	return; }

        System.out.print("Perfil (COORDINADOR / ARTISTA): ");
        String p = sc.nextLine().trim().toUpperCase();
        Perfiles perfil;
        try {
            perfil = Perfiles.valueOf(p);
        } catch (IllegalArgumentException e) {
            System.out.println("Perfil no válido."); return;
        }

        Credenciales c = new Credenciales(null, nombre, pass, perfil);
        boolean ok = credDAO.agregar(c);
        System.out.println(ok ? "Registro correcto. ID=" + c.getId() : "Error al registrar.");
    }

    private void login() {
        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Password: ");
        String pass = sc.nextLine().trim();

        boolean ok = credDAO.login(nombre, pass);
        System.out.println(ok ? "Login correcto!" : "Credenciales inválidas.");
    }
	
	
}
