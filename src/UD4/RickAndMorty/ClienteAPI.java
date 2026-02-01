package UD4.RickAndMorty;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ClienteAPI {

	public static JsonObject getURL(String urlStr) throws Exception {

		URL url = new URL(urlStr);
		HttpURLConnection con = (HttpURLConnection) url.openConnection();
		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Java client");

		try (InputStreamReader reader = new InputStreamReader(con.getInputStream())) {

			return JsonParser.parseReader(reader).getAsJsonObject();
		}
	}
	
	public static JsonObject getEpisodio(int temporada, int episodio) throws Exception {
		
		// Ejemplo: https://rickandmortyapi.com/api/episode/?episode=S01E02
		
		String codigo = String.format("S%02dE%02d", temporada, episodio);
		String url = "https://rickandmortyapi.com/api/episode/?episode=" + codigo;

		JsonObject respuesta = getURL(url);
		JsonArray results = respuesta.getAsJsonArray("results");

		return results.get(0).getAsJsonObject();
	}

	public static JsonObject getPersonaje(String url) throws Exception {
		return getURL(url);
	}
}
