package UD2._05_Semaforo;

import java.util.concurrent.Semaphore;

/**
 * Clase que simula un <b>almacén limitado</b> donde se aplica
 * el patrón de concurrencia Productor–Consumidor usando semáforos.
 * 
 * <p>
 * Se utilizan tres semáforos:
 * <ul>
 *   <li>{@link #productor}: controla el número máximo de productos
 *       que se pueden almacenar (capacidad máxima del almacén).</li>
 *   <li>{@link #consumidor}: controla el número de productos disponibles
 *       para ser consumidos.</li>
 *   <li>{@link #mutex}: asegura la exclusión mutua en la sección crítica
 *       donde se incrementa o decrementa el contador de productos.</li>
 * </ul>
 * </p>
 * 
 * <h2>Funcionamiento</h2>
 * <ul>
 *   <li>Cuando un productor llama a {@link #producir(String)}, 
 *       se bloquea si el almacén está lleno (no hay permisos en {@code productor}).</li>
 *   <li>Cuando un consumidor llama a {@link #consumir(String)}, 
 *       se bloquea si el almacén está vacío (no hay permisos en {@code consumidor}).</li>
 *   <li>El semáforo {@code mutex} garantiza que solo un hilo a la vez
 *       puede modificar la variable compartida {@link #producto}.</li>
 * </ul>
 * 
 * <h2>Ejemplo de ejecución</h2>
 * <pre>{@code
 * public static void main(String[] args) {
 *     Almacen a = new Almacen();
 *     new Thread(() -> a.producir("productor 1")).start();
 *     new Thread(() -> a.consumir("consumidor 1")).start();
 * }
 * }</pre>
 * 
 * <h2>Relación con otras clases</h2>
 * <ul>
 *   <li>Este ejemplo es autocontenido: la lógica de productores y consumidores
 *       está implementada directamente en el método {@code main()}.</li>
 *   <li>Alternativamente, podrían crearse clases {@code Productor} y {@code Consumidor}
 *       que llamen a {@link #producir(String)} y {@link #consumir(String)}.</li>
 * </ul>
 * 
 * @author manu
 */
public class Almacen {
    /** Capacidad máxima del almacén */
    private final int MAX_LIMITE = 20;

    /** Contador de productos actualmente en el almacén */
    private int producto = 0;

    /** Semáforo que controla la capacidad disponible para productores */
    private Semaphore productor = new Semaphore(MAX_LIMITE);

    /** Semáforo que controla la disponibilidad de productos para consumidores */
    private Semaphore consumidor = new Semaphore(0);

    /** Semáforo binario para garantizar exclusión mutua en secciones críticas */
    private Semaphore mutex = new Semaphore(1);

    /**
     * Método que simula la producción de un producto por un hilo productor.
     * 
     * <p>
     * El productor se bloquea si el almacén está lleno.
     * Una vez dentro de la sección crítica, incrementa el número de productos.
     * Luego libera a un consumidor indicando que hay un producto disponible.
     * </p>
     *
     * @param nombreProductor nombre o identificador del productor (para trazas de ejecución)
     */
    public void producir(String nombreProductor) {
        try {
            productor.acquire();       // espera si almacén lleno
            mutex.acquire();           // entra en sección crítica

            producto++;
            System.out.println(nombreProductor + " produce. Productos: " + producto);

            mutex.release();           // sale de sección crítica
            //Thread.sleep(200);         // simulación de tiempo de producción
            consumidor.release();      // avisa a un consumidor
            
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Método que simula el consumo de un producto por un hilo consumidor.
     * 
     * <p>
     * El consumidor se bloquea si el almacén está vacío.
     * Una vez dentro de la sección crítica, decrementa el número de productos.
     * Luego libera a un productor indicando que hay un hueco disponible.
     * </p>
     *
     * @param nombreConsumidor nombre o identificador del consumidor (para trazas de ejecución)
     */
    public void consumir(String nombreConsumidor) {
        try {
            consumidor.acquire();      // espera si almacén vacío
            mutex.acquire();           // entra en sección crítica

            producto--;
            System.out.println(nombreConsumidor + " consume. Productos: " + producto);

            mutex.release();           // sale de sección crítica
            //Thread.sleep(200);         // simulación de tiempo de consumo
            productor.release();       // avisa a un productor
            
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Método principal de prueba.
     * 
     * <p>
     * Se lanzan 100 hilos aleatoriamente como productores o consumidores
     * para simular concurrencia en el almacén.
     * </p>
     */
    public static void main(String[] args) {
        Almacen a = new Almacen();

        // Creamos varios productores y consumidores de forma equilibrada
        for (int i = 0; i < 100; i++) {
            final int id = i; // cada hilo tiene su id
            if ((int)(Math.random() * 2) == 0) {
                new Thread(() -> a.consumir("consumidor " + id)).start();
            } else {
                new Thread(() -> a.producir("productor " + id)).start();
            }
        }
    }
}
