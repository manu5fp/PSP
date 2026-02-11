package UD5.Asimétrica;
// Clase servidor TCP
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Clase {@code Servidor} que implementa un servidor TCP sencillo.
 * <p>
 * El servidor escucha conexiones entrantes en un puerto determinado
 * y atiende a los clientes de forma secuencial. A cada cliente que se
 * conecta le envía un mensaje identificándolo mediante un número.
 * </p>
 *
 * <p>
 * Este ejemplo muestra el uso de {@link ServerSocket} para la escucha
 * de conexiones y {@link Socket} para la comunicación con cada cliente.
 * </p>
 *
 * @author Diego
 * @version 1.0
 */
public class TCPServidor {

    /**
     * Método principal del servidor.
     * <p>
     * Realiza las siguientes acciones:
     * <ul>
     *   <li>Crea un {@link ServerSocket} para escuchar peticiones en el puerto 5000.</li>
     *   <li>Espera conexiones entrantes de clientes.</li>
     *   <li>Asigna un número a cada cliente que se conecta.</li>
     *   <li>Envía un mensaje al cliente mediante un {@link DataOutputStream}.</li>
     *   <li>Cierra la conexión con el cliente.</li>
     * </ul>
     * </p>
     *
     * <p>
     * El servidor permanece en ejecución indefinidamente hasta que se
     * produce una excepción o se detiene manualmente.
     * </p>
     *
     * @param argv argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String argv[]) {

        // Socket del servidor para escuchar conexiones entrantes
        ServerSocket servidor;

        // Socket para atender a cada cliente
        Socket cliente;

        // Contador para identificar a los clientes
        int numCliente = 0;

        // Puerto en el que el servidor escucha las conexiones
        int PUERTO = 5000;

        System.out.println("Soy el servidor y empiezo a escuchar peticiones por el puerto: " + PUERTO);

        try {
            // Creación del socket servidor asociado al puerto indicado
            servidor = new ServerSocket(PUERTO);

            // Bucle infinito para atender clientes de forma secuencial
            do {
                numCliente++;

                // Espera (bloqueante) a que un cliente se conecte
                cliente = servidor.accept();

                System.out.println("\tLlega el cliente: " + numCliente);

                // Flujo de salida para enviar datos al cliente
                DataOutputStream ps = new DataOutputStream(cliente.getOutputStream());

                // Envío de un mensaje en formato UTF al cliente
                ps.writeUTF("Usted es mi cliente: " + numCliente);

                // Cierre de la conexión con el cliente
                cliente.close();

                System.out.println("\tSe ha cerrado la conexión con el cliente: " + numCliente);

            } while (true);

        } catch (Exception e) {
            // Captura y muestra cualquier error de red o de entrada/salida
            e.printStackTrace();
        }
    }
}

