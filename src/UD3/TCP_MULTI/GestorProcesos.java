package UD3.TCP_MULTI;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * Clase {@code GestorProcesos} que gestiona la comunicación con un cliente TCP.
 * <p>
 * Esta clase extiende {@link Thread} y representa un hilo independiente
 * encargado de atender a un único cliente conectado al servidor.
 * </p>
 *
 * <p>
 * Cada instancia de {@code GestorProcesos} se crea cuando un cliente
 * establece conexión con el servidor y se ejecuta de forma concurrente
 * respecto a otros clientes.
 * </p>
 *
 * @author Diego
 * @version 1.0
 */
public class GestorProcesos extends Thread {

    /**
     * Socket asociado al cliente atendido por este hilo.
     */
    private Socket socket;

    /**
     * Flujo de salida para enviar datos al cliente.
     */
    private OutputStream os;

    /**
     * Constructor del gestor de procesos.
     * <p>
     * Recibe el {@link Socket} correspondiente al cliente y lo almacena
     * para su posterior gestión.
     * </p>
     *
     * @param socket socket asociado al cliente conectado.
     */
    public GestorProcesos(Socket socket) {
        this.socket = socket;
    }

    /**
     * Método ejecutado automáticamente al iniciar el hilo mediante
     * {@link Thread#start()}.
     * <p>
     * Invoca el método {@link #realizarProceso()} y captura posibles
     * excepciones de entrada/salida.
     * </p>
     */
    @Override
    public void run() {
        try {
            realizarProceso();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Realiza el procesamiento asociado al cliente.
     * <p>
     * El método:
     * <ul>
     *   <li>Obtiene el flujo de salida del socket.</li>
     *   <li>Genera un tiempo de espera aleatorio.</li>
     *   <li>Simula un procesamiento mediante {@code sleep()}.</li>
     *   <li>Envía al cliente el tiempo de espera generado.</li>
     *   <li>Cierra el flujo de salida.</li>
     * </ul>
     * </p>
     *
     * @throws IOException si se produce un error al acceder al flujo
     *                     de salida del socket.
     */
    public void realizarProceso() throws IOException {

        // Obtención del flujo de salida asociado al socket del cliente
        os = this.socket.getOutputStream();

        // Generación de un tiempo de espera aleatorio (en segundos)
        int tiempoEspera = Azar.entero(10);

        try {
            // Simulación de un proceso largo
            TimeUnit.SECONDS.sleep(tiempoEspera);

            // Envío del tiempo de espera al cliente
            os.write(tiempoEspera);

        } catch (InterruptedException e) {
            // Error si el hilo es interrumpido durante el sleep
            e.printStackTrace();

        } finally {
            // Cierre del flujo de salida
            os.close();
        }
    }
}
