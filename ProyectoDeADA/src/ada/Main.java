/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ada;

public class Main {
    public static void main(String[] args) {
        // Crear instancias de los algoritmos
        Algoritmo algLineal = new Algoritmo("Algoritmo Lineal");
        Algoritmo algCuadratico = new Algoritmo("Algoritmo Cuadrático");

        // Crear instancia del analizador de tiempo
        AnalizadorDeTiempo analizador = new AnalizadorDeTiempo();

        // Ejecutar y medir tiempo de ejecución de los algoritmos con una entrada de tamaño n
        int n = 1000;
        long tiempoLineal = analizador.medirTiempoEjecucion(algLineal, n);
        long tiempoCuadratico = analizador.medirTiempoEjecucion(algCuadratico, n);

        // Imprimir los tiempos de ejecución
        System.out.println("Tiempo de ejecución del " + algLineal.getNombre() + ": " + tiempoLineal + " ns");
        System.out.println("Tiempo de ejecución del " + algCuadratico.getNombre() + ": " + tiempoCuadratico + " ns");

        // Crear la función temporal y el graficador
        FuncionTemporal funcionLineal = new FuncionTemporal("O(n)");
        FuncionTemporal funcionCuadratica = new FuncionTemporal("O(n^2)");
        Graficador graficador = new Graficador();

        // Graficar las funciones temporales
        graficador.graficarFuncion(funcionLineal.getFuncionTiempo());
        graficador.graficarFuncion(funcionCuadratica.getFuncionTiempo());

        // Comparar los algoritmos
        Comparador comparador = new Comparador();
        comparador.comparar(algLineal, algCuadratico, analizador, n);
    }
}

