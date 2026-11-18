package ud2.APUNTES.a2;

/** Las tres formas habituales de indicar a un hilo qué código debe ejecutar. */
public class FormasCrearHilos {
    static class MiHilo extends Thread {
        MiHilo(String nombre) { super(nombre); }
        @Override public void run() { saludar("heredando de Thread"); }
    }

    static class MiTarea implements Runnable {
        @Override public void run() { saludar("implementando Runnable"); }
    }

    static void saludar(String forma) {
        System.out.println(Thread.currentThread().getName() + ": " + forma);
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] hilos = {
            new MiHilo("hilo-1"),
            new Thread(new MiTarea(), "hilo-2"),
            new Thread(() -> saludar("con una expresión lambda"), "hilo-3")
        };
        for (Thread h : hilos) { h.start(); h.join(); } // join aquí solo para fijar el orden
    }
}