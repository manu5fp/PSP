package ud2.APUNTES.a5;

/** synchronized solo excluye a quienes usan el mismo monitor. */
public class MonitorEquivocado {
    static int compartido;
    static final Object CERROJO = new Object();

    static class Mal implements Runnable {
        @Override public synchronized void run() { // Cada objeto Mal usa su propio this
            for (int i = 0; i < 500_000; i++) compartido++;
        }
    }

    static class Bien implements Runnable {
        @Override public void run() {
            for (int i = 0; i < 500_000; i++) {
                synchronized (CERROJO) { compartido++; } // Todos usan el mismo objeto
            }
        }
    }

    static int probar(Runnable a, Runnable b) throws InterruptedException {
        compartido = 0;
        Thread h1 = new Thread(a), h2 = new Thread(b);
        h1.start(); h2.start();
        h1.join(); h2.join();
        return compartido;
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Esperado: 1000000");
        System.out.println("Monitores distintos: " + probar(new Mal(), new Mal()));
        System.out.println("Mismo monitor:       " + probar(new Bien(), new Bien()));
    }
}