package UD2.varios;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Clase de utilidad para la generación de números aleatorios.
 * 
 * Utiliza {@link ThreadLocalRandom}, que es preferible a {@link java.util.Random}
 * en aplicaciones concurrentes, ya que evita la contención de hilos y ofrece
 * mejor rendimiento en escenarios multihilo.
 * 
 * Proporciona métodos sobrecargados para obtener:
 * - Números enteros aleatorios en distintos rangos.
 * - Números reales (double) aleatorios en distintos rangos.
 * 
 * @author manu
 */
public class Azar {

    /**
     * Genera un número entero aleatorio sin restricciones de rango.
     * 
     * @return un entero aleatorio (positivo o negativo).
     */
    public static int entero() {
        return ThreadLocalRandom.current().nextInt();
    }

    /**
     * Genera un número entero aleatorio entre 0 y {@code max}, ambos inclusive.
     * 
     * @param max límite superior del rango.
     * @return un entero aleatorio en el rango [0, max].
     */
    public static int entero(int max) {
        return ThreadLocalRandom.current().nextInt(max + 1);
    }

    /**
     * Genera un número entero aleatorio entre {@code min} y {@code max}, ambos inclusive.
     * 
     * @param min límite inferior del rango.
     * @param max límite superior del rango.
     * @return un entero aleatorio en el rango [min, max].
     */
    public static int entero(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }

    /**
     * Genera un número real aleatorio en el rango [0.0, 1.0).
     * 
     * @return un número real entre 0.0 (inclusive) y 1.0 (exclusivo).
     */
    public static double real() {
        return ThreadLocalRandom.current().nextDouble();
    }

    /**
     * Genera un número real aleatorio en el rango [0.0, max].
     * 
     * @param max límite superior del rango.
     * @return un número real entre 0.0 (inclusive) y max (exclusivo).
     */
    public static double real(double max) {
        return ThreadLocalRandom.current().nextDouble(max);
    }

    /**
     * Genera un número real aleatorio en el rango [min, max].
     * 
     * @param min límite inferior del rango.
     * @param max límite superior del rango.
     * @return un número real entre min (inclusive) y max (exclusivo).
     */
    public static double real(double min, double max) {
        return ThreadLocalRandom.current().nextDouble(min, max);
    }
}

