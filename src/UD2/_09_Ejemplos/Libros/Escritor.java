package UD2._09_Ejemplos.Libros;

/**
 * Clase que representa un escritor en un sistema de concurrencia donde un
 * {@link Libro} es el recurso compartido.
 * <p>
 * La clase implementa {@link Runnable} para que cada escritor pueda ejecutarse
 * en un hilo independiente.
 * </p>
 *
 * <h3>Funcionamiento:</h3>
 * <ul>
 * <li>El escritor entra en una sección crítica sincronizada sobre el objeto
 * {@link Libro}.</li>
 * <li>Simula el proceso de escritura durmiendo el hilo durante 3 segundos.</li>
 * <li>Cuando termina, marca el libro como completado y notifica con
 * {@link Object#notify()} a un posible lector que esté esperando.</li>
 * </ul>
 *
 * <h3>Importancia de la sincronización:</h3>
 * <p>
 * El uso de {@code synchronized (book)} asegura que sólo un hilo (escritor o
 * lector) pueda interactuar con el libro al mismo tiempo, evitando
 * inconsistencias en su estado.
 * </p>
 *
 * <h3>Ejemplo de uso:</h3>
 * 
 * <pre>
 * Libro l = new Libro("Aprendiendo Java");
 * Thread escritor = new Thread(new Escritor("Miguel", l));
 * escritor.start();
 * </pre>
 *
 * @author manu
 */
public class Escritor implements Runnable {
	private String nombre;
	private Libro book;

	/**
	 * Constructor de la clase.
	 *
	 * @param nombre nombre del escritor (para fines de identificación en los
	 *               mensajes).
	 * @param book   el libro compartido que se va a escribir.
	 */
	public Escritor(String nombre, Libro book) {
		this.nombre = nombre;
		this.book = book;
	}

	/**
	 * Devuelve el nombre del escritor.
	 *
	 * @return nombre del escritor.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Método ejecutado cuando se lanza el hilo asociado al escritor.
	 * <p>
	 * Dentro de una sección crítica sincronizada sobre el objeto {@link Libro}:
	 * <ol>
	 * <li>Se muestra un mensaje indicando que el escritor está escribiendo.</li>
	 * <li>Se simula la escritura con {@link Thread#sleep(long)} durante 3
	 * segundos.</li>
	 * <li>Se marca el libro como completado.</li>
	 * <li>Se notifica a un lector que pueda estar esperando en
	 * {@code book.wait()}.</li>
	 * </ol>
	 * </p>
	 */
	@Override
	public void run() {
		synchronized (book) {
			System.out.println(nombre + " está escribiendo el libro: " + book.getTitle());
			try {
				Thread.sleep(3000); // Simula el proceso de escritura
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			book.setCompleted(true);
			System.out.println("El libro se ha terminado");

			// Notifica a un lector que esté esperando en el objeto book
			book.notify();
			System.out.println("despertando a los lectores...");
		}
	}
}
