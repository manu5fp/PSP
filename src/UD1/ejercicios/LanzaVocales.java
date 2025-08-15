package UD1.ejercicios;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class LanzaVocales {

	public static void main(String[] args) throws IOException, InterruptedException {
		String[] vocales = { "A", "E", "I", "O", "U" };
		String directoryName = System.getProperty("user.dir") + "/bin";
		// System.out.println(directoryName);
		String clase = "Vocales.Contador";
		ProcessBuilder pb;
		try {
			for (int i = 0; i < vocales.length; i++) {
				pb = new ProcessBuilder("java", clase, System.getProperty("user.dir") + "/hobbit.txt", vocales[i],
						System.getProperty("user.dir") + "/out/" + vocales[i] + ".txt");

				pb.redirectError(new File("errores.txt"));
				pb.directory(new File(directoryName));
				pb.start();
			}
			 Thread.sleep(3000); // Espera a que todos los procesos terminen
			int total = 0;
			for (String vocal : vocales) {
				int cuenta = new Scanner(new File("out/" + vocal + ".txt")).nextInt();
				System.out.println(vocal + ": " + cuenta);
				total += cuenta;
			}
			System.out.println("Total de vocales: " + total);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
