package ud2.a9;


import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class Coordinacion {

    public static void main(String[] args) throws InterruptedException {

        final int CORREDORES = 3;

        // El hilo principal esperará a que terminen los tres corredores.
        CountDownLatch llegada = new CountDownLatch(CORREDORES);

        // Los corredores deben reunirse antes de comenzar cada etapa.
        CyclicBarrier barrera = new CyclicBarrier(CORREDORES, () -> {
            System.out.println("Todos han llegado. ¡Comienza la siguiente etapa!\n");
        });

        // Creamos los corredores.
        for (int i = 1; i <= CORREDORES; i++) {

            final int numero = i;

            Thread corredor = new Thread(() -> {

                try {
                    // PRIMERA ETAPA
                    System.out.println("Corredor " + numero + ": comienza la etapa 1");

                    Thread.sleep(numero * 500);

                    System.out.println("Corredor " + numero + ": termina la etapa 1");

                    // Espera a que lleguen los otros corredores.
                    barrera.await();


                    // SEGUNDA ETAPA
                    System.out.println("Corredor " + numero + ": comienza la etapa 2");

                    Thread.sleep(numero * 500);

                    System.out.println("Corredor " + numero + ": termina la carrera");

                } catch (InterruptedException e) {
                    // Interrumpido en sleep() o en await(): no perdemos la petición.
                    Thread.currentThread().interrupt();
                    System.out.println("Corredor " + numero + ": interrumpido");

                } catch (BrokenBarrierException e) {
                    // La barrera se ha roto: otro corredor fue interrumpido
                    // mientras esperaba, y ya no llegarán todos.
                    System.out.println("Corredor " + numero + ": la barrera se ha roto");

                } finally {
                    // Avisamos de que este corredor ha terminado.
                    llegada.countDown();
                }

            }, "Corredor-" + numero);

            corredor.start();
        }

        // El hilo principal espera a que terminen todos.
        llegada.await();

        System.out.println("\nLa carrera ha terminado. Todos los corredores han llegado.");
    }
}
