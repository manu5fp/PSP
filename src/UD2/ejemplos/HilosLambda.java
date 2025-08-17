package UD2.ejemplos;
public class HilosLambda {

	public static void main(String[] args) {
		
		Thread hilo1 = new Thread(() -> {
			System.out.println("Hola desde un hilo con lambda");
		});
		hilo1.start();
		
		Runnable hilo2 = () -> {
			System.out.println("Hola desde un hilo con lambda");
		};
		new Thread(hilo2).start();
		
		Runnable hilo3 = new Runnable() {
			public void run() {
				System.out.println("Hola desde un hilo con clase anónima");
			}
		};
		new Thread(hilo3).start();
	}

}
