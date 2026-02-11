package UD5.Simetrica;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.util.Arrays;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * Chat multicast con cifrado simétrico AES.
 * <p>
 * Este programa permite la comunicación entre múltiples clientes mediante UDP multicast.
 * Los mensajes enviados se cifran con AES antes de enviarse y se descifran al recibirlos.
 * Además, se muestra el mensaje cifrado entre paréntesis junto al mensaje original.
 * </p>
 *
 * <p>
 * Este ejemplo está diseñado con fines didácticos para que el alumnado comprenda:
 * <ul>
 *   <li>Cómo funciona el cifrado simétrico.</li>
 *   <li>Cómo se integra el cifrado en aplicaciones de red reales.</li>
 *   <li>La diferencia entre texto en claro y texto cifrado.</li>
 * </ul>
 * </p>
 *
 * @author Profesor DAM
 */
public class ChatMulticastCifrado {

    /** Dirección IP del grupo multicast */
    private static final String GRUPO = "225.0.0.1";

    /** Puerto de comunicación */
    private static final int PUERTO = 12345;

    /**
     * Clave AES real generada de forma segura y representada en hexadecimal.
     * Longitud: 192 bits (24 bytes).
     */
    private static final String CLAVE_HEX =
            "5d4a45ea4379e3467c3e681cb962c2feab70d9264a9831d9";

    /** Clave AES convertida a bytes reales */
    private static final byte[] CLAVE_BYTES = hexStringToByteArray(CLAVE_HEX);

    /** Objeto de clave simétrica para AES */
    private static final SecretKeySpec CLAVE_AES =
            new SecretKeySpec(CLAVE_BYTES, "AES");

    /**
     * Método principal. Inicializa la conexión multicast,
     * lanza los hilos de envío y recepción y gestiona la comunicación.
     *
     * @param args No se utilizan argumentos.
     * @throws Exception Si ocurre algún error de red o criptográfico.
     */
    public static void main(String[] args) throws Exception {

        InetAddress grupo = InetAddress.getByName(GRUPO);
        MulticastSocket socket = new MulticastSocket(PUERTO);

        InetSocketAddress grupoSocket =
                new InetSocketAddress(grupo, PUERTO);

        NetworkInterface netIf =
                NetworkInterface.getByInetAddress(InetAddress.getLocalHost());

        socket.joinGroup(grupoSocket, netIf);

        System.out.println("Conectado al chat multicast cifrado");

        // Hilo receptor
        new Thread(() -> recibir(socket)).start();

        // Hilo emisor
        enviar(socket, grupo);

        socket.leaveGroup(grupoSocket, netIf);
        socket.close();

        System.out.println("Chat cerrado");
    }

    /**
     * Recibe mensajes del grupo multicast, los descifra
     * y muestra tanto el mensaje original como el cifrado.
     *
     * @param socket Socket multicast utilizado para recibir.
     */
    private static void recibir(MulticastSocket socket) {
        try {
            while (true) {
                byte[] buf = new byte[2048];
                DatagramPacket paquete =
                        new DatagramPacket(buf, buf.length);

                socket.receive(paquete);

                byte[] datos = Arrays.copyOf(
                        paquete.getData(), paquete.getLength()
                );

                byte[] descifrado = descifrar(datos);

                System.out.println("\nRecibido: " +
                        new String(descifrado) +
                        " (" + bytesToHex(datos) + ")");
            }
        } catch (Exception e) {
            // Socket cerrado: fin del hilo
        }
    }

    /**
     * Envía mensajes al grupo multicast.
     * Cada mensaje se cifra antes de enviarse.
     *
     * @param socket Socket multicast.
     * @param grupo Dirección IP del grupo.
     * @throws Exception Si ocurre un error criptográfico o de red.
     */
    private static void enviar(MulticastSocket socket, InetAddress grupo)
            throws Exception {

        Scanner in = new Scanner(System.in);
        String msg = "";

        while (!msg.trim().equals("*")) {
            System.out.print("Escribe el mensaje (* para salir): ");
            msg = in.nextLine();

            byte[] cifrado = cifrar(msg.getBytes());

            DatagramPacket paquete =
                    new DatagramPacket(
                            cifrado,
                            cifrado.length,
                            grupo,
                            PUERTO
                    );

            socket.send(paquete);
        }
        in.close();
    }

    /**
     * Cifra un array de bytes utilizando AES.
     *
     * @param datos Texto en claro.
     * @return Datos cifrados.
     * @throws Exception Si ocurre un error de cifrado.
     */
    private static byte[] cifrar(byte[] datos) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, CLAVE_AES);
        return cipher.doFinal(datos);
    }

    /**
     * Descifra un array de bytes utilizando AES.
     *
     * @param datos Datos cifrados.
     * @return Texto en claro.
     * @throws Exception Si ocurre un error de descifrado.
     */
    private static byte[] descifrar(byte[] datos) throws Exception {
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.DECRYPT_MODE, CLAVE_AES);
        return cipher.doFinal(datos);
    }

    /**
     * Convierte una cadena hexadecimal en un array de bytes reales.
     *
     * @param hex Cadena en hexadecimal.
     * @return Array de bytes correspondiente.
     */
    private static byte[] hexStringToByteArray(String hex) {
        int len = hex.length();
        byte[] data = new byte[len / 2];

        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) (
                    (Character.digit(hex.charAt(i), 16) << 4)
                  + Character.digit(hex.charAt(i + 1), 16)
            );
        }
        return data;
    }

    /**
     * Convierte un array de bytes en una cadena hexadecimal.
     *
     * @param bytes Array de bytes.
     * @return Cadena hexadecimal.
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}

