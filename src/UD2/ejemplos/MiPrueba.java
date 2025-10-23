package UD2.ejemplos;
import java.util.Arrays;

import UD2.varios.Crono;

public class MiPrueba {

	private static final int H = 4;
	private static int[][] m = new int[H][1000000];
	private static long[] sumaParcial1 = new long[H];
	private static long[] sumaParcial2 = new long[H];

	public static void main(String[] args) throws InterruptedException {
		for (int i = 0; i < m.length; i++)
			for (int j = 0; j < m[i].length; j++)
				m[i][j] = (int) (Math.random() * 1000000);

		Crono.start();
		
		for (int i = 0; i < m.length; i++)
			for (int j = 0; j < m[i].length; j++)
				sumaParcial1[i] += m[i][j];
				//sumaParcial1[i] += Math.exp(m[i][j]) + Math.pow(2, m[i][j]);
				

		System.out.println(Crono.stop());
		
		Crono.start();
		
		for (int i = 0; i < sumaParcial2.length; i++) {
			new HiloSumador(m[i], sumaParcial2, i).start();
		}
		System.out.println(Crono.stop());
		
		System.out.println(Arrays.equals(sumaParcial1,sumaParcial2));

	}

}
