package UD2.Problema;

import java.util.concurrent.Semaphore;

import UD2.varios.Azar;
import UD2.varios.Crono;

public class Sincro3SemaforosBien {

    // Variables compartidas
    static int x, y, z;
    static int Suma, Producto;

    // Semáforos de sincronización
    static Semaphore xLista = new Semaphore(0);
    static Semaphore lecturasYZ = new Semaphore(0);
    static Semaphore resultadosListos = new Semaphore(0);

    public static void main(String[] args) throws InterruptedException {

        for (int i = 0; i < 10000; i++) {

            System.out.println("\nIteración: " + i);

            // ---- Hilo A ----
            Thread A = new Thread(() -> {

                System.out.println("A: Leyendo x...");
                x = Azar.entero(1, 1000);

                // Avisa a B de que x está leído
                xLista.release();

                System.out.println("A: x = " + x);
                System.out.println("A: Esperando a que se lean y y z...");

                try {
                    // Espera dos permisos: uno de B y otro de C
                    lecturasYZ.acquire(2);

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }

                System.out.println("A: Calculando suma...");
                Suma = y + z;

                // Avisa a C de que la suma está calculada
                resultadosListos.release();

            });

            // ---- Hilo B ----
            Thread B = new Thread(() -> {

                System.out.println("B: Leyendo y...");
                y = Azar.entero(1, 500);

                // Avisa de que y está leído
                lecturasYZ.release();

                System.out.println("B: y = " + y);
                System.out.println("B: Esperando a que x esté leído...");

                try {
                    // Espera a que A haya leído x
                    xLista.acquire();

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }

                System.out.println("B: Calculando producto...");
                Producto = x * y;

                // Avisa a C de que el producto está calculado
                resultadosListos.release();

            });

            // ---- Hilo C ----
            Thread C = new Thread(() -> {

                System.out.println("C: Leyendo z...");
                z = Azar.entero(1, 2500);

                // Avisa de que z está leído
                lecturasYZ.release();

                System.out.println("C: z = " + z);
                System.out.println("C: Esperando a que se calculen los resultados...");

                try {
                    // Espera dos permisos: uno de A y otro de B
                    resultadosListos.acquire(2);

                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }

                System.out.println("C: La suma es: " + Suma);
                System.out.println("C: El producto es: " + Producto);

            });

            Crono.start();

            A.start();
            B.start();
            C.start();

            A.join();
            B.join();
            C.join();

            System.out.println("Tiempo empleado: " + Crono.stop());
        }
    }
}
