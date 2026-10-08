package UD2._00_Hilos;

/**
 * La clase {@code LanzarHilos} ejemplifica la creación de tareas concurrentes en Java
 * implementando la interfaz {@code Runnable}.
 *
 * <h2>Descripción</h2>
 * Cada objeto de esta clase representa una tarea que realiza una cuenta atrás desde
 * un valor inicial hasta 0, mostrando en cada iteración su identificador único
 * y el número restante.
 * <p>
 * Al finalizar la cuenta atrás, el hilo muestra un mensaje de "Lanzamiento".
 * </p>
 *
 * <h2>Atributos principales</h2>
 * <ul>
 *   <li>{@code count}: número desde el que se inicia la cuenta atrás.</li>
 *   <li>{@code taskCount}: contador estático que se incrementa en cada
 *       nueva instancia de la clase, utilizado para asignar un identificador único.</li>
 *   <li>{@code id}: identificador de la tarea actual, derivado de {@code taskCount}.</li>
 * </ul>
 *
 * <h2>Detalles de la ejecución</h2>
 * <ol>
 *   <li>Si se invoca directamente el método {@code run()}, la tarea se ejecuta
 *       en el hilo principal, de manera secuencial (no hay concurrencia real).</li>
 *   <li>Si se usa {@code new Thread(obj).start()}, la tarea se ejecuta en un
 *       nuevo hilo independiente, logrando ejecución concurrente.</li>
 * </ol>
 *
 * <h2>Ejemplo de salida con {@code run()}</h2>
 * <pre>
 * #0 (5)
 * #0 (4)
 * #0 (3)
 * #0 (2)
 * #0 (1)
 * Lanzamiento (0)
 * #1 (4)
 * #1 (3)
 * #1 (2)
 * #1 (1)
 * Lanzamiento (1)
 * Comienza la cuenta atrás!
 * </pre>
 *
 * <h2>Ejemplo de salida con {@code start()}</h2>
 * <pre>
 * #0 (5)
 * #1 (4)
 * #0 (4)
 * #1 (3)
 * ...
 * Lanzamiento (0)
 * Lanzamiento (1)
 * Comienza la cuenta atrás!
 * </pre>
 * El orden varía en cada ejecución debido a la concurrencia real.
 *
 * <h2>Aspecto pedagógico</h2>
 * Este ejemplo es muy útil para explicar a los estudiantes la diferencia
 * entre llamar a {@code run()} (ejecución secuencial) y llamar a {@code start()}
 * (ejecución concurrente en un nuevo hilo).
 * 
 * @author manu
 * @version 1.0
 */
public class LanzarHilos implements Runnable {

    private int count = 10;
    private static int taskCount = 0;
    private final int id = taskCount;

    public LanzarHilos() {
        taskCount++;
    }

    public LanzarHilos(int count) {
        this.count = count;
        taskCount++;
    }

    @Override
    public void run() {
        while (count > 0) {
            System.out.println("#" + id + " (" + count + ")");
            count--;
            try {
                Thread.sleep(200); // Simula trabajo y permite ver la concurrencia
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Lanzamiento (" + id + ")");
    }

    public static void main(String[] args) {
        LanzarHilos h0 = new LanzarHilos(5);
        h0.run(); // Ejecución secuencial en el hilo principal
        // new Thread(h0).start(); // Descomentar para ejecución concurrente

        LanzarHilos h1 = new LanzarHilos(4);
        h1.run(); // Ejecución secuencial en el hilo principal
        // new Thread(h1).start(); // Descomentar para ejecución concurrente

        System.out.println("Comienza la cuenta atrás!");
    }
}
