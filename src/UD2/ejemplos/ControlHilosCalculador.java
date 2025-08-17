package UD2.ejemplos;

/**
 * La clase {@code ControlHilosCalculador} contiene un método principal que
 * demuestra cómo controlar la ejecución de varios hilos basados en la tarea
 * {@link Calculador}.
 * <p>
 * Este ejemplo es útil para comprender:
 * </p>
 * <ul>
 *   <li>La creación de hilos a partir de un objeto {@code Runnable}.</li>
 *   <li>El ajuste de prioridades de los hilos.</li>
 *   <li>La coordinación de la ejecución mediante los métodos {@code join()} e
 *       {@code interrupt()}.</li>
 * </ul>
 *
 * <h2>Descripción del flujo</h2>
 * <ol>
 *   <li>Se crean dos hilos, cada uno ejecutando una instancia de {@link Calculador}:
 *       <ul>
 *         <li>{@code hilo1} llamado "Hilo rápido", con máxima prioridad.</li>
 *         <li>{@code hilo2} llamado "Hilo lento", con mínima prioridad.</li>
 *       </ul>
 *   </li>
 *   <li>Ambos hilos se inician con {@code start()}.</li>
 *   <li>El programa principal espera a que {@code hilo1} termine usando {@code join()}.</li>
 *   <li>Una vez finalizado {@code hilo1}, el programa interrumpe a {@code hilo2}
 *       con {@code interrupt()}, lo que provoca que su método {@code run()} termine
 *       anticipadamente.</li>
 *   <li>Finalmente, se imprime un mensaje indicando que el programa ha concluido.</li>
 * </ol>
 *
 * <h2>Aspectos pedagógicos</h2>
 * <ul>
 *   <li><b>Prioridad de hilos:</b> Aunque se asignen prioridades, la planificación
 *       real depende del sistema operativo; no se garantiza que el hilo rápido
 *       siempre ejecute primero, pero suele tener más oportunidades de ejecución.</li>
 *   <li><b>Método {@code join()}:</b> Permite sincronizar el hilo principal con uno
 *       de los hilos secundarios, asegurando que este termine antes de continuar.</li>
 *   <li><b>Interrupciones:</b> Sirven para solicitar a un hilo que termine su ejecución.
 *       El hilo debe gestionar la excepción {@code InterruptedException}, como hace
 *       {@link Calculador} en su método {@code run()}.</li>
 * </ul>
 *
 * <h2>Ejemplo de salida posible</h2>
 * <pre>
 * Hilo rápido calculando...0
 * Hilo lento calculando...0
 * Hilo rápido calculando...1
 * ...
 * Hilo rápido finalizado.
 * Hilo lento interrumpido
 * Programa finalizado
 * </pre>
 *
 * La salida exacta puede variar en cada ejecución debido a la concurrencia.
 *
 * @author manu
 * @version 1.0
 */
public class ControlHilosCalculador {
    public static void main(String[] args) throws InterruptedException {
        Thread hilo1 = new Thread(new Calculador(), "Hilo rápido");
        Thread hilo2 = new Thread(new Calculador(), "Hilo lento");

        // Se asignan prioridades extremas
        hilo1.setPriority(Thread.MAX_PRIORITY);
        hilo2.setPriority(Thread.MIN_PRIORITY);

        // Se inician ambos hilos
        hilo1.start();
        hilo2.start();

        // El hilo principal espera a que hilo1 termine
        hilo1.join();

        // Una vez finalizado hilo1, se interrumpe hilo2
        hilo2.interrupt();

        // Mensaje final del programa principal
        System.out.println("Programa finalizado");
    }
}
