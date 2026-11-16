package ud2.a7_semaforos.e08_cinco_semaforos;

import java.util.concurrent.Semaphore;

import ud2.comun.Azar;
import ud2.comun.Crono;

public class Sincro5Semaforos {
    static int x, y, z;
    static int Suma, Producto;

    static Semaphore r1 = new Semaphore(0); // x leído
    static Semaphore r2 = new Semaphore(0); // y leído
    static Semaphore r3 = new Semaphore(0); // z leído
    static Semaphore r4 = new Semaphore(0); // suma calculada
    static Semaphore r5 = new Semaphore(0); // producto calculado

    public static void main(String[] args) throws InterruptedException {

        Thread A = new Thread(() -> {
            System.out.println("A: leyendo x...");
	          x = Azar.entero(1,1000); // Simula lectura
            r1.release(); // x leído
	          System.out.println("A: x = " + x);
	          System.out.println("A: Calculando suma...");
            try {
                r2.acquire(); // y leído
                r3.acquire(); // z leído
            } catch (InterruptedException e) {}

            Suma = y + z;
            //System.out.println("A: suma = " + Suma);
            r4.release(); // suma calculada
        });

        Thread B = new Thread(() -> {
            System.out.println("B: leyendo y...");
       	 y = Azar.entero(1,500); // Simula lectura
            r2.release(); // y leído
       	 System.out.println("B: y = " + y);
	         System.out.println("B: Calculando producto...");
            try {
                r1.acquire(); // x leído
            } catch (InterruptedException e) {}

            Producto = x * y;
            //System.out.println("B: producto = " + Producto);
            r5.release(); // producto calculado
        });

        Thread C = new Thread(() -> {
            System.out.println("C: leyendo z...");
            z = Azar.entero(1,2500); // Simula lectura
            r3.release(); // z leído
            System.out.println("C: z = " + z);
            try {
                r4.acquire(); // espera suma
                System.out.println("C: la suma es " + Suma);
                r5.acquire(); // espera producto
                System.out.println("C: el producto es " + Producto);
            } catch (InterruptedException e) {}
        });


        Crono.start();
        A.start(); B.start(); C.start();
        A.join(); B.join(); C.join();
        System.out.println("Tiempo empleado: " + Crono.stop());
        
    }
}


