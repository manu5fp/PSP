package UD2._03_Contador;

/**
 * Clase principal de prueba para la clase {@link ContadorSeguro}.
 * <p>
 * Este programa crea 1000 hilos, cada uno de los cuales incrementa
 * en una unidad el valor del contador compartido. Tras esperar
 * (mediante {@link Thread#join()}) a que todos los hilos finalicen,
 * se muestra el valor final de la cuenta.
 * </p>
 *
 * <h3>Comportamiento esperado:</h3>
 * <ul>
 *   <li>Si el contador fuera realmente seguro en concurrencia,
 *       el resultado final debería ser exactamente 1000.</li>
 *   <li>Sin embargo, al no estar sincronizado, suelen producirse
 *       <b>condiciones de carrera</b>: dos o más hilos leen el mismo
 *       valor de {@code cuenta} y lo actualizan, perdiendo incrementos.</li>
 * </ul>
 *
 * <h3>Ejemplo de salida:</h3>
 * <pre>
 * Cuenta final: 986
 * </pre>
 * (El valor puede variar en cada ejecución).
 *
 * <h3>Lección para estudiantes:</h3>
 * <p>
 * Este programa demuestra la importancia de la <b>sincronización en programación
 * concurrente</b>. Aunque se lancen 1000 incrementos, el resultado final no
 * siempre coincide debido a que los hilos acceden a la variable compartida
 * sin mecanismos de exclusión mutua.
 * </p>
 *
 * @author manu
 * @version 1.0
 */
public class ContadorApp {

    /**
     * Método principal que lanza la prueba del contador.
     *
     * @param args argumentos de línea de comandos (no usados).
     * @throws InterruptedException si algún hilo es interrumpido
     *         mientras se espera con {@code join()}.
     */
    public static void main(String[] args) throws InterruptedException {
        ContadorSeguro contador = new ContadorSeguro();
        Thread[] hilos = new Thread[1000];

        // Se crean y lanzan los hilos
        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new Thread(() -> contador.incrementar());
            hilos[i].start();
        }

        // Espera a que terminen todos los hilos
        for (Thread h : hilos) {
            h.join();
        }

        // Muestra el valor final del contador
        System.out.println("Cuenta final: " + contador.getCuenta());
    }
}
