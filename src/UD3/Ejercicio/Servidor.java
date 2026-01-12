package UD3.Ejercicio;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    private static final int PUERTO = 5000;
    private static final String FICHERO_USUARIOS = "users.txt";
    private static final String FICHERO_CANTIDADES = "cantidades.txt";

    public static void main(String[] args) {

        System.out.println("Servidor escuchando en el puerto " + PUERTO);

        try (ServerSocket servidor = new ServerSocket(PUERTO)) {

            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Cliente conectado");

                atenderCliente(cliente);

                cliente.close();
                System.out.println("Cliente desconectado");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void atenderCliente(Socket cliente) {

        try {
            DataInputStream entrada = new DataInputStream(cliente.getInputStream());
            DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());

            // Recibimos nombre
            String user = entrada.readUTF();
            System.out.println("Nombre recibido: " + user);

            if (usuarioExiste(user)) {
                salida.writeUTF("acceso permitido");

                double cantidad = entrada.readDouble();
                guardarCantidad(user, cantidad);

            } else {
                salida.writeUTF("acceso denegado");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean usuarioExiste(String user) throws IOException {
    	boolean existe = false;
        try (BufferedReader br = new BufferedReader(new FileReader(FICHERO_USUARIOS))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.equalsIgnoreCase(user)) {
                    return true;
                }
            }
        }
        return existe;
    }

    private static void guardarCantidad(String user, double cantidad) throws IOException {

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FICHERO_CANTIDADES, true))) {
            bw.write(user + " " + cantidad);
            bw.newLine();
        }
    }
}
