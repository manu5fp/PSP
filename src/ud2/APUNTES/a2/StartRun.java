package ud2.APUNTES.a2;

/** start() crea un hilo nuevo; run() es una llamada normal en el hilo actual. */
public class StartRun {
    public static void main(String[] args) throws InterruptedException {
        Runnable tarea = () -> System.out.println("Ejecuta: " + Thread.currentThread().getName());
        Thread hilo = new Thread(tarea, "secundario");

        hilo.run();   // Sin concurrencia: lo ejecuta main
        hilo.start(); // Con concurrencia: lo ejecuta "secundario"
        hilo.join();

        try {
            hilo.start(); // Un hilo no puede iniciarse dos veces
        } catch (IllegalThreadStateException e) {
            System.out.println("Segundo start(): IllegalThreadStateException");
        }
    }
}
