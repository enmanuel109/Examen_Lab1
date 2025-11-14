/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen_lab1;

import java.util.Calendar;

/**
 *
 * @author esteb
 */

/*
Cree una clase Movie que herede de RentItem. Esta clase tiene un atributo adicional:
Fecha de estreno (por defecto, la fecha actual y uso del calendario).
Requisitos:
Métodos set y get para la fecha de estreno.
Redefinir toString() para mostrar lo de RentItem + estado + el texto " – Movie".
Método String getEstado() que calcule si la película es:
ESTRENO: si la diferencia entre la fecha actual y la de estreno es ≤ 3 meses.
NORMAL: en caso contrario.
Redefinir pagoRenta(int días) con la siguiente lógica:
Si es ESTRENO y se alquila más de 2 días → recargo de 50 Lps por cada día adicional.
Si es NORMAL y se alquila más de 5 días → recargo de 30 Lps por cada día adicional.
 */
public class Movie extends RentItem {

    private Calendar fechaEstreno;

    public Movie(int codItem, String nombItem, double precioBase) {
        super(codItem, nombItem, precioBase);
        this.fechaEstreno = Calendar.getInstance();
    }

    public void setFechaEstreno(Calendar fechaEstreno) {
        this.fechaEstreno = fechaEstreno;
    }

    public Calendar getFechaEstreno() {
        return fechaEstreno;
    }

    public String getEstado() {
        Calendar hoy = Calendar.getInstance();

        int anioEstreno = fechaEstreno.get(Calendar.YEAR);
        int mesEstreno = fechaEstreno.get(Calendar.MONTH);

        int anioActual = hoy.get(Calendar.YEAR);
        int mesActual = hoy.get(Calendar.MONTH);

        int mesesDif = (anioActual - anioEstreno) * 12 + (mesActual - mesEstreno);

        if (mesesDif < 3) {
            return "ESTRENO";
        } else {
            return "NORMAL";
        }

    }

    @Override
    public String toString() {
 
        return super.toString() + "| Estado: " + getEstado();
    }

    @Override
    public double pagoRenta(int dias) {
        double total = precioBase * dias;
        String estado = getEstado();
        if ("ESTRENO".equals(estado) && dias > 2) {
            total += 50.0 * (dias - 2);
        } else if ("NORMAL".equals(estado) && dias > 5) {
            total += 30.0 * (dias - 5);
        }
        return total;
    }

}
