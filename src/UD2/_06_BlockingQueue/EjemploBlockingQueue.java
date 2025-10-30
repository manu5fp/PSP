package UD2._06_BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class EjemploBlockingQueue {
    public static void main(String[] args) {
        BlockingQueue<Integer> cola = new ArrayBlockingQueue<>(10);

        Runnable productor = () -> {
            try {
                for (int i = 0; i < 50; i++) {
                    cola.put(i);
                    System.out.println("Producido: " + i);
                }
            } catch (InterruptedException e) {}
        };

        Runnable consumidor = () -> {
            try {
                for (int i = 0; i < 50; i++) {
                    int valor = cola.take();
                    System.out.println("Consumido: " + valor);
                }
            } catch (InterruptedException e) {}
        };

        new Thread(productor).start();
        new Thread(consumidor).start();
    }
}
