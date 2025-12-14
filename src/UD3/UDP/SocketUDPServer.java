package UD3.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Clase {@code SocketUDPServer} que implementa un servidor UDP básico.
 * <p>
 * El servidor crea un {@link DatagramSocket} asociado a un puerto concreto,
 * recibe un datagrama enviado por un cliente y responde con otro datagrama
 * al mismo cliente.
 * </p>
 *
 * <p>
 * Este ejemplo ilustra el funcionamiento de la comunicación sin conexión
 * (UDP), donde no existe establecimiento ni cierre de conexión como en TCP.
 * </p>
 *
 * @author Diego
 * @version 1.0
 */
public class SocketUDPServer {

    /**
     * Método principal del servidor UDP.
     * <p>
     * Realiza las siguientes operaciones:
     * <ul>
     *   <li>Crea un socket UDP en el puerto 49171.</li>
     *   <li>Espera la llegada de un datagrama desde un cliente.</li>
     *   <li>Muestra por pantalla el mensaje recibido.</li>
     *   <li>Envía un mensaje de respuesta al cliente.</li>
     *   <li>Cierra el socket.</li>
     * </ul>
     * </p>
     *
     * @param args argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {

        // Socket UDP del servidor
        DatagramSocket socket;

        try {
            System.out.println("(Servidor): Creando socket...");

            // Creación del socket UDP asociado al puerto indicado
            socket = new DatagramSocket(49171);

            System.out.println("(Servidor): Recibiendo datagrama...");

            // Buffer para almacenar los datos recibidos
            byte[] bufferLectura = new byte[64];

            // Datagrama para la recepción de datos
            DatagramPacket datagramaEntrada =
                    new DatagramPacket(bufferLectura, bufferLectura.length);

            // Recepción del datagrama (bloqueante)
            socket.receive(datagramaEntrada);

            // Conversión del mensaje recibido a String
            System.out.println("(Servidor): Mensaje recibido: "
                    + new String(bufferLectura));

            System.out.println("(Servidor): Enviando datagrama...");

            // Mensaje que se enviará al cliente
            byte[] mensajeEnviado =
                    "Mensaje enviado desde el servidor".getBytes();

            // Datagrama de salida dirigido al cliente que envió el mensaje
            DatagramPacket datagramaSalida =
                    new DatagramPacket(
                            mensajeEnviado,
                            mensajeEnviado.length,
                            datagramaEntrada.getAddress(),
                            datagramaEntrada.getPort()
                    );

            // Envío del datagrama al cliente
            socket.send(datagramaSalida);

            System.out.println("(Servidor): Cerrando socket...");

            // Cierre del socket
            socket.close();

            System.out.println("(Servidor): Socket cerrado.");

        } catch (SocketException e) {
            // Error relacionado con la creación o uso del socket
            e.printStackTrace();

        } catch (IOException e) {
            // Error de entrada/salida durante el envío o recepción
            e.printStackTrace();
        }
    }
}
