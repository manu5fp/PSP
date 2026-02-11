package UD5.Simétrica;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.spec.InvalidKeySpecException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.NoSuchPaddingException;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;

/**
 * Ejemplo completo de cifrado y descifrado de ficheros usando streams
 * cifrados en Java.
 * <p>
 * Este programa:
 * <ul>
 *   <li>Lee una clave secreta desde un fichero binario.</li>
 *   <li>Cifra un fichero usando {@link CipherOutputStream}.</li>
 *   <li>Descifra el fichero generado usando {@link CipherInputStream}.</li>
 * </ul>
 * </p>
 *
 * <p>
 * Permite comprender cómo integrar el cifrado directamente en flujos de
 * entrada/salida, de forma totalmente transparente para el programador.
 * </p>
 *
 * <p>
 * Este enfoque es especialmente útil para cifrar comunicaciones en red
 * usando sockets.
 * </p>
 *
 * @author manu
 * @version 1.0
 */
public class CifradoDescifradoConStreams {

    /**
     * Algoritmo de cifrado simétrico utilizado.
     * <p>
     * Puede cambiarse fácilmente por otros algoritmos compatibles como
     * {@code DESede} (Triple DES) o {@code AES}.
     * </p>
     */
    private static final String ALGORITMO_CLAVE_SIMETRICA = "DES"; // "DES" → "DESede" → "AES"

    /**
     * Nombre del fichero que contiene la clave secreta.
     */
    private static final String NOM_FICH_CLAVE = "clave.raw";

    /**
     * Método principal del programa.
     * <p>
     * Realiza el cifrado y descifrado completo de un fichero usando streams
     * cifrados.
     * </p>
     *
     * @param args argumentos de línea de comandos (no utilizados en este ejemplo)
     */
    public static void main(String[] args) {

        String nomFich = "msg.txt";
        String nomFichEncriptado = nomFich + ".encript";
        String nomFichDesencriptado = nomFich + ".desencript";

        byte[] valorClave;

        /* ===============================
         * 1. LECTURA DE LA CLAVE SECRETA
         * =============================== */
        try (FileInputStream fisClave = new FileInputStream(NOM_FICH_CLAVE)) {
            valorClave = fisClave.readAllBytes();
        } catch (IOException e) {
            System.out.printf("ERROR leyendo el fichero de clave %s%n", NOM_FICH_CLAVE);
            return;
        }

        try {

            /* ===============================
             * 2. CREACIÓN DE LA CLAVE Y CIFRADOR
             * =============================== */

            SecretKeySpec keySpec =
                    new SecretKeySpec(valorClave, ALGORITMO_CLAVE_SIMETRICA);

            SecretKeyFactory keyFactory =
                    SecretKeyFactory.getInstance(ALGORITMO_CLAVE_SIMETRICA);

            SecretKey clave = keyFactory.generateSecret(keySpec);

            Cipher cifrado = Cipher.getInstance(ALGORITMO_CLAVE_SIMETRICA);

            /* ===============================
             * 3. CIFRADO DEL FICHERO
             * =============================== */

            cifrado.init(Cipher.ENCRYPT_MODE, clave);

            try (FileInputStream fis = new FileInputStream(nomFich);
                 BufferedInputStream is = new BufferedInputStream(fis);
                 FileOutputStream fos = new FileOutputStream(nomFichEncriptado);
                 BufferedOutputStream os = new BufferedOutputStream(fos);
                 CipherOutputStream cos = new CipherOutputStream(os, cifrado)) {

                int b;
                while ((b = is.read()) != -1) {
                    cos.write(b);
                }
            }

            System.out.printf("Fichero cifrado %s generado a partir de %s.%n",
                    nomFichEncriptado, nomFich);

            /* ===============================
             * 4. DESCIFRADO DEL FICHERO
             * =============================== */

            cifrado.init(Cipher.DECRYPT_MODE, clave);

            try (FileInputStream fis = new FileInputStream(nomFichEncriptado);
                 BufferedInputStream is = new BufferedInputStream(fis);
                 CipherInputStream cis = new CipherInputStream(is, cifrado);
                 FileOutputStream fos = new FileOutputStream(nomFichDesencriptado);
                 BufferedOutputStream bos = new BufferedOutputStream(fos)) {

                int b;
                while ((b = cis.read()) != -1) {
                    bos.write(b);
                }
            }

            System.out.printf("Fichero descifrado %s generado a partir de %s.%n",
                    nomFichDesencriptado, nomFichEncriptado);

        } catch (NoSuchAlgorithmException e) {
            System.out.printf("No existe el algoritmo de cifrado %s.%n",
                    ALGORITMO_CLAVE_SIMETRICA);
        } catch (NoSuchPaddingException e) {
            System.out.println("Error en el esquema de relleno.");
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            System.out.println("Especificación de clave no válida.");
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            System.out.println("Clave no válida.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("ERROR de E/S durante el proceso.");
            e.printStackTrace();
        }
    }
}

