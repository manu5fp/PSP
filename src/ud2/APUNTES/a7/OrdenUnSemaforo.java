package ud2.APUNTES.a7;

import java.util.concurrent.Semaphore;

/** Con un solo semáforo, B y C compiten por el mismo permiso: no hay orden garantizado. */
public class OrdenUnSemaforo {
    public static void main(String[] args) throws InterruptedException {
        int cb = 0, bc = 0;
        for (int prueba = 0; prueba < 20; prueba++) {
            StringBuffer traza = new StringBuffer();
            
            Semaphore s = new Semaphore(0);
            Thread c = new Thread(() -> {
                try { s.acquire(); traza.append("C"); s.release(); }
                catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            });
            Thread b = new Thread(() -> {
                try { s.acquire(); traza.append("B"); s.release(); }
                catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            });
            b.start();
            c.start(); 

            traza.append("A");
            s.release();
            b.join(); c.join();
            if (traza.toString().equals("ACB")) cb++;
            if (traza.toString().equals("ABC")) bc++;
        }
        System.out.println("Trazas ABC: " + bc + "; trazas ACB: " + cb + " (de 20)");
    }
}