package modelo;

import java.util.ArrayList;

public class Compra {

    private ArrayList<Producto> productos;
    private double subtotal;
    private double descuento;
    private double envio;
    private double total;

    public Compra(Carrito carrito) {

        productos = new ArrayList<>(carrito.getProductos());

        subtotal = carrito.calcularSubtotal();
        descuento = carrito.calcularDescuento();
        envio = carrito.calcularEnvio();
        total = carrito.calcularTotal();
    }

    public void mostrarCompra() {

        System.out.println("\n===== COMPRA =====");

        for (Producto producto : productos) {
            System.out.println(producto);
        }

        System.out.println("Subtotal: S/ " + subtotal);
        System.out.println("Descuento: S/ " + descuento);
        System.out.println("Envío: S/ " + envio);
        System.out.println("Total: S/ " + total);
    }
}