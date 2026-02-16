 
package UD5.Asimétrica;

import java.io.*;
import java.net.*;
import java.security.*;
import java.security.spec.X509EncodedKeySpec;
import javax.crypto.Cipher;
import java.util.Base64;
import java.util.Scanner;

public class TCPClienteConEnvio {
    private static final String ALGORITMO = "RSA";

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);

        try {
            Socket servidor = new Socket("localhost", 5000);
            DataInputStream in = new DataInputStream(servidor.getInputStream());
            DataOutputStream out = new DataOutputStream(servidor.getOutputStream());

            // --- PASO 1: RECIBIR CLAVE PÚBLICA (Handshake) ---
            int len = in.readInt();
            byte[] publicaBytes = new byte[len];
            in.readFully(publicaBytes);

            KeyFactory kf = KeyFactory.getInstance(ALGORITMO);
            PublicKey clavePublica = kf.generatePublic(new X509EncodedKeySpec(publicaBytes));
            System.out.println("Clave pública recibida del servidor.");

            // --- PASO 2: RECIBIR PREGUNTA ---
            recibirYDescifrar(in, clavePublica);

            // --- PASO 3: ENVIAR EDAD ---
            System.out.print("Introduce tu edad: ");
            int edad = teclado.nextInt();
            out.writeInt(edad);

            // --- PASO 4: RECIBIR RESPUESTA FINAL ---
            recibirYDescifrar(in, clavePublica);

            servidor.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            teclado.close();
        }
    }

    /**
     * Lee un mensaje doble (claro y cifrado) y verifica el descifrado.
     */
    private static void recibirYDescifrar(DataInputStream in, PublicKey key) throws Exception {
        String msgClaro = in.readUTF();
        String msgCifradoB64 = in.readUTF();

        // Descifrar para comprobar
        Cipher cipher = Cipher.getInstance(ALGORITMO);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] descifrado = cipher.doFinal(Base64.getDecoder().decode(msgCifradoB64));
        String verificado = new String(descifrado, "UTF-8");

        System.out.println("\nServidor dice: " + msgClaro + " (" + msgCifradoB64 + ")");
        System.out.println("Verificación descifrada: " + verificado);
    }
}
