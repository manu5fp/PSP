package UD2._07_Ejemplos.ProdConsumSimple;

/**
 * Clase principal de ejemplo del patrón Productor–Consumidor.
 * 
 * <p>Este programa crea una {@link Cola} compartida y lanza dos hilos:</p>
 * <ul>
 *   <li>Un hilo productor, representado por la clase {@link Productor}, 
 *       que introduce valores en la cola.</li>
 *   <li>Un hilo consumidor, representado por la clase {@link Consumidor}, 
 *       que extrae y procesa esos valores.</li>
 * </ul>
 * 
 * <h2>Sincronización:</h2>
 * <p>
 * La clase {@link Cola} utiliza los métodos {@code wait()} y {@code notifyAll()}
 * para coordinar el acceso concurrente:
 * </p>
 * <ul>
 *   <li>El productor se bloquea si intenta poner un valor cuando ya hay uno disponible.</li>
 *   <li>El consumidor se bloquea si intenta leer un valor cuando la cola está vacía.</li>
 *   <li>Cuando se produce un cambio de estado, se avisa al otro hilo para que pueda continuar.</li>
 * </ul>
 * 
 * <h2>Comportamiento esperado:</h2>
 * <ol>
 *   <li>El productor genera un número entero y lo inserta en la cola.</li>
 *   <li>El consumidor recupera ese número de la cola e imprime el valor consumido.</li>
 *   <li>Ambos hilos se coordinan para no pisarse ni dejar datos sin consumir.</li>
 * </ol>
 * 
 * <p>Este es un ejemplo clásico de sincronización en Java para ilustrar cómo
 * se comunican dos procesos mediante una región crítica controlada con monitores.</p>
 * 
 * @author manu
 */
public class EjemploPC {
    /**
     * Método principal que inicializa la cola y arranca los hilos productor y consumidor.
     *
     * @param args no se utilizan en este ejemplo
     */
    public static void main(String[] args) {
        // Cola compartida entre productor y consumidor
        Cola cola = new Cola();

        // Se crean y arrancan los hilos
        new Thread(new Productor(cola)).start();
        new Thread(new Consumidor(cola)).start();
    }
}
