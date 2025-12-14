package UD3.TCP_MULTI;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Clase {@code SocketTCPClient} que implementa un cliente TCP sencillo.
 * <p>
 * El cliente se conecta a un servidor TCP mediante una dirección IP y
 * un puerto determinados, recibe un dato enviado por el servidor y
 * cierra la conexión.
 * </p>
 *
 * <p>
 * Esta clase está diseñada para interactuar con un servidor TCP
 * concurrente que envía información a cada cliente desde un hilo
 * independiente.
 * </p>
 *
 * @author Profesor
 * @version 1.0
 */
public class SocketTCPClient {

    /**
     * Dirección IP o nombre del servidor.
     */
    private String serverIP;

    /**
     * Puerto en el que escucha el servidor.
     */
    private int serverPort;

    /**
     * Socket utilizado para la comunicación con el servidor.
     */
    private Socket socket;

    /**
     * Flujo de entrada para recibir datos desde el servidor.
     */
    public InputStream is;

    /**
     * Constructor del cliente TCP.
     *
     * @param serverIP   dirección IP o nombre del servidor.
     * @param serverPort puerto en el que escucha el servidor.
     */
    public SocketTCPClient(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    /**
     * Establece la conexión con el servidor TCP.
     * <p>
     * Crea el {@link Socket} asociado a la dirección IP y puerto indicados
     * y obtiene el flujo de entrada para recibir datos del servidor.
     * </p>
     *
     * @throws UnknownHostException si no se puede resolver el nombre del host.
     * @throws IOException          si se produce un error al crear el socket
     *                              o al obtener el flujo de entrada.
     */
    public void start() throws UnknownHostException, IOException {
        System.out.println("(Cliente): Estableciendo conexión...");

        // Creación del socket cliente
        socket = new Socket(serverIP, serverPort);

        // Obtención del flujo de entrada
        is = socket.getInputStream();

        System.out.println("(Cliente): Conexión establecida.");
    }

    /**
     * Cierra la conexión con el servidor.
     * <p>
     * Cierra el flujo de entrada y el socket asociado al cliente.
     * </p>
     *
     * @throws IOException si se produce un error al cerrar los recursos.
     */
    public void stop() throws IOException {
        System.out.println("(Cliente): Cerrando conexiones...");

        // Cierre del flujo de entrada
        is.close();

        // Cierre del socket
        socket.close();

        System.out.println("(Cliente): Conexiones cerradas.");
    }

    /**
     * Método principal del cliente TCP.
     * <p>
     * Crea un cliente TCP, establece la conexión con el servidor,
     * recibe un dato enviado por este y finaliza la comunicación.
     * </p>
     *
     * @param args argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {

        // Creación del cliente TCP
        SocketTCPClient cliente = new SocketTCPClient("localhost", 49171);

        try {
            // Inicio de la conexión
            cliente.start();

            // Recepción del dato enviado por el servidor
            System.out.println("Mensaje del servidor: " + cliente.is.read());

            // Cierre de la conexión
            cliente.stop();

        } catch (UnknownHostException e) {
            // Error al resolver la dirección del servidor
            e.printStackTrace();

        } catch (IOException e) {
            // Error de entrada/salida
            e.printStackTrace();
        }
    }
}
