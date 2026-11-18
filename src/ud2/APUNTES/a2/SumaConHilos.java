package ud2.APUNTES.a2;

/** Reparte una suma entre hilos. join() garantiza que los resultados ya están escritos. */
public class SumaConHilos {
    private static long sumar(long desde, long hasta) {
        long s = 0;
        for (long i = desde; i <= hasta; i++) s += i;
        return s;
    }

    public static void main(String[] args) throws InterruptedException {
        final int HILOS = 4;
        final long LIMITE = 1_000_000;
        long[] parciales = new long[HILOS]; // Cada hilo escribe solo en su propia posición
        Thread[] hilos = new Thread[HILOS];

        for (int i = 0; i < HILOS; i++) {
            final int indice = i;
            final long desde = i * LIMITE / HILOS + 1;
            final long hasta = (i + 1) * LIMITE / HILOS;
            hilos[i] = new Thread(() -> parciales[indice] = sumar(desde, hasta));
            hilos[i].start();
        }

        long total = 0;
        for (int i = 0; i < HILOS; i++) {
            hilos[i].join();
            total += parciales[i];
        }
        System.out.println("Total: " + total + " (esperado " + LIMITE * (LIMITE + 1) / 2 + ")");
    }
}