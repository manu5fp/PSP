package ud2.a2_creacion.e05_suma_matriz;

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
	    	opPesada();
	    }
	    private void opLigera() {
	    	for (int i = 0; i < fila.length; i++)
				sumas[pos] += fila[i];
			
	    }
	    private void opPesada() {
	    	for (int i = 0; i < fila.length; i++) 
				sumas[pos] += Math.sqrt(fila[i]) + Math.cbrt(fila[i]) ;
			
	    }
}
