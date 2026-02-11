package UD5.Simetrica;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

/**
 * Genera una clave secreta para cifrado simétrico usando el algoritmo 3DES
 * y la guarda en un fichero binario.
 * <p>
 * La clave generada se almacena en el fichero {@code clave.raw}, que será
 * utilizado posteriormente por los programas de cifrado y descifrado.
 * </p>
 *
 * <p>
 * Este programa tiene fines educativos y muestra el proceso completo de
 * generación segura de claves criptográficas en Java.
 * </p>
 *
 * @author manu
 * @version 1.0
 */
public class GeneraClaveSimDES {

    /**
     * Algoritmo de cifrado simétrico utilizado: Triple DES (3DES).
     */
    private static final String ALGORITMO_CLAVE_SIMETRICA = "DESede";

    /**
     * Algoritmo para la generación de números aleatorios criptográficamente seguros.
     */
    private static final String ALGORITMO_GEN_NUM_ALEAT = "SHA1PRNG";

    /**
     * Nombre del fichero donde se almacenará la clave generada.
     */
    private static final String NOM_FICH_CLAVE = "clave.raw";

    /**
     * Método principal que genera la clave secreta y la guarda en un fichero.
     *
     * @param args argumentos de la línea de comandos (no utilizados)
     */
    public static void main(String[] args) {

        try {
            /* ============================
             * 1. CREACIÓN DEL GENERADOR DE CLAVES
             * ============================ */

            // Se obtiene un generador de claves para el algoritmo 3DES
            KeyGenerator genClaves = KeyGenerator.getInstance(ALGORITMO_CLAVE_SIMETRICA);

            // Se obtiene un generador de números aleatorios seguro
            SecureRandom srand = SecureRandom.getInstance(ALGORITMO_GEN_NUM_ALEAT);

            // Se inicializa el generador de claves usando aleatoriedad segura
            genClaves.init(srand);

            /* ============================
             * 2. GENERACIÓN DE LA CLAVE SECRETA
             * ============================ */

            // Se genera la clave secreta
            SecretKey clave = genClaves.generateKey();

            // Muestra el formato de la clave
            System.out.printf("Formato de clave: %s%n", clave.getFormat());

            /* ============================
             * 3. OBTENCIÓN DEL VALOR REAL DE LA CLAVE
             * ============================ */

            /*
             * La SecretKeyFactory se utiliza para convertir una clave secreta
             * genérica en una especificación concreta que permite extraer
             * su valor real en bytes.
             */
            SecretKeyFactory keySpecFactory =
                    SecretKeyFactory.getInstance(ALGORITMO_CLAVE_SIMETRICA);

            DESedeKeySpec keySpec =
                    (DESedeKeySpec) keySpecFactory.getKeySpec(clave, DESedeKeySpec.class);

            byte[] valorClave = keySpec.getKey();

            System.out.printf("Longitud de clave: %d bits%n", valorClave.length * 8);
            System.out.printf("Valor de la clave (hex): [%s]%n", valorHexadecimal(valorClave));

            /* ============================
             * 4. ALMACENAMIENTO DE LA CLAVE EN FICHERO
             * ============================ */

            try (FileOutputStream fos = new FileOutputStream(NOM_FICH_CLAVE)) {
                fos.write(valorClave);
                System.out.printf("Clave guardada correctamente en el fichero %s%n", NOM_FICH_CLAVE);
            }

        } catch (NoSuchAlgorithmException e) {
            System.out.println("Algoritmo de generación de claves no soportado.");
        } catch (InvalidKeySpecException e) {
            System.out.println("Error en la especificación de la clave.");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Error de E/S escribiendo la clave en fichero.");
            e.printStackTrace();
        }
    }

    /**
     * Convierte un array de bytes en su representación hexadecimal.
     *
     * @param bytes array de bytes a transformar.
     * @return cadena hexadecimal equivalente.
     */
    static String valorHexadecimal(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte b : bytes) {
            result.append(String.format("%02x", b));
        }
        return result.toString();
    }
}
