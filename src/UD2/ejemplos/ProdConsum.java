package UD2.ejemplos;

public class ProdConsum {

 static class Cafetera {
    public void prepararCafe(String nombre) {
        System.out.println(" El camarero " + nombre + " está preparando el café...");
        try {
            Thread.sleep(1000); // Simula el tiempo de preparación
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (this) {
            System.out.println(" Café listo, notificando al consumidor...");
            notify(); // Despierta a un consumidor
        }
    }
    public void prepararCafes(String nombre) {
        System.out.println(" El camarero " + nombre + " está preparando los cafés...");
        try {
            Thread.sleep(1000); // Simula el tiempo de preparación
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        synchronized (this) {
            System.out.println(" Cafés listos, notificando los consumidores...");
            notifyAll(); // Despierta a todos los consumidores
        }
    }
    public void tomarCafe(String nombre) {
        synchronized (this) {
            try {
                System.out.println(" Cliente " + nombre + " esperando el café...");
                wait(); // Espera hasta que el camarero (productor) prepare su café
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(" Cliente " + nombre + " tomando el café.");
        }
    }
}

    
    public static void main(String[] args) {
        Cafetera cafetera = new Cafetera();
        
        String[] cli = {"mario", "juan", "jose", "miguel"};
        String[] cam = {"aprendiz", "veterano"};
        
        
        for (String n: cli){
            Thread consumidor = new Thread(() -> {
             cafetera.tomarCafe(n);
          });
          consumidor.start();
        }
        
        Thread productor;
        // turno = 0 le toca servir cafés al aprendiz, turno = 1 al veterano
        if ((int)(Math.random() * 2) == 0)
            productor = new Thread(() -> {
                for (int i = 0; i < cli.length; i++)
                    cafetera.prepararCafe(cam[0]);
          });
            else
            productor = new Thread(() -> {
                cafetera.prepararCafes(cam[1]);
          });
        try {
            Thread.sleep(500); // Espera un poco antes de que el productor empiece
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        productor.start();
    }
}


