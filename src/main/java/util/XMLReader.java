/**
* Clase XMLReader.java
*
* @author Roberto Garcia Fernandez
* @version 1.0
*/


package util;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class XMLReader {
	private static final String RUTA_XML = "src/main/resources/paises.xml";

    private static final Map<String, String> paises = new HashMap<>();

    static {
        cargar();
    }

    private static void cargar() {
        try (BufferedReader br = new BufferedReader(new FileReader(RUTA_XML))) {

            String linea;
            String id = null;
            String nombre = null;

            while ((linea = br.readLine()) != null) {

                linea = linea.trim();

                
                if (linea.startsWith("<id>") && linea.endsWith("</id>")) {
                    id = linea.replace("<id>", "")
                              .replace("</id>", "")
                              .trim()
                              .toUpperCase();
                }

   
                else if (linea.startsWith("<nombre>") && linea.endsWith("</nombre>")) {
                    nombre = linea.replace("<nombre>", "")
                                  .replace("</nombre>", "")
                                  .trim();
                }

         
                if (id != null && nombre != null) {
                    paises.put(id, nombre);
                    id = null;
                    nombre = null;
                }
            }

            System.out.println("Paises cargados: " + paises.size());

        } catch (Exception e) {
            System.err.println("ERROR leyendo XML simple: " + e.getMessage());
        }
    }



    public static boolean existePais(String id) {
        if (id == null) return false;
        return paises.containsKey(id.toUpperCase());
    }

    public static String getNombrePais(String id) {
        if (id == null) return null;
        return paises.get(id.toUpperCase());
    }

    public static Map<String, String> getPaises() {
        return paises;
    }
}
