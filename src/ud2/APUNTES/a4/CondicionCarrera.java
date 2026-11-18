package ud2.APUNTES.a4;

import java.util.concurrent.atomic.AtomicInteger;

/** Compara un contador sin protección, con synchronized y con AtomicInteger. */
public class CondicionCarrera {
    interface Contador { void incrementar(); int valor(); }

    static class Inseguro implements Contador {
        private int cuenta;
        public void incrementar() { cuenta++; }  // Leer, sumar y escribir: tres pasos
        public int valor() { return cuenta; }
    }

    static class Sincronizado implements Contador {
        private int cuenta;
        public synchronized void incrementar() { cuenta++; }
        public synchronized int valor() { return cuenta; }
    }

    static class Atomico implements Contador {
        private final AtomicInteger cuenta = new AtomicInteger();
        public void incrementar() { cuenta.incrementAndGet(); }
        public int valor() { return cuenta.get(); }
    }

    static int probar(Contador c, int hilos, int veces) throws InterruptedException {
        Thread[] t = new Thread[hilos];
        for (int i = 0; i < hilos; i++) {
            t[i] = new Thread(() -> {
                for (int j = 0; j < veces; j++) c.incrementar();
            });
            t[i].start();
        }
        for (Thread h : t) h.join();
        return c.valor();
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Esperado:     4000000");
        System.out.println("Inseguro:     " + probar(new Inseguro(), 4, 1_000_000));
        System.out.println("Sincronizado: " + probar(new Sincronizado(), 4, 1_000_000));
        System.out.println("Atómico:      " + probar(new Atomico(), 4, 1_000_000));
    }
}