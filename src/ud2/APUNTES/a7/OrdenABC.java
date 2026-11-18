package ud2.APUNTES.a7;

import java.util.concurrent.Semaphore;

/** Impone el orden A, B, C con el mínimo de semáforos: dos, uno por cada relación "antes de". */
public class OrdenABC {
    public static void main(String[] args) throws InterruptedException {
        for (int prueba = 0; prueba < 3; prueba++) {
            Semaphore aHecho = new Semaphore(0);
            Semaphore bHecho = new Semaphore(0);

            Thread c = new Thread(() -> {
                try { bHecho.acquire(); System.out.print("C"); }
                catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            });
            Thread b = new Thread(() -> {
                try { aHecho.acquire(); System.out.print("B"); bHecho.release(); }
                catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            });
            Thread a = new Thread(() -> { System.out.print("A"); aHecho.release(); });

            c.start(); b.start(); a.start(); // Se lanzan en orden inverso a propósito
            a.join(); b.join(); c.join();
            System.out.println();
        }
    }
}