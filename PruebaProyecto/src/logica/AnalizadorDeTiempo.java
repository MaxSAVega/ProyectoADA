package logica;

public class AnalizadorDeTiempo {
    
    public double calcularTiempoEjecucion(String codigoFuente) {
        // Implementa aquí la lógica para medir el tiempo de ejecución del algoritmo.
        // Este es un valor de ejemplo, el cálculo real dependerá del análisis.
        return 10.0;
    }

    public String obtenerFuncionTiempoEquivalente(String codigoFuente) {
        if (esRecursivo(codigoFuente)) {
            return analizarRecursion(codigoFuente);
        } else if (esBucleLogaritmico(codigoFuente)) {
            return "O(log n)";
        } else if (esBucleAnidado(codigoFuente)) {
            return "O(n^2)";
        } else if (esBucleSimpleAvanzado(codigoFuente)) {
            return "O(n)";
        } else {
            return "Complejidad no determinada, se requiere análisis manual.";
        }
    }
    
    public String compararConFunciones(String funcionTiempo) {
        return "Comparación realizada: " + funcionTiempo + " vs O(1), O(log n), O(n), O(n^2), O(n log n)";
    }

    private boolean esRecursivo(String codigoFuente) {
        return codigoFuente.contains("return") && codigoFuente.contains("(") && codigoFuente.contains(")");
    }

    private String analizarRecursion(String codigoFuente) {
        if (codigoFuente.contains("T(n/2)")) {
            return "O(log n)";
        } else if (codigoFuente.contains("T(n-1)")) {
            return "O(n)";
        } else if (codigoFuente.contains("T(n)")) {
            return "O(2^n)";
        }
        return "Indeterminado";
    }

    private boolean esBucleLogaritmico(String codigoFuente) {
        return codigoFuente.contains("start") && codigoFuente.contains("end") && 
               (codigoFuente.contains("while") || codigoFuente.contains("for")) &&
               (codigoFuente.contains("start = middle + 1") || codigoFuente.contains("end = middle - 1"));
    }

    public boolean esBucleAnidado(String codigoFuente) {
    // Contar la cantidad de bucles anidados
    int buclesAnidados = 0;
    String[] lineas = codigoFuente.split("\n");
    
    for (String linea : lineas) {
        if (linea.contains("for") || linea.contains("while")) {
            // Si encontramos un bucle, incrementamos el contador
            buclesAnidados++;
            // Verificamos si hay otro bucle en la misma línea o en líneas anteriores
            for (String otraLinea : lineas) {
                if (!linea.equals(otraLinea) && (otraLinea.contains("for") || otraLinea.contains("while"))) {
                    // Si hay otro bucle, consideramos que hay anidamiento
                    return true;
                }
            }
        }
    }
    return false; // No se encontraron bucles anidados
}


    private boolean esBucleSimpleAvanzado(String codigoFuente) {
        String regex = "for\\s*\\(.*;.*;.*\\)|while\\s*\\(.*\\)";
        return codigoFuente.matches(regex);
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





