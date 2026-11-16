package ud2.a7_semaforos.e10_tres_semaforos;

import java.util.concurrent.Semaphore;

import ud2.comun.Azar;
import ud2.comun.Crono;

public class Sincro3Semaforos {
	    // Variables compartidas
	    static int x, y, z;
	    static int Suma, Producto;
	    // Semáforos de sincronización
	    // r1: indica que x está leído (y luego que el producto está calculado)
	    // r2: indica que y está leído (y luego que la suma está calculada)
	    // r3: indica que z está leído
	    static Semaphore r1 = new Semaphore(0);
	    static Semaphore r2 = new Semaphore(0);
	    static Semaphore r3 = new Semaphore(0);

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
		          r2.release(2); // Señala que la suma está calculada
		          
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
		         r1.release(2); // Señala que el producto está calculado
	        });
	        // ---- Hilo C ---- 
	    	Thread C  = new Thread(() -> {
	            System.out.println("C: Leyendo z...");
	            z = Azar.entero(1,2500); // Simula lectura
	            r3.release(); // Señala que z ya está leído
	            System.out.println("C: z = " + z);
	            try {
	                // Esperar a que la suma esté calculada
	                r2.acquire(2);
	            } catch (InterruptedException e) {
	                Thread.currentThread().interrupt();
	            }
	            System.out.println("C: La suma es: " + Suma);
	            try {
	                // Esperar a que el producto esté calculado
	                r1.acquire(2);
	            } catch (InterruptedException e) {
	                Thread.currentThread().interrupt();
	            }
	            System.out.println("C: El producto es: " + Producto);
	        });
	        
	        Crono.start();
	        A.start(); B.start(); C.start();
	        A.join(); B.join(); C.join();
	        System.out.println("Tiempo empleado: " + Crono.stop());
	    }
	
}
