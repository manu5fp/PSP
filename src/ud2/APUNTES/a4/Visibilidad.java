package ud2.a4;

/**
 * Una bandera debe ser volatile para que el cambio sea visible con garantías.
 */

public class Visibilidad {

    static boolean parar;
    static volatile boolean pararV;

    // PRUEBA CON VOLATILE
    static boolean probarV() throws InterruptedException {

        Thread lector = new Thread(() -> {
            while (!pararV) {
            }
        });

        lector.setDaemon(true);
        lector.start();

        Thread.sleep(500);

        pararV = true;

        lector.join(2000);

        return !lector.isAlive();
    }

    // PRUEBA SIN VOLATILE
    static boolean probar() throws InterruptedException {

        Thread lector = new Thread(() -> {
            while (!parar) {
            }
        });

        lector.setDaemon(true);
        lector.start();

        Thread.sleep(500);

        parar = true;

        lector.join(2000);

        return !lector.isAlive();
    }

    public static void main(String[] args) throws InterruptedException {

        boolean conVolatile = probarV();

        System.out.println(
            "Con volatile, ¿terminó el lector?: " + conVolatile
        );

        boolean sinVolatile = probar();

        System.out.println(
            "Sin volatile, ¿terminó el lector?: " + sinVolatile
        );
    }
}

/*
 * Salida de una ejecución en nuestro equipo: 
 * Con volatile, ¿terminó el lector?:true 
 * Sin volatile, ¿terminó el lector?: false
 */