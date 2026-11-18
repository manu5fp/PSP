package ud2.APUNTES.a8;

public class Interbloqueo {

    private static final Object A = new Object();
    private static final Object B = new Object();

    public static void main(String[] args) throws InterruptedException {

        // ------------------------------------------------
        // CASO 1: ADQUISICIÓN DE CERROJOS EN EL MISMO ORDEN
        // ------------------------------------------------

        Thread hilo1 = new Thread(() -> {

            synchronized (A) {
                System.out.println("Hilo 1: tiene A");

                synchronized (B) {
                    System.out.println("Hilo 1: tiene A y B");
                }
            }

        });

        Thread hilo2 = new Thread(() -> {

            synchronized (A) {
                System.out.println("Hilo 2: tiene A");

                synchronized (B) {
                    System.out.println("Hilo 2: tiene A y B");
                }
            }

        });

        hilo1.start();
        hilo2.start();

        hilo1.join();
        hilo2.join();

        System.out.println("Caso 1: los dos hilos han terminado.");


        // ------------------------------------------------
        // CASO 2: ADQUISICIÓN DE CERROJOS EN ORDEN INVERSO
        // ------------------------------------------------

        Thread hilo3 = new Thread(() -> {

            synchronized (A) {
                System.out.println("Hilo 3: tiene A");
                try {
                    // Damos tiempo a que el otro hilo tome su primer cerrojo.
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    // No perdemos la petición de interrupción (apartado 2.4).
                    Thread.currentThread().interrupt();
                    System.out.println("Hilo 3: interrumpido");
                    return;
                }
                synchronized (B) {
                    System.out.println("Hilo 3: tiene A y B");
                }
            }

        });

        Thread hilo4 = new Thread(() -> {

            synchronized (B) {
                System.out.println("Hilo 4: tiene B");
                try {
                    // Damos tiempo a que el otro hilo tome su primer cerrojo.
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    // No perdemos la petición de interrupción (apartado 2.4).
                    Thread.currentThread().interrupt();
                    System.out.println("Hilo 4: interrumpido");
                    return;
                }
                synchronized (A) {
                    System.out.println("Hilo 4: tiene B y A");
                }
            }

        });

        hilo3.setDaemon(true);
        hilo4.setDaemon(true);

        hilo3.start();
        hilo4.start();

        // Damos tiempo a que se produzca el interbloqueo.
        Thread.sleep(500);

        // Detectamos los hilos interbloqueados.

        if (hilo3.isAlive() || hilo4.isAlive()) {
            System.out.println("Caso 2: interbloqueo detectado.");
        } else {
            System.out.println("No se ha detectado interbloqueo.");
        }
    }
}
