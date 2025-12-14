package UD3.TCP_MULTI;

/**
 * Clase de prueba para el servidor TCP concurrente y varios clientes en paralelo.
 */
public class TestTCPMultiple {

    public static void main(String[] args) {

        // Arrancamos el servidor concurrente
        new Thread(() -> {
            try {
                SocketTCPServer servidor = new SocketTCPServer(49172);
                servidor.lanzar();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        // Esperamos a que el servidor esté listo
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Lanzamos varios clientes de forma concurrente
        for (int i = 1; i <= 5; i++) {
            final int id = i;
            new Thread(() -> {
                try {
                    SocketTCPClient cliente =
                            new SocketTCPClient("localhost", 49172);
                    cliente.start();
                    System.out.println("Cliente concurrente " + id +
                            " recibe: " + cliente.is.read());
                    cliente.stop();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
