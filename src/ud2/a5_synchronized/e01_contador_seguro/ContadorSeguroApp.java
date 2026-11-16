package ud2.a5_synchronized.e01_contador_seguro;

/** Mismo programa que ContadorApp, con el contador protegido. */
public class ContadorSeguroApp {
    public static void main(String[] args) throws InterruptedException {
        ContadorSeguro contador = new ContadorSeguro();
        Thread[] hilos = new Thread[10];
        int incrementos = 100_000;
        for (int i = 0; i < hilos.length; i++) {
            hilos[i] = new Thread(() -> {
                for (int j = 0; j < incrementos; j++) {
                    contador.incrementar();
                }
            });
            hilos[i].start();
        }
        for (Thread h : hilos) {
            h.join();
        }
        System.out.println("Cuenta final: " + contador.getCuenta() + " (esperada: 1000000)");
    }
}
