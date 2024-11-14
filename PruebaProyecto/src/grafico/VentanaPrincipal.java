package grafico;

import logica.AnalizadorDeTiempo;
import logica.Resultados;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VentanaPrincipal extends JFrame {
    private JTextArea codigoFuenteArea;
    private JButton analizarButton;
    private JTextArea resultadosArea;

    private AnalizadorDeTiempo analizador;

    public VentanaPrincipal() {
        analizador = new AnalizadorDeTiempo();
        setTitle("Analizador de Algoritmos");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        
        JLabel labelCodigo = new JLabel("Ingrese el código fuente:");
        labelCodigo.setBounds(10, 10, 200, 20);
        add(labelCodigo);
        
        codigoFuenteArea = new JTextArea();
        codigoFuenteArea.setBounds(10, 40, 460, 100);
        add(codigoFuenteArea);
        
        analizarButton = new JButton("Analizar");
        analizarButton.setBounds(10, 150, 100, 30);
        add(analizarButton);
        
        resultadosArea = new JTextArea();
        resultadosArea.setBounds(10, 190, 460, 150);
        resultadosArea.setEditable(false);
        add(resultadosArea);

        analizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                analizarCodigo();
            }
        });
    }

    private void analizarCodigo() {
        String codigoFuente = codigoFuenteArea.getText();
        double tiempoEjecucion = analizador.calcularTiempoEjecucion(codigoFuente);
        String funcionTiempo = analizador.obtenerFuncionTiempoEquivalente(codigoFuente);
        String comparacion = analizador.compararConFunciones(funcionTiempo);
        
        Resultados resultados = new Resultados(tiempoEjecucion, funcionTiempo, comparacion);
        
        mostrarResultados(resultados);
    }

    private void mostrarResultados(Resultados resultados) {
        resultadosArea.setText("Tiempo de Ejecución: " + resultados.getTiempoEjecucion() + " ms\n");
        resultadosArea.append("Función Tiempo Equivalente: " + resultados.getFuncionTiempoEquivalente() + "\n");
        resultadosArea.append("Comparación con otras funciones: " + resultados.getComparacionFunciones());
    }
}


