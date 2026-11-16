package ud2.a7_semaforos.e07_hilos_sin_sincronizar;

import ud2.comun.Azar;
import ud2.comun.Crono;

public class SincroHilos {
    // Variables compartidas
    static int x, y, z;
    static int Suma, Producto;

    public static void main(String[] args) throws InterruptedException {
    	
     // ---- Hilo A ----
    	Thread A  = new Thread(() -> {
           System.out.println("A: Leyendo x...");
           x = Azar.entero(1,1000); // Simula lectura
           System.out.println("A: x vale " + x);
           System.out.println("A: Calculando suma...");
           Suma = y + z;
        });
     // ---- Hilo B ----
    	Thread B  = new Thread(() -> {
          System.out.println("B: Leyendo y...");
          y = Azar.entero(1,500); // Simula lectura
          System.out.println("B: y vale " + y);
          System.out.println("B: Calculando producto...");
          Producto = x * y;
        });
        // ---- Hilo C ---- 
    	Thread C  = new Thread(() -> {
            System.out.println("C: Leyendo z...");
            z = Azar.entero(1,2500); // Simula lectura
            System.out.println("C: z vale " + z);
            System.out.println("C: La suma es: " + Suma);
            System.out.println("C: El producto es: " + Producto);
        });
        
        Crono.start();
        A.start(); 
        B.start(); 
        C.start();
        System.out.println("Tiempo empleado: " + Crono.stop());

    }

  
}
