package UD2._09_Ejemplos.Libros;

/**
 * Clase que representa un lector en un sistema de concurrencia donde un
 * {@link Libro} es el recurso compartido.
 * <p>
 * La clase implementa {@link Runnable} para que cada lector pueda ejecutarse en
 * un hilo independiente.
 * </p>
 *
 * <h3>Funcionamiento:</h3>
 * <ul>
 * <li>El lector entra en una sección crítica sincronizada sobre el objeto
 * {@link Libro}.</li>
 * <li>Si el libro no está terminado, el lector llama a {@link Object#wait()} y
 * queda bloqueado hasta que un {@link Escritor} invoque
 * {@link Object#notify()}.</li>
 * <li>Cuando el libro está disponible, el lector muestra un mensaje indicando
 * que puede leerlo.</li>
 * <li>Simula la lectura durmiendo 2 segundos.</li>
 * <li>Finalmente libera el recurso para que otro lector pueda acceder.</li>
 * </ul>
 *
 * <h3>Importancia de la sincronización:</h3>
 * <p>
 * El uso de {@code synchronized (book)} asegura que mientras un lector esté
 * esperando o leyendo, ningún otro hilo (lector o escritor) modifique el estado
 * del libro, evitando condiciones de carrera.
 * </p>
 *
 * <h3>Ejemplo de uso:</h3>
 * 
 * <pre>
 * Libro l = new Libro("Aprendiendo Java");
 * Thread lector1 = new Thread(new Lector(l), "Lector-1");
 * Thread lector2 = new Thread(new Lector(l), "Lector-2");
 * lector1.start();
 * lector2.start();
 * </pre>
 *
 * @author manu
 */
public class Lector implements Runnable {
	private Libro book;

	/**
	 * Constructor de la clase.
	 *
	 * @param book el libro compartido que se va a leer.
	 */
	public Lector(Libro book) {
		this.book = book;
	}

	/**
	 * Método ejecutado cuando se lanza el hilo asociado al lector.
	 * <p>
	 * Dentro de una sección crítica sincronizada sobre el objeto {@link Libro}:
	 * <ol>
	 * <li>El lector espera con {@link Object#wait()} a que el libro esté terminado
	 * por un {@link Escritor}.</li>
	 * <li>Cuando es notificado, simula la lectura durante 2 segundos.</li>
	 * <li>Finalmente muestra un mensaje indicando que otro lector puede
	 * continuar.</li>
	 * </ol>
	 * </p>
	 */
	@Override
	public void run() {
		synchronized (book) {
			if (!book.isCompleted()) {
				System.out.println(
						Thread.currentThread().getName() + " esperando que se escriba el libro: " + book.getTitle());
				try {
					// El lector espera hasta que un Escritor termine el libro
					book.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + ": El libro se ha escrito!! puedes leerlo");

				try {
					Thread.sleep(2000); // Simula el tiempo de lectura
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} 
			System.out.println(Thread.currentThread().getName() + " ha leído el libro " + book.getTitle()
						+ " puede leerlo el siguiente ...");
			book.notify();
			
		}
	}
}
