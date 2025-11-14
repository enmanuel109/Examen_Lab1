package examen_lab1;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class VentanaRenta extends BaseGUI {

    private JPanel panelPrincipal;
    private JLabel lblTitulo;
    private JLabel lblImagen;
    private JTextArea txtInfo;
    private JTextField txtDias;
    private JButton btnCalcular;
    private JButton btnCerrar;
    private RentItem item;

    public VentanaRenta(RentItem item) {
        super("Rentar Ítem", 540, 330);
        this.item = item;
        initComponents();
    }

    private void initComponents() {
        panelPrincipal = createPanelPrincipal();
        panelPrincipal.setLayout(null);

        lblTitulo = createLabelTitle("RENTAR ÍTEM", 190, 15, 300, 40);
        lblTitulo.setFont(lblTitulo.getFont().deriveFont(22f));
        panelPrincipal.add(lblTitulo);

        lblImagen = new JLabel();
        lblImagen.setBounds(40, 80, 150, 160);
        lblImagen.setHorizontalAlignment(SwingConstants.CENTER);
        lblImagen.setVerticalAlignment(SwingConstants.CENTER);
        if (item.getImagen() != null) {
            lblImagen.setIcon(item.getImagen());
        } else {
            lblImagen.setText("Sin imagen");
        }
        panelPrincipal.add(lblImagen);

        txtInfo = new JTextArea();
        txtInfo.setEditable(false);
        txtInfo.setOpaque(false);
        txtInfo.setLineWrap(true);
        txtInfo.setWrapStyleWord(true);
        txtInfo.setBorder(null);
        txtInfo.setBounds(220, 80, 280, 90);

        String info = "Código: " + item.getCodigo()
                + "\nNombre: " + item.getNombre()
                + "\nPrecio base: " + item.getPrecioBase() + " Lps";

        if (item instanceof Movie) {
            Movie m = (Movie) item;
            info = info + "\nEstado: " + m.getEstado();
        } else if (item instanceof Game) {
            info = info + "\nTipo: Videojuego";
        }

        txtInfo.setText(info);
        panelPrincipal.add(txtInfo);

        JLabel lblDias = new JLabel("Días de renta:");
        lblDias.setBounds(220, 185, 120, 30);
        lblDias.setFont(lblDias.getFont().deriveFont(14f));
        panelPrincipal.add(lblDias);

        txtDias = createTextField(330, 185, 80, 30);
        txtDias.setBorder(new CompoundBorder(
                new LineBorder(new Color(220, 220, 220), 1, true),
                new EmptyBorder(3, 10, 3, 10) // menos padding vertical
        ));
        panelPrincipal.add(txtDias);

        btnCalcular = createBtn("Calcular");
        btnCalcular.setBounds(223, 236, 105, 28);
        panelPrincipal.add(btnCalcular);

        btnCerrar = createBtn("Cerrar");
        btnCerrar.setBounds(350, 235, 110, 30);
        panelPrincipal.add(btnCerrar);

        btnCerrar.addActionListener(e -> dispose());
        btnCalcular.addActionListener(e -> calcularRenta());

        setContentPane(panelPrincipal);
    }

    private void calcularRenta() {
        String diasStr = txtDias.getText();
        if (diasStr == null || diasStr.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese la cantidad de días.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int dias;
        try {
            dias = Integer.parseInt(diasStr.trim());
            if (dias <= 0) {
                JOptionPane.showMessageDialog(this, "Los días deben ser mayores que cero.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Cantidad de días inválida.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double total = item.pagoRenta(dias);

        JOptionPane.showMessageDialog(
                this,
                "Monto total de la renta: " + total + " Lps",
                "Total Renta",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            RentItem demo = new Movie(1, "Pelicula demo", 50);
            VentanaRenta v = new VentanaRenta(demo);
            v.setVisible(true);
        });
    }
}
