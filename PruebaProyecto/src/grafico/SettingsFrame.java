
package grafico;

import javax.swing.*;
import java.awt.*;
import logica.Configuracion;

public class SettingsFrame extends JFrame {

    private JTextField inputSizeField;
    private JComboBox<String> complexityOptions;
    private Configuracion configuracion;
    
    public SettingsFrame(Configuracion configuracion) {
        this.configuracion = configuracion;
        setTitle("Configuración");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Panel de configuración
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        // Etiqueta y campo de tamaño de entrada
        JLabel inputSizeLabel = new JLabel("Tamaño de Entrada (n):");
        inputSizeField = new JTextField(String.valueOf(configuracion.getTamanioEntrada()));

        
        // Etiqueta y opciones de complejidad
        JLabel complexityLabel = new JLabel("Algoritmo de Comparación:");
        complexityOptions = new JComboBox<>(new String[] { "O(1)", "O(log n)", "O(n)", "O(n log n)", "O(n^2)" });
        complexityOptions.setSelectedItem(configuracion.getComplejidadReferencia());
        
        // Botón para guardar la configuración
        JButton saveButton = new JButton("Guardar Configuración");
        saveButton.addActionListener(e -> saveSettings());

        // Agregar componentes al panel
        panel.add(inputSizeLabel);
        panel.add(inputSizeField);
        panel.add(complexityLabel);
        panel.add(complexityOptions);
        
        // Agregar panel y botón a la ventana
        add(panel, BorderLayout.CENTER);
        add(saveButton, BorderLayout.SOUTH);
    }

    private void saveSettings() {
        try {
            int tamanioEntrada = Integer.parseInt(inputSizeField.getText());
            configuracion.setTamanioEntrada(tamanioEntrada);
            configuracion.setComplejidadReferencia((String) complexityOptions.getSelectedItem());
            JOptionPane.showMessageDialog(this, "Configuración guardada correctamente.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Por favor, ingresa un tamaño de entrada válido.", "Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    
}

