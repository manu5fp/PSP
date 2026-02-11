package UD5.Simetrica;

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
 * Cifra un fichero usando cifrado simétrico DES.
 * <p>
 * Este algoritmo está actualmente obsoleto y su uso se limita exclusivamente
 * a fines didácticos para comprender los fundamentos del cifrado simétrico.
 * </p>
 *
 * @author manu
 * @version 1.0
 */
public class CifradoDES {

    /**
     * Algoritmo de cifrado simétrico: DES.
     */
    private static final String ALGORITMO_CLAVE_SIMETRICA = "DES";

    /**
     * Nombre del fichero que contiene la clave secreta.
     */
    private static final String NOM_FICH_CLAVE = "clave.raw";

    /**
     * Método principal que cifra un fichero usando DES.
     *
     * @param args argumentos de línea de comandos (no utilizados)
     */
    public static void main(String[] args) {

        String nomFich = "msg.txt";
        byte[] valorClave;

        /* ===============================
         * LECTURA DE LA CLAVE SECRETA
         * =============================== */
        try (FileInputStream fisClave = new FileInputStream(NOM_FICH_CLAVE)) {
            valorClave = fisClave.readAllBytes();
        } catch (IOException e) {
            System.out.printf("ERROR leyendo el fichero de clave %s%n", NOM_FICH_CLAVE);
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
