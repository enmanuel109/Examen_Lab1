/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package examen_lab1;

import javax.swing.ImageIcon;

/**
 *
 * @author esteb
 */

/*
Cree una clase abstracta y base llamada RentItem con los siguientes atributos:
Código del ítem (único).
Nombre del ítem.
Precio base de renta.
Cantidad de copias disponibles (inicia en 0).
Imagen del ítem (cargada desde archivo en GUI).

Requisitos:
Constructor que inicialice los tres primeros atributos; las copias inician en 0.
Método toString() redefinido para devolver una cadena con los atributos principales.
Método abstracto double pagoRenta(int días).
Métodos getters para los tres atributos iniciales.
*/

public abstract class RentItem {
    protected int codItem;
    protected String nombItem;
    protected double precioBase;
    protected int cantCopias;
    protected ImageIcon imagen;

    public RentItem(int codItem, String nombItem, double precioBase ) {
        this.codItem = codItem;
        this.nombItem = nombItem;
        this.precioBase = precioBase;
        this.cantCopias = 0;
        this.imagen = null;
    }
    
    public int getCodigo(){
        return codItem;
    }
    
    public String getNombre(){
        return nombItem;
    }
    
    public double getPrecioBase(){
        return precioBase;
    }
    
    public ImageIcon getImagen(){
        return imagen;
    }
    
    public void setImagen(ImageIcon img){
        this.imagen = img;
    }
    
    @Override
    public String toString(){
        
        return codItem + " - " + nombItem + " |Precio Base: " + precioBase + " Lps"; 
    }
    
    public abstract double pagoRenta(int dias);
}
