package UD2.ejemplos;

public class LanzaTareas {
	 public static void main(String[] args) throws InterruptedException {
	        final int NUM_HILOS = 5;
	        Thread[] hilos = new Thread[NUM_HILOS];

	        for (int i = 0; i < NUM_HILOS; i++) {
	            hilos[i] = new Thread(new TareaCompleja());
	            hilos[i].setName("Hilo-" + i);
	           // if (i==1 || i==2)
	           // 	hilos[i].setPriority(Thread.MAX_PRIORITY);
               // else
               // 	hilos[i].setPriority(Thread.MIN_PRIORITY);
	            hilos[i].start();
	        }
	        // Esperar a que terminen todos
	        for (Thread h : hilos) {
	            h.join();
	        }

	        System.out.println("Todos los hilos han terminado.");
	    }

}
