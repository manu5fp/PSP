package ud2.a9;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PoolHilos {

    public static void main(String[] args) throws InterruptedException {

        // Creamos un pool con 3 hilos.
        ExecutorService pool = Executors.newFixedThreadPool(3);

        // Enviamos 10 tareas al pool.
        for (int i = 1; i <= 10; i++) {

            final int numero = i;

            pool.execute(() -> {

                System.out.println(
                        "Tarea " + numero
                        + " ejecutada por "
                        + Thread.currentThread().getName()
                );

                try {
                    // Simulamos el tiempo necesario para realizar la tarea.
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        // Indicamos que no se enviarán más tareas.
        pool.shutdown();

        System.out.println("Tareas enviadas. Esperando su finalización...");

        // Esperamos a que terminen todas las tareas.
        pool.awaitTermination(15, java.util.concurrent.TimeUnit.SECONDS);

        System.out.println("Todas las tareas han terminado.");
    }
}
