package UD2._04_Semaforo;

import java.util.concurrent.Semaphore;

/**
 * Clase que representa un recurso compartido entre varios hilos.
 * 
 * <p>El acceso al recurso está controlado por un semáforo,
 * que permite limitar cuántos hilos pueden usarlo simultáneamente.</p>
 * 
 * @author manu
 */
class RecursoCompartido {

    /**
     * Semáforo con 2 permisos. 
     * Esto significa que como máximo 2 hilos pueden usar el recurso a la vez.
     */
    private Semaphore semaforo = new Semaphore(2);

    /**
     * Método que simula el uso de un recurso compartido por un hilo.
     *
     * @param nombre Nombre del hilo que solicita acceso
     */
    public void usarRecurso(String nombre) {
        try {
            // Solicita un permiso del semáforo (si no hay, espera)
            semaforo.acquire();
            
            System.out.println(nombre + " usando el recurso...");
            Thread.sleep(1000); // Simula el trabajo con el recurso
            System.out.println(nombre + " terminó.");
            
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // Libera el permiso para que otro hilo pueda usar el recurso
            semaforo.release();
        }
    }
}
