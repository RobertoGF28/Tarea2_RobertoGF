/**
* Clase MenuArtista.java
*
* @author Roberto Garcia Fernandez
* @version 1.0
*/


package vista;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dao.EspectaculoDAO;
import dao.NumeroDAO;
import modelo.Artista;
import modelo.Especialidades;
import modelo.Espectaculo;
import modelo.Numero;

public class MenuArtista {
    private Scanner sc = new Scanner(System.in);
    private Artista artista;

    public MenuArtista(Artista artista) {
        this.artista = artista;
    }

    public void mostrar() {
       
    	int opcion;
       do {
    	   System.out.println("=== MENU ARTISTA ===");
           System.out.println("1) Ver Epectaculos");
           System.out.println("2) Ver mi ficha");
           System.out.println("0) Volver");

       
            opcion = sc.nextInt();

           switch (opcion) {
               case 1:
                   verEspectaculoDetalle();
                   break;

               case 2:
                   mostrarFicha(this.artista);
                   break;

               case 0:
                   System.out.println("Volviendo al menu principal...");
                   break;

               default:
                   System.out.println("Opción inválida.");
           }
       }while (opcion!=0);
	}
    
         
    
    
    private void verEspectaculoDetalle() {
    	sc.nextLine();

        EspectaculoDAO edao = new EspectaculoDAO();
        NumeroDAO ndao = new NumeroDAO();

        System.out.println("\n=== LISTA DE ESPECTÁCULOS ===");

        List<Espectaculo> espectaculos = edao.listarTodos();

        if (espectaculos.isEmpty()) {
            System.out.println("No hay espectáculos registrados.");
            return;
        }

        
        for (int i = 0; i < espectaculos.size(); i++) {
            Espectaculo e = espectaculos.get(i);
            System.out.println(e.getIdEsp() + " - " + e.getNombre());
        }

        System.out.print("\nSeleccione ID del espectáculo: ");
        Long idEsp = Long.parseLong(sc.nextLine());

        Espectaculo esp = edao.buscarPorId(idEsp);
        if (esp == null) {
            System.err.println("Espectáculo no encontrado.");
            return;
        }

        System.out.println("\n=== NÚMEROS DEL ESPECTÁCULO ===");
        System.out.println("Espectáculo: " + esp.getNombre() + "\n");

        List<Numero> numeros = ndao.listarPorEspectaculo(idEsp);

        if (numeros.isEmpty()) {
            System.out.println("Este espectáculo no tiene números registrados.");
            return;
        }

        for (int i = 0; i < numeros.size(); i++) {

            Numero n = numeros.get(i);

            System.out.println("-----------------------------");
            System.out.println("Orden: " + n.getOrden());
            System.out.println("Nombre: " + n.getNombre());
            System.out.println("Duración: " + n.getDuracion() + " min");
        }

        System.out.println("-----------------------------");
    }
    private void mostrarFicha(Artista artista) {

        NumeroDAO ndao = new NumeroDAO();
        EspectaculoDAO edao = new EspectaculoDAO();

        System.out.println("\n=== FICHA DEL ARTISTA ===");

        System.out.println("Nombre: " + artista.getNombre());
        System.out.println("Apodo: " + artista.getApodoString());
        System.out.println("Especialidades: ");

        Especialidades[] espec = artista.getEspecialidad();
        if (espec != null) {
            for (int i = 0; i < espec.length; i++) {
                System.out.println(" - " + espec[i]);
            }
        }

        System.out.println("\n=== ESPECTÁCULOS DONDE PARTICIPA ===");


        List<Numero> numeros = ndao.listarPorArtista(artista.getIdArt());

        if (numeros.isEmpty()) {
            System.out.println("Este artista no participa en ningún espectáculo.");
            return;
        }

        List<Long> idsEspVistos = new ArrayList<>();

        for (int i = 0; i < numeros.size(); i++) {
            Numero n = numeros.get(i);

            Long idEsp = ndao.obtenerIdEspectaculoDeNumero(n.getIdNum());

            if (!idsEspVistos.contains(idEsp)) {
                idsEspVistos.add(idEsp);

                Espectaculo esp = edao.buscarPorId(idEsp);

                System.out.println("\n--------------------------------------");
                System.out.println("ESPECTÁCULO: " + esp.getNombre());
                System.out.println("Inicio: " + esp.getFechaInicio());
                System.out.println("Fin: " + esp.getFechaFin());
                System.out.println("Coordinador: " + esp.getCoordinador().getNombre());
                System.out.println("Números donde participa:");
            }

      
            System.out.println("  Orden " + n.getOrden() + ": " + n.getNombre() +
                               " (duración: " + n.getDuracion() + " min)");
        }

        System.out.println("--------------------------------------");
    }
}
