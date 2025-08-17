package UD2.ejemplos;

/**
 * La clase {@code HilosLambda} demuestra distintas maneras de crear y ejecutar hilos en Java.
 * <p>
 * Este ejemplo es especialmente útil para mostrar las diferencias entre:
 * </p>
 * <ul>
 *   <li>Uso directo de una expresión <b>lambda</b> al crear un {@code Thread}.</li>
 *   <li>Creación de un objeto {@code Runnable} mediante lambda y posterior 
 *       paso al constructor de {@code Thread}.</li>
 *   <li>Uso de una <b>clase anónima</b> que implementa la interfaz {@code Runnable}.</li>
 * </ul>
 *
 * <h2>Descripción del flujo</h2>
 * <ol>
 *   <li>Se crea y ejecuta un hilo mediante una expresión lambda directamente en el
 *       constructor de {@code Thread}.</li>
 *   <li>Se declara un {@code Runnable} con lambda, que luego se pasa a un nuevo hilo
 *       mediante {@code new Thread(runnable).start()}.</li>
 *   <li>Se declara un {@code Runnable} usando una clase anónima, que también se pasa
 *       a un hilo y se ejecuta.</li>
 * </ol>
 *
 * <h2>Aspectos pedagógicos</h2>
 * <ul>
 *   <li><b>Expresiones lambda:</b> Son la forma más moderna y concisa de implementar
 *       la interfaz funcional {@code Runnable}.</li>
 *   <li><b>Clases anónimas:</b> Aunque más verbosas, siguen siendo útiles cuando se
 *       necesita añadir lógica más compleja o varios métodos.</li>
 *   <li><b>Ejecución concurrente:</b> Los tres hilos se lanzan de forma independiente,
 *       por lo que el orden de los mensajes no está garantizado.</li>
 * </ul>
 *
 * <h2>Ejemplo de salida posible</h2>
 * <pre>
 * Hola desde un hilo con lambda
 * Hola desde un hilo con clase anónima
 * Hola desde un hilo con lambda
 * </pre>
 * 
 * El orden puede variar en cada ejecución.
 *
 * @author manu
 * @version 1.0
 */
public class HilosLambda {

    public static void main(String[] args) {

        // 1. Lambda directa en el constructor del Thread
        Thread hilo1 = new Thread(() -> {
            System.out.println("Hola desde un hilo con lambda");
        });
        hilo1.start();

        // 2. Lambda asignada a un Runnable y pasada a un Thread
        Runnable hilo2 = () -> {
            System.out.println("Hola desde un hilo con lambda");
        };
        new Thread(hilo2).start();

        // 3. Runnable implementado mediante clase anónima
        Runnable hilo3 = new Runnable() {
            public void run() {
                System.out.println("Hola desde un hilo con clase anónima");
            }
        };
        new Thread(hilo3).start();
    }
}
