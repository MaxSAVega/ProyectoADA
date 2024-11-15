import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class VentanaPrincipal extends JFrame {

    private JTable tablaComplejidad;
    private JScrollPane scrollTabla;

    public VentanaPrincipal() {
        // Configuración de la ventana y otros componentes
        initUI();
    }

    private void initUI() {
        setTitle("Analizador de Complejidad");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Inicializar tabla de complejidad
        tablaComplejidad = new JTable();
        scrollTabla = new JScrollPane(tablaComplejidad);
        add(scrollTabla);

        // Código de ejemplo para actualizar la tabla con una complejidad
        actualizarTablaComplejidad("O(n^2)");
    }

    public void actualizarTablaComplejidad(String complejidad) {
        String[] columnas = {"n", complejidad};

        Object[][] datos = generarDatos(complejidad);
        DefaultTableModel model = new DefaultTableModel(datos, columnas);
        tablaComplejidad.setModel(model);
    }

    private Object[][] generarDatos(String complejidad) {
        int[] valoresN = {1, 10, 100, 1000, 10000};
        Object[][] datos = new Object[valoresN.length][2];

        for (int i = 0; i < valoresN.length; i++) {
            datos[i][0] = valoresN[i];
            datos[i][1] = calcularComplejidad(complejidad, valoresN[i]);
        }

        return datos;
    }

    private String calcularComplejidad(String complejidad, int n) {
        switch (complejidad) {
            case "O(1)":
                return "1";
            case "O(log n)":
                return String.valueOf(Math.log(n) / Math.log(2));  // log base 2
            case "O(n)":
                return String.valueOf(n);
            case "O(n log n)":
                return String.valueOf(n * Math.log(n) / Math.log(2));
            case "O(n^2)":
                return String.valueOf(n * n);
            case "O(2^n)":
                return String.valueOf(Math.pow(2, n));
            default:
                return "N/A";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal vp = new VentanaPrincipal();
            vp.setVisible(true);
        });
    }
}
