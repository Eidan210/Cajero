/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caso3;

/**
 *
 * @author eidan
 */
public class Producto {
    private String nombre;
    private double precio;
    private int stock;
    private String categoria;
    
    public Producto (String nombre, double precio, int stock, String categoria){
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }
    
    // getters
    
    public String getNombre(){
        return nombre;
    }
    
    public double getPrecio(){
        return precio;
    }
    
    public int getStock(){
        return stock;
    }
    
    public String getCategoria(){
        return categoria;
    }
    
    // Metodos
    
    public void disminuirStock(int cantidad){
        if (cantidad > 0 && hayStock(cantidad)){
            stock -= cantidad;
        }else{
            System.out.println("Cantidad Invalida...");
        }
    } 
    
    
    public boolean hayStock(int cantidad){
        return stock >= cantidad;
    }
}
