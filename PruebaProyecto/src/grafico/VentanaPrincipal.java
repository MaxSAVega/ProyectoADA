package grafico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import logica.AnalizadorDeTiempo;

public class VentanaPrincipal extends JFrame {

    private JTable tablaComplejidad;
    private JScrollPane scrollTabla;
    private JTextArea codigoFuenteArea;
    private JButton botonAnalizar, botonLimpiar, botonExportar;
    private AnalizadorDeTiempo analizador;

    public VentanaPrincipal() {
        analizador = new AnalizadorDeTiempo();
        initUI();
    }

    private void initUI() {
        setTitle("Analizador de Complejidad Big O");
        setSize(900, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Panel principal dividido
        JSplitPane panelDividido = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
        panelDividido.setDividerLocation(300);

        // Panel para ingresar código fuente
        JPanel panelCodigo = new JPanel(new BorderLayout());
        panelCodigo.setBorder(BorderFactory.createTitledBorder("Código Fuente"));
        codigoFuenteArea = new JTextArea();
        codigoFuenteArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane scrollCodigo = new JScrollPane(codigoFuenteArea);
        panelCodigo.add(scrollCodigo, BorderLayout.CENTER);

        // Panel para botones
        JPanel panelBotones = new JPanel();
        botonAnalizar = new JButton("Analizar");
        botonLimpiar = new JButton("Limpiar");
        botonExportar = new JButton("Exportar Resultados");
        
        botonAnalizar.addActionListener(e -> analizarCodigo());
        botonLimpiar.addActionListener(e -> limpiarCodigo());
        botonExportar.addActionListener(e -> exportarResultados());

        panelBotones.add(botonAnalizar);
        panelBotones.add(botonLimpiar);
        panelBotones.add(botonExportar);
        panelCodigo.add(panelBotones, BorderLayout.SOUTH);

        // Panel para mostrar resultados
        JPanel panelResultados = new JPanel(new BorderLayout());
        panelResultados.setBorder(BorderFactory.createTitledBorder("Resultados"));
        tablaComplejidad = new JTable();
        scrollTabla = new JScrollPane(tablaComplejidad);
        panelResultados.add(scrollTabla, BorderLayout.CENTER);

        // Agregar paneles al divisor
        panelDividido.setTopComponent(panelCodigo);
        panelDividido.setBottomComponent(panelResultados);

        // Agregar divisor a la ventana principal
        add(panelDividido, BorderLayout.CENTER);
    }

    private void analizarCodigo() {
        String codigoFuente = codigoFuenteArea.getText();
        if (codigoFuente.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un código fuente para analizar.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String complejidad = analizador.obtenerFuncionTiempoEquivalente(codigoFuente);
        mostrarResultados(codigoFuente, complejidad);
    }

    private void mostrarResultados(String codigoFuente, String complejidad) {
        String[] columnas = {"Aspecto", "Resultado"};
        String[][] datos = {
            {"Complejidad estimada", complejidad},
            {"Tamaño del código", String.valueOf(codigoFuente.length())},
            {"Detalle de comparación", analizador.compararConFunciones(complejidad)}
        };

        DefaultTableModel model = new DefaultTableModel(datos, columnas);
        tablaComplejidad.setModel(model);

        // Resaltar fila según la complejidad
        if (complejidad.contains("O(n^2)") || complejidad.contains("O(2^n)")) {
            tablaComplejidad.setBackground(new Color(255, 204, 204)); // Rojo claro
        } else if (complejidad.contains("O(log n)") || complejidad.contains("O(n log n)")) {
            tablaComplejidad.setBackground(new Color(255, 255, 204)); // Amarillo claro
        } else if (complejidad.contains("O(1)")) {
            tablaComplejidad.setBackground(new Color(204, 255, 204)); // Verde claro
        } else {
            tablaComplejidad.setBackground(Color.WHITE); // Fondo por defecto
        }
    }

    private void limpiarCodigo() {
        codigoFuenteArea.setText("");
        tablaComplejidad.setModel(new DefaultTableModel());
    }

    private void exportarResultados() {
        try {
            String resultados = "Resultados del Análisis de Complejidad:\n";
            for (int i = 0; i < tablaComplejidad.getRowCount(); i++) {
                resultados += tablaComplejidad.getValueAt(i, 0) + ": " + tablaComplejidad.getValueAt(i, 1) + "\n";
            }

            JFileChooser fileChooser = new JFileChooser();
            int seleccion = fileChooser.showSaveDialog(this);
            if (seleccion == JFileChooser.APPROVE_OPTION) {
                java.io.File archivo = fileChooser.getSelectedFile();
                java.nio.file.Files.write(archivo.toPath(), resultados.getBytes());
                JOptionPane.showMessageDialog(this, "Resultados exportados correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al exportar resultados: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal vp = new VentanaPrincipal();
            vp.setVisible(true);
        });
    }
}


