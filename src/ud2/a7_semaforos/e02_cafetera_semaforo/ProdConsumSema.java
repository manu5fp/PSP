package ud2.a7_semaforos.e02_cafetera_semaforo;

import java.util.concurrent.Semaphore;

public class ProdConsumSema {
	
	    static class Cafetera {

	        // Para servir cafés individuales
	        private Semaphore semCafe = new Semaphore(0, true);

	        // Para servir todos los cafés a la vez
	        //private Semaphore semMuchosCafes = new Semaphore(0);

	        public void prepararCafe(String nombre) {
	            System.out.println(" El camarero " + nombre + " está preparando un café...");
	            try {
	                Thread.sleep(1000);
	            } catch (InterruptedException e) { }

	            System.out.println("Café listo, despertando a 1 cliente.");
	            semCafe.release();   // Despierta a UN consumidor
	        }

	        public void prepararCafes(String nombre, int numClientes) {
	            System.out.println(" El camarero " + nombre + " está preparando cafés para todos...");
	            try {
	                Thread.sleep(1000);
	            } catch (InterruptedException e) { }

	            System.out.println("Cafés listos, despertando a TODOS los clientes.");
	            semCafe.release(numClientes);  // Despierta a TODOS
	        }

	        public void tomarCafe(String nombre) {
	            System.out.println("Cliente " + nombre + " esperando el café...");

	            try {
	            	semCafe.acquire();
	            	System.out.println("Cliente " + nombre + " tomando el café.");
	                
	            } catch (InterruptedException e) { }
	        }
	    }

	    public static void main(String[] args) {

	        Cafetera cafetera = new Cafetera();

	        String[] cli = { "mario", "juan", "bea", "jose", "antonia", "miguel", "alfonso", "ricardo", "ana" };
	        String[] cam = { "aprendiz", "veterano" };

	        for (String n : cli) {
	            Thread consumidor = new Thread(() -> cafetera.tomarCafe(n));
	            consumidor.start();
	        }

	        Thread productor;

	        boolean aprendiz = (Math.random() < 0.5);

	        if (aprendiz) {
	            // PREPARA CAFÉS UNO A UNO
	            productor = new Thread(() -> {
	                for (int i = 0; i < cli.length; i++)
	                    cafetera.prepararCafe(cam[0]);
	            });

	        } else {
	            // PREPARA TODOS LOS CAFÉS A LA VEZ
	            productor = new Thread(() -> {
	                cafetera.prepararCafes(cam[1], cli.length);
	            });
	        }

	        try {
	            Thread.sleep(500); // Deja que los consumidores comiencen a esperar
	        } catch (InterruptedException e) { }

	        productor.start();
	    }
	
}
