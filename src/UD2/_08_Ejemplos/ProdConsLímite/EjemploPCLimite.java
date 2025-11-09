package UD2._08_Ejemplos.ProdConsLímite;

/**
 * Clase principal de ejemplo del patrón Productor–Consumidor
 * utilizando una cola con capacidad limitada ({@link ColaLimitada}).
 * 
 * <p>Este programa lanza dos hilos:</p>
 * <ul>
 *   <li>Un productor ({@link ProductorLimite}), que intenta insertar
 *       números en la cola mientras esta no esté llena.</li>
 *   <li>Un consumidor ({@link ConsumidorLimite}), que extrae números
 *       de la cola siempre que no esté vacía.</li>
 * </ul>
 * 
 * <h2>Diferencias respecto a {@link EjemploPC}:</h2>
 * <p>
 * A diferencia de la clase {@link Cola}, que usa los métodos de
 * sincronización con {@code wait()} y {@code notifyAll()}, la
 * implementación de {@link ColaLimitada} es más simple:
 * </p>
 * <ul>
 *   <li>Si la cola está llena, el productor no inserta el valor
 *       y devuelve {@code false}.</li>
 *   <li>Si la cola está vacía, el consumidor devuelve {@code -1}
 *       como valor especial.</li>
 * </ul>
 * 
 * <h2>Limitaciones:</h2>
 * <p>
 * Este ejemplo no bloquea los hilos cuando la cola está llena o vacía,
 * sino que devuelve un indicador. Eso puede generar situaciones como:
 * </p>
 * <ul>
 *   <li>Valores no insertados por el productor si la cola está llena.</li>
 *   <li>Consumidores que intentan leer sin éxito cuando no hay datos.</li>
 * </ul>
 * 
 * <h2>Uso didáctico:</h2>
 * <p>
 * Es útil para mostrar a los alumnos una implementación alternativa y más
 * básica, comparando con la versión de {@link EjemploPC} que sí utiliza
 * monitores (wait/notify). Esto permite discutir ventajas e inconvenientes
 * de cada enfoque.
 * </p>
 * 
 * @author manu
 */
public class EjemploPCLimite {
    /**
     * Método principal que inicializa la cola limitada y arranca
     * los hilos productor y consumidor.
     *
     * @param args no se utilizan en este ejemplo
     */
    public static void main(String[] args) {
        // Cola limitada a 4 elementos
        ColaLimitada cola = new ColaLimitada(4);

        // Se crean y lanzan los hilos productor y consumidor
        new Thread(new ProductorLimite(cola)).start();
        new Thread(new ConsumidorLimite(cola)).start();
    }
}
