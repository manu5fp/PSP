package UD4.DictionaryAPI;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class Downloader {

	    public static final String PROTOCOLO = "https";
	    public static final String SERVIDOR = "api.dictionaryapi.dev";

	    public static StringBuilder getDefTermino(String termino) throws Exception {

	        String urlStr = PROTOCOLO + "://" + SERVIDOR + "/api/v2/entries/en/" + termino;
	        URL url = new URL(urlStr);

	        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("GET");
	        connection.setConnectTimeout(5000);
	        connection.setReadTimeout(5000);

	        StringBuilder content = new StringBuilder();

	        BufferedReader reader = new BufferedReader(
	                new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8));

	        String line;
	        while ((line = reader.readLine()) != null) {
	            content.append(line).append("\n");
	        }

	        reader.close();

	        return content;
	    }

}
