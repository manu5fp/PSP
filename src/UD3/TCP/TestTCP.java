package UD3.TCP;

import java.io.IOException;

import UD5.Cliente;
import UD5.Servidor;

/**
 * Clase de prueba para el servidor TCP secuencial y varios clientes TCP. Inicia
 * el servidor en un hilo independiente y lanza varios clientes que se conectan
 * de forma consecutiva.
 */
public class TestTCP {

	public static void main(String[] args) throws IOException {
// Arranque del servidor TCP en un hilo independiente
		new Thread(() -> {
			try {
				Servidor.main(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}).start();

// Pequeña pausa para asegurar que el servidor arranca antes
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

// Lanzamiento de varios clientes TCP
		for (int i = 1; i <= 3; i++) {
			Cliente.main(null);
		}
	}
}
