package UD3.UDP;

public class TestUDP {
	public static void main(String[] args) {
		// Arranque del servidor UDP en un hilo independiente
		new Thread(() -> {
			try {
				SocketUDPServer.main(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();

		// Pausa para asegurar que el servidor está listo
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Lanzamiento de varios clientes UDP
		for (int i = 1; i <= 4; i++) {
			System.out.println("\n--- Cliente UDP " + i + " ---");
			SocketUDPClient.main(null);
		}
	}
}