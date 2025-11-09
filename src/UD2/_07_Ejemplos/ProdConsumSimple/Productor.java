package UD2._07_Ejemplos.ProdConsumSimple;

/**
 * Clase que representa al <b>productor</b> dentro del patrón
 * Productor–Consumidor con una {@link Cola}.
 * 
 * <p>
 * El productor genera valores enteros secuenciales (del 0 al 4 en este ejemplo)
 * y los introduce en la cola compartida mediante el método {@link Cola#put(int)}.
 * Si la cola ya contiene un valor pendiente de consumir, el productor queda
 * bloqueado hasta que el consumidor lo extraiga.
 * </p>
 * 
 * <h2>Funcionamiento:</h2>
 * <ul>
 *   <li>Se ejecuta en un hilo independiente, ya que implementa {@link Runnable}.</li>
 *   <li>En el bucle de {@link #run()}, el productor inserta 5 valores en la cola.</li>
 *   <li>Cada vez que inserta un valor, se muestra un mensaje por consola indicando
 *       que se ha producido.</li>
 * </ul>
 * 
 * <h2>Relación con otras clases:</h2>
 * <ul>
 *   <li>Colabora con la clase {@link Cola}, que gestiona el intercambio de datos
 *       y la sincronización entre hilos mediante {@code wait()} y {@code notifyAll()}.</li>
 *   <li>Trabaja en conjunto con la clase {@link Consumidor}, que extrae los valores.</li>
 *   <li>Se ejecuta desde el programa principal {@link EjemploPC}.</li>
 * </ul>
 * 
 * <h2>Ejemplo de uso:</h2>
 * <pre>{@code
 * Cola cola = new Cola();
 * Thread productor = new Thread(new Productor(cola));
 * productor.start();
 * }</pre>
 * 
 * @author manu
 */
class Productor implements Runnable {
    private Cola cola;

    /**
     * Constructor del productor.
     *
     * @param c la cola compartida donde se insertarán los valores producidos
     */
    public Productor(Cola c) { 
        cola = c; 
    }

    /**
     * Método principal del hilo productor.
     * Inserta 5 valores (del 0 al 4) en la cola compartida
     * y escribe un mensaje en consola por cada producción.
     */
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            cola.put(i);
            System.out.println("Producido: " + i);
        }
    }
}
