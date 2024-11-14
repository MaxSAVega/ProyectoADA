/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ada;

public class Algoritmo {
    private String nombre;

    public Algoritmo(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    // Método que simula la ejecución del algoritmo con diferentes complejidades
    public void ejecutar(int n) {
        switch (nombre) {
            case "Algoritmo Lineal":
                algoritmoLineal(n);
                break;
            case "Algoritmo Cuadrático":
                algoritmoCuadratico(n);
                break;
            case "Algoritmo Logarítmico":
                algoritmoLogaritmico(n);
                break;
            default:
                System.out.println("Algoritmo no reconocido");
        }
    }

    // Simulación de un algoritmo lineal O(n)
    private void algoritmoLineal(int n) {
        for (int i = 0; i < n; i++) {
            // Simulación de procesamiento
        }
    }

    // Simulación de un algoritmo cuadrático O(n^2)
    private void algoritmoCuadratico(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Simulación de procesamiento
            }
        }
    }

    // Simulación de un algoritmo logarítmico O(log n)
    private void algoritmoLogaritmico(int n) {
        while (n > 1) {
            n /= 2;
            // Simulación de procesamiento
        }
    }
}


