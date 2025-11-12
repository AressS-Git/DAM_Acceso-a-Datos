package TerceraPractica;

import java.io.*;
import java.util.Properties;

public class EjemploProperties {
    public static void main() {
        Properties config = new Properties();

        //Intentar cargar un archivo existente
        try(FileInputStream fis = new FileInputStream("config.properties")) {
            //Cargamos el archivo
            config.load(fis);
            System.out.println("Configuración cargada desde el archivo");
        } catch (IOException e) {
            System.err.println("Creando configuración por defecto");
            config.setProperty("db.host", "localhost");
            config.setProperty("db.port", "3306");
            config.setProperty("db.name", "mi_base_datos");
            config.setProperty("db.debug", "false");
        }

        //Leer propiedades
        String host = config.getProperty("db.host", "prueba");
        String port = config.getProperty("db.port");
        String db = config.getProperty("db.name");
        boolean debug = Boolean.parseBoolean(config.getProperty("db.debug"));

        System.out.println("=== Configuración ===");
        System.out.println("Host: " + host);
        System.out.println("Port: " + port);
        System.out.println("DB: " + db);
        System.out.println("Debug: " + debug);

        //Guardar la configuración
        try(FileOutputStream fos = new FileOutputStream("config.properties")) {
            //Escritura de carácteres ASCII en el fichero específicado
            config.store(fos, "Configuración de la aplicación");
            System.out.println("\nConfiguración guardada");
        } catch (IOException e) {
            System.err.println("Error al leer datos.bin" +  e.getMessage());
        }
    }
}
