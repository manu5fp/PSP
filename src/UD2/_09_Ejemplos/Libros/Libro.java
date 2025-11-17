package UD2._09_Ejemplos.Libros;

/**
 * Clase que representa un libro dentro de un sistema concurrente
 * en el que participan escritores y lectores.
 * <p>
 * Esta clase funciona como recurso compartido:
 * <ul>
 *   <li>Un {@link Escritor} modifica su estado cuando el libro se ha terminado.</li>
 *   <li>Uno o varios {@link Lector} esperan a que el libro esté completado para poder leerlo.</li>
 * </ul>
 * </p>
 *
 * <h3>Sincronización:</h3>
 * <p>
 * Los métodos de esta clase no están sincronizados por sí mismos, pero la
 * sincronización se realiza externamente en las clases {@link Escritor} y {@link Lector},
 * utilizando el propio objeto {@code Libro} como monitor.
 * Esto permite el uso de {@link Object#wait()} y {@link Object#notify()}.
 * </p>
 *
 * <h3>Ejemplo de uso:</h3>
 * <pre>
 * Libro l = new Libro("Aprendiendo Java");
 * Escritor escritor = new Escritor("Autor-1", l);
 * Lector lector1 = new Lector(l);
 * Lector lector2 = new Lector(l);
 *
 * new Thread(escritor).start();
 * new Thread(lector1, "Lector-1").start();
 * new Thread(lector2, "Lector-2").start();
 * </pre>
 *
 * @author manu
 */
public class Libro {
    /** Título del libro */
    private String titulo;
    /** Indica si el libro está terminado (true) o aún en proceso (false) */
    private boolean completado;

    /**
     * Constructor de la clase.
     *
     * @param titulo título del libro que se va a crear.
     */
    public Libro(String titulo) {
        this.titulo = titulo;
        this.completado = false; // por defecto un libro no está terminado
    }

    /**
     * Obtiene el título del libro.
     *
     * @return el título actual del libro.
     */
    public String getTitle() {
        return titulo;
    }

    /**
     * Establece un nuevo título para el libro.
     *
     * @param titulo el nuevo título.
     */
    public void setTitle(String titulo) {
        this.titulo = titulo;
    }

    /**
     * Consulta si el libro está terminado.
     *
     * @return {@code true} si el libro ya está terminado, {@code false} en caso contrario.
     */
    public boolean isCompleted() {
        return completado;
    }

    /**
     * Marca el estado de completitud del libro.
     *
     * @param completado {@code true} si el libro se ha terminado,
     *                   {@code false} si aún está en proceso.
     */
    public void setCompleted(boolean completado) {
        this.completado = completado;
    }
}
