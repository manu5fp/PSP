package UD1.ejercicios;

/**
 * La clase Sumador calcula la suma de todos los números enteros comprendidos
 * entre dos valores dados, incluyendo los extremos.
 * 
 * <p>Si el primer número es mayor que el segundo, los intercambia automáticamente
 * para que la suma siempre se realice de menor a mayor.</p>
 * 
 * Ejemplo de uso:
 * <pre>
 *     Sumador s = new Sumador();
 *     long r = s.sumar(3, 7); // Devuelve 3+4+5+6+7 = 25
 * </pre>
 * 
 * <b>Nota:</b> El resultado se devuelve como {@code long} para evitar desbordamientos
 * si el rango de números es grande.
 * 
 * @author manu
 */
public class Sumador {

    /**
     * Suma todos los números enteros comprendidos entre {@code n1} y {@code n2},
     * incluyendo ambos extremos.
     * 
     * @param n1 Primer número del rango (puede ser mayor o menor que n2).
     * @param n2 Segundo número del rango.
     * @return Suma total de todos los enteros en el rango [n1, n2].
     */
    public long sumar(int n1, int n2) {
        long resultado = 0;

        // Si n1 es mayor que n2, intercambiar sus valores
        if (n1 > n2) {
            int aux = n1;
            n1 = n2;
            n2 = aux;
        }

        // Acumular la suma desde n1 hasta n2 (incluidos)
        for (int i = n1; i <= n2; i++) {
            resultado += i;
        }

        return resultado;
    }

    /**
     * Método principal que recibe dos argumentos numéricos por línea de comandos,
     * calcula su suma inclusiva y la muestra por pantalla.
     * 
     * Ejemplo de ejecución:
     * <pre>
     *     java Sumador 3 7
     * </pre>
     * 
     * Salida:
     * <pre>
     *     25
     * </pre>
     * 
     * @param args Argumentos de línea de comandos.
     *             args[0] → Primer número.
     *             args[1] → Segundo número.
     */
    public static void main(String[] args) {
        Sumador s = new Sumador();

        // Conversión de los argumentos de cadena a enteros
        int n1 = Integer.parseInt(args[0]);
        int n2 = Integer.parseInt(args[1]);

        // Llamada al método de suma
        long resultado = s.sumar(n1, n2);

        // Mostrar el resultado
        System.out.println(resultado);
    }
}
