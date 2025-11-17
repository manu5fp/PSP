package UD2._09_Ejemplos.Libros;

/**
 * Clase principal que ejecuta el ejemplo de sincronización
 * entre escritores y lectores de un libro.
 * <p>
 * Este programa ilustra el uso de {@link Object#wait()} y {@link Object#notify()}
 * para coordinar la cooperación entre hilos:
 * <ul>
 *   <li>Dos lectores (Diego y Manu) esperan a que el libro esté escrito.</li>
 *   <li>El escritor (Alan Moore) escribe el libro y notifica a los lectores.</li>
 * </ul>
 * </p>
 *
 * <h3>Flujo del programa:</h3>
 * <ol>
 *   <li>Se crea un objeto {@link Libro} como recurso compartido.</li>
 *   <li>Se lanzan dos hilos lectores que esperan a que el libro esté terminado.</li>
 *   <li>Tras una pausa de 3 segundos, se lanza el hilo escritor.</li>
 *   <li>El escritor escribe el libro, marca su estado como completado y notifica a los lectores.</li>
 *   <li>Los lectores despiertan y simulan la lectura del libro.</li>
 * </ol>
 *
 * <h3>Ejemplo de salida esperada:</h3>
 * <pre>
 * Diego esperando que se escriba el libro: Watchmen
 * Manu esperando que se escriba el libro: Watchmen
 * Alan Moore está escribiendo el libro: Watchmen
 * El libro se ha terminado
 * despertando un lector...
 * Diego: El libro se ha escrito!! puedes leerlo
 * Manu: El libro se ha escrito!! puedes leerlo
 * </pre>
 *
 * @author manu
 */
public class LibrosApp {
    /**
     * Método principal que ejecuta la aplicación de ejemplo.
     *
     * @param args argumentos de línea de comandos (no se usan en este ejemplo).
     */
    public static void main(String args[]) {
        // Recurso compartido
        Libro book = new Libro("Watchmen");

        // Dos lectores que esperan a que el libro esté escrito
        Lector l1 = new Lector(book);
        Lector l2 = new Lector(book);

        Thread t1 = new Thread(l1, "Diego");
        Thread t2 = new Thread(l2, "Manu");

        t1.start();
        t2.start();

        // Espera de 3 segundos antes de que aparezca el escritor
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Escritor que escribe el libro
        Escritor e = new Escritor("Alan Moore", book);
        Thread t3 = new Thread(e);
        t3.start();
    }
}
