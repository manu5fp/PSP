package ud2.APUNTES.a9;

import java.util.concurrent.Exchanger;

public class EjemploExchanger {

 
    public static void main(String[] args) throws InterruptedException {

        // Exchanger compartido por los dos hilos
    	Exchanger<String> intercambiador = new Exchanger<>();
		Thread[] listaHilos = new Thread[2];
		for (int i = 0; i < listaHilos.length; i++) {

			Thread h = new Thread(() -> {
				String hilo = Thread.currentThread().getName();

				// i prepara su mensaje
				String mensaje = "Información preparada por " + hilo;

				System.out.println(hilo + ": He preparado mi mensaje.");
				System.out.println(hilo + ": Voy a intercambiarlo...");

				try {
           
					// Entrega su mensaje y espera a recibir
					mensaje = intercambiador.exchange(mensaje);

					System.out.println(hilo + ": He recibido: " + mensaje);

				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			});

			listaHilos[i] = h;
		}
		for (int i = 0; i < listaHilos.length; i++) {
			listaHilos[i].start();
		}

		for (int i = 0; i < listaHilos.length; i++) {
			listaHilos[i].join();
		}

		System.out.println("Intercambio finalizado.");
    }
}