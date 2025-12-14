package UD3.UDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * Clase {@code SocketUDPClient} que implementa un cliente UDP sencillo.
 * <p>
 * El cliente envía un datagrama a un servidor UDP que escucha en un
 * puerto determinado y espera recibir una respuesta.
 * </p>
 *
 * <p>
 * Este ejemplo muestra cómo realizar comunicación sin conexión
 * utilizando el protocolo UDP en Java.
 * </p>
 *
 * @author Diego
 * @version 1.0
 */
public class SocketUDPClient {

    /**
     * Método principal del cliente UDP.
     * <p>
     * Realiza las siguientes acciones:
     * <ul>
     *   <li>Define el mensaje que se enviará al servidor.</li>
     *   <li>Resuelve la dirección del servidor (localhost).</li>
     *   <li>Crea un {@link DatagramSocket} sin puerto fijo.</li>
     *   <li>Envía un {@link DatagramPacket} al servidor.</li>
     *   <li>Espera la recepción de un datagrama de respuesta.</li>
     *   <li>Muestra el mensaje recibido por pantalla.</li>
     *   <li>Cierra el socket.</li>
     * </ul>
     * </p>
     *
     * @param args argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {

        // Mensaje que se enviará al servidor
        String strMensaje = "Mensaje enviado desde el cliente";

        // Socket UDP del cliente
        DatagramSocket socketUDP;

        try {
            System.out.println("(Cliente): Estableciendo parámetros de conexión...");

            // Dirección del servidor (localhost)
            InetAddress hostServidor = InetAddress.getByName("localhost");

            // Puerto en el que escucha el servidor
            int puertoServidor = 49171;

            System.out.println("(Cliente): Creando socket...");

            // Creación del socket UDP (el sistema asigna un puerto local)
            socketUDP = new DatagramSocket();

            System.out.println("(Cliente): Enviando datagrama...");

            // Conversión del mensaje a bytes
            byte[] mensaje = strMensaje.getBytes();

            // Creación del datagrama de petición
            DatagramPacket peticion =
                    new DatagramPacket(mensaje, mensaje.length,
                            hostServidor, puertoServidor);

            // Envío del datagrama al servidor
            socketUDP.send(peticion);

            System.out.println("(Cliente): Recibiendo datagrama...");

            // Buffer para recibir la respuesta
            byte[] buffer = new byte[64];

            // Datagrama para recibir la respuesta del servidor
            DatagramPacket respuesta =
                    new DatagramPacket(buffer, buffer.length);

            // Recepción del datagrama (bloqueante)
            socketUDP.receive(respuesta);

            // Conversión del mensaje recibido a String
            System.out.println("(Cliente): Mensaje recibido: "
                    + new String(buffer));

            System.out.println("(Cliente): Cerrando socket...");

            // Cierre del socket
            socketUDP.close();

            System.out.println("(Cliente): Socket cerrado.");

        } catch (SocketException e) {
            // Error relacionado con el socket UDP
            e.printStackTrace();

        } catch (UnknownHostException e) {
            // Error al resolver el nombre del host
            e.printStackTrace();

        } catch (IOException e) {
            // Error de entrada/salida
            e.printStackTrace();
        }
    }
}
