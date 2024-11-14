
package logica;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnalizadorDeTiempo {

    // Método que analiza el código fuente y estima su tiempo de ejecución
    public double calcularTiempoEjecucion(String codigoFuente) {
        // En una implementación real, deberías ejecutar el código y medir el tiempo.
        // Aquí solo simularemos el tiempo de ejecución basándonos en la complejidad.
        
        String funcionTiempo = obtenerFuncionTiempoEquivalente(codigoFuente);
        
        // Simulación de tiempo de ejecución en ms basado en la complejidad teórica
        switch (funcionTiempo) {
            case "O(1)":
                return 1;
            case "O(n)":
                return 10;
            case "O(n^2)":
                return 100;
            case "O(log n)":
                return 5;
            case "O(n log n)":
                return 50;
            default:
                return 0; // No detectado
        }
    }

    // Método que analiza el código y determina su complejidad temporal teórica
    public String obtenerFuncionTiempoEquivalente(String codigoFuente) {
        // Verifica si el código tiene un bucle simple, doble o logarítmico
        if (contieneBucleDoble(codigoFuente)) {
            return "O(n^2)";
        } else if (contieneBucleSimple(codigoFuente)) {
            return "O(n)";
        } else if (contieneLogaritmico(codigoFuente)) {
            return "O(log n)";
        } else if (contieneBucleYSorteo(codigoFuente)) {
            return "O(n log n)";
        } else {
            return "O(1)"; // Caso de operación constante
        }
    }

    // Método para comparar con otras funciones de tiempo
    public String compararConFunciones(String funcionTiempo) {
        // Comparación básica con otras funciones comunes
        return "Comparación realizada: " + funcionTiempo + " vs O(1), O(log n), O(n), O(n^2), O(n log n)";
    }

    // Métodos de detección de patrones para diferentes complejidades

    private boolean contieneBucleSimple(String codigo) {
        Pattern patron = Pattern.compile("\\b(for|while)\\s*\\(.*n.*\\)");
        Matcher matcher = patron.matcher(codigo);
        return matcher.find();
    }

    private boolean contieneBucleDoble(String codigo) {
        Pattern patron = Pattern.compile("\\b(for|while)\\s*\\(.*n.*\\).*\\b(for|while)\\s*\\(.*n.*\\)");
        Matcher matcher = patron.matcher(codigo);
        return matcher.find();
    }

    private boolean contieneLogaritmico(String codigo) {
        Pattern patron = Pattern.compile("\\b(for|while)\\s*\\(.*n.*(/|\\*).*2.*\\)");
        Matcher matcher = patron.matcher(codigo);
        return matcher.find();
    }

    private boolean contieneBucleYSorteo(String codigo) {
        return contieneBucleSimple(codigo) && codigo.contains("sort");
    }
    
    
}


