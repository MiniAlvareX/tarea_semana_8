package modelo;

import java.util.ArrayList;

public class Tienda {

    private ArrayList<Producto> productos;
    private ArrayList<Compra> historial;

    public Tienda() {

        productos = new ArrayList<>();
        historial = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public void listarProductos() {

        System.out.println("\n===== PRODUCTOS =====");

        if (productos.isEmpty()) {
            System.out.println("No hay productos registrados.");
            return;
        }

        for (Producto producto : productos) {
            System.out.println(producto);
        }
    }

    public Producto buscarProducto(int id) {

        for (Producto producto : productos) {

            if (producto.getId() == id) {
                return producto;
            }
        }

        return null;
    }

    public void agregarCompra(Compra compra) {
        historial.add(compra);
    }

    public void mostrarHistorial() {

        System.out.println("\n===== HISTORIAL DE COMPRAS =====");

        if (historial.isEmpty()) {
            System.out.println("No existen compras realizadas.");
            return;
        }

        for (int i = 0; i < historial.size(); i++) {

            System.out.println("\nCompra #" + (i + 1));

            historial.get(i).mostrarCompra();
        }
    }
}