package UD2._08_Ejemplos.ProdConsLímite;

import java.util.LinkedList;

/**
 * La clase ColaLimitada implementa una cola de enteros con 
 * una capacidad máxima determinada en su construcción.
 * 
 * <p>Esta cola está pensada como recurso compartido entre 
 * hilos productores y consumidores. Sin embargo, en esta versión 
 * simplificada no se utilizan métodos de espera (`wait()`, `notifyAll()`), 
 * sino que se devuelve un valor indicativo cuando no es posible 
 * realizar la operación.</p>
 * 
 * <h2>Características:</h2>
 * <ul>
 *   <li>La cola se implementa con una {@link LinkedList}.</li>
 *   <li>El acceso está protegido con bloques `synchronized` para evitar 
 *       condiciones de carrera.</li>
 *   <li>Si un productor intenta encolar cuando la cola está llena, 
 *       la operación falla (devuelve <code>false</code>).</li>
 *   <li>Si un consumidor intenta desencolar cuando la cola está vacía, 
 *       la operación devuelve <code>-1</code>.</li>
 * </ul>
 * 
 * <h2>Diferencias respecto a {@link Cola}:</h2>
 * <ul>
 *   <li>Permite almacenar múltiples elementos (no solo uno).</li>
 *   <li>No utiliza bloqueo con espera/avisos, sino control por valores 
 *       de retorno (<code>false</code> o <code>-1</code>).</li>
 *   <li>Es útil para ilustrar una implementación más simple de la 
 *       comunicación productor-consumidor.</li>
 * </ul>
 * 
 * @author manu
 * @version 1.0
 */
public class ColaLimitada {
    /** Capacidad máxima de la cola. */
    private int MAX_ELEMENTOS;

    /** Estructura de datos interna para almacenar los valores. */
    private LinkedList<Integer> cola;

    /**
     * Constructor de la cola limitada.
     * 
     * @param max número máximo de elementos que la cola puede almacenar
     */
    public ColaLimitada(int max) {
        cola = new LinkedList<>();
        this.MAX_ELEMENTOS = max;
    }

    /**
     * Inserta un valor al final de la cola.
     * 
     * <p>Si la cola ya está llena (su tamaño ha alcanzado MAX_ELEMENTOS), 
     * la operación no se realiza y se devuelve <code>false</code>.</p>
     * 
     * @param numero valor entero a añadir
     * @return <code>true</code> si la inserción se realizó correctamente, 
     *         <code>false</code> si la cola estaba llena
     */
    public synchronized boolean encolar(int numero) {
        if (cola.size() == MAX_ELEMENTOS) 
            return false;           // no se puede añadir más
        cola.addLast(numero);       // inserta al final
        return true;
    }

    /**
     * Extrae y devuelve el valor situado al inicio de la cola.
     * 
     * <p>Si la cola está vacía, devuelve <code>-1</code> como valor 
     * especial para indicar que no hay elementos disponibles.</p>
     * 
     * @return el valor entero extraído, o <code>-1</code> si la cola está vacía
     */
    public synchronized int desencolar() {
        if (cola.isEmpty()) 
            return -1;              // no hay datos
        return cola.removeFirst();  // elimina y devuelve el primero
    }
}
