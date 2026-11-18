package ud2.APUNTES.a4;

/** Un programa con error de concurrencia puede dar casi siempre el resultado correcto. */
public class CarreraEnganosa {
    static int cuenta; // Sin ninguna protección

    public static void main(String[] args) throws InterruptedException {
        int correctas = 0;
        final int REPETICIONES = 30;
        for (int r = 0; r < REPETICIONES; r++) {
            cuenta = 0;
            Thread[] hilos = new Thread[1000];
            for (int i = 0; i < hilos.length; i++) {
                hilos[i] = new Thread(() -> cuenta++); // Cada hilo incrementa una sola vez
                hilos[i].start();
            }
            for (Thread h : hilos) h.join();
            if (cuenta == 1000) correctas++;
        }
        System.out.println("Ejecuciones con 1000 exacto: " + correctas + " de " + REPETICIONES);
    }
}