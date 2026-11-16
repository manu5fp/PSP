package ud2.a7_semaforos.e01_recurso_limitado;

import java.util.concurrent.Semaphore;

import ud2.comun.Azar;

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
            // Fuera del try interior: si acquire() se interrumpe, no hay permiso que devolver
            semaforo.acquire();
            try {
            System.out.println(nombre + " usando el recurso...");
            Thread.sleep(Azar.entero(1000)); // Simula el trabajo con el recurso
            System.out.println(nombre + " terminó.");
            
        } finally {
                semaforo.release();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
