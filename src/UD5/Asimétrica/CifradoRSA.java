package UD5.Asimétrica;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Scanner;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 * Clase encargada de realizar el cifrado de un mensaje de texto utilizando 
 * una clave pública RSA previamente generada y almacenada en disco.
 * * <p>El flujo de trabajo consiste en:</p>
 * <ol>
 * <li>Leer los bytes de la clave pública desde un archivo.</li>
 * <li>Reconstruir el objeto PublicKey a partir de la especificación X.509.</li>
 * <li>Configurar e inicializar el motor de cifrado (Cipher).</li>
 * <li>Transformar el texto plano en un criptograma ilegible.</li>
 * </ol>
 * * @author Tu Nombre/Docente
 * @version 1.0
 */
public class CifradoRSA {
    
    /** Algoritmo de cifrado asimétrico. */
    private static final String ALGORITMO_CLAVE_PUB = "RSA";
    
    /** Nombre del archivo que contiene la clave pública (formato DER/binario). */
    private static final String FICH_CLAVE_PUB = "clavepublica.pem";

    /**
     * Punto de entrada que solicita un texto al usuario y lo cifra.
     * * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Mensaje a cifrar: ");
        String cadenaEnClaro = sc.nextLine();
        sc.close();

        byte[] clavePubCodif;

        // 1. Cargar la clave pública desde el sistema de archivos
        try (FileInputStream fisClavePub = new FileInputStream(FICH_CLAVE_PUB)) {
            clavePubCodif = fisClavePub.readAllBytes();
        } catch (FileNotFoundException e) {
            System.err.printf("ERROR: no existe fichero de clave pública %s\n", FICH_CLAVE_PUB);
            return;
        } catch (IOException e) {
            System.err.printf("ERROR: de E/S leyendo clave de fichero %s\n", FICH_CLAVE_PUB);
            return;
        }

        try {
            // 2. Reconstruir la clave pública
            // KeyFactory se usa para convertir claves (opacas) en especificaciones (transparentes) y viceversa
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITMO_CLAVE_PUB);
            X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(clavePubCodif);
            PublicKey clavePublica = keyFactory.generatePublic(publicKeySpec);

            // 3. Preparar los datos (convertir String a bytes con codificación fija)
            byte[] mensajeEnClaro = cadenaEnClaro.getBytes("UTF-8");

            // 4. Configurar el motor de cifrado (Cipher)
            // Se inicializa en modo ENCRYPT_MODE usando la clave pública
            Cipher cifrado = Cipher.getInstance(ALGORITMO_CLAVE_PUB);
            cifrado.init(Cipher.ENCRYPT_MODE, clavePublica);

            // 5. Ejecutar el cifrado
            byte[] mensajeCifrado = cifrado.doFinal(mensajeEnClaro);

            // 6. Mostrar el resultado codificado en Base64 para su transporte/visualización
            System.out.printf("Texto cifrado codif. en base 64 como texto: \n%s\n",
                    Base64.getEncoder().encodeToString(mensajeCifrado).replaceAll("(.{76})", "$1\n"));

        } catch (NoSuchAlgorithmException e) {
            System.err.printf("ERROR: no existe algoritmo de cifrado %s.\n", ALGORITMO_CLAVE_PUB);
        } catch (InvalidKeySpecException e) {
            System.err.println("ERROR: la clave pública no tiene el formato esperado (X.509).");
        } catch (InvalidKeyException e) {
            System.err.println("ERROR: la clave proporcionada no es válida para RSA.");
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            System.err.println("ERROR: fallo en el proceso de cifrado (relleno o tamaño de bloque).");
        } catch (NoSuchPaddingException e) {
            System.err.println("ERROR: el esquema de relleno (padding) no es válido.");
        } catch (UnsupportedEncodingException e) {
            System.err.println("ERROR: codificación de caracteres UTF-8 no soportada.");
        }
    }
}