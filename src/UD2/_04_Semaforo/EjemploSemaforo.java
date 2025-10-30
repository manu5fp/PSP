package UD2._04_Semaforo;

/**
 * Clase principal que prueba el uso de un recurso compartido
 * controlado mediante un {@link java.util.concurrent.Semaphore}.
 * 
 * <p>Se crean varios hilos que intentan usar el recurso al mismo tiempo,
 * pero el semáforo limita cuántos pueden acceder de forma concurrente.</p>
 * 
 * @author manu
 */
public class EjemploSemaforo {

    public static void main(String[] args) {
        // Creamos el recurso compartido
        RecursoCompartido recurso = new RecursoCompartido();

        // Lanzamos 6 hilos que intentarán usarlo
        for (int i = 0; i < 6; i++) {
            String nombre = "Hilo " + i;

            // Cada hilo ejecuta el método usarRecurso()
            new Thread(() -> recurso.usarRecurso(nombre)).start();
        }
    }
}
