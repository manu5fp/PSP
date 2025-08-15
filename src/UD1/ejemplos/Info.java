package UD1.ejemplos;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * La clase Info demuestra el uso de {@link ProcessBuilder}
 * para ejecutar un proceso externo (en este caso {@code java -version}),
 * redirigir su salida a un fichero y mostrar información de su configuración.
 * 
 * <p>Este ejemplo muestra:</p>
 * <ul>
 *   <li>Cómo redirigir la salida estándar y de error a un archivo.</li>
 *   <li>Cómo acceder y mostrar las variables de entorno del proceso.</li>
 *   <li>Cómo ver el comando y argumentos que se ejecutarán.</li>
 * </ul>
 * 
 * Ejemplo de ejecución:
 * <pre>
 *     java UD1.ejemplos.Info
 * </pre>
 * 
 * Al ejecutarlo, crea un archivo {@code java-version.log} con la salida
 * del comando y muestra información en consola.
 * 
 * @author manu
 */
public class Info {

    /**
     * Punto de entrada del programa. Configura un {@link ProcessBuilder}
     * para ejecutar {@code java -version}, redirige la salida a un archivo
     * y muestra información sobre variables de entorno y comando.
     * 
     * @param args No se utilizan argumentos en este ejemplo.
     * @throws IOException Si ocurre un error al iniciar el proceso o al acceder al archivo.
     */
    public static void main(String[] args) throws IOException {
        // Crear ProcessBuilder para ejecutar el comando "java -version"
        ProcessBuilder processBuilder = new ProcessBuilder("java", "-version");

        // Unir la salida de error a la de salida estándar
        processBuilder.redirectErrorStream(true);

        // Redirigir la salida del proceso al archivo "java-version.log"
        File log = new File("java-version.log");
        processBuilder.redirectOutput(log);
        
        // Mostrar las variables de entorno del proceso
        System.out.println("Variables de entorno:");
        Map variablesEntorno = processBuilder.environment();
        Iterator it = variablesEntorno.keySet().iterator();
        while (it.hasNext()) {
            String key = (String) it.next();
            System.out.println("Clave: " + key + " -> Valor: " + variablesEntorno.get(key));
        }

        // Mostrar el comando y argumentos del proceso
        System.out.println("\nProceso y argumentos:");
        List command = processBuilder.command();
        Iterator iter = command.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

        // Iniciar el proceso
        Process process = processBuilder.start();
    }
}
