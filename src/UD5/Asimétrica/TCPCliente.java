package UD5.Asimétrica;

import java.io.*;
import java.net.*;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.Cipher;

public class TCPCliente {
    private static final String ALGORITMO = "RSA";
    private static final String FICH_PUBLICA = "clavepublica.pem";

    public static void main(String argv[]) {
        int PUERTO = 5000;

        try {
            // 1. Cargar la clave pública
            byte[] publicaBytes = new FileInputStream(FICH_PUBLICA).readAllBytes();
            KeyFactory kf = KeyFactory.getInstance(ALGORITMO);
            PublicKey clavePublica = kf.generatePublic(new X509EncodedKeySpec(publicaBytes));

            InetAddress direccion = InetAddress.getLocalHost();
            Socket servidor = new Socket(direccion, PUERTO);

            DataInputStream in = new DataInputStream(servidor.getInputStream());
            
            // 2. Leer los dos strings enviados por el servidor
            String mensajeClaro = in.readUTF();
            String mensajeCifradoB64 = in.readUTF();

            // 3. Descifrar el mensaje para verificar
            Cipher cipher = Cipher.getInstance(ALGORITMO);
            cipher.init(Cipher.DECRYPT_MODE, clavePublica);
            byte[] descifradoBytes = cipher.doFinal(Base64.getDecoder().decode(mensajeCifradoB64));
            String mensajeRecuperado = new String(descifradoBytes, "UTF-8");

            System.out.println("Mensaje recibido: " + mensajeClaro + " (" + mensajeCifradoB64 + ")");
            System.out.println("Verificación (descifrado con clave pública): " + mensajeRecuperado);

            servidor.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}