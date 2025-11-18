package controlador;

import java.util.Scanner;

public class LoginController {

	
	public void login(Scanner sc) {
		boolean loggedIn=false;
		String nombreUsu;
		String password;
		try {
			do {
				sc.nextLine();
				System.out.println("Introduce el nombre de usuario: ");
				nombreUsu=sc.nextLine();
				
				
				
			} while (loggedIn==false);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	
}
