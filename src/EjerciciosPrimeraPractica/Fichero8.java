package EjerciciosPrimeraPractica;

import java.util.Scanner;
import java.io.File;
import java.net.URI;

/*
Función explorarCarpeta(String ruta): lista contenido.
Función analizarElemento(String ruta): muestra si es archivo (con tamaño) o carpeta (con número de elementos).
Función convertirAURI(String ruta): convierte ruta a URI.
Practica: list(), isFile(), isDirectory(), toURI().
*/
public class Fichero8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("***EXPLORADOR DE CARPETAS***");
        explorarCarpeta(sc);
        sc.close();
    }

    //Este metodo explora la carpeta e implementa el resto de metodos de la clase
    public static void explorarCarpeta(Scanner sc)  {
        System.out.print("Introduce la ruta del directorio que quieras explorar: ");
        String rutaCarpeta = sc.nextLine();
        File ruta = new File(rutaCarpeta);
        //Comprueba si la ruta existe antes de explorarla
        if (ruta.exists()) {
            //Se recorre la carpeta con un for each para poder analizar cada elemento uno a uno
            for (String nombreElemento  : ruta.list()) {
                File elementoEnArray = new File(ruta, nombreElemento);
                //LLamo a la funcion para analizar elementos
                analizarElemento(ruta, elementoEnArray, nombreElemento);
            }
            //Como ya se que la ruta existe y he trabajado con ella puedo convertirla a URI sin problemas
            convertirAURI(ruta);
        } else {
            System.err.println("La ruta introducida es errónea o no existe en el equipo");
        }
    }

    //El metodo analiza la ruta que se le pase y dependiendo si es un directorio o un archivo hace una cosa u otra
    public static void analizarElemento(File ruta, File elementoEnArray, String nombreElemento) {
        //Si es un archivo muestra su peso en bytes
        if (elementoEnArray.isFile()) {
            long pesoArchivoEnBytes = elementoEnArray.length();
            System.out.println("Archivo: " + nombreElemento + " [Peso del archivo: " + pesoArchivoEnBytes + " bytes]");

        //Si es un directorio muestra la cantidad de elementos que tiene
        } else if (elementoEnArray.isDirectory()) {
            //Creo un array con los elementos del directorio para poder usar length y asi sacar el numero de elementos
            String[] elementos = elementoEnArray.list();
            System.out.println("Directorio: " + nombreElemento + " [Número de elementos del directorio: " + (elementos != null ? elementos.length : 0) + "]");
        } else {
            System.err.println("El elemnto no es i un archivo ni un directorio");;
        }
    }

    //Metodo que convierte una ruta a formato URI
    public static void convertirAURI(File ruta) {
        System.out.println("Intentanto convertir la ruta '" + ruta + "' a URI");
        try {
            URI uri = ruta.toURI();
            System.out.println("Ruta convertida a URI correctamente: " + uri.toString());
        } catch (Exception e) {
            // Mensaje de error en caso de fallo de conversión
            System.err.println("Error al intentar convertir la ruta a URI: " + e.getMessage());
        }
    }
}
