package vista;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		
		try {
			
			int opcion;
			
			System.out.println("===================CIRCO===================");
			System.out.println("1. Ver Espectáculos");
			System.out.println("2. Iniciar Sesión");
			System.out.println("0. Salir");
			System.out.println("===========================================");
			opcion=sc.nextInt();
			
			do {
				
				switch (opcion) {
				case 1:
					
					break;

				default:
					break;
				}
				
				
			} while (opcion!=0);
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
