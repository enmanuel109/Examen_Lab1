package examen_lab1;

import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
        super("Rentar Ítem", 540, 370);
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
        txtInfo.setBounds(220, 80, 280, 100);
        txtInfo.setFont(txtInfo.getFont().deriveFont(14f));
        actualizarInfo();
        panelPrincipal.add(txtInfo);

        JLabel lblDias = new JLabel("Días de renta:");
        lblDias.setBounds(220, 225, 120, 30);
        lblDias.setFont(lblDias.getFont().deriveFont(14f));
        panelPrincipal.add(lblDias);

        txtDias = createTextField(330, 225, 80, 30);
        txtDias.setBorder(new CompoundBorder(
                new LineBorder(new Color(220, 220, 220), 1, true),
                new EmptyBorder(3, 10, 3, 10)
        ));
        panelPrincipal.add(txtDias);

        btnCalcular = createBtn("Calcular");
        btnCalcular.setBounds(223, 266, 105, 28);
        panelPrincipal.add(btnCalcular);

        btnCerrar = createBtn("Cerrar");
        btnCerrar.setBounds(350, 265, 110, 30);
        btnCerrar.setBackground(Color.red);
        panelPrincipal.add(btnCerrar);

        btnCerrar.addActionListener(e -> dispose());
        btnCalcular.addActionListener(e -> calcularRenta());

        setContentPane(panelPrincipal);
    }

    private void actualizarInfo() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        String info = "Código: " + item.getCodigo()
                + "\nNombre: " + item.getNombre()
                + "\nPrecio base: " + item.getPrecioBase() + " Lps"
                + "\nCopias disponibles: " + item.cantCopias;

        if (item instanceof Movie) {
            Movie m = (Movie) item;
            Calendar c = m.getFechaEstreno();
            String fecha = sdf.format(c.getTime());
            info = info
                    + "\nEstado: " + m.getEstado()
                    + "\nFecha de estreno: " + fecha;
        } else if (item instanceof Game) {
            Game g = (Game) item;
            Calendar c = g.getFechaPublicacion();
            String fecha = sdf.format(c.getTime());
            info = info
                    + "\nTipo: Videojuego"
                    + "\nFecha de publicación: " + fecha;
        }

        txtInfo.setText(info);
    }

    private void calcularRenta() {
        if (item.cantCopias <= 0) {
            JOptionPane.showMessageDialog(this, "No hay copias disponibles.", "Sin stock", JOptionPane.ERROR_MESSAGE);
            return;
        }

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

        item.cantCopias--;
        actualizarInfo();

        JOptionPane.showMessageDialog(
                this,
                "Monto total de la renta: " + total + " Lps"
                + "\nCopias restantes: " + item.cantCopias,
                "Total Renta",
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}
