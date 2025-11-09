package UD2._07_Ejemplos.ProdConsumSimple;

/**
 * La clase Consumidor implementa la interfaz {@link Runnable} 
 * y representa un hilo consumidor dentro del patrón Productor–Consumidor.
 * 
 * <p>Un consumidor extrae datos de una {@link Cola} compartida. 
 * Cada vez que llama a {@link Cola#get()}, puede quedarse bloqueado 
 * si la cola está vacía, hasta que un productor deposite un nuevo valor.</p>
 * 
 * <h2>Comportamiento:</h2>
 * <ul>
 *   <li>En este ejemplo, cada consumidor obtiene exactamente 5 valores de la cola.</li>
 *   <li>Después de cada extracción, muestra el valor consumido en consola.</li>
 *   <li>El acceso a la cola está sincronizado en la propia clase {@link Cola}, 
 *       por lo que aquí no se necesitan mecanismos adicionales de concurrencia.</li>
 * </ul>
 * 
 * <h2>Relación con otras clases:</h2>
 * <ul>
 *   <li>Trabaja junto a la clase {@link Productor}, que introduce valores en la cola.</li>
 *   <li>Utiliza una instancia compartida de {@link Cola} para la comunicación entre hilos.</li>
 * </ul>
 * 
 * @author manu
 */
class Consumidor implements Runnable {
    /** Referencia a la cola compartida. */
    private Cola cola;

    /**
     * Constructor del consumidor.
     * 
     * @param c la cola de la cual se extraerán los valores
     */
    public Consumidor(Cola c) { 
        cola = c; 
    }

    /**
     * Método principal del hilo consumidor.
     * 
     * <p>Extrae cinco valores de la cola (bloqueándose si está vacía) 
     * e imprime cada uno de ellos.</p>
     */
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            int valor = cola.get();   // puede bloquearse si no hay datos
            System.out.println("Consumido: " + valor);
        }
    }
}
