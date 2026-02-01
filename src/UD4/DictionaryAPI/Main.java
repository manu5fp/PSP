package UD4.DictionaryAPI;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		System.out.print("Introduce una palabra en inglés: ");
		String termino = sc.nextLine();

		String fileName = termino + ".json";

		try {
			StringBuilder json = Downloader.getDefTermino(termino);

			try {
				BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, StandardCharsets.UTF_8));
				writer.write(json.toString());
				writer.close();
			} catch (IOException e) {
				System.out.println("Error de escritura");
			}
			System.out.println("Archivo generado: " + fileName);

			System.out.print("¿Quieres ver el resultado en un navegador? (s/n): ");
			String respuesta = sc.nextLine();

			if (respuesta.equalsIgnoreCase("s")) {
				Navegador.abrir(fileName);
			}

		} catch (Exception e) {
			System.err.println("Error en la aplicación");
			e.printStackTrace();
		}

		sc.close();
	}
}