package UD1.ejercicios;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ContadorVocales {

	public void hacerRecuento(String nfe, String letra, String nfr) {
		long result = 0;
		File f = new File(nfe);
		System.out.println(f.getAbsolutePath());
		try {
			Scanner sc = new Scanner(f);
			while (sc.hasNext()) {
				String l = sc.nextLine();
				for (int i = 0; i < l.length(); i++)
					if(letra.toUpperCase().equals(Character.toString(l.charAt(i)).toUpperCase()))
							result++;
			}
			sc.close();
			System.out.println(result);
			f = new File(nfr);
			PrintWriter pw = new PrintWriter(f);
			pw.print(result);
			pw.close();
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args){
		ContadorVocales c = new ContadorVocales();
		String nombreFicheroEntrada = args[0];
	    String letra = args[1];
	    String nombreFicheroResultado = args[2];
	    System.out.println(letra);
	    c.hacerRecuento(nombreFicheroEntrada, letra, nombreFicheroResultado);
		
	}
}