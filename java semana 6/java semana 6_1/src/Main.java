import controlador.Controlador;
import modelo.Producto;
import modelo.Tienda;
import vista.Vista;

public class Main {

    public static void main(String[] args) {

        Tienda tienda = new Tienda();

        Carrito carrito = new Carrito();

        Vista vista = new Vista();

        // Productos iniciales
        tienda.agregarProducto(
                new Producto(1, "Laptop", 2500.00, 5));

        tienda.agregarProducto(
                new Producto(2, "Mouse", 50.00, 10));

        tienda.agregarProducto(
                new Producto(3, "Teclado", 120.00, 8));

        tienda.agregarProducto(
                new Producto(4, "Monitor", 800.00, 4));

        Controlador controlador =
                new Controlador(tienda, carrito, vista);

        controlador.iniciar();
    }
}