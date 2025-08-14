package UD1.varios;

public class Crono {
	private static long tini, 
						ttot;

	public static void start() {
		tini = System.nanoTime();
	}

	public static String stop() {

		ttot = System.nanoTime() - tini;
		String msg = "";
		
		if (ttot > 1000000)
			if ((ttot / 1000000) > 1000)
					msg += ((ttot / 1000000.0) / 1000) + " segundos.";
				else
					msg += (ttot / 1000000.0) + " milisegundos.";
		else
			msg += ttot + " nanosegundos.";
		
		return msg;
	}
}
