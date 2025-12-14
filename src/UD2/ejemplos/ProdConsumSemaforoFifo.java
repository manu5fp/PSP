package UD2.ejemplos;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class ProdConsumSemaforoFifo {

    static class Cafetera {

        private Semaphore semCafe = new Semaphore(0, true);  // FIFO con true
        private AtomicInteger llegada = new AtomicInteger(0);

        public void prepararCafe(String nombre) {
            System.out.println("El camarero " + nombre + " está preparando un café");
            try { 
            	Thread.sleep(30); 
            	} catch (InterruptedException e) {}
            semCafe.release();
        }

        public void prepararCafes(String nombre, int numClientes) {
            System.out.println("El camarero " + nombre + " está preparando cafés para todos...");
            try { 
            	Thread.sleep(1000); 
            	
            	System.out.println("Cafés listos, despertando a TODOS los clientes.");
            	for (int i = 0; i < numClientes; i++) {
            		semCafe.release();
            		Thread.sleep(10); 
               	}
            } catch (InterruptedException e) {}
        }

        public void tomarCafe(String nombre) {
            int miNumero = llegada.incrementAndGet();

            try {
                semCafe.acquire();  // FIFO garantizado aquí
            } catch (InterruptedException e) {}
            System.out.println(nombre + " con orden FIFO = " + miNumero);
        }
        
    }

    public static void main(String[] args) {

        Cafetera cafetera = new Cafetera();

        String[] cli = { "mario", "juan", "bea", "jose", "antonia", "miguel", "alfonso", "ricardo", "ana" };
        String[] cam = { "aprendiz", "veterano" };

        for (String n : cli) {
            Thread consumidor = new Thread(() -> cafetera.tomarCafe(n));
            System.out.println("Cliente " + n + " esperando el café...");
            consumidor.start();
        }

        Thread productor;

        boolean aprendiz = (Math.random() < 0.5);

        if (aprendiz) {
            productor = new Thread(() -> {
                for (int i = 0; i < cli.length; i++)
                    cafetera.prepararCafe(cam[0]);
            });
        } else {
            productor = new Thread(() -> {
                cafetera.prepararCafes(cam[1], cli.length);
            });
        }

        productor.start();
    }
}


