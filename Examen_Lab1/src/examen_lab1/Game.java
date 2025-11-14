/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen_lab1;

import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author Cantarero
 */
public class Game implements MenuActions {

    private Calendar Fecha_Publicacion;
    private ArrayList<String> especificaciones;

    public Game() {
        Fecha_Publicacion = Calendar.getInstance();
        especificaciones = new ArrayList<>();
    }

    public void setFechaPublicacion(int year, int mes, int dia) {
        Calendar c = Calendar.getInstance();
        c.set(year, mes - 1, dia);
        this.Fecha_Publicacion = c;
    }

    public ArrayList<String> ListEspecificaciones(){
        return especificaciones;
    }
    
    public void submenu() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void ejecutarOpcion(int opcion) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
