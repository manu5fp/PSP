package UD3.MultiCast;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;

public class MultiClient {

    public static void main(String args[]) throws Exception {

        int puerto = 12345;
        InetAddress grupo = InetAddress.getByName("225.0.0.1");

        // Creamos el socket multicast
        MulticastSocket ms = new MulticastSocket(puerto);

        // Dirección del grupo multicast (IP + puerto)
        InetSocketAddress grupoSocket = new InetSocketAddress(grupo, puerto);

        // Interfaz de red (por defecto la asociada a localhost)
        NetworkInterface netIf = NetworkInterface.getByInetAddress(
                InetAddress.getLocalHost()
        );

        // Nos unimos al grupo multicast (forma moderna)
        ms.joinGroup(grupoSocket, netIf);

        String msg = "";

        while (!msg.trim().equals("*")) {

            byte[] buf = new byte[1000];
            DatagramPacket paquete = new DatagramPacket(buf, buf.length);

            ms.receive(paquete);

            msg = new String(paquete.getData(), 0, paquete.getLength());
            System.out.println("Recibo: " + msg);
        }

        // Abandonamos el grupo
        ms.leaveGroup(grupoSocket, netIf);

        ms.close();
        System.out.println("Socket cerrado...");
    }
}
