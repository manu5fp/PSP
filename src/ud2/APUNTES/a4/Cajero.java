package ud2.a4;
/** Comprobar y actuar debe ser una única operación atómica. */
public class Cajero {
	static class Cuenta {
		private int saldo;

		Cuenta(int saldo) {
			this.saldo = saldo;
		}

		public void retirarMal(int cantidad) {
			if (saldo >= cantidad) { // Otro hilo puede retirar entre estas dos líneas
				Thread.yield(); // Solo aumenta la probabilidad de que se vea el error
				saldo -= cantidad;
			}
		}

		public synchronized void retirarBien(int cantidad) {
			if (saldo >= cantidad) {
				Thread.yield();
				saldo -= cantidad;
			}
		}

		public synchronized int saldo() {
			return saldo;
		}

		private static int probar(boolean seguro) throws InterruptedException {
			int minimo = Integer.MAX_VALUE;
			
			for (int r = 0; r < 500; r++) {
				Cuenta c = new Cuenta(1000);
				
				Thread[] hilos = new Thread[10];
				for (int i = 0; i < hilos.length; i++) {
					hilos[i] = new Thread(() -> {
						for (int j = 0; j < 100; j++) {
							if (seguro)
								c.retirarBien(10);
							else
								c.retirarMal(10);
						}
					});
					hilos[i].start();
				}
				for (Thread h : hilos)
					h.join();
	
				minimo = Math.min(minimo, c.saldo());
			}
			return minimo;
		}

		public static void main(String[] args) throws InterruptedException {
			System.out.println("Saldo mínimo observado sin sincronizar: " + probar(false));
			System.out.println("Saldo mínimo observado sincronizando: " + probar(true));
		}
	}
}
/*
 * Salida de una ejecución: Saldo mínimo observado sin sincronizar: -70 Saldo
 * mínimo observado sincronizando: 0
 */