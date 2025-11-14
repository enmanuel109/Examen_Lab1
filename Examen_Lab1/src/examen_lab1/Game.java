/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen_lab1;

import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

/**
 *
 * @author Cantarero
 */
public class Game extends RentItem implements MenuActions {

    private Calendar Fecha_Publicacion;
    private ArrayList<String> especificaciones;

    public Game(int codItem, String nombItem) {
        super(codItem, nombItem, 20);
        Fecha_Publicacion = Calendar.getInstance();
        especificaciones = new ArrayList<>();
    }

    public void setFechaPublicacion(int year, int month, int day) {
        Calendar FechaPublicacion = Calendar.getInstance();
        FechaPublicacion.set(year, month - 1, day);
        this.Fecha_Publicacion = FechaPublicacion;
    }

    public void listEspecificaciones() {
        listarRec(0);
    }

    private void listarRec(int index) {
        if (index >= especificaciones.size()) {
            return;
        }
        System.out.println("- " + especificaciones.get(index));
        listarRec(index + 1);
    }

    @Override
    public double pagoRenta(int dias) {
        return 20 * dias;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = simpleDate.format(Fecha_Publicacion.getTime());

        return super.toString() + " | Publicacion: " + fecha + " – PS3 Game";
    }

    public void submenu() {
        JFrame Submenu = new JFrame("Submenu de " + getNombre());
        Submenu.setSize(350, 250);
        Submenu.setLocationRelativeTo(null);
        Submenu.setLayout(new GridLayout(5, 1));
        Submenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton BtnFecha = new JButton("Actualizar fecha de publicacion");
        JButton BtnAgregarEspecificacion = new JButton("Agregar especificacion");
        JButton BtnVerEspecificacion = new JButton("Ver especificaciones");
        JButton BtnSalir = new JButton("Salir");

        BtnFecha.addActionListener(e -> ejecutarOpcion(1));

        BtnAgregarEspecificacion.addActionListener(e -> ejecutarOpcion(2));

        BtnVerEspecificacion.addActionListener(e -> ejecutarOpcion(3));

        BtnSalir.addActionListener(e -> Submenu.dispose());

        Submenu.add(new JLabel("Submenu de " + getNombre(), SwingConstants.CENTER));
        Submenu.add(BtnFecha);
        Submenu.add(BtnAgregarEspecificacion);
        Submenu.add(BtnVerEspecificacion);
        Submenu.add(BtnSalir);

        Submenu.setVisible(true);
    }

    @Override
    public void ejecutarOpcion(int opcion) {
        switch (opcion) {

            case 1:
                JFrame fechaFrame = new JFrame("Actualizar fecha");
                fechaFrame.setSize(300, 200);
                fechaFrame.setLayout(new GridLayout(4, 2));
                fechaFrame.setLocationRelativeTo(null);

                JTextField TxtYear = new JTextField();
                JTextField TxtMonth = new JTextField();
                JTextField TxtDay = new JTextField();
                JButton btnGuardar = new JButton("Guardar");

                fechaFrame.add(new JLabel("Año:"));
                fechaFrame.add(TxtYear);
                fechaFrame.add(new JLabel("Mes (1-12):"));
                fechaFrame.add(TxtMonth);
                fechaFrame.add(new JLabel("Dia:"));
                fechaFrame.add(TxtDay);
                fechaFrame.add(btnGuardar);

                btnGuardar.addActionListener(ev -> {
                    try {
                        int Year = Integer.parseInt(TxtYear.getText());
                        int Month = Integer.parseInt(TxtMonth.getText());
                        int Day = Integer.parseInt(TxtDay.getText());
                        setFechaPublicacion(Year, Month, Day);
                        JOptionPane.showMessageDialog(null, "Fecha actualizada");
                        fechaFrame.dispose();
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Datos invalidos");
                    }
                });

                fechaFrame.setVisible(true);
                break;

            case 2:
                String esp = JOptionPane.showInputDialog("Ingrese nueva especificacion:");
                if (esp != null && !esp.trim().isEmpty()) {
                    especificaciones.add(esp.trim());
                    JOptionPane.showMessageDialog(null, "Especificacion agregada");
                }
                break;

            case 3:
                listEspecificaciones();
                break;

            default:
                JOptionPane.showMessageDialog(null, "Opcion no valida.");
        }
    }

}
