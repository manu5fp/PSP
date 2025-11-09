package UD2._08_Ejemplos.ProdConsLímite;

/**
 * Clase que representa al <b>productor</b> en el ejemplo del
 * patrón Productor–Consumidor con una {@link ColaLimitada}.
 * 
 * <p>
 * El productor genera valores enteros secuenciales (del 0 al 4 en este ejemplo)
 * e intenta insertarlos en la cola limitada mediante el método
 * {@link ColaLimitada#encolar(int)}. 
 * </p>
 * 
 * <h2>Diferencias respecto al {@link Productor} clásico:</h2>
 * <ul>
 *   <li>La cola {@link ColaLimitada} tiene un tamaño máximo fijo.</li>
 *   <li>El método {@link ColaLimitada#encolar(int)} devuelve un booleano que
 *       indica si la operación tuvo éxito ({@code true}) o si la cola estaba
 *       llena ({@code false}).</li>
 *   <li>En este ejemplo, el productor no bloquea el hilo ni espera a que haya
 *       espacio libre: simplemente intenta encolar y continúa.</li>
 * </ul>
 * 
 * <h2>Funcionamiento:</h2>
 * <ul>
 *   <li>Implementa la interfaz {@link Runnable} para poder ejecutarse en un hilo independiente.</li>
 *   <li>En el método {@link #run()}, produce 5 valores enteros e intenta
 *       encolarlos en la estructura compartida.</li>
 *   <li>Cada intento de inserción se acompaña de un mensaje en consola.</li>
 * </ul>
 * 
 * <h2>Relación con otras clases:</h2>
 * <ul>
 *   <li>Colabora con la clase {@link ColaLimitada}, que implementa la cola
 *       circular de tamaño máximo.</li>
 *   <li>Trabaja en conjunto con la clase {@link ConsumidorLimite}, que
 *       extrae los valores de la cola.</li>
 *   <li>Se ejecuta desde el programa principal {@link EjemploPCLimite}.</li>
 * </ul>
 * 
 * <h2>Ejemplo de uso:</h2>
 * <pre>{@code
 * ColaLimitada cola = new ColaLimitada(4);
 * Thread productor = new Thread(new ProductorLimite(cola));
 * productor.start();
 * }</pre>
 * 
 * @author manu
 */
class ProductorLimite implements Runnable {
    private ColaLimitada cola;

    /**
     * Constructor del productor con cola limitada.
     *
     * @param c la cola compartida de tamaño máximo fijo
     */
    public ProductorLimite(ColaLimitada c) { 
        cola = c; 
    }

    /**
     * Método principal del hilo productor.
     * Genera 5 valores enteros e intenta encolarlos en la cola.
     * Si la cola está llena, la inserción falla silenciosamente
     * y el hilo continúa con el siguiente valor.
     */
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            cola.encolar(i);
            System.out.println("Producido: " + i);
        }
    }
}
