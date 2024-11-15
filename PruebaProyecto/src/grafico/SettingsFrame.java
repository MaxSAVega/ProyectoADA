
package grafico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SettingsFrame extends JFrame {

    private JTextField inputSizeField;
    private JComboBox<String> complexityOptions;

    public SettingsFrame() {
        setTitle("Configuración");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Panel de configuración
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(3, 2, 10, 10));

        // Etiqueta y campo de tamaño de entrada
        JLabel inputSizeLabel = new JLabel("Tamaño de Entrada (n):");
        inputSizeField = new JTextField();
        
        // Etiqueta y opciones de complejidad
        JLabel complexityLabel = new JLabel("Algoritmo de Comparación:");
        complexityOptions = new JComboBox<>(new String[] { "O(1)", "O(log n)", "O(n)", "O(n log n)", "O(n^2)" });

        // Botón para guardar la configuración
        JButton saveButton = new JButton("Guardar Configuración");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveSettings();
            }
        });

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
        String inputSize = inputSizeField.getText();
        String complexity = (String) complexityOptions.getSelectedItem();
        JOptionPane.showMessageDialog(this, "Configuración guardada:\nTamaño de Entrada: " + inputSize + "\nComplejidad: " + complexity);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SettingsFrame settingsFrame = new SettingsFrame();
            settingsFrame.setVisible(true);
        });
    }
}

