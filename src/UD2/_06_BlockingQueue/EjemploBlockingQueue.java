package UD2._06_BlockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * La clase {@code EjemploBlockingQueue} demuestra el uso de la interfaz
 * {@link BlockingQueue} y su implementación {@link ArrayBlockingQueue}
 * para resolver el clásico problema del productor-consumidor
 * en programación concurrente.
 * 
 * <p>En este ejemplo, un hilo productor genera una secuencia de números enteros
 * y los introduce en una cola con capacidad limitada. Otro hilo consumidor
 * extrae esos números de la cola y los procesa. La sincronización entre ambos
 * se realiza automáticamente mediante los métodos bloqueantes
 * {@link BlockingQueue#put(Object)} y {@link BlockingQueue#take()},
 * sin necesidad de utilizar {@code synchronized}, {@code wait()} ni {@code notify()}.</p>
 * 
 * <p><strong>Conceptos que ilustra este ejemplo:</strong></p>
 * <ul>
 *   <li>Comunicación segura entre hilos mediante estructuras bloqueantes.</li>
 *   <li>Bloqueo automático de los hilos productores o consumidores
 *       según la disponibilidad de la cola.</li>
 *   <li>Uso de clases del paquete {@code java.util.concurrent}.</li>
 * </ul>
 * 
 * <p>Ejemplo de ejecución:</p>
 * <pre>
 *     java UD2.ejemplos.EjemploBlockingQueue
 * </pre>
 * 
 * <p>La salida mostrará una secuencia alternada de mensajes "Producido" y
 * "Consumido", indicando el intercambio de datos entre los hilos.</p>
 * 
 * @author (Tu nombre)
 */
public class EjemploBlockingQueue {

    /**
     * Punto de entrada principal del programa. Crea una cola bloqueante con
     * capacidad limitada, define las tareas de productor y consumidor y las
     * ejecuta en hilos independientes.
     * 
     * @param args No se utilizan argumentos en este ejemplo.
     */
    public static void main(String[] args) {

        // Crear una cola bloqueante con capacidad máxima de 10 elementos.
        // Si está llena, los productores se bloquean hasta que haya espacio disponible.
        // Si está vacía, los consumidores esperan hasta que haya un elemento.
        BlockingQueue<Integer> cola = new ArrayBlockingQueue<>(10);

        /**
         * Tarea Productor:
         * ----------------
         * Este hilo genera una secuencia de 50 números enteros (0 a 49)
         * y los introduce en la cola mediante el método bloqueante put().
         * Si la cola está llena, el hilo se suspende automáticamente
         * hasta que un consumidor extraiga algún elemento.
         */
        Runnable productor = () -> {
            try {
                for (int i = 0; i < 50; i++) {
                    cola.put(i); // Inserta en la cola o espera si está llena
                    System.out.println("Producido: " + i);
                }
            } catch (InterruptedException e) {
                // Si el hilo es interrumpido durante la espera
                Thread.currentThread().interrupt();
            }
        };

        /**
         * Tarea Consumidor:
         * -----------------
         * Este hilo extrae elementos de la cola utilizando take().
         * Si la cola está vacía, el hilo se bloquea automáticamente
         * hasta que haya elementos disponibles.
         */
        Runnable consumidor = () -> {
            try {
                for (int i = 0; i < 50; i++) {
                    int valor = cola.take(); // Extrae de la cola o espera si está vacía
                    System.out.println("Consumido: " + valor);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        // Iniciar los hilos productor y consumidor
        new Thread(productor).start();
        new Thread(consumidor).start();
    }
}
