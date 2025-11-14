package examen_lab1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MenuPrincipal extends BaseGUI {

    private JPanel panelPrincipal;
    private JButton btnAgregarItem, btnRentar, btnEjecutar, btnSalir, btnImprimir;
    private JLabel lblTitulo;
    public static ArrayList<RentItem> rentItems = new ArrayList<>();

    public MenuPrincipal() {
        super("Menu Principal", 615, 550);
        initComponents();
    }

    public void initComponents() {
        panelPrincipal = createPanelPrincipal();
        panelPrincipal.setLayout(null);

        lblTitulo = createLabelTitle("MENU PRINCIPAL", 175, 20, 280, 50);
        panelPrincipal.add(lblTitulo);

        btnAgregarItem = createBtn("Agregar Item");
        btnAgregarItem.setBounds(200, 105, 220, 50);
        panelPrincipal.add(btnAgregarItem);

        btnRentar = createBtn("Rentar");
        btnRentar.setBounds(200, 185, 220, 50);
        panelPrincipal.add(btnRentar);

        btnEjecutar = createBtn("Ejecutar Submenú");
        btnEjecutar.setBounds(200, 265, 220, 50);
        panelPrincipal.add(btnEjecutar);

        btnImprimir = createBtn("Ver items registrados");
        btnImprimir.setBounds(200, 345, 220, 50);
        panelPrincipal.add(btnImprimir);

        btnSalir = createBtn("Salir");
        btnSalir.setBounds(480, 455, 80, 40);
        panelPrincipal.add(btnSalir);

        btnSalir.addActionListener(e -> dispose());
        btnAgregarItem.addActionListener(e -> agregarItem());
        btnRentar.addActionListener(e -> Rentar());
        btnImprimir.addActionListener(e -> imprimirTodo());

        setContentPane(panelPrincipal);
    }

    private void agregarItem() {
        String opciones[] = {"Movie", "Game"};
        int tipo = JOptionPane.showOptionDialog(
                this,
                "Seleccione tipo de ítem",
                "Agregar Ítem",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                opciones,
                opciones[0]
        );

        if (tipo == JOptionPane.CLOSED_OPTION) {
            return;
        }

        String codStr = JOptionPane.showInputDialog(this, "Código (número entero):");
        if (codStr == null) {
            return;
        }

        int codigo;
        try {
            codigo = Integer.parseInt(codStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Código inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (RentItem ri : rentItems) {
            if (ri.getCodigo() == codigo) {
                JOptionPane.showMessageDialog(this, "El código ya existe.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        String nombre = JOptionPane.showInputDialog(this, "Nombre del ítem:");
        if (nombre == null || nombre.trim().isEmpty()) {
            return;
        }
        nombre = nombre.trim();

        RentItem nuevo;

        if (tipo == 0) {
            String precioStr = JOptionPane.showInputDialog(this, "Precio base de renta:");
            if (precioStr == null) {
                return;
            }
            double precio;
            try {
                precio = Double.parseDouble(precioStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Precio inválido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            nuevo = new Movie(codigo, nombre, precio);
        } else {
            nuevo = new Game(codigo, nombre);
        }

        JFileChooser chooser = new JFileChooser();
        chooser.setFileFilter(new FileNameExtensionFilter("Imágenes", "jpg", "jpeg", "png", "gif"));
        int resp = chooser.showOpenDialog(this);
        if (resp == JFileChooser.APPROVE_OPTION) {
            ImageIcon icon = new ImageIcon(chooser.getSelectedFile().getAbsolutePath());
            nuevo.setImagen(icon);
        }

        rentItems.add(nuevo);
        JOptionPane.showMessageDialog(this, "Ítem agregado correctamente.");
    }

    public void Rentar() {
        if (rentItems.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay ítems registrados.", "Rentar", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String codStr = JOptionPane.showInputDialog(this, "Ingrese el código del ítem a rentar:");
        if (codStr == null) {
            return;
        }

        int codigo;
        try {
            codigo = Integer.parseInt(codStr);
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Código inválido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        RentItem encontrado = null;
        for (RentItem ri : rentItems) {
            if (ri.getCodigo() == codigo) {
                encontrado = ri;
                break;
            }
        }

        if (encontrado == null) {
            JOptionPane.showMessageDialog(this, "Item No Existe", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        VentanaRenta vr = new VentanaRenta(encontrado);
        vr.setVisible(true);
    }

    private void imprimirTodo() {
        if (rentItems.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay ítems registrados", "Ver ítems", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        JPanel listado = new JPanel();
        listado.setLayout(new BoxLayout(listado, BoxLayout.Y_AXIS));
        listado.setBackground(panelPrincipal.getBackground());
        listado.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (RentItem r : rentItems) {
            JPanel card = new JPanel(new BorderLayout(10, 10));
            card.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
            card.setBackground(Color.WHITE);
            card.setMaximumSize(new Dimension(700, 260));
            card.setPreferredSize(new Dimension(700, 260));

            JLabel imgLabel = new JLabel();
            imgLabel.setHorizontalAlignment(SwingConstants.CENTER);
            imgLabel.setVerticalAlignment(SwingConstants.CENTER);
            if (r.getImagen() != null) {
                ImageIcon original = r.getImagen();
                Image scaled = original.getImage().getScaledInstance(160, 200, Image.SCALE_SMOOTH);
                imgLabel.setIcon(new ImageIcon(scaled));
            } else {
                imgLabel.setPreferredSize(new Dimension(160, 200));
                imgLabel.setText("(Sin imagen)");
            }
            imgLabel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
            card.add(imgLabel, BorderLayout.WEST);

            JTextArea ta = new JTextArea();
            ta.setEditable(false);
            ta.setOpaque(false);
            if (lblTitulo != null) {
                ta.setFont(lblTitulo.getFont().deriveFont(Font.PLAIN, 14f));
            } else {
                ta.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            }
            StringBuilder sb = new StringBuilder();
            sb.append("Código: ").append(r.getCodigo()).append("\n");
            sb.append("Nombre: ").append(r.getNombre()).append("\n");
            if (r instanceof Movie) {
                try {
                    sb.append("Estado: ").append(((Movie) r).getEstado()).append("\n");
                } catch (Exception ex) {
                }
            }
            sb.append(String.format("Precio renta: %.2f Lps/día\n", r.getPrecioBase()));
            ta.setText(sb.toString());
            ta.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
            card.add(ta, BorderLayout.CENTER);

            listado.add(card);
            listado.add(Box.createVerticalStrut(10));
        }

        JScrollPane scroll = new JScrollPane(listado, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setPreferredSize(new Dimension(730, 480));
        scroll.getVerticalScrollBar().setUnitIncrement(16);

        JPanel dialogPanel = new JPanel(new BorderLayout(8, 8));
        dialogPanel.setBackground(panelPrincipal.getBackground());
        JLabel title = new JLabel("LISTADO DE ÍTEMS", SwingConstants.CENTER);
        if (lblTitulo != null) {
            title.setFont(lblTitulo.getFont());
        } else {
            title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        }
        title.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
        dialogPanel.add(title, BorderLayout.NORTH);
        dialogPanel.add(scroll, BorderLayout.CENTER);

        JOptionPane.showMessageDialog(this, dialogPanel, "Listado de Ítems", JOptionPane.PLAIN_MESSAGE);
    }

    public static void main(String[] args) {
        new MenuPrincipal().setVisible(true);
    }
}
