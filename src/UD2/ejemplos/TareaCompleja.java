package UD2.ejemplos;

public class TareaCompleja implements Runnable {

	private int numEjecucion;
	
	public TareaCompleja() {}
	
	@Override
	public void run() {
		String nombre = Thread.currentThread().getName();
		String cad;
		while (numEjecucion < 10) {
			time();
			cad = "Soy el hilo "+ nombre;
			cad += " y mi valor de avance es " + numEjecucion;
			System.out.println(cad);
			numEjecucion++;
		}
		
		System.out.println("Finalizó " + nombre);

	}

	private void time() {
		for (int i = 0; i < 10000; i++) {
			int base = 10,
				exp = 1000;
				Math.pow(base,exp);			
		}
	}
}
