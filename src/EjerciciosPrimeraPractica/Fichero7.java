package EjerciciosPrimeraPractica;

import java.io.IOException;
import java.util.Scanner;
import java.io.File;

/*
Función organizarBiblioteca(): crea carpeta de categoría y archivo catalogo.txt.
Función verificarLibro(): verifica si existe un libro; si no, pregunta si se crea.
Practica: exists(), mkdir(), createNewFile(), funciones separadas.
 */
public class Fichero7 {
    public static final File RUTA = new File("C:\\Users\\AlumnoAfternoon\\Documents\\Pruebas-Java");
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        //Compruebo nada más empezar si la ruta existe y si es un directorio
        if (RUTA.exists() && RUTA.isDirectory()) {
            System.out.println("***ORGANIZADOR DE BILBIOTECA***");
            organizarBilbioteca(RUTA, sc);
        } else {
            System.err.println("La RUTA no existe o no es un directorio");
        }
        sc.close();
    }

    //Esta funcion crea la carpeta indicada y el archivo indicado
    public static void organizarBilbioteca(File RUTA, Scanner sc) throws IOException {
        System.out.print("Indica la categoría de libros: ");
        String categoria = sc.nextLine();
        System.out.print("Introduce el nombre del libro: ");
        String nombreLibro = sc.nextLine();
        File rutaCategoria = new File(RUTA, categoria);
        File archivoCatalago = new File(rutaCategoria, "catalogo.txt");
        //Se crea la carpeta si no existe
        if (!rutaCategoria.exists()) {
            System.out.println("El directorio no existe");
            if (rutaCategoria.mkdir()) {
                System.out.println("Directorio creado correctamente: " + rutaCategoria.getAbsolutePath());
            } else {
                System.out.println("El directorio no se ha podido crear");
            }
        } else {
            System.err.println("El directorio ya existe: " + rutaCategoria.getAbsolutePath());
        }
        //Se crea el archivo catalogo si no existe
        if (!archivoCatalago.exists()) {
            System.out.println("El archivo catalogo.txt no existe");
            if (archivoCatalago.createNewFile()) {
                System.out.println("Archivo catalogo.txt creado correctmente:" + archivoCatalago.getAbsolutePath());
            } else {
                System.out.println("El archivo catálogo.txt no se ha podido crear");
            }
        } else {
            System.err.println("El archivo catalogo.txt ya existe: " + archivoCatalago.getAbsolutePath());
        };
        //LLamo a la función para crear el libro despues de crear todos los directorios anteriores
        verificarLibro(rutaCategoria, nombreLibro, sc);
    }

    //LEsta funcion verifica si el libro existe y si no es así lo crea
    public static void verificarLibro(File rutaCategoria, String nombreLibro, Scanner sc) throws IOException {
        File archivoLibro = new File(rutaCategoria, nombreLibro);
        String op;
        //Si el libro no existe se le preguntará al usuario sui quiere crearlo
        if (!archivoLibro.exists()) {
            //Creo un do-while para obligar al usuario a que responda bien la pregunta de creación del libro
            do {
                System.out.print("El archivo + " + nombreLibro +  "no existe, quiere crearlo?(si/no) ");
                op = sc.nextLine();
                if (op.equals("si")) {
                    if (archivoLibro.createNewFile()) {
                        System.out.println("Libro añadido: " + archivoLibro.getAbsolutePath());
                    } else {
                        System.out.println("El libro no se ha podido crear");
                    }
                } else if (op.equals("no")) {
                    System.out.println("Entendido, no se añadirá el libro");
                } else {
                    System.err.println("Por favor, responde si o no");
                }
            } while (!(op.equals("si")) && !(op.equals("no")));
        } else {
            System.err.println("El archivo ya existe: " + archivoLibro.getAbsolutePath());
        }
    }
}
