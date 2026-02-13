package UD5.Asimétrica;

import java.io.*;
import java.net.*;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;

public class TCPServidor {
    private static final String ALGORITMO = "RSA";
    private static final String FICH_PRIVADA = "claveprivada.pem";

    public static void main(String argv[]) {
        int PUERTO = 5000;
        int numCliente = 0;

        try {
            // 1. Cargar la clave privada del servidor
            byte[] privadaBytes = new FileInputStream(FICH_PRIVADA).readAllBytes();
            KeyFactory kf = KeyFactory.getInstance(ALGORITMO);
            PrivateKey clavePrivada = kf.generatePrivate(new PKCS8EncodedKeySpec(privadaBytes));

            ServerSocket servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor escuchando en puerto " + PUERTO);

            while (true) {
                Socket cliente = servidor.accept();
                numCliente++;
                
                String mensajeClaro = "Usted es mi cliente: " + numCliente;
                
                // 2. Cifrar el mensaje con la clave privada
                Cipher cipher = Cipher.getInstance(ALGORITMO);
                cipher.init(Cipher.ENCRYPT_MODE, clavePrivada);
                byte[] cifradoBytes = cipher.doFinal(mensajeClaro.getBytes("UTF-8"));
                String mensajeCifradoB64 = Base64.getEncoder().encodeToString(cifradoBytes);

                // 3. Enviar ambos (para cumplir tu requisito de visualización)
                DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
                out.writeUTF(mensajeClaro);
                out.writeUTF(mensajeCifradoB64);

                System.out.println("Enviado: " + mensajeClaro + " (" + mensajeCifradoB64 + ")");
                cliente.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}