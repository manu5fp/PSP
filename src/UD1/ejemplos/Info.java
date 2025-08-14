package UD1.ejemplos;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Info {

	public static void main(String[] args) throws IOException {
		ProcessBuilder processBuilder = new ProcessBuilder("java", "-version");

		// La salida de error se enviará al mismo sitio que la estándar
		processBuilder.redirectErrorStream(true);

		File log = new File("java-version.log");
		processBuilder.redirectOutput(log);
		
		// Mostramos la información de las variables de entorno
		System.out.println("Variables de entorno:");
		Map variablesEntorno = processBuilder.environment();
		Iterator it = variablesEntorno.keySet().iterator();
		while(it.hasNext()){
		  String key = (String) it.next();
		  System.out.println("Clave: " + key + " -> Valor: " + variablesEntorno.get(key));
		}

		// Mostramos el nombre del proceso y sus argumentos
		System.out.println("\nProceso y argumentos:");
		List command = processBuilder.command();
		Iterator iter = command.iterator();
		while (iter.hasNext()) {
		    System.out.println(iter.next());
		}

		Process process = processBuilder.start();
	}

}
