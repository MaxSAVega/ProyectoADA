package grafico;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;
import logica.AnalizadorDeTiempo;

public class VentanaPrincipal extends JFrame {

    private JTable tablaComplejidad;
    private JScrollPane scrollTabla;
    private JTextArea codigoFuenteArea;
    private JButton botonAnalizar, botonLimpiar, botonAyuda, botonConfiguracion;
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

        // Botones
        botonAnalizar = new JButton("Analizar");
        botonAnalizar.addActionListener(e -> analizarCodigo());
        botonLimpiar = new JButton("Limpiar");
        botonLimpiar.addActionListener(e -> limpiarCodigo());
        
 // Botones para abrir ayuda y configuración
        botonAyuda = new JButton("Ayuda");
        botonAyuda.addActionListener(e -> abrirAyuda());
        
        botonConfiguracion = new JButton("Configuración");
        botonConfiguracion.addActionListener(e -> abrirConfiguracion());

        JPanel panelBotones = new JPanel();
        panelBotones.add(botonAnalizar);
        panelBotones.add(botonLimpiar);
        panelBotones.add(botonAyuda);
        panelBotones.add(botonConfiguracion);
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
    
     private void abrirAyuda() {
        HelpFrame helpFrame = new HelpFrame();
        helpFrame.setVisible(true);
    }

    private void abrirConfiguracion() {
        SettingsFrame settingsFrame = new SettingsFrame();
        settingsFrame.setVisible(true);
    }

    private void analizarCodigo() {
        String codigoFuente = codigoFuenteArea.getText();
        if (codigoFuente.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un código fuente para analizar.", "Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        List<String[]> resultados = analizarLineas(codigoFuente);
        mostrarResultados(resultados);
        calcularComplejidadTotal(resultados);
    }

    private List<String[]> analizarLineas(String codigoFuente) {
        String[] lineas = codigoFuente.split("\n");
        List<String[]> resultados = new ArrayList<>();

        for (String linea : lineas) {
            String complejidad = determinarComplejidad(linea.trim());
            resultados.add(new String[]{linea.trim(), complejidad});
        }

        return resultados;
    }

    private String determinarComplejidad(String linea) {
    if (linea.startsWith("let ") || linea.startsWith("var ") || linea.startsWith("const ")) {
        return "O(1)"; // Declaración de variable
    } else if (linea.startsWith("if") || linea.startsWith("switch")) {
        return "O(1)"; // Condicional simple
    } else if (linea.startsWith("for") || linea.startsWith("while")) {
        if (analizador.esBucleAnidado(codigoFuenteArea.getText())) {
            return "O(n^2)"; // Bucle anidado
        }
        return "O(n)"; // Bucle simple
    }
    return "No determinada"; // No se puede identificar
}


    private void mostrarResultados(List<String[]> resultados) {
        String[] columnas = {"Línea de Código", "Complejidad Estimada"};
        DefaultTableModel model = new DefaultTableModel(columnas, 0);

        for (String[] resultado : resultados) {
            model.addRow(resultado);
        }

        tablaComplejidad.setModel(model);
    }

    private void calcularComplejidadTotal(List<String[]> resultados) {
        Map<String, Integer> complejidades = new HashMap<>();
        for (String[] resultado : resultados) {
            String complejidad = resultado[1];
            if (complejidad.startsWith("O")) {
                complejidades.put(complejidad, complejidades.getOrDefault(complejidad, 0) + 1);
            }
        }

        StringBuilder resultadoFinal = new StringBuilder("Complejidad Total:\n");
        for (Map.Entry<String, Integer> entry : complejidades.entrySet()) {
            resultadoFinal.append(entry.getValue()).append("x").append(entry.getKey()).append(" + ");
        }

        String resultadoSimplificado = simplificarComplejidad(complejidades);
        JOptionPane.showMessageDialog(this, resultadoFinal.substring(0, resultadoFinal.length() - 3)
                + "\nSimplificado: " + resultadoSimplificado, "Complejidad Total", JOptionPane.INFORMATION_MESSAGE);
    }

    private String simplificarComplejidad(Map<String, Integer> complejidades) {
        if (complejidades.containsKey("O(n^2)")) {
            return "O(n^2)";
        } else if (complejidades.containsKey("O(n)")) {
            return "O(n)";
        } else if (complejidades.containsKey("O(1)")) {
            return "O(1)";
        }
        return "No determinada";
    }

    private void limpiarCodigo() {
        codigoFuenteArea.setText("");
        tablaComplejidad.setModel(new DefaultTableModel());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal vp = new VentanaPrincipal();
            vp.setVisible(true);
        });
    }
}




