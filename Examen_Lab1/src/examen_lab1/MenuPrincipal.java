package examen_lab1;

import javax.swing.*;

public class MenuPrincipal extends BaseGUI {

    //variables
    private JPanel panelPrincipal;
    private JButton btnAgregarItem, btnRentar, btnEjecutar, btnSalir, btnImprimir;
    private JLabel lblTitulo;

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

        //listeners
        btnSalir.addActionListener(e -> dispose());

        btnAgregarItem.addActionListener(e -> agregarItem());

        setContentPane(panelPrincipal);
    }

    //funciones de los botones
    private void agregarItem() {
        /*
        Medio importante (funcionamiento de un option dialog):
        int respuesta = JOptionPane.showOptionDialog(
        frame padre (this),
        mensaje,
        título de la ventana,
        optionType,
        messageType,
        icono (opcional, yo puse null),
        options (arreglo con el texto de los botones),
        default option
         */

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
        } //si no se pone esto lo lleva al otro joption

        String codStr = JOptionPane.showInputDialog(this, "Código (entero):");
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

        //en construcción
    }
    
    public void Rentar(){
        //en construcción
    }

    public static void main(String[] args) {
        new MenuPrincipal().setVisible(true);
    }
}
