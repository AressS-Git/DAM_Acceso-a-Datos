package TerceraPractica;

//Importación de paquetes
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestMySQLConnection {
    public static void main(String[] args) {
        //inicialización de variables
        String url = "jdbc:mysql://localhost:3306/mi_base_datos";
        String user = "root";
        String password = "mysql";

        //Try catch en el que intentamos hacer ping a la base de datos creada
        try (Connection conn = DriverManager.getConnection(url, user, password)) {
            System.out.println("Conexión existosa al BD de mysql");
        } catch (SQLException e) {
            System.err.println("Error al conectar " + e.getMessage());
        }
    }
}