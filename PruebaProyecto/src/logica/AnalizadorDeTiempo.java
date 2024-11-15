package logica;

public class AnalizadorDeTiempo {
    
    public double calcularTiempoEjecucion(String codigoFuente) {
        // Implementa aquí la lógica para medir el tiempo de ejecución del algoritmo.
        // Este es un valor de ejemplo, el cálculo real dependerá del análisis.
        return 10.0;
    }

    public String obtenerFuncionTiempoEquivalente(String codigoFuente) {
        // Analiza el código fuente para determinar la complejidad temporal.
        if (esBucleLogaritmico(codigoFuente)) {
            return "O(log n)";
        } else if (esBucleAnidado(codigoFuente)) {
            return "O(n^2)";
        } else if (esBucleSimple(codigoFuente)) {
            return "O(n)";
        } else {
            return "O(1)";
        }
    }
    
    public String compararConFunciones(String funcionTiempo) {
        // Comparación de la función de tiempo con otras funciones
        return "Comparación realizada: " + funcionTiempo + " vs O(1), O(log n), O(n), O(n^2), O(n log n)";
    }

    private boolean esBucleLogaritmico(String codigoFuente) {
        // Verifica si el código fuente contiene un bucle que divide el rango de búsqueda, lo que indica O(log n).
        return codigoFuente.contains("start") && codigoFuente.contains("end") && 
               (codigoFuente.contains("while") || codigoFuente.contains("for")) &&
               (codigoFuente.contains("start = middle + 1") || codigoFuente.contains("end = middle - 1"));
    }

    private boolean esBucleAnidado(String codigoFuente) {
        // Verifica si el código tiene bucles anidados para identificar O(n^2)
        int buclesEncontrados = contarOcurrencias(codigoFuente, "for") + contarOcurrencias(codigoFuente, "while");
        return buclesEncontrados > 1;
    }

    private boolean esBucleSimple(String codigoFuente) {
        // Verifica si el código tiene un solo bucle
        int buclesEncontrados = contarOcurrencias(codigoFuente, "for") + contarOcurrencias(codigoFuente, "while");
        return buclesEncontrados == 1;
    }

    private int contarOcurrencias(String texto, String subcadena) {
        int index = 0, count = 0;
        while ((index = texto.indexOf(subcadena, index)) != -1) {
            count++;
            index += subcadena.length();
        }
        return count;
    }
}




