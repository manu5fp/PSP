package UD1.ejemplos;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;

/**
 * Ejemplo de programación paralela en Java usando Fork/Join.
 * Problema: sumar un array enorme dividiéndolo en partes iguales.
 * Con paralelismo: cada core suma su parte; luego se combinan.
 */
public class ParalelismoEjemplo {

    public static void main(String[] args) throws Exception {
        int[] datos = generarDatos(100_000_000); // 100 millones de enteros

        // ── Forma secuencial ──────────────────────────────────
        long inicio = System.currentTimeMillis();
        long sumaSecuencial = 0;
        for (int n : datos) sumaSecuencial += n;
        long tiempoSecuencial = System.currentTimeMillis() - inicio;

        // ── Forma paralela con Streams ────────────────────────
        inicio = System.currentTimeMillis();
        long sumaParalela = Arrays.stream(datos)
                                  .parallel()    // ← activa paralelismo automático
                                  .asLongStream()
                                  .sum();
        long tiempoParalelo = System.currentTimeMillis() - inicio;

        System.out.println("Suma secuencial : " + sumaSecuencial + " → " + tiempoSecuencial + " ms");
        System.out.println("Suma paralela   : " + sumaParalela   + " → " + tiempoParalelo   + " ms");
        System.out.println("Speedup         : " + (double) tiempoSecuencial / tiempoParalelo + "×");

        // ── Pool personalizado ────────────────────────────────
        // Por defecto usa N-1 hilos (N = núcleos CPU disponibles)
        int nucleos = Runtime.getRuntime().availableProcessors();
        System.out.println("Núcleos disponibles: " + nucleos);
        inicio = System.currentTimeMillis();
        ForkJoinPool poolPersonalizado = new ForkJoinPool(nucleos);
        long sumaConPool = poolPersonalizado.submit(
            () -> Arrays.stream(datos).parallel().asLongStream().sum()
        ).get();
        poolPersonalizado.shutdown();
        long tiempoPersonal = System.currentTimeMillis() - inicio;
        System.out.println("Tiempo con pool personalizado: " + tiempoPersonal + "ms");
    }

    private static int[] generarDatos(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = i % 1000; // evitar overflow
        return arr;
    }
}