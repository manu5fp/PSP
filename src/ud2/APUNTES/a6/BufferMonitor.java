package ud2.a6;


import java.util.Vector;

/**
 * Ejemplo productor-consumidor utilizando wait() y notifyAll().
 */
public class BufferMonitor {

    // Búfer compartido por todos los hilos.
    static class Buffer {

        private Vector<Integer> datos = new Vector<>();
        private int capacidad;

        public Buffer(int capacidad) {
            this.capacidad = capacidad;
        }

        // Introduce un dato. Si el búfer está lleno, espera.
        public synchronized void poner(int dato)
                throws InterruptedException {

            while (datos.size() == capacidad) {
                wait();
            }

            datos.add(dato);

            // Avisamos a los consumidores.
            notifyAll();
        }

        // Extrae un dato. Si el búfer está vacío, espera.
        public synchronized int sacar()
                throws InterruptedException {

            while (datos.isEmpty()) {
                wait();
            }

            int dato = datos.remove(0);

            // Avisamos a los productores.
            notifyAll();

            return dato;
        }

        public synchronized int ocupacion() {
            return datos.size();
        }
    }


    public static void main(String[] args) throws InterruptedException {

        final int NUM_PRODUCTORES = 3;
        final int NUM_CONSUMIDORES = 3;
        final int ELEMENTOS = 20_000;

        // Creamos el búfer compartido.
        Buffer buffer = new Buffer(10);

        // Almacenamos los hilos.
        Thread[] hilos = new Thread[
                NUM_PRODUCTORES + NUM_CONSUMIDORES];

        // Cada consumidor acumula su propia suma.
        long[] sumas = new long[NUM_CONSUMIDORES];


        // -------------------------------------------------
        // PRODUCTORES
        // -------------------------------------------------

        for (int i = 0; i < NUM_PRODUCTORES; i++) {

            hilos[i] = new Thread(() -> {

                try {
                    for (int n = 1; n <= ELEMENTOS; n++) {
                        buffer.poner(n);
                    }

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }


        // -------------------------------------------------
        // CONSUMIDORES
        // -------------------------------------------------

        for (int i = 0; i < NUM_CONSUMIDORES; i++) {

            final int id = i;

            hilos[NUM_PRODUCTORES + i] = new Thread(() -> {

                try {
                    for (int n = 0; n < ELEMENTOS; n++) {

                        int dato = buffer.sacar();

                        sumas[id] += dato;
                    }

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }


        // -------------------------------------------------
        // INICIO Y ESPERA DE LOS HILOS
        // -------------------------------------------------

        for (Thread hilo : hilos) {
            hilo.start();
        }

        for (Thread hilo : hilos) {
            hilo.join();
        }


        // -------------------------------------------------
        // COMPROBACIÓN DE RESULTADOS
        // -------------------------------------------------

        long total = 0;

        for (int i = 0; i < NUM_CONSUMIDORES; i++) {
            total += sumas[i];
        }

        long esperado = NUM_PRODUCTORES
                * (long) ELEMENTOS * (ELEMENTOS + 1) / 2;

        System.out.println("Suma consumida: " + total);
        System.out.println("Suma esperada:  " + esperado);
        System.out.println("Elementos restantes: " + buffer.ocupacion());
    }
}
