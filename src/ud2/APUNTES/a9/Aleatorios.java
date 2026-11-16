package ud2.a9;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Aleatorios {

    private static final int NUM_HILOS = 8;
    private static final int ITERACIONES = 1_000_000;

    // Una única instancia compartida por todos los hilos.
    private static final Random random = new Random();

    // Evita que el resultado calculado por los hilos sea ignorado.
    private static volatile double resultado;

    public static void main(String[] args) throws InterruptedException {

        // Primera prueba: Math.random()
        medir("Math.random()", 1);

        // Segunda prueba: una instancia Random compartida.
        medir("Random compartido", 2);

        // Tercera prueba: ThreadLocalRandom.
        medir("ThreadLocalRandom", 3);
    }

    private static void medir(String nombre, int tipo)
            throws InterruptedException {

        Thread[] hilos = new Thread[NUM_HILOS];

        long inicio = System.nanoTime();

        // Creamos los hilos.
        for (int i = 0; i < NUM_HILOS; i++) {

            hilos[i] = new Thread(() -> {

                double suma = 0;

                // Cada hilo genera un millón de números aleatorios.
                for (int j = 0; j < ITERACIONES; j++) {

                    double numero;

                    if (tipo == 1) {
                        numero = Math.random();

                    } else if (tipo == 2) {
                        numero = random.nextDouble();

                    } else {
                        numero = ThreadLocalRandom.current().nextDouble();
                    }

                    suma += numero;
                }

                resultado = suma;
            });

            hilos[i].start();
        }

        // Esperamos a que terminen todos los hilos.
        for (Thread hilo : hilos) {
            hilo.join();
        }

        long fin = System.nanoTime();

        double segundos = (fin - inicio) / 1_000_000_000.0;

        System.out.printf("%-20s: %.3f segundos%n", nombre, segundos);
    }
}
