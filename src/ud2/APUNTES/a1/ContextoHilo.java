package ud2.APUNTES.a1;

/** Las variables locales están en la pila de cada hilo; los objetos pueden compartirse. */
public class ContextoHilo {
    static class Caja { int valor; }

    public static void main(String[] args) throws InterruptedException {
        Caja compartida = new Caja();
        Runnable tarea = () -> {
            int local = 0;              // Cada hilo tiene su copia en su pila
            for (int i = 0; i < 1000; i++) local++;
            synchronized (compartida) { // El objeto está en el montón y es uno solo
                compartida.valor += local;
            }
            System.out.println(Thread.currentThread().getName() + " local=" + local);
        };
        Thread a = new Thread(tarea, "A");
        Thread b = new Thread(tarea, "B");
        a.start(); a.join();
        b.start(); b.join();
        System.out.println("Compartida=" + compartida.valor);
    }
}