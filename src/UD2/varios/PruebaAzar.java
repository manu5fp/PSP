package UD2.varios;

public class PruebaAzar {
    public static void main(String[] args) {
       
        // Enteros aleatorios
        System.out.println("Entero cualquiera: " + Azar.entero());
        System.out.println("Entero entre 0 y 10: " + Azar.entero(10));
        System.out.println("Entero entre 5 y 15: " + Azar.entero(5, 15));

        // Reales aleatorios
        System.out.println("Real entre 0.0 y 1.0: " + Azar.real());
        System.out.println("Real entre 0.0 y 10.0: " + Azar.real(10.0));
        System.out.println("Real entre 2.5 y 5.5: " + Azar.real(2.5, 5.5));

        // Generar varios números para ver la variabilidad
        System.out.println("\nDiez enteros entre 1 y 6 (simulando un dado):");
        for (int i = 0; i < 10; i++) {
            System.out.print(Azar.entero(1, 6) + " ");
        }

        System.out.println("\n\nCinco reales entre 0.0 y 100.0:");
        
        for (int i = 0; i < 5; i++) 
        	System.out.println(Azar.real(100.0));
    }
}
