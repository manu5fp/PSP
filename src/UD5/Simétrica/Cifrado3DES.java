package UD5.Simétrica; 

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.SecretKeySpec;

/**
 * Cifra un fichero usando cifrado simétrico Triple DES (3DES).
 * <p>
 * Lee una clave secreta desde el fichero {@code clave.raw} y cifra un fichero
 * de entrada, generando un nuevo fichero cifrado con extensión {@code .encript}.
 * </p>
 *
 * <p>
 * Este programa tiene fines didácticos y permite comprender el funcionamiento
 * del cifrado simétrico por bloques utilizando la API criptográfica de Java.
 * </p>
 *
 * @author manu
 * @version 1.0
 */
public class Cifrado3DES {

    /**
     * Algoritmo de cifrado simétrico: Triple DES.
     */
    private static final String ALGORITMO_CLAVE_SIMETRICA = "DESede";

    /**
     * Nombre del fichero que contiene la clave secreta.
     */
    private static final String FICH_CLAVE = "clave.raw";

    /**
     * Método principal que cifra el fichero indicado.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {

        String nomFich = "msg.txt";
        byte[] valorClave;

        /* ===============================
         * LECTURA DE LA CLAVE SECRETA
         * =============================== */
        try (FileInputStream fisClave = new FileInputStream(FICH_CLAVE)) {
            valorClave = fisClave.readAllBytes();
        } catch (IOException e) {
            System.out.printf("ERROR leyendo el fichero de clave %s%n", FICH_CLAVE);
            return;
        }

        /* ===============================
         * CONFIGURACIÓN DEL CIFRADOR
         * =============================== */
        try {
            SecretKeySpec keySpec =
                    new SecretKeySpec(valorClave, ALGORITMO_CLAVE_SIMETRICA);

            SecretKeyFactory keyFactory =
                    SecretKeyFactory.getInstance(ALGORITMO_CLAVE_SIMETRICA);

            SecretKey clave = keyFactory.generateSecret(keySpec);

            Cipher cifrado = Cipher.getInstance(ALGORITMO_CLAVE_SIMETRICA);
            cifrado.init(Cipher.ENCRYPT_MODE, clave);

            /* ===============================
             * CIFRADO DEL FICHERO
             * =============================== */
            try (FileInputStream fis = new FileInputStream(nomFich);
                 BufferedInputStream is = new BufferedInputStream(fis);
                 FileOutputStream fos = new FileOutputStream(nomFich + ".encript");
                 BufferedOutputStream os = new BufferedOutputStream(fos)) {

                byte[] buff = new byte[cifrado.getBlockSize()];
                int leidos;

                while ((leidos = is.read(buff)) != -1) {
                    os.write(cifrado.update(buff, 0, leidos));
                }

                os.write(cifrado.doFinal());
            }

        } catch (Exception e) {
            System.out.println("Error durante el proceso de cifrado.");
            e.printStackTrace();
        }
    }
}
