package ud2.APUNTES.a2;

/**
 * Finalización cooperativa con interrupt(): el hilo decide cómo y cuándo
 * terminar.
 */
public class Interrupcion {
	static class Vigilante implements Runnable {
		@Override
		public void run() {
			int rondas = 0;
			try {
				while (!Thread.currentThread().isInterrupted()) {
					rondas++;
					Thread.sleep(50); // Punto de bloqueo: lanza la excepción si hay interrupción
				}
			} catch (InterruptedException e) {    //	Al lanzarse la excepción, el indicador de interrupción queda borrado
				
				System.out.println("Indicador tras la excepción: " + Thread.currentThread().isInterrupted());
			} finally {
				System.out.println("Limpieza realizada; rondas > 0: " + (rondas > 0));
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread hilo = new Thread(new Vigilante());
		hilo.start();
		Thread.sleep(200);
		hilo.interrupt();
		hilo.join();
		System.out.println("Estado final: " + hilo.getState());
		while (!Thread.currentThread().isInterrupted()) {
			// Sin bloqueo: interrupt() solo activa el indicador y no detiene nada
			Thread.currentThread().interrupt();													
			System.out.println("isInterrupted(): " + Thread.currentThread().isInterrupted());	// Consulta el indicador 
			System.out.println("interrupted(): " + Thread.interrupted());						// Consulta y borra el indicador
			System.out.println("interrupted(): " + Thread.interrupted());
			Thread.currentThread().interrupt();  												// si comentamos esta línea no para nunca	
		}
	}
}
/*
 * Salida:
 * 
 * Indicador tras la excepción: false 
 * Limpieza realizada; rondas > 0: true
 * Estado final: TERMINATED 
 * isInterrupted(): true 
 * interrupted(): true  
 * interrupted(): false
 * 
 */