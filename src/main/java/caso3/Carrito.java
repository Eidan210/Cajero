/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package caso3;

/**
 *
 * @author eidan
 */
public class Carrito {

    private Producto productos[] = new Producto[10];
    private int cantidades[] = new int[10];
    private int cantidadCompras;
    private Cliente cliente;
    private Inventario inventario;

    public Carrito(Cliente cliente, Inventario inventario){
        this.cliente = cliente;
        this.inventario = inventario;
    }

    public void agregarProducto(Producto p, int cantidad) {
        if (cantidadCompras == productos.length) {
            System.out.println("El carrito esta lleno..");
        } else if (cantidad > 0 && inventario.hayStock(p,cantidad)) {
            // 1. descontar el stock del producto
            inventario.disminuirStock(p,cantidad);

            // 2. guardar el producto en su casilla
            productos[cantidadCompras] = p;

            // 3. guardar la cantidad en su casilla
            cantidades[cantidadCompras] = cantidad;

            // 4. aumentar el contador
            cantidadCompras++;
            System.out.println("Compra exitosa: " + cantidad + " x " + p.getNombre());
        } else {
            System.out.println("Cantidad invalida o sin stock...");
        }

    }

    public double calcularSubTotal() {
        double total = 0;
        for (int i = 0; i < cantidadCompras; i++) {
            total += productos[i].getPrecio() * cantidades[i];
        }
        return total;
    }

    public double calcularTotal() {
        double subtotal = calcularSubTotal();
        if (subtotal > 100000) {
            return subtotal * 0.90;
        }
        return subtotal;
    }

    public void mostrarFactura() {
        System.out.println("===== FACTURA =====");
        System.out.println("Cliente: " + cliente.getNombre() + " (" + cliente.getEmail() + ")");

        for (int i = 0; i < cantidadCompras; i++) {
            double subtotal = productos[i].getPrecio() * cantidades[i];
            System.out.println(productos[i].getNombre() + " x" + cantidades[i] + " = $" + subtotal);
        }
        double subtotalCompra = calcularSubTotal();
        double total = calcularTotal();
        double descuento = subtotalCompra - total;
        System.out.println("SUBTOTAL: $" + subtotalCompra);
        System.out.println("DESCUENTO: $" + descuento);
        System.out.println("TOTAL: $" + total);
    }
}
