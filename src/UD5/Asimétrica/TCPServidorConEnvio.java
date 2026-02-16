package UD5.Asimétrica;

import java.io.*;
import java.net.*;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import javax.crypto.Cipher;
import java.util.Base64;

/**
 * Servidor que envía su clave pública al cliente y mantiene un diálogo cifrado.
 */
public class TCPServidorConEnvio {
    private static final String ALGORITMO = "RSA";
    private static final String FICH_PRIVADA = "claveprivada.pem";
    private static final String FICH_PUBLICA = "clavepublica.pem";

    public static void main(String[] args) {
        int PUERTO = 5000;

        try {
            // 1. Cargar ambas claves desde los archivos generados anteriormente
            byte[] privadaBytes = new FileInputStream(FICH_PRIVADA).readAllBytes();
            byte[] publicaBytes = new FileInputStream(FICH_PUBLICA).readAllBytes();

            KeyFactory kf = KeyFactory.getInstance(ALGORITMO);
            PrivateKey clavePrivada = kf.generatePrivate(new PKCS8EncodedKeySpec(privadaBytes));

            ServerSocket servidor = new ServerSocket(PUERTO);
            System.out.println("Servidor iniciado. Esperando clientes...");

            while (true) {
                try (Socket cliente = servidor.accept()) {
                    DataOutputStream out = new DataOutputStream(cliente.getOutputStream());
                    DataInputStream in = new DataInputStream(cliente.getInputStream());

                    // --- PASO 1: HANDSHAKE (Enviar clave pública) ---
                    out.writeInt(publicaBytes.length); // Enviamos tamaño
                    out.write(publicaBytes);           // Enviamos los bytes de la clave pública
                    System.out.println("Clave pública enviada al cliente.");

                    // --- PASO 2: SOLICITAR EDAD (Cifrado) ---
                    String pregunta = "Hola, ¿qué edad tienes?";
                    enviarMensajeCifrado(pregunta, clavePrivada, out);

                    // --- PASO 3: RECIBIR RESPUESTA (En claro por simplicidad) ---
                    int edad = in.readInt();
                    System.out.println("El cliente dice que tiene: " + edad);

                    // --- PASO 4: LÓGICA DE NEGOCIO Y RESPUESTA ---
                    String respuesta;
                    if (edad <= 0) {
                        respuesta = "Eso es imposible";
                    } else if (edad < 18) {
                        respuesta = "Eres menor de edad, no te puedo dar cierta información";
                    } else {
                        respuesta = "Te voy a contar un secreto: ¡YA PUEDES VOTAR!";
                    }

                    enviarMensajeCifrado(respuesta, clavePrivada, out);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Cifra un mensaje con la clave privada y lo envía por el flujo de salida.
     */
    private static void enviarMensajeCifrado(String msg, PrivateKey key, DataOutputStream out) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITMO);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] bytesCifrados = cipher.doFinal(msg.getBytes("UTF-8"));
        String b64 = Base64.getEncoder().encodeToString(bytesCifrados);

        out.writeUTF(msg); // Mensaje en claro
        out.writeUTF(b64); // Mensaje cifrado
        System.out.println("Enviado: " + msg + " (" + b64 + ")");
    }
}
