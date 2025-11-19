package util;

import java.sql.SQLException;
import java.util.Scanner;

import controlador.LoginController;
import dao.CredencialesDAO;

public class PruebaLogin {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		LoginController lc= new LoginController();
		
		lc.lanzar();
		
	}

}
