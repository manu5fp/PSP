package UD2.ejemplos;

public class HiloSumador extends Thread {
private int[] fila; 
private long[] sumas;
private int pos;
public HiloSumador(int[] fila, long[] sumas, int pos) {
	this.fila = fila; 
	this.sumas = sumas;
	this.pos = pos;
}
	    @Override
	    public void run() {
	        for (int i = 0; i < fila.length; i++) {
				sumas[pos] += Math.exp(fila[i]) + Math.pow(2, fila[i]) ;
			}
	    }
}
