package UD3.Ejercicio;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) {

        int PUERTO = 5000;
        InetAddress direccion;
        Socket servidor;

        try (Scanner teclado = new Scanner(System.in)) {

            System.out.print("Introduce tu nombre de usuario: ");
            String user = teclado.nextLine();

            direccion = InetAddress.getLocalHost();
            servidor = new Socket(direccion, PUERTO);

            DataOutputStream salida = new DataOutputStream(servidor.getOutputStream());
            DataInputStream entrada = new DataInputStream(servidor.getInputStream());

            // Enviamos el nick
            salida.writeUTF(user);

            // Recibimos respuesta
            String respuesta = entrada.readUTF();
            System.out.println("Servidor: " + respuesta);

            if (respuesta.equalsIgnoreCase("acceso permitido")) {
                System.out.print("Introduce una cantidad: ");
                double cantidad = teclado.nextDouble();
                salida.writeDouble(cantidad);
            }

            servidor.close();
            System.out.println("Conexión cerrada");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

