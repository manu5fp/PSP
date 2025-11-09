package UD2._07_Ejemplos.ProdConsumSimple;

/**
 * La clase Cola implementa una estructura compartida entre 
 * productores y consumidores usando el modelo clásico 
 * del problema Productor-Consumidor.
 * 
 * <p>Esta clase utiliza los métodos de sincronización de 
 * Java (`synchronized`, `wait()`, `notifyAll()`) para garantizar:
 * <ul>
 *   <li>La exclusión mutua (solo un hilo accede a la vez).</li>
 *   <li>La espera activa de los hilos que no pueden avanzar 
 *       (productores esperan si ya hay un valor, consumidores esperan 
 *       si la cola está vacía).</li>
 *   <li>La notificación de los hilos en espera cuando cambia el estado 
 *       (se ha producido un nuevo valor o se ha consumido el existente).</li>
 * </ul>
 * </p>
 * 
 * <p>En este ejemplo simple, la "cola" solo almacena un valor entero 
 * (`numero`). No es una cola general con múltiples posiciones.</p>
 * 
 * <p>Estados posibles:
 * <ul>
 *   <li><b>disponible = false</b>: La cola está vacía → 
 *       un productor puede introducir un valor, los consumidores esperan.</li>
 *   <li><b>disponible = true</b>: La cola está llena → 
 *       un consumidor puede retirar el valor, los productores esperan.</li>
 * </ul>
 * </p>
 * 
 * @author manu
 * @version 1.0
 */
class Cola {
    /** Valor entero almacenado en la cola (un único hueco). */
    private int numero;

    /** Indica si la cola está ocupada (true) o vacía (false). */
    private boolean disponible = false;

    /**
     * Método que introduce un valor en la cola.
     * 
     * <p>Si ya existe un valor disponible (cola llena), 
     * el hilo productor se bloquea y entra en espera 
     * hasta que un consumidor libere espacio.</p>
     * 
     * @param valor el número entero que se quiere colocar en la cola
     */
    public synchronized void put(int valor) {
        while (disponible) { // mientras haya un dato, no se puede producir
            try {
                wait(); // el productor espera a que la cola se vacíe
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        numero = valor;          // coloca el nuevo valor
        disponible = true;       // marca la cola como llena
        notifyAll();             // despierta a los consumidores en espera
    }

    /**
     * Método que extrae un valor de la cola.
     * 
     * <p>Si no hay valores disponibles (cola vacía), 
     * el hilo consumidor se bloquea y entra en espera 
     * hasta que un productor coloque un valor.</p>
     * 
     * @return el número entero extraído de la cola
     */
    public synchronized int get() {
        while (!disponible) { // mientras no haya un dato, no se puede consumir
            try {
                wait(); // el consumidor espera a que haya un valor
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        disponible = false;     // marca la cola como vacía
        notifyAll();            // despierta a los productores en espera
        return numero;          // devuelve el valor retirado
    }
}
