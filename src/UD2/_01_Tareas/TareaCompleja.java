package UD2._01_Tareas;

/**
 * Ejemplo de tarea "pesada" que se puede ejecutar en un hilo independiente.
 * 
 * La clase implementa la interfaz {@code Runnable}, lo que permite que su lógica
 * se ejecute dentro de un objeto {@code Thread}.
 * 
 * Cada hilo realiza un bucle de 10 iteraciones. En cada una:
 *  - Ejecuta un cálculo costoso en el método {@code time()} (para simular carga).
 *  - Muestra por pantalla su nombre y el valor de avance.
 * 
 * Al finalizar las iteraciones, el hilo informa que ha terminado.
 * 
 * @author manu
 */
public class TareaCompleja implements Runnable {

    /** Contador interno que indica cuántas veces se ha ejecutado la tarea. */
    private int numEjecucion;

    /** Constructor por defecto (no requiere parámetros). */
    public TareaCompleja() {}

    /**
     * Método principal de la tarea: define la lógica que el hilo ejecutará.
     * 
     * Cada hilo:
     *  - Recupera su propio nombre con {@code Thread.currentThread().getName()}.
     *  - Ejecuta un bucle de 10 pasos.
     *  - En cada paso, simula un cálculo costoso (time()), muestra el progreso
     *    y aumenta el contador {@code numEjecucion}.
     */
    @Override
    public void run() {
        String nombre = Thread.currentThread().getName();
        String cad;
        while (numEjecucion < 10) {
            time(); // simula trabajo intensivo
            cad = "Soy el hilo " + nombre;
            cad += " y mi valor de avance es " + numEjecucion;
            System.out.println(cad);
            numEjecucion++;
        }
        System.out.println("Finalizó " + nombre);
    }

    /**
     * Simula una operación de cálculo intensivo que consume tiempo de CPU.
     * 
     * El método realiza un bucle con muchas operaciones matemáticas, de modo que
     * el hilo tarde un poco en avanzar y se note la concurrencia cuando se
     * ejecutan varios hilos a la vez.
     */
    private void time() {
        for (int i = 0; i < 9000000; i++) {
            int base = 1000, exp = 10000;
            Math.pow(base, exp);
        }
    }
}
