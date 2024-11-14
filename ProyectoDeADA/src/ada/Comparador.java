/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ada;

public class Comparador {
    public void comparar(Algoritmo alg1, Algoritmo alg2, AnalizadorDeTiempo analizador, int n) {
        long tiempo1 = analizador.medirTiempoEjecucion(alg1, n);
        long tiempo2 = analizador.medirTiempoEjecucion(alg2, n);

        System.out.println("Comparación de tiempos:");
        System.out.println(alg1.getNombre() + ": " + tiempo1 + " ns");
        System.out.println(alg2.getNombre() + ": " + tiempo2 + " ns");

        if (tiempo1 < tiempo2) {
            System.out.println(alg1.getNombre() + " es más rápido.");
        } else if (tiempo2 < tiempo1) {
            System.out.println(alg2.getNombre() + " es más rápido.");
        } else {
            System.out.println("Ambos algoritmos tienen el mismo tiempo de ejecución.");
        }
    }
}


