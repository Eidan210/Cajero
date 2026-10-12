/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caso3;

import java.util.*;

/**
 *
 * @author eidan
 */
public class Inventario {

    private ArrayList<Producto> productos = new ArrayList<>();

    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    public void mostrarCatalogo() {
        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            System.out.println((i + 1) + " " + p.getNombre() + " $ " + p.getPrecio()
                    + " (Stock:" + p.getStock() + ")");
        }
    }

    public boolean hayStock(Producto p, int cantidad) {
        return p.hayStock(cantidad);
    }

    public void disminuirStock(Producto p, int cantidad) {
        p.disminuirStock(cantidad);
    }

    public int cantidadProductos() {
        return productos.size();
    }

    public Producto getProducto(int i) {
        return productos.get(i);
    }
}
