package UD4.DictionaryAPI;
import java.awt.Desktop;
import java.io.File;

public class Navegador {

	    public static void abrir(String path) throws Exception {

	        File file = new File(path);

	        if (!file.exists()) {
	            throw new IllegalArgumentException("El fichero no existe");
	        }

	        Desktop desktop = Desktop.getDesktop();
	        desktop.browse(file.toURI());
	    }

}
