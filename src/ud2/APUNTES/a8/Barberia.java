package ud2.APUNTES.a8;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/** Barbero dormilón con tres semáforos y un contador protegido. */
public class Barberia {
    private final int sillas;
    private int esperando;                                   // Protegido por mutex
    private final Semaphore mutex = new Semaphore(1);        // Protege esperando
    private final Semaphore clientes = new Semaphore(0);     // Duerme al barbero
    private final Semaphore barbero = new Semaphore(0);      // Retiene al cliente en la silla
    final AtomicInteger atendidos = new AtomicInteger();
    final AtomicInteger rechazados = new AtomicInteger();

    Barberia(int sillas) { this.sillas = sillas; }

    /** Devuelve false si el cliente se marcha por no encontrar silla. */
    boolean llegaCliente() throws InterruptedException {
        mutex.acquire();
        if (esperando == sillas) {
            mutex.release();
            rechazados.incrementAndGet();
            return false;
        }
        esperando++;
        mutex.release();

        clientes.release();  // Avisa al barbero
        barbero.acquire();   // Espera a que el barbero lo llame
        return true;
    }

    void atiendeBarbero() throws InterruptedException {
        clientes.acquire();  // Duerme si no hay clientes
        mutex.acquire();
        esperando--;
        mutex.release();
        barbero.release();   // Llama al siguiente cliente
        atendidos.incrementAndGet();
    }

    public static void main(String[] args) throws InterruptedException {
        Barberia b = new Barberia(3);
        Thread barbero = new Thread(() -> {
            try {
                while (true) { b.atiendeBarbero(); Thread.sleep(2); }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        barbero.start();

        final int CLIENTES = 200;
        Thread[] c = new Thread[CLIENTES];
        for (int i = 0; i < CLIENTES; i++) {
            c[i] = new Thread(() -> {
                try { b.llegaCliente(); }
                catch (InterruptedException e) { Thread.currentThread().interrupt(); }
            });
            c[i].start();
            Thread.sleep(1);
        }
        for (Thread h : c) h.join();
        barbero.interrupt();
        barbero.join();
        int total = b.atendidos.get() + b.rechazados.get();
        System.out.println("Atendidos + rechazados = " + total + " (clientes: " + CLIENTES + ")");
    }
}