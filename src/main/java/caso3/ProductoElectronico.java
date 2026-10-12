/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caso3;

/**
 *
 * @author eidan
 */

public class ProductoElectronico extends Producto {
    private int mesesGarantia;

    public ProductoElectronico(String nombre, double precio, int stock, String categoria, int mesesGarantia){
        super(nombre, precio, stock, categoria);
        this.mesesGarantia = mesesGarantia;
    }

    // getters

    public int getMesesGarantia(){
        return mesesGarantia;
    }
}
