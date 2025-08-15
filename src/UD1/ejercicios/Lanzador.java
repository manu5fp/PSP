package UD1.ejercicios;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * La clase Lanzador ejecuta de forma independiente la clase {@code UD1.Sumador}
 * como un proceso externo varias veces, cada vez con un rango de números distinto,
 * y combina los resultados parciales en un total.
 * 
 * <p>El funcionamiento es:</p>
 * <ol>
 *   <li>Divide un rango en varios subrangos.</li>
 *   <li>Lanza un proceso Java independiente por cada subrango,
 *       pasándole como argumentos los límites del rango.</li>
 *   <li>Redirige la salida de cada proceso a un archivo.</li>
 *   <li>Espera un tiempo para que los procesos finalicen.</li>
 *   <li>Lee los resultados parciales de los archivos y calcula la suma total.</li>
 * </ol>
 * 
 * <p>Este patrón se puede aplicar para distribuir trabajo en paralelo
 * entre varios procesos.</p>
 * 
 * @author
 */
public class Lanzador {

    /**
     * Lanza un proceso Java que ejecuta la clase {@code UD1.Sumador}
     * con los parámetros indicados.
     *
     * @param n1           Límite inferior del rango de suma.
     * @param n2           Límite superior del rango de suma.
     * @param fichResultado Nombre del archivo donde se guardará el resultado.
     */
    public void lanzarSumador(Long n1, Long n2, String fichResultado) {
        // Directorio donde se encuentran las clases compiladas
        String directoryName = System.getProperty("user.dir") + "/bin";
        String clase = "UD1.Sumador";
        ProcessBuilder pb;

        try {
            // Construir el proceso: java UD1.Sumador n1 n2
            pb = new ProcessBuilder("java", clase, n1.toString(), n2.toString());

            // Redirigir la salida de error a un archivo
            pb.redirectError(new File("TXT/errores.txt"));

            // Redirigir la salida estándar a un archivo específico
            pb.redirectOutput(new File("TXT/" + fichResultado));

            // Establecer el directorio de trabajo
            pb.directory(new File(directoryName));

            // Iniciar el proceso
            pb.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Método principal que divide un rango de números en 4 subrangos,
     * ejecuta {@link #lanzarSumador(Long, Long, String)} para cada uno
     * y combina los resultados en una suma total.
     *
     * @param args No se utilizan argumentos en este ejemplo.
     * @throws InterruptedException Si ocurre una interrupción durante la espera.
     * @throws IOException Si ocurre un error al leer los archivos de resultados.
     */
    public static void main(String[] args) throws InterruptedException, IOException {
        Lanzador l = new Lanzador();

        long ini = 1;
        // Lanzar 4 procesos para rangos de 25 números
        for (int i = 1; i <= 4; i++) {
            l.lanzarSumador(ini, ini + 24, "result" + i + ".txt");
            ini += 25;
        }

        // Esperar 2 segundos para que finalicen los procesos
        Thread.sleep(2000);

        // Leer los resultados parciales y sumarlos
        int sumaTotal = 0;
        for (int i = 0; i < 4; i++) {
            sumaTotal += new Scanner(new File("TXT/result" + (i + 1) + ".txt")).nextInt();
        }

        System.out.println("Suma total: " + sumaTotal);
    }
}
