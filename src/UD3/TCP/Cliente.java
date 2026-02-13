package UD3.TCP;
// Clase cliente TPC
import java.io.DataInputStream;
import java.net.InetAddress;
import java.net.Socket;

/**
 * Clase {@code Cliente} que implementa un cliente TCP sencillo.
 * <p>
 * Este cliente se conecta a un servidor que escucha en el puerto 5000
 * de la máquina local (localhost), recibe un mensaje en formato UTF
 * y finaliza la conexión.
 * </p>
 *
 * <p>
 * Es un ejemplo básico de comunicación cliente-servidor utilizando
 * sockets en Java.
 * </p>
 *
 * @author Diego
 * @version 1.0
 */
public class Cliente {

    /**
     * Método principal del programa.
     * <p>
     * Realiza los siguientes pasos:
     * <ul>
     *   <li>Obtiene la dirección IP local.</li>
     *   <li>Establece una conexión con el servidor mediante un {@link Socket}.</li>
     *   <li>Recibe datos enviados por el servidor usando un {@link DataInputStream}.</li>
     *   <li>Muestra el mensaje recibido por pantalla.</li>
     *   <li>Cierra la conexión.</li>
     * </ul>
     * </p>
     *
     * @param argv argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String argv[]) {

        // Dirección IP del servidor (en este caso, la máquina local)
        InetAddress direccion;

        // Socket que representa la conexión con el servidor
        Socket servidor;

        // Puerto en el que el servidor está escuchando
        int PUERTO = 5000;

        System.out.println("Soy el cliente e intento conectarme");

        try {
            // Obtenemos la dirección IP local (localhost)
            direccion = InetAddress.getLocalHost();

            // Creamos el socket indicando dirección y puerto
            servidor = new Socket(direccion, PUERTO);

            System.out.println("Conexión realizada con éxito");

            // Flujo de entrada para recibir datos del servidor
            DataInputStream datos = new DataInputStream(servidor.getInputStream());

            // Leemos un mensaje en formato UTF enviado por el servidor
            System.out.println(datos.readUTF());

            // Cerramos la conexión
            servidor.close();

            System.out.println("Soy el cliente y cierro la conexión");

        } catch (Exception e) {
            // Captura y muestra cualquier error de conexión o E/S
            e.printStackTrace();
        }
    }
}
