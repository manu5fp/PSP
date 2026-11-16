package ud2.a2;

/** Una excepción no capturada termina solo su hilo, no el proceso. */
public class ExcepcionEnHilo {
	public static void main(String[] args) throws InterruptedException {
		Thread fallido = new Thread(() -> {
			try {
				throw new IllegalStateException("fallo simulado");
			}catch(IllegalStateException ex) {
				System.out.println(Thread.currentThread().getName() + " terminó por: " + ex.getMessage());
			};
		}, "hilo fallido");
		
		fallido.start();
		fallido.join();
		System.out.println("main sigue vivo. Estado de fallido: " + fallido.getState());
	}
}
/*
 * Salida: fallido terminó por: fallo simulado main sigue vivo. Estado de
 * fallido: TERMINATED
 */