package UD3.TCP_MULTI;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Clase {@code SocketTCPServer} que implementa un servidor TCP concurrente.
 * <p>
 * El servidor escucha conexiones entrantes en un puerto determinado y,
 * por cada cliente que se conecta, crea un nuevo hilo de ejecución
 * mediante la clase {@code GestorProcesos} para atenderlo de forma
 * independiente.
 * </p>
 *
 * <p>
 * Este diseño permite que el servidor atienda múltiples clientes
 * simultáneamente, a diferencia de un servidor secuencial.
 * </p>
 *
 * @author Diego
 * @version 1.0
 */
public class SocketTCPServer {

    /**
     * Socket del servidor encargado de escuchar las conexiones entrantes.
     */
    private ServerSocket serverSocket;

    /**
     * Constructor del servidor TCP.
     * <p>
     * Crea un {@link ServerSocket} asociado al puerto indicado.
     * </p>
     *
     * @param puerto puerto en el que el servidor escuchará las conexiones.
     * @throws IOException si se produce un error al abrir el socket.
     */
    public SocketTCPServer(int puerto) throws IOException {
        serverSocket = new ServerSocket(puerto);
    }

    /**
     * Inicia la ejecución del servidor.
     * <p>
     * El servidor queda a la espera de conexiones entrantes. Cada vez
     * que un cliente se conecta:
     * <ul>
     *   <li>Acepta la conexión mediante {@link ServerSocket#accept()}.</li>
     *   <li>Muestra un mensaje indicando el número de cliente.</li>
     *   <li>Crea y lanza un nuevo hilo {@code GestorProcesos} para atenderlo.</li>
     * </ul>
     * </p>
     *
     * <p>
     * Este método se ejecuta de forma indefinida hasta que el servidor
     * es detenido o se produce una excepción.
     * </p>
     *
     * @throws IOException si se produce un error durante la aceptación
     *                     de conexiones.
     */
    public void lanzar() throws IOException {
        System.out.println("(Servidor): A la escucha");

        int cont = 0;

        // Bucle infinito para aceptar conexiones
        while (true) {
            // Espera a que un cliente se conecte (bloqueante)
            Socket socket = serverSocket.accept();
            cont++;

            System.out.println("(Servidor): Conexión establecida con cliente " + cont);

            // Se crea un hilo para gestionar la comunicación con el cliente
            new GestorProcesos(socket).start();
        }
    }

    /**
     * Detiene el servidor cerrando el {@link ServerSocket}.
     *
     * @throws IOException si se produce un error al cerrar el socket.
     */
    public void stop() throws IOException {
        serverSocket.close();
    }

    /**
     * Método principal del servidor TCP.
     * <p>
     * Crea una instancia del servidor asociada al puerto 49171
     * y lanza el proceso de escucha de clientes.
     * </p>
     *
     * @param args argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        try {
            SocketTCPServer servidor = new SocketTCPServer(49171);
            servidor.lanzar();

        } catch (IOException e) {
            // Error relacionado con la creación o ejecución del servidor
            e.printStackTrace();
        }
    }
}
