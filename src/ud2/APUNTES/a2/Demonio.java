package ud2.a2;

/**
 * La JVM no espera a los hilos demonio: su finally puede no llegar a
 * ejecutarse.
 */
public class Demonio {
	public static void main(String[] args) throws InterruptedException {
		Thread hiloNormal = new Thread(() -> {
			while (!Thread.currentThread().isInterrupted()) {
				// Tarea simulada
			}
			System.out.println("Mensaje del hilo al parar");

		});

		Thread demonio = new Thread(() -> {
			try {
				while (true) {
					Thread.sleep(50);
				}
			} catch (InterruptedException e) {
				System.out.println("Mensaje del demonio al parar");
			}
		});
		demonio.setDaemon(true); // Debe hacerse antes de start()

		hiloNormal.start();
		demonio.start();
		Thread.sleep(120);

		hiloNormal.interrupt();
		hiloNormal.join();
		Thread.sleep(120);
		demonio.interrupt();
		// demonio.join();
		System.out.println("main termina; la JVM no espera al demonio");
	}
}