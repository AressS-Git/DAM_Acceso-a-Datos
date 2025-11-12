package TerceraPractica;

//Paquetes nativos para SQL y BBDD
import java.sql.*;

public class EjemploJDBC {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/mi_base_datos";
        String usuario = "root";
        String password = "mysql";

        //Inicialización objetos
        Connection conn = null; //Objeto de conexión a la bbdd para mysql
        Statement stmt = null;  //Objeto que captura sentencias de sql
        ResultSet rs = null;    //Objeto que captura el resultado de dicha secuencia

        try {
            //Establecer conexión
            conn = DriverManager.getConnection(url, usuario, password);
            System.out.println("Conectado con MySQL");

            //Crear statement y ejecutar consulta
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT id, nombre, edad from usuarios");
            while (rs.next()) {
                //Inicialización y conversión de variables de mysql a Java
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                int edad = rs.getInt("edad");

                //Mostar en consulta el resultado final fila a fila
                System.out.println(id + "- " + nombre + " " + edad + " años");
            }
        } catch (SQLException e) {
            System.err.println("Error SQl: " + e.getMessage());
        } finally {
            //Cerrar las conexiones de cada uno de los objetos
            try {
                if (rs != null) {rs.close();}
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (stmt != null) {stmt.close();}
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            try {
                if (conn != null) {conn.close();}
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
