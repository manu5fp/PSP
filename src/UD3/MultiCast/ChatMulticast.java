package UD3.MultiCast;

import java.net.*;
import java.util.Scanner;

public class ChatMulticast {

    private static final String GRUPO = "225.0.0.1";
    private static final int PUERTO = 12345;

    public static void main(String[] args) throws Exception {

        InetAddress grupo = InetAddress.getByName(GRUPO);
        MulticastSocket socket = new MulticastSocket(PUERTO);

        InetSocketAddress grupoSocket =
                new InetSocketAddress(grupo, PUERTO);

        NetworkInterface netIf =
                NetworkInterface.getByInetAddress(InetAddress.getLocalHost());

        socket.joinGroup(grupoSocket, netIf);

        System.out.println("Conectado al chat multicast");

        // Hilo receptor
        new Thread(() -> recibir(socket)).start();

        // Hilo emisor (principal)
        enviar(socket, grupo);

        socket.leaveGroup(grupoSocket, netIf);
        socket.close();
        System.out.println("Chat cerrado");
    }

    private static void recibir(MulticastSocket socket) {
        try {
            while (true) {
                byte[] buf = new byte[1024];
                DatagramPacket paquete =
                        new DatagramPacket(buf, buf.length);

                socket.receive(paquete);

                String msg = new String(
                        paquete.getData(), 0, paquete.getLength()
                );

                System.out.println("\nRecibido: " + msg);
            }
        } catch (Exception e) {
            // Socket cerrado. Salimos del hilo
        }
    }

    private static void enviar(MulticastSocket socket, InetAddress grupo)
            throws Exception {

        Scanner in = new Scanner(System.in);
        String msg = "";

        while (!msg.trim().equals("*")) {
            System.out.print("Escribe el mensaje (* para salir): ");
        	msg = in.nextLine();

            DatagramPacket paquete =
                    new DatagramPacket(
                            msg.getBytes(),
                            msg.length(),
                            grupo,
                            PUERTO
                    );

            socket.send(paquete);
        }
        in.close();
    }
}
