package ud2.APUNTES.a6;


import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

public class ColaBloqueante {

    private static final int FIN = -1;

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<Integer> cola = new ArrayBlockingQueue<>(2);

        AtomicLong suma = new AtomicLong(0);

        // PRODUCTOR
        Thread productor = new Thread(() -> {
            try {
                for (int i = 1; i <= 1000; i++) {
                    cola.put(i);
                }

                cola.put(FIN);

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        // CONSUMIDOR
        Thread consumidor = new Thread(() -> {
            try {
                while (true) {
                    int dato = cola.take();

                    if (dato == FIN) {
                        break;
                    }

                    suma.addAndGet(dato);
                }

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        productor.start();
        consumidor.start();

        productor.join();
        consumidor.join();

        System.out.println("Suma: " + suma.get());
    }
}