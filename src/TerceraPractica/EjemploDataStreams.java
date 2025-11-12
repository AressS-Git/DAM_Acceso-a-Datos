package TerceraPractica;

import java.io.*;
import java.util.Properties;

public class EjemploDataStreams {
    public static void main(String[] args) {
        try(DataOutputStream dos = new DataOutputStream(new FileOutputStream("primitivos.dat"))) {
            dos.writeInt(12345);
            dos.writeDouble(99.99);
            dos.writeUTF("Producto de ejemplo");
            dos.writeBoolean(true);
        } catch(IOException e) {
            System.err.println("Error al escribir: " + e.getMessage());
        }
        try(DataInputStream dis = new DataInputStream(new FileInputStream("primitivos.dat"))) {
            //Inicicalización y asiignación de valores
            int numero = dis.readInt();
            double precio = dis.readDouble();
            String nombre = dis.readUTF();
            boolean activo = dis.readBoolean();

            //Imprimimos los valores
            System.out.println("Número: " + numero);
            System.out.println("Nombre: " + nombre);
            System.out.println("Precio: " + precio);
            System.out.println("Activo: " + activo);
        } catch(IOException e) {
            System.err.println("Error al escribir: " + e.getMessage());
        }
    }
}
