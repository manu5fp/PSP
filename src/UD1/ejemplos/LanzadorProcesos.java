package UD1.ejemplos;

public class LanzadorProcesos {

	public void ejecutar(String ruta) {

		ProcessBuilder pb;
		Process p;
		try {
			pb = new ProcessBuilder(ruta, "fichero.txt");
			p = pb.start();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		String ruta = "xed";
		LanzadorProcesos lp = new LanzadorProcesos();
		lp.ejecutar(ruta);
		System.out.println("Finalizado");
	}

}
