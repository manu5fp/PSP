package ud2.a7_semaforos.e06_sin_hilos;

import ud2.comun.Azar;
import ud2.comun.Crono;

public class SinHilos {
	// Variables compartidas
	static int x, y, z;
	static int Suma, Producto;

	public static void main(String[] args) {

		Crono.start();

		System.out.println("A: leyendo x...");
		x = Azar.entero(1, 1000); // Simula lectura
		System.out.println("A: x = " + x);

		System.out.println("B: leyendo y...");
		y = Azar.entero(1, 500); // Simula lectura
		System.out.println("B: y = " + y);

		System.out.println("C: leyendo z...");
		z = Azar.entero(1, 2500); // Simula lectura
		System.out.println("C: z = " + z);

		System.out.println("A: Calculando suma...");
		Suma = y + z;
		System.out.println("A: suma = " + Suma);

		System.out.println("B: Calculando producto...");
		Producto = x * y;
		System.out.println("B: producto = " + Producto);

		System.out.println("C: la suma es " + Suma);

		System.out.println("C: el producto es " + Producto);

		System.out.println("Tiempo empleado: " + Crono.stop());

	}

}
