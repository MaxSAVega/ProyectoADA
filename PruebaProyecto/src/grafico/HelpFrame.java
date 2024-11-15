
package grafico;

import javax.swing.*;

public class HelpFrame extends JFrame {

    public HelpFrame() {
        setTitle("Ayuda");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Texto de ayuda
        JTextArea helpText = new JTextArea();
        helpText.setText("Guía de Uso de la Aplicación:\n\n"
                + "1. En la ventana principal, ingrese el código del algoritmo que desea analizar.\n"
                + "2. Presione el botón 'Analizar' para calcular la complejidad del algoritmo.\n"
                + "3. Vaya a Configuración para ajustar el tamaño de entrada y la complejidad de referencia.\n"
                + "4. Revise los resultados en el área de resultados y, si desea, visualice la comparación gráfica.\n\n"
                + "Para más información, consulte la documentación completa.");
        helpText.setEditable(false);
        helpText.setWrapStyleWord(true);
        helpText.setLineWrap(true);
        
        // Añadir un JScrollPane para hacer el texto desplazable
        JScrollPane scrollPane = new JScrollPane(helpText);
        
        // Agregar JScrollPane a la ventana
        add(scrollPane);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            HelpFrame helpFrame = new HelpFrame();
            helpFrame.setVisible(true);
        });
    }
}

