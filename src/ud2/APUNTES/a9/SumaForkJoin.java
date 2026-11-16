package ud2.a9;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class SumaForkJoin extends RecursiveTask<Double> {
    /** Tiempo inicial en nanosegundos */
    private static long tini;
    /** Tiempo total transcurrido en nanosegundos */
    private static long ttot;
    // Si el fragmento tiene como máximo 200.000 elementos,
    // se resuelve directamente, sin seguir dividiéndolo.
    private static final int UMBRAL = 200000;

    private int[] datos;
    private int inicio;
    private int fin;

    public SumaForkJoin(int[] datos, int inicio, int fin) {
        this.datos = datos;
        this.inicio = inicio;
        this.fin = fin;
    }

    @Override
    protected Double compute() {

        // CASO BASE: el fragmento es suficientemente pequeño.
        // Sumamos sus elementos directamente.
        if (fin - inicio <= UMBRAL) {

            double suma = 0;

            for (int i = inicio; i < fin; i++) {
                suma += Math.sqrt(datos[i]) + Math.cbrt(datos[i]);
            }

            return suma;
        }

        // CASO RECURSIVO: dividimos el fragmento en dos mitades.
        int medio = (inicio + fin) / 2;

        SumaForkJoin tareaIzquierda =
                new SumaForkJoin(datos, inicio, medio);

        SumaForkJoin tareaDerecha =
                new SumaForkJoin(datos, medio, fin);

        // Enviamos la primera mitad para que pueda ejecutarse
        // en paralelo en otro hilo del pool.
        tareaIzquierda.fork();

        // El hilo actual calcula la segunda mitad.
        double sumaDerecha = tareaDerecha.compute();

        // Esperamos a que termine la primera mitad y recogemos
        // su resultado.
        double sumaIzquierda = tareaIzquierda.join();

        // Combinamos los resultados de las dos mitades.
        return sumaIzquierda + sumaDerecha;
    }

    public static void main(String[] args) {

        // Creamos un vector de 25 millones de elementos.
        int[] datos = new int[25000000];

        // Rellenamos el vector con el valor 2.
        Arrays.fill(datos, 2);

        // Creamos el pool de Fork/Join.
        ForkJoinPool pool = new ForkJoinPool();

        // Ejecutamos la tarea principal y obtenemos el resultado.
        SumaForkJoin tareaPrincipal =
                new SumaForkJoin(datos, 0, datos.length);
        
        start();
        double resultado = pool.invoke(tareaPrincipal);

        System.out.println("Suma: " + resultado);
        System.out.println(stop());
        // Cerramos el pool.
        pool.shutdown();
      
        resultado = 0;
        
        start();
        for (int i = 0; i < datos.length; i++) {
        	resultado += Math.sqrt(datos[i]) + Math.cbrt(datos[i]);
		}
        System.out.println("Suma: " + resultado);
        System.out.println(stop());
        
    }
    public static void start() {
        tini = System.nanoTime();
    }

    /**
     * Detiene la medición y devuelve el tiempo transcurrido
     * en formato legible (nanosegundos, milisegundos o segundos).
     * 
     * @return Cadena con el tiempo transcurrido y la unidad correspondiente.
     */
    public static String stop() {
        ttot = System.nanoTime() - tini;
        String msg = "";

        if (ttot > 1_000_000) { // Más de 1 ms
            if ((ttot / 1_000_000) > 1000) {
                // Más de 1 segundo
                msg += ((ttot / 1_000_000.0) / 1000) + " segundos.";
            } else {
                // Entre 1 ms y 1000 ms
                msg += (ttot / 1_000_000.0) + " milisegundos.";
            }
        } else {
            // Menos de 1 ms
            msg += ttot + " nanosegundos.";
        }

        return msg;
    }
}
