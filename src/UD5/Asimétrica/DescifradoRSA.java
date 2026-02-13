package UD5.Asimétrica;

import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.Cipher;

/**
 * Clase encargada de revertir el proceso de cifrado RSA (Descifrado).
 * <p>
 * Esta clase lee una clave privada desde un archivo local y utiliza el algoritmo 
 * RSA para transformar un criptograma (en formato Base64) de vuelta a su 
 * estado original de texto plano.
 * </p>
 * * <b>Nota de seguridad:</b> Esta implementación incluye una fase de saneamiento de 
 * la entrada para evitar errores comunes de <i>Padding</i> al copiar y pegar 
 * desde la consola de comandos.
 * * @author Tu Nombre/Docente
 * @version 1.1
 * @see GeneradorClaves
 * @see CifradoRSA
 */
public class DescifradoRSA {

    /** Algoritmo de cifrado asimétrico utilizado. */
    private static final String ALGORITMO_RSA = "RSA";
    
    /** Nombre del archivo que contiene la clave privada generada previamente. */
    private static final String FICH_CLAVE_PRIVADA = "claveprivada.pem";

    /**
     * Punto de entrada de la aplicación de descifrado.
     * <p>
     * El flujo de ejecución es:
     * <ol>
     * <li>Lectura del texto cifrado desde la entrada estándar (Scanner).</li>
     * <li>Limpieza de caracteres no deseados (espacios, saltos de línea).</li>
     * <li>Carga de la clave privada desde disco.</li>
     * <li>Inicialización del motor {@link javax.crypto.Cipher} en modo descifrado.</li>
     * <li>Conversión de Base64 a binario y ejecución del algoritmo RSA.</li>
     * </ol>
     * </p>
     * * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el texto cifrado (Base64). Pulsa ENTER dos veces para finalizar:");
        
        // StringBuilder para capturar bloques de texto multilínea del terminal
        StringBuilder sb = new StringBuilder();
        while (sc.hasNextLine()) {
            String linea = sc.nextLine();
            // Si la línea está vacía, asumimos que el usuario ha terminado de pegar
            if (linea.isEmpty()) break; 
            sb.append(linea);
        }
        sc.close();

        /* * LIMPIEZA CRÍTICA: 
         * El error "BadPaddingException" suele ocurrir porque el Base64 copiado 
         * contiene espacios o saltos de línea. La regex \\s elimina cualquier 
         * carácter de espacio en blanco.
         */
        String textoCifradoLimpio = sb.toString().replaceAll("\\s", "");

        try (FileInputStream fis = new FileInputStream(FICH_CLAVE_PRIVADA)) {
            // 1. Lectura de los bytes de la clave privada
            byte[] clavePrivadaBytes = fis.readAllBytes();

            // 2. Reconstrucción de la clave privada a partir del formato PKCS#8
            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITMO_RSA);
            PKCS8EncodedKeySpec privadaSpec = new PKCS8EncodedKeySpec(clavePrivadaBytes);
            PrivateKey clavePrivada = keyFactory.generatePrivate(privadaSpec);

            // 3. Conversión de la cadena Base64 (texto) a bytes (binario)
            byte[] bytesCifrados = Base64.getDecoder().decode(textoCifradoLimpio);

            // 4. Configuración del motor de cifrado para la operación de descifrado
            Cipher descifrador = Cipher.getInstance(ALGORITMO_RSA);
            descifrador.init(Cipher.DECRYPT_MODE, clavePrivada);

            // 5. Proceso de descifrado y conversión de bytes a String (UTF-8)
            byte[] mensajeOriginalBytes = descifrador.doFinal(bytesCifrados);
            String mensajeOriginal = new String(mensajeOriginalBytes, "UTF-8");

            System.out.println("\n--- Mensaje Descifrado con Éxito ---");
            System.out.println(mensajeOriginal);

        } catch (Exception e) {
            System.err.println("ERROR: El descifrado falló. Posibles causas:");
            System.err.println("- La clave privada no corresponde a la pública usada para cifrar.");
            System.err.println("- El texto Base64 está incompleto o corrupto.");
            System.err.println("- El formato de relleno (Padding) no coincide.");
            e.printStackTrace();
        }
    }
}