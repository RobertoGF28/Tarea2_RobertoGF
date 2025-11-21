package controlador;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

import dao.CredencialesDAO;
import modelo.Credenciales;
import modelo.Perfiles;

public class LoginController {

    private final CredencialesDAO cDAO = new CredencialesDAO();

    private String adminUser;
    private String adminPass;
    
    public LoginController() {
        cargarAdmin();
    }

    private void cargarAdmin() {
        try (InputStream in = LoginController.class.getClassLoader().getResourceAsStream("application.properties")) {

            Properties p = new Properties();
            p.load(in);

            adminUser = p.getProperty("adminUser");
            adminPass = p.getProperty("adminPass");

        } catch (IOException e) {
            System.out.println("No se pudo cargar application.properties: " + e.getLocalizedMessage());
        }
    }

    public Credenciales login(String usuario, String password) {

        
        if (usuario.equals(adminUser) && password.equals(adminPass)) {
            return new Credenciales(0L, adminUser, adminPass, Perfiles.ADMIN);
        }

        Credenciales c = cDAO.obtenerPorNombre(usuario);
        if (c == null) return null;

        if (c.getPassword().equals(password)) {
            return c;
        }

        return null;
    }
	
	
}
