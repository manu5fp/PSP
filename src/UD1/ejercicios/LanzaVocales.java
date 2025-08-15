package UD1.ejercicios;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * La clase LanzaVocales ejecuta el programa {@code Vocales.Contador}
 * como un proceso independiente para contar de forma paralela
 * las apariciones de cada vocal en un archivo de texto.
 * 
 * <p>El funcionamiento es:</p>
 * <ol>
 *   <li>Define las vocales a buscar (A, E, I, O, U).</li>
 *   <li>Para cada vocal, lanza un proceso Java que ejecuta
 *       {@code Vocales.Contador} pasándole:
 *       <ul>
 *         <li>El archivo de entrada (hobbit.txt)</li>
 *         <li>La vocal a buscar</li>
 *         <li>El archivo de salida donde se guardará el conteo</li>
 *       </ul>
 *   </li>
 *   <li>Espera un tiempo para que todos los procesos terminen.</li>
 *   <li>Lee los resultados de cada archivo de salida y calcula el total de vocales.</li>
 * </ol>
 * 
 * <p>Este patrón es útil para tareas que pueden dividirse en partes independientes
 * y procesarse en paralelo, distribuyendo la carga entre procesos.</p>
 * 
 * Ejemplo de ejecución:
 * <pre>
 *   java LanzaVocales
 * </pre>
 * 
 * @author manu
 */
public class LanzaVocales {

    /**
     * Método principal. Lanza un proceso independiente para contar cada vocal
     * en el archivo hobbit.txt, recoge los resultados y muestra el total.
     *
     * @param args No se utilizan en este ejemplo.
     * @throws IOException Si ocurre un error de entrada/salida al leer resultados.
     * @throws InterruptedException Si la espera es interrumpida.
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        String[] vocales = { "A", "E", "I", "O", "U" };
        String directoryName = System.getProperty("user.dir") + "/bin";
        String clase = "Vocales.Contador";
        ProcessBuilder pb;

        try {
            // Lanzar un proceso por cada vocal
            for (String vocal : vocales) {
                pb = new ProcessBuilder(
                        "java",
                        clase,
                        System.getProperty("user.dir") + "/hobbit.txt",
                        vocal,
                        System.getProperty("user.dir") + "/out/" + vocal + ".txt"
                );

                // Redirigir errores a un archivo
                pb.redirectError(new File("errores.txt"));

                // Directorio de trabajo donde están las clases compiladas
                pb.directory(new File(directoryName));

                // Iniciar el proceso
                pb.start();
            }

            // Esperar a que todos los procesos finalicen (método simple: sleep)
            Thread.sleep(3000);

            // Leer resultados y calcular total
            int total = 0;
            for (String vocal : vocales) {
                int cuenta = new Scanner(new File("out/" + vocal + ".txt")).nextInt();
                System.out.println(vocal + ": " + cuenta);
                total += cuenta;
            }

            System.out.println("Total de vocales: " + total);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
