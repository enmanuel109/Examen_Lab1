package examen_lab1;

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

    public static void main(String[] args) {
        new MenuPrincipal().setVisible(true);
    }
}
