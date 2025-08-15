package UD1.ejemplos;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * La clase Info muestra información del sistema y de un proceso lanzado mediante ProcessBuilder.
 * 
 * Funcionalidades:
 *  - Obtiene el número de procesadores disponibles.
 *  - Configura un ProcessBuilder para ejecutar "java -version".
 *  - Redirige la salida de error hacia la salida estándar.
 *  - Guarda toda la salida del proceso en un fichero.
 *  - Muestra el comando y argumentos que se van a ejecutar.
 *  - Lista todas las variables de entorno del sistema.
 *  
 * @author manu
 */
public class Info {

    public static void main(String[] args) throws IOException {

        // ------------------------------------------------------------------
        // 1. Mostrar información sobre el número de procesadores disponibles
        // ------------------------------------------------------------------
        System.out.println("Procesadores: " + Runtime.getRuntime().availableProcessors());

        // ------------------------------------------------------------------
        // 2. Crear el ProcessBuilder para ejecutar el comando "java -version"
        // ------------------------------------------------------------------
        // IMPORTANTE: aquí no se ejecuta todavía, solo se configura.
        ProcessBuilder processBuilder = new ProcessBuilder("java", "-version");
        
        // ------------------------------------------------------------------
        // 3. Redirigir la salida de error (stderr) hacia la salida estándar (stdout)
        // ------------------------------------------------------------------
        // Esto es útil porque el comando "java -version" escribe en stderr por defecto.
        processBuilder.redirectErrorStream(true);

        // ------------------------------------------------------------------
        // 4. Guardar la salida del proceso en un archivo
        // ------------------------------------------------------------------
        File log = new File("java-version.log");
        processBuilder.redirectOutput(log);

        // ------------------------------------------------------------------
        // 5. Mostrar el comando y sus argumentos
        // ------------------------------------------------------------------
        System.out.println("\nProceso y argumentos:");
        List<String> command = processBuilder.command(); // Lista de argumentos que ejecutará el proceso
        for (String arg : command) {
            System.out.println(arg);
        }

        // ------------------------------------------------------------------
        // 6. Mostrar las variables de entorno
        // ------------------------------------------------------------------
        // processBuilder.environment() devuelve un Map con todas las variables
        System.out.println("\nVariables de entorno:");
        Map<String, String> variablesEntorno = processBuilder.environment();
        for (String key : variablesEntorno.keySet()) {
            System.out.println("Clave: " + key + " -> Valor: " + variablesEntorno.get(key));
        }

        // ------------------------------------------------------------------
        // 7. Lanzar el proceso con la configuración indicada
        // ------------------------------------------------------------------
        // Aquí se ejecuta realmente el comando "java -version".
        Process process = processBuilder.start();

        // El resultado estará en el archivo "java-version.log"
    }
}
