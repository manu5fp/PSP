package UD2.ejemplos;

public class LanzarHilos implements Runnable {

	private int count = 10;
	private static int taskCount = 0;
	private final int id = taskCount;

	public LanzarHilos() {
		taskCount++;
	}

	public LanzarHilos(int count) {
		this.count = count;
		taskCount++;
	}

	@Override
	public void run() {
		while (count > 0) {
			System.out.println("#" + id + " (" + count + ")");
			count--;
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Lanzamiento (" + id + ")");
	}

	public static void main(String[] args) {
		LanzarHilos h0 = new LanzarHilos(5);
		h0.run();
		// new Thread(h0).start();
		LanzarHilos h1 = new LanzarHilos(4);
		h1.run();
		// new Thread(h1).start();
		System.out.println("Comienza la cuenta atrás!");
	}
}