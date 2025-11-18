package vista;

import java.util.Scanner;

import com.mysql.cj.xdevapi.DbDoc;

import util.ConexDB;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		ConexDB conexBD= new ConexDB();
		
		conexBD.getConnection();
		
		try {
			
			int opcion;
			
			System.out.println("===================CIRCO===================");
			System.out.println("1. Ver Espect�culos");
			System.out.println("2. Iniciar Sesi�n");
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
