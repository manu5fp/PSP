package UD2._02_Calculador;

/**
 * La clase {@code Calculador} representa una tarea concurrente que puede
 * ejecutarse en un hilo independiente. Su objetivo es simular un proceso
 * de cálculo, mostrando mensajes por pantalla y realizando pausas aleatorias.
 * <p>
 * Implementa la interfaz {@link Runnable}, por lo que puede asociarse a un
 * objeto de la clase {@link Thread} y ejecutarse de forma paralela con otras
 * instancias.
 * </p>
 *
 * <h2>Comportamiento</h2>
 * <ul>
 *   <li>El método {@code run()} ejecuta un bucle de 5 iteraciones.</li>
 *   <li>En cada iteración:
 *     <ul>
 *       <li>Muestra el nombre del hilo en ejecución y el número de iteración.</li>
 *       <li>Duerme durante un intervalo de tiempo aleatorio de hasta 2 segundos
 *           para simular un cálculo costoso.</li>
 *     </ul>
 *   </li>
 *   <li>Si el hilo es interrumpido durante la espera, captura la excepción
 *       {@link InterruptedException}, muestra un mensaje y finaliza la ejecución.</li>
 *   <li>Si completa todas las iteraciones, imprime un mensaje indicando que el
 *       hilo ha finalizado correctamente.</li>
 * </ul>
 *
 * <h2>Ejemplo de uso</h2>
 * <pre>{@code
 * public static void main(String[] args) {
 *     Runnable tarea = new Calculador();
 *     Thread hilo1 = new Thread(tarea, "Hilo-1");
 *     Thread hilo2 = new Thread(new Calculador(), "Hilo-2");
 *
 *     hilo1.start();
 *     hilo2.start();
 * }
 * }</pre>
 *
 * En este ejemplo, se lanzan dos hilos concurrentes que ejecutan la misma tarea
 * de cálculo en paralelo.
 *
 * @author manu
 * @version 1.0
 */
public class Calculador implements Runnable {

    /**
     * Método que contiene la lógica de la tarea a ejecutar en un hilo.
     * Recorre 5 iteraciones mostrando mensajes y simulando cálculos
     * con pausas aleatorias de hasta 2000 ms.
     */
    @Override
    public void run() {
        for (int i = 0; i < 1; i++) {
            System.out.println(Thread.currentThread().getName() + " calculando..." + i);
            try {
                Thread.sleep((long) (Math.random() * 2000));
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + " interrumpido");
                return; // finaliza inmediatamente si el hilo ha sido interrumpido
            }
        }
        System.out.println(Thread.currentThread().getName() + " finalizado.");
    }
}
