package ud2.APUNTES.a8;

import java.util.concurrent.Semaphore;

/** Filósofos con un camarero: como máximo N-1 sentados, nunca se forma el ciclo de espera. */
public class Filosofos {
    public static void main(String[] args) throws InterruptedException {
        final int N = 5, 
        		  COMIDAS = 2000;
        
        Semaphore[] palillos = new Semaphore[N];
        
        for (int i = 0; i < N; i++) 
        	palillos[i] = new Semaphore(1);
        
        Semaphore camarero = new Semaphore(N - 1);
        
        int[] comidas = new int[N];

        Thread[] f = new Thread[N];
       
        for (int i = 0; i < N; i++) {
            final int id = i;
            f[i] = new Thread(() -> {
                Semaphore izq = palillos[id], 
                		  der = palillos[(id + 1) % N];
                try {
                    for (int c = 0; c < COMIDAS; c++) {
                        camarero.acquire();
                        izq.acquire();
                        der.acquire();
                        comidas[id]++;
                        der.release();
                        izq.release();
                        camarero.release();
                    }
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            f[i].start();
        }
        for (Thread h : f) 
        	h.join();
        int total = 0;
        for (int c : comidas) 
        	total += c;
        System.out.println("Comidas totales: " + total + " (esperadas " + N * COMIDAS + ")");
    }
}