package UD5.Asimétrica;

import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * Clase encargada de generar y persistir un par de claves (pública y privada)
 * utilizando el algoritmo RSA.
 * * <p>Esta clase demuestra el flujo de trabajo básico del cifrado asimétrico:</p>
 * <ol>
 * <li>Generación de números aleatorios seguros.</li>
 * <li>Inicialización del generador de pares de claves.</li>
 * <li>Exportación de claves en formatos estándar (X.509 y PKCS#8).</li>
 * </ol>
 * * @author Tu Nombre/Docente
 * @version 1.0
 */
public class GeneradorClaves {

    /** Algoritmo de cifrado asimétrico a utilizar. */
    private static final String ALGORITMO_CLAVE_PUBLICA = "RSA";
    
    /** Tamaño de la clave en bits. 1024 es educativo, se recomienda 2048+ para producción. */
    private static final int TAM_CLAVE = 1024;
    
    /** Generador de números aleatorios criptográficamente fuerte. */
    private static SecureRandom algSeguroGenNumAleat;
    
    /** Nombre del archivo donde se almacenará la clave pública. */
    private static final String NOM_FICH_CLAVE_PUBLICA = "clavepublica.pem";
    
    /** Nombre del archivo donde se almacenará la clave privada. */
    private static final String NOM_FICH_CLAVE_PRIVADA = "claveprivada.pem";

    /**
     * Punto de entrada principal que ejecuta el proceso de generación y guardado de claves.
     * * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        try {
            // 1. Configurar la fuente de aleatoriedad segura
            algSeguroGenNumAleat = SecureRandom.getInstanceStrong();

            // 2. Inicializar el generador de pares de claves para RSA
            KeyPairGenerator genParClaves = KeyPairGenerator.getInstance(ALGORITMO_CLAVE_PUBLICA);
            genParClaves.initialize(TAM_CLAVE, algSeguroGenNumAleat);

            // 3. Generar el par de claves
            KeyPair parClaves = genParClaves.generateKeyPair();
            PublicKey clavePublica = parClaves.getPublic();
            PrivateKey clavePrivada = parClaves.getPrivate();

            // 4. Guardar y exportar la Clave Pública
            // Se usa X.509, el estándar para certificados y claves públicas
            try (FileOutputStream fosClavePublica = new FileOutputStream(NOM_FICH_CLAVE_PUBLICA)) {
                X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(clavePublica.getEncoded());
                fosClavePublica.write(x509EncodedKeySpec.getEncoded());
                
                System.out.printf("Clave pública guardada en formato %s en fichero %s:\n%s\n", 
                        x509EncodedKeySpec.getFormat(), 
                        NOM_FICH_CLAVE_PUBLICA,
                        formatBase64(x509EncodedKeySpec.getEncoded()));
            } catch (IOException e) {
                System.err.println("Error de E/S escribiendo clave pública");
                throw e;
            }

            // 5. Guardar y exportar la Clave Privada
            // Se usa PKCS#8, el estándar para almacenamiento de claves privadas
            try (FileOutputStream fosClavePrivada = new FileOutputStream(NOM_FICH_CLAVE_PRIVADA)) {
                PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(clavePrivada.getEncoded());
                fosClavePrivada.write(pkcs8EncodedKeySpec.getEncoded());
                
                System.out.printf("Clave privada guardada en formato %s en fichero %s: \n%s\n",
                        pkcs8EncodedKeySpec.getFormat(), 
                        NOM_FICH_CLAVE_PRIVADA,
                        formatBase64(pkcs8EncodedKeySpec.getEncoded()));
            } catch (IOException e) {
                System.err.println("Error de E/S escribiendo clave privada");
                throw e;
            }

        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error: Algoritmo de generación de claves no soportado en este entorno.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Método auxiliar para formatear los bytes en Base64 con saltos de línea.
     * * @param bytes Array de bytes a codificar.
     * @return String codificado en Base64.
     */
    private static String formatBase64(byte[] bytes) {
        return Base64.getEncoder().encodeToString(bytes).replaceAll("(.{76})", "$1\n");
    }
}