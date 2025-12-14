package UD2.ejemplos;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

public class ProdConsumSema_FIFO_Demo {

    static class Cafetera {

        private Semaphore semCafe = new Semaphore(0, false);  // FIFO real

        // Contadores para registrar el orden REAL
        private AtomicInteger llegada = new AtomicInteger(0);
        private AtomicInteger consumo = new AtomicInteger(0);

        public void prepararCafe(String nombre) {
            try { Thread.sleep(300); } catch (InterruptedException e) {}
            semCafe.release();
        }
        public void prepararCafes(String nombre) {
            try { Thread.sleep(500); } catch (InterruptedException e) {}
            semCafe.release(9);
        }
        public void tomarCafe(String nombre) {
            int ordenLlegada = llegada.incrementAndGet();

            try {
                semCafe.acquire();
            } catch (InterruptedException e) {}

            int ordenConsumo = consumo.incrementAndGet();

            synchronized (System.out) {
                System.out.println(
                        nombre +
                        " → llegada: " + ordenLlegada +
                        " | consumo: " + ordenConsumo);
            }
        }   
        
    }

    public static void main(String[] args) {

        Cafetera cafetera = new Cafetera();

        String[] cli = { "mario", "juan", "bea", "jose", "antonia", "miguel", "alfonso", "ricardo", "ana" };

        for (String n : cli) {
            Thread consumidor = new Thread(() -> cafetera.tomarCafe(n));
            consumidor.start();
        }

        try { Thread.sleep(500); } catch (InterruptedException e) {}

        // El camarero prepara cafés uno a uno
        Thread productor = new Thread(() -> {
            for (int i = 0; i < cli.length; i++) {
                cafetera.prepararCafe("aprendiz");
                //cafetera.prepararCafes("veterano");
            }
        });

        productor.start();
        
      
    }
}
