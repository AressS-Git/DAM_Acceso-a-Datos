package TerceraPractica;

//Importar librerias io
import java.io.FileInputStream;
import java.io.IOException;

public class EjemploFileInputStream {
    public static void main(String[] args) {
        //Inicialización de variable para recorrer fichero.bin
        int b;

        //Try catch en el que iniciamos FileInputStream y se cierra automáticamente
        try(FileInputStream fis = new FileInputStream("datos.bin")) {
            while((b = fis.read()) != -1) {
                System.out.print(b + " ");
            }
        } catch (IOException e) {
            System.err.println("Error al leer datos.bin" +  e.getMessage());
        }
    }
}
