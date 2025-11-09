package UD2._08_Ejemplos.ProdConsLímite;

/**
 * La clase ConsumidorLimite implementa la interfaz {@link Runnable} 
 * y representa un hilo consumidor que trabaja con una {@link ColaLimitada}.
 * 
 * <p>A diferencia del ejemplo con {@link Cola}, en esta versión no se usan
 * métodos bloqueantes con {@code wait()} y {@code notify()}. 
 * En su lugar, la cola devuelve un valor especial cuando está vacía:</p>
 * <ul>
 *   <li>Si hay elementos disponibles → se devuelve y elimina el primero.</li>
 *   <li>Si la cola está vacía → se devuelve el valor -1.</li>
 * </ul>
 * 
 * <h2>Comportamiento:</h2>
 * <ul>
 *   <li>El consumidor intenta desencolar cinco valores de la cola.</li>
 *   <li>Imprime en consola cada valor consumido (incluido -1 si no había nada en la cola).</li>
 *   <li>Este diseño no bloquea al consumidor: puede continuar aunque no haya datos.</li>
 * </ul>
 * 
 * <h2>Relación con otras clases:</h2>
 * <ul>
 *   <li>Trabaja junto a la clase {@link ProductorLimite}, que introduce valores en la cola.</li>
 *   <li>Se apoya en la clase {@link ColaLimitada} para la comunicación entre hilos.</li>
 * </ul>
 * 
 * @author manu
 */
class ConsumidorLimite implements Runnable {
    /** Referencia a la cola limitada compartida. */
    private ColaLimitada cola;

    /**
     * Constructor del consumidor.
     * 
     * @param c la cola limitada de la cual se extraerán los valores
     */
    public ConsumidorLimite(ColaLimitada c) { 
        cola = c; 
    }

    /**
     * Método principal del hilo consumidor.
     * 
     * <p>Extrae cinco valores de la cola. 
     * Si la cola está vacía en algún momento, recibirá el valor {@code -1}.</p>
     */
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            int valor = cola.desencolar();
            System.out.println("Consumido: " + valor);
        }
    }
}
