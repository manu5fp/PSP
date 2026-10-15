package UD2.ejemplos;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerExample {
// Un contador normal, no atómico
	private static int nonAtomicCounter = 0;
// Un contador atómico
	private static AtomicInteger atomicCounter = new AtomicInteger(0);
// Número de hilos a crear
	private static final int NUM_THREADS = 100;
// Número de incrementos por hilo
	private static final int INCREMENTS_PER_THREAD = 1000;

	public static void main(String[] args) throws InterruptedException {
		System.out.println("--- Demostración con int normal (NO Thread-Safe) ---");

		// Crear un array para los hilos del contador no atómico
		Thread[] nonAtomicThreads = new Thread[NUM_THREADS];
		
		for (int i = 0; i < NUM_THREADS; i++) {
			nonAtomicThreads[i] = new Thread(() -> {
				for (int j = 0; j < INCREMENTS_PER_THREAD; j++) {
					nonAtomicCounter++; // Operación no atómica
				}
			});
			nonAtomicThreads[i].start(); // Iniciar el hilo

		}

		//Esperar a que todos los hilos no atómicos terminen
		for (Thread thread : nonAtomicThreads) {
			thread.join();
		}

		//El resultado final probablemente no será el esperado debido a condiciones de carrera
		System.out.println("Valor final del contador NO atómico: " + nonAtomicCounter);
		System.out.println("Valor esperado (NO atómico): " + (NUM_THREADS * INCREMENTS_PER_THREAD));
		if (nonAtomicCounter != (NUM_THREADS * INCREMENTS_PER_THREAD)) {
			System.out.println("¡Advertencia: Hubo una condición de carrera en el contador NO atómico!");
		} else {
			System.out.println(
					"Sorprendentemente, el contador NO atómico es correcto (esto es raro en ejecuciones concurrentes).");
		}
		System.out.println("\n--- Demostración con AtomicInteger (Thread-Safe) ---");

		//Crear un array para los hilos del contador atómico
		Thread[] atomicThreads = new Thread[NUM_THREADS];
		for (int i = 0; i < NUM_THREADS; i++) {
			atomicThreads[i] = new Thread(() -> {
				for (int j = 0; j < INCREMENTS_PER_THREAD; j++) {
					atomicCounter.incrementAndGet(); // Operación atómica
				}
			});
			atomicThreads[i].start(); // Iniciar el hilo

		}

		//Esperar a que todos los hilos atómicos terminen
		for (Thread thread : atomicThreads) {
			thread.join();
		}

		//El resultado final siempre será el esperado
		System.out.println("Valor final del contador ATÓMICO: " + atomicCounter.get());
		System.out.println("Valor esperado (ATÓMICO): " + (NUM_THREADS * INCREMENTS_PER_THREAD));
		if (atomicCounter.get() == (NUM_THREADS * INCREMENTS_PER_THREAD)) {
			System.out.println("¡El contador ATÓMICO es correcto, como se esperaba!");
		} else {
			System.out.println("¡Error inesperado en el contador ATÓMICO! (Esto no debería suceder).");
		}
	}
}