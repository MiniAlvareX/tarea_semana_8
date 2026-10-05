package modelo;

import java.util.ArrayList;

public class Carrito {

    private ArrayList<Producto> productos;
    private double descuento;

    public Carrito() {
        productos = new ArrayList<>();
        descuento = 0;
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void eliminarProducto(int id) {

        for (Producto producto : productos) {

            if (producto.getId() == id) {
                productos.remove(producto);
                System.out.println("Producto eliminado del carrito.");
                return;
            }
        }

        System.out.println("Producto no encontrado en el carrito.");
    }

    public void mostrarCarrito() {

        if (productos.isEmpty()) {
            System.out.println("El carrito está vacío.");
            return;
        }

        System.out.println("\n===== CARRITO =====");

        for (Producto producto : productos) {
            System.out.println(producto);
        }

        System.out.println("Subtotal: S/ " + calcularSubtotal());
        System.out.println("Descuento: S/ " + calcularDescuento());
        System.out.println("Envío: S/ " + calcularEnvio());
        System.out.println("Total: S/ " + calcularTotal());
    }

    public double calcularSubtotal() {

        double subtotal = 0;

        for (Producto producto : productos) {
            subtotal += producto.getPrecio();
        }

        return subtotal;
    }

    public void aplicarDescuento(double porcentaje) {

        if (porcentaje < 0 || porcentaje > 100) {
            System.out.println("Descuento inválido.");
            return;
        }

        descuento = porcentaje;

        System.out.println("Descuento aplicado: " + porcentaje + "%");
    }

    public double calcularDescuento() {

        return calcularSubtotal() * descuento / 100;
    }

    public double calcularEnvio() {

        double subtotal = calcularSubtotal();

        if (subtotal == 0) {
            return 0;
        }

        if (subtotal >= 100) {
            return 0;
        }

        return 10;
    }

    public double calcularTotal() {

        return calcularSubtotal()
                - calcularDescuento()
                + calcularEnvio();
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public void vaciarCarrito() {
        productos.clear();
        descuento = 0;
    }
}