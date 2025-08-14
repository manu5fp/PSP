package UD1.varios;
import java.util.Map;

/**
 * VariablesEntorno no va a ejecutar ningún proceso.
 * Este ejemplo sirve para ver cómo capturar las variables del entorno (sistema
 * operativo) donde se está ejecutando este programa.
 * 
 * @author diego
 */
public class VariablesEntorno {

    public static void main(String args[]) {

        /**
         * ProcessBuilder nos devuelve las variables de entorno del sistema
         * en un mapa, mediante el método environment()
         */
        ProcessBuilder pb = new ProcessBuilder();
        
        System.out.println("********** Variables de entorno **********");
        
        Map <String, String> varsEntorno = pb.environment();
        /**
         * Para recorrerlo, hacemos un "foreach" donde en cada interación
         * tomaresmo una entrada (Map.Entry e) del conjunto de entradas
         * de dicho mapa (varsEntorno.entrySet())
         */
        for (Map.Entry e: varsEntorno.entrySet()) {
            System.out.println(e.getKey() + " = " + e.getValue());
        }
        
        System.out.println("******************************************");
    }
}
