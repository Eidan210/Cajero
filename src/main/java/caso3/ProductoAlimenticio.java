package caso3;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author eidan
 */
public class ProductoAlimenticio extends Producto{
    private String fechaCaducidad;
    
    public ProductoAlimenticio(String nombre,double precio, int stock,String categoria,String fechaCaducidad){
        super(nombre, precio, stock, categoria);
        this.fechaCaducidad = fechaCaducidad;
    }
    
    // getters
    
    public String getFechaCaducidad(){
        return fechaCaducidad;
    }
}
