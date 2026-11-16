package ud2.a7_semaforos.e09_cuatro_semaforos;

import java.util.concurrent.Semaphore;

import ud2.comun.Azar;
import ud2.comun.Crono;

public class Sincro4Semaforos {
	    // Variables compartidas
	    static int x, y, z;
	    static int Suma, Producto;
	    // Semáforos de sincronización
	    static Semaphore r1 = new Semaphore(0); // x leído
	    static Semaphore r2 = new Semaphore(0); // y leído
	    static Semaphore r3 = new Semaphore(0); // z leído
	    static Semaphore r4 = new Semaphore(0); // suma calculada / producto calculado

	    public static void main(String[] args) throws InterruptedException {
	    	
		    // ---- Hilo A ----
	        Thread A = new Thread(() -> {
	        	  System.out.println("A: Leyendo x...");
		          x = Azar.entero(1,1000); // Simula lectura
		          r1.release(); // Señala que x ya está leído
		          System.out.println("A: x = " + x);
		          System.out.println("A: Calculando suma...");
		          try {
		                // Esperar a que y y z estén leídos antes de calcular la suma
		                r2.acquire(); // espera a y
		                r3.acquire(); // espera a z
		            } catch (InterruptedException e) {
		                Thread.currentThread().interrupt();
		            }
		          Suma = y + z;
		          r4.release(); // Señala que la suma está lista
		          
	        });
		    // ---- Hilo B ----
	        Thread B = new Thread(() -> {
	        	 System.out.println("B: Leyendo y...");
	        	 y = Azar.entero(1,500); // Simula lectura
	        	 r2.release(); // Señala que y ya está leído
	        	 System.out.println("B: y = " + y);
		         System.out.println("B: Calculando producto...");
		         try {
		                // Esperar a que x esté leído antes de calcular el producto
		                r1.acquire(); // espera a x
		            } catch (InterruptedException e) {
		                Thread.currentThread().interrupt();
		            }
		         Producto = x * y;
		         r4.release(); // Señala que el producto está calculado
	        });
	        // ---- Hilo C ---- 
	        Thread C  = new Thread(() -> {
	            System.out.println("C: Leyendo z...");
	            z = Azar.entero(1,2500); // Simula lectura
	            r3.release(); // Señala que z ya está leído
	            System.out.println("C: z = " + z);
	            try {
	            	r4.acquire(2); // Espera a que la suma esté lista
	            } catch (InterruptedException e) {
	                Thread.currentThread().interrupt();
	            }
	            System.out.println("C: La suma es: " + Suma);
	            System.out.println("C: El producto es: " + Producto);
	        });
	        
	        Crono.start();
	        A.start(); B.start(); C.start();
	        A.join(); B.join(); C.join();
	        System.out.println("Tiempo empleado: " + Crono.stop());
	    }

}
