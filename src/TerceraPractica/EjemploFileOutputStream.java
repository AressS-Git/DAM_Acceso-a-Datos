package TerceraPractica;

import java.io.FileOutputStream;
import java.io.IOException;

public class EjemploFileOutputStream {
    public static void main(String[] args) {
        //Inicialización de un array de números ASCII para escribir "Hola Mundo"
        byte[] datos = {72, 111, 108, 97, 32, 77, 117, 110, 100, 111};
        try(FileOutputStream fos = new FileOutputStream("salida.bin")) {
            //Escritura de carácteres ASCII en el fichero específicado
            fos.write(datos);
        } catch (IOException e) {
            System.err.println("Error al leer datos.bin" +  e.getMessage());
        }
    }
}
