/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ada;

public class AnalizadorDeTiempo {
    public long medirTiempoEjecucion(Algoritmo algoritmo, int n) {
        long inicio = System.nanoTime();
        algoritmo.ejecutar(n); // Ejecuta el algoritmo con tamaño de entrada n
        long fin = System.nanoTime();
        return fin - inicio;
    }
}


