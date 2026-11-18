package ud2.APUNTES.a7;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Semáforo contador: limita a N los hilos que pueden
 * utilizar un recurso al mismo tiempo.
 */
public class Aparcamiento {

    // Objeto utilizado para sincronizar los mensajes.
    private static final Object CERROJO = new Object();

    public static void main(String[] args) throws InterruptedException {

        final int PLAZAS = 3;

        Semaphore plazas = new Semaphore(PLAZAS, true);

        AtomicInteger dentro = new AtomicInteger();

        Thread[] coches = new Thread[12];

        for (int i = 0; i < coches.length; i++) {

            coches[i] = new Thread(() -> {

                try {
                    // El coche espera hasta conseguir una plaza.
                    plazas.acquire();

                    try {

                        synchronized (CERROJO) {
                            // El coche entra en el aparcamiento.
                            dentro.incrementAndGet();
                            
                            // Mensaje de entrada sincronizado.
                            System.out.println(
                                    Thread.currentThread().getName()
                                    + " aparca. Dentro: " + dentro.get()
                                    + " | Esperando: " + plazas.getQueueLength()
                            );
                        }

                        // Simulamos el tiempo que permanece aparcado.
                        Thread.sleep(30);

                        
                        synchronized (CERROJO) {
                            // El coche abandona el aparcamiento.
                            dentro.decrementAndGet();
                            // Mensaje de salida sincronizado.
                        	System.out.println(
                                    Thread.currentThread().getName()
                                    + " sale. Dentro: " + dentro.get()
                                    + " | Esperando: " + plazas.getQueueLength()
                            );
                        }

                    } finally {
                        // El coche libera la plaza.
                        plazas.release();
                    }

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }

            }, "Coche-" + (i + 1));

            coches[i].start();
        }

        // Esperamos a que terminen todos los coches.
        for (Thread coche : coches) {
            coche.join();
        }

        System.out.println("\n--- RESULTADO FINAL ---");

        System.out.println(
                "Permisos disponibles al final: "
                + plazas.availablePermits()
        );
    }
}

