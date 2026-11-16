package ud2.a2_creacion.e06_interrupcion;
public class EjemploInterrupt {

    static class Descarga extends Thread {

        @Override
        public void run() {
            for (int progreso = 0; progreso <= 100; progreso += 10) {

                System.out.println("Descargando: " + progreso + "%");

                try {
                    // Simulamos el tiempo necesario para descargar
                    // cada bloque.
                    Thread.sleep(1000);

                } catch (InterruptedException e) {
                    System.out.println(
                        "Descarga interrumpida. Cancelando operación..."
                    );
                    return;
                }
            }

            System.out.println("Descarga completada.");
        }
    }

    public static void main(String[] args) throws InterruptedException {

        Descarga descarga = new Descarga();

        // Iniciamos el hilo de descarga.
        descarga.start();
        descarga.join();
        // El hilo principal espera 3,5 segundos.
        Thread.sleep(3500);

        // Solicitamos la interrupción de la descarga.
        System.out.println("Main: solicito cancelar la descarga.");
        descarga.interrupt();

        // Esperamos a que el hilo de descarga termine.
        descarga.join();

        System.out.println("Main: el hilo de descarga ha terminado.");
    }
}