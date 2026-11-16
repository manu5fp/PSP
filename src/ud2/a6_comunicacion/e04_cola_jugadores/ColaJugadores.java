package ud2.a6_comunicacion.e04_cola_jugadores;

/**
 * Cada jugador espera dos rondas. El árbitro las inicia con notifyAll().
 * Antes se llamaba TazaCafe y usaba wait() sin condición.
 */
public class ColaJugadores {
    private static final int JUGADORES = 8;
    private int ronda = 0;
    private int esperando = 0;

    synchronized void jugar(String nombre) throws InterruptedException {
        for (int vuelta = 0; vuelta < 2; vuelta++) {
            int miRonda = ronda;
            esperando++;
            notifyAll();                // Avisa al árbitro de que hay otro jugador listo
            while (ronda == miRonda) {  // while: un aviso de otro tipo no le deja seguir
                wait();
            }
            System.out.println("Jugador " + nombre + " liberado en la ronda " + ronda);
        }
    }

    synchronized void iniciarRonda() throws InterruptedException {
        while (esperando < JUGADORES) { // El aviso no se pierde: se espera a que estén todos
            wait();
        }
        esperando = 0;
        ronda++;
        System.out.println("--- Ronda " + ronda + ": se despierta a todos ---");
        notifyAll();
    }

    public static void main(String[] args) throws InterruptedException {
        ColaJugadores cola = new ColaJugadores();
        Thread[] hilos = new Thread[JUGADORES];
        for (int i = 0; i < JUGADORES; i++) {
            String nombre = String.valueOf((char) ('a' + i));
            hilos[i] = new Thread(() -> {
                try {
                    cola.jugar(nombre);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
            hilos[i].start();
        }
        cola.iniciarRonda();
        cola.iniciarRonda();
        for (Thread h : hilos) {
            h.join();
        }
        System.out.println("Todos han terminado");
    }
}
