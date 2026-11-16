package ud2.a3;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * La prioridad es una sugerencia: observa el trabajo hecho por cada hilo.
 */
public class Prioridades {
	
	static class Trabajador extends Thread {
		private final AtomicBoolean seguir;
		private final CountDownLatch salida;
		long iteraciones;

		Trabajador(String nombre, int prioridad, AtomicBoolean seguir, CountDownLatch salida) {
			super(nombre);
			setPriority(prioridad);
			this.seguir = seguir;
			this.salida = salida;
		}

		@Override
		public void run() {
			try {
				salida.await();
			} catch (InterruptedException e) {
				return;
			}
			while (seguir.get())
				iteraciones++;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Rango: " + Thread.MIN_PRIORITY + " a " + Thread.MAX_PRIORITY + ", por defecto "
				+ Thread.NORM_PRIORITY);
		AtomicBoolean seguir = new AtomicBoolean(true);
		CountDownLatch salida = new CountDownLatch(1);
		
		Trabajador alta = new Trabajador("alta", Thread.MAX_PRIORITY, seguir, salida);
		Trabajador baja = new Trabajador("baja", Thread.MIN_PRIORITY, seguir, salida);
		alta.start();
		baja.start();
		
		salida.countDown();
		Thread.sleep(1000);
		seguir.set(false);
		
		alta.join();
		baja.join();
		System.out.printf("Relación alta/baja: %.2f%n", (double) alta.iteraciones / baja.iteraciones);
		try {
			alta.setPriority(11);
		} catch (IllegalArgumentException e) {
			System.out.println("setPriority(11): IllegalArgumentException");
		}
	}
}
/*
 * Salida de una ejecución en un equipo Linux con varios núcleos: Rango: 1 a 10,
 * por defecto 5 Relación alta/baja: 1,02 
 * setPriority(11): IllegalArgumentException
 */