package UD4.RickAndMorty;

import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Main {

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);

		System.out.print("Temporada: ");
		int temporada = sc.nextInt();

		System.out.print("Episodio: ");
		int episodio = sc.nextInt();

		JsonObject episodioJson = ClienteAPI.getEpisodio(temporada, episodio);

		System.out.println("\n" + episodioJson.get("name").getAsString() + " (" + episodioJson.get("air_date").getAsString() + ")");
		System.out.println("\nPersonajes:\n");

		JsonArray personajes = episodioJson.getAsJsonArray("characters");

		for (int i = 0; i < personajes.size(); i++) {

			String urlPersonaje = personajes.get(i).getAsString();
			JsonObject personaje = ClienteAPI.getPersonaje(urlPersonaje);

			System.out.println(
					"- " + personaje.get("name").getAsString() + " (" + personaje.get("species").getAsString() 
					+ " / " + personaje.get("status").getAsString() + " / " + personaje.get("gender").getAsString() + ")");
		}

		sc.close();
	}

	
}