package logica;

public class Resultados {
    private double tiempoEjecucion;
    private String funcionTiempoEquivalente;
    private String comparacionFunciones;

    public Resultados(double tiempoEjecucion, String funcionTiempoEquivalente, String comparacionFunciones) {
        this.tiempoEjecucion = tiempoEjecucion;
        this.funcionTiempoEquivalente = funcionTiempoEquivalente;
        this.comparacionFunciones = comparacionFunciones;
    }

    public double getTiempoEjecucion() {
        return tiempoEjecucion;
    }

    public String getFuncionTiempoEquivalente() {
        return funcionTiempoEquivalente;
    }

    public String getComparacionFunciones() {
        return comparacionFunciones;
    }
}

