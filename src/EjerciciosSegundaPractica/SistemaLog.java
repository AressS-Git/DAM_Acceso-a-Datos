package EjerciciosSegundaPractica;

/*Firmas de funciones:
* Escribe un mensaje en el log con timestamp
* @param mensaje contenido a registrar
* @param nivel nivel del log (INFO, WARNING, ERROR)
* @throws IOException si hay error al escribir
public void escribirLog(String mensaje, NivelLog nivel) throws IOExceptio
 * Verifica si el archivo debe rotarse y ejecuta la rotación
 * @return true si se realizó la rotación
 * @throws IOException si hay error en la rotaciónprivate boolean rotarSiNecesario() throws IOException
 * Obtiene el tamaño actual del archivo de log
 * @return tamaño en bytes
private long obtenerTamanoLog()
*/

/*Clases requeridas:
enum NivelLog {
 INFO, WARNING, ERROR
}
class SistemaLog {
 private String archivoLog;
 private long tamanoMaximo; // en bytes
 private int numeroRotacion;
 public SistemaLog(String archivoLog, long tamanoMaximo) {
 // Constructor
 }
 // Métodos anteriores
}
 */

/*Casos de uso:
Uso:
SistemaLog log = new SistemaLog("app.log", 1024); // 1KB máximo
log.escribirLog("Aplicación iniciada", NivelLog.INFO);
log.escribirLog("Usuario conectado", NivelLog.INFO);
log.escribirLog("Error de conexión", NivelLog.ERROR);
// ... más mensajes hasta superar 1KB
Salida (app.log):
[2025-10-14 10:30:15] [INFO] Aplicación iniciada
[2025-10-14 10:30:16] [INFO] Usuario conectado
[2025-10-14 10:30:17] [ERROR] Error de conexión
Cuando se supera el tamaño, se renombra a app.log.1 y se crea nuevo app.log
Consola:
Log escrito: Aplicación iniciada
Log escrito: Usuario conectado
ROTACIÓN: app.log renombrado a app.log.1
Log escrito: Error de conexión
 */

/*
Buenas prácticas:
• Usar BufferedWriter para escritura eficiente
• flush() después de cada escritura crítica
• Try-with-resources donde sea posible
• Formato de fecha ISO 8601
• Validar parámetros en constructor
• Sincronización si se usa en entorno multi-thread (opcional)
 */

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

enum NivelLog {
    INFO, WARNING, ERROR
}

public class SistemaLog {
    private String archivoLog;
    private long tamanoMaximo; // en bytes
    private int numeroRotacion;

    public SistemaLog(String archivoLog, long tamanoMaximo) {
        this.archivoLog = archivoLog;
        this.tamanoMaximo = tamanoMaximo;
    }

    public void escribirLog(String mensaje, NivelLog nivel) throws IOException {
        rotarSiEsNecesario();
        LocalDateTime fechaActual = LocalDateTime.now();
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaActualFormateada =  formateador.format(fechaActual);
        String lineaAEscribir = "[" + fechaActualFormateada + "] [" + nivel + "] " + mensaje;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(this.archivoLog, true))) {
            bw.write(lineaAEscribir);
            bw.newLine();
            bw.flush();
            System.out.println("Log escrito: " + mensaje);
        }
    }

    private boolean rotarSiEsNecesario() throws IOException{
        long tamanoActual = obtenerTamanoLog();
        if(tamanoActual >= tamanoMaximo) {
            System.err.println("El archivo ha superado el tamaño máximo establecido: " + tamanoMaximo);
            File archivoLogActual = new File(this.archivoLog);
            numeroRotacion++;
            File archivoLogSustituto = new File(this.archivoLog + "." + this.numeroRotacion);
            if(archivoLogActual.renameTo(archivoLogSustituto)) {
                System.out.println("ROTACIÓN: " + this.archivoLog + " renombrado a " + archivoLogSustituto.getName());
            } else {
                System.err.println("Error al rename archivo log: " + archivoLogActual);
                //Volver al número de rotación anterior si no se ha podido renombrar el archivo
                numeroRotacion--;
            }
            return true;
        } else {
            System.out.println("El archivo no supera el tamaño establecido, la rotación no es necesaria");
            System.out.println("Tamaño establecido: " + tamanoMaximo + " || Tamaño del archivo: " + tamanoActual);
            return false;
        }
    }

    private long obtenerTamanoLog() throws IOException{
        File archivoLogFile = new File(archivoLog);
        if(archivoLogFile.exists() && archivoLogFile.isFile()) {
            return archivoLogFile.length();
        } else  {
            return 0;
        }
    }

    public static void main(String[] args) throws IOException {
        SistemaLog log = new SistemaLog("app.log", 100);
        log.escribirLog("Aplicación iniciada", NivelLog.INFO);
        log.escribirLog("Error de conexión", NivelLog.ERROR);
        log.escribirLog("Intento de acceso fallido", NivelLog.WARNING);
    }
    //Línea para comprobar cambio de cuenta GitHub
}
