/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen_lab1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;

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

    public void submenu() {
        while (true) {
            String input = JOptionPane.showInputDialog(
                    null,
                    "--- Submenu de " + getNombre() + " ---\n"
                    + "1) Actualizar fecha de publicacion\n"
                    + "2) Agregar especificacion\n"
                    + "3) Ver especificaciones\n"
                    + "4) Salir",
                    JOptionPane.QUESTION_MESSAGE
            );

            if (input == null) {
                break;
            }

            int opcion;

            try {
                opcion = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Opcion invalida", "Error", JOptionPane.ERROR_MESSAGE);
                continue;
            }

            if (opcion == 4) {
                break;
            }

            ejecutarOpcion(opcion);
        }
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

    @Override
    public void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                String Year = JOptionPane.showInputDialog(null, "Año de publicacio:");
                String Month = JOptionPane.showInputDialog(null, "Mes (1-12):");
                String DAY = JOptionPane.showInputDialog(null, "Día:");

                if (Year == null || Month == null || DAY == null) {
                    return;
                }

                try {
                    int NewYear = Integer.parseInt(Year);
                    int NewMonth = Integer.parseInt(Month);
                    int NewDay = Integer.parseInt(DAY);

                    setFechaPublicacion(NewYear, NewMonth, NewDay);
                    JOptionPane.showMessageDialog(null, "Fecha actualizada");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Datos invalidos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                break;

            case 2:
                String NewEspecificacion = JOptionPane.showInputDialog(null, "Ingrese nueva especificacion:");
                if (NewEspecificacion != null && !NewEspecificacion.trim().isEmpty()) {
                    especificaciones.add(NewEspecificacion.trim());
                    JOptionPane.showMessageDialog(null, "Especificacion agregada.");
                }
                break;

            case 3:
                listEspecificaciones();
                break;

            default:
                JOptionPane.showMessageDialog(null, "Opcion no valida.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
