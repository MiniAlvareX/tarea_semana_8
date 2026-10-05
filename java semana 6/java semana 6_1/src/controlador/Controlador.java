package controlador;

import Carrito;
import modelo.Compra;
import modelo.Producto;
import modelo.Tienda;
import vista.Vista;

public class Controlador {

    private Tienda tienda;
    private Carrito carrito;
    private Vista vista;

    public Controlador(Tienda tienda, Carrito carrito, Vista vista) {

        this.tienda = tienda;
        this.carrito = carrito;
        this.vista = vista;
    }

    public void iniciar() {

        int opcion;

        do {

            vista.mostrarMenu();

            opcion = vista.leerEntero("Seleccione una opción: ");

            switch (opcion) {

                case 1:
                    agregarProducto();
                    break;

                case 2:
                    tienda.listarProductos();
                    break;

                case 3:
                    agregarAlCarrito();
                    break;

                case 4:
                    carrito.mostrarCarrito();
                    break;

                case 5:
                    eliminarDelCarrito();
                    break;

                case 6:
                    aplicarDescuento();
                    break;

                case 7:
                    calcularEnvio();
                    break;

                case 8:
                    tienda.mostrarHistorial();
                    break;

                case 9:
                    realizarCompra();
                    break;

                case 0:
                    System.out.println("Programa finalizado.");
                    break;

                default:
                    System.out.println("Opción inválida.");
            }

        } while (opcion != 0);
    }

    private void agregarProducto() {

        int id = vista.leerEntero("Ingrese ID: ");

        String nombre = vista.leerString("Ingrese nombre: ");

        double precio = vista.leerDouble("Ingrese precio: ");

        int stock = vista.leerEntero("Ingrese stock: ");

        Producto producto =
                new Producto(id, nombre, precio, stock);

        tienda.agregarProducto(producto);

        System.out.println("Producto agregado correctamente.");
    }

    private void agregarAlCarrito() {

        int id = vista.leerEntero(
                "Ingrese ID del producto: ");

        Producto producto = tienda.buscarProducto(id);

        if (producto == null) {

            System.out.println("Producto no encontrado.");
            return;
        }

        if (producto.getStock() <= 0) {

            System.out.println("Producto sin stock.");
            return;
        }

        carrito.agregarProducto(producto);

        producto.setStock(producto.getStock() - 1);

        System.out.println(
                "Producto agregado al carrito.");
    }

    private void eliminarDelCarrito() {

        int id = vista.leerEntero(
                "Ingrese ID del producto: ");

        carrito.eliminarProducto(id);
    }

    private void aplicarDescuento() {

        double porcentaje = vista.leerDouble(
                "Ingrese descuento (%): ");

        carrito.aplicarDescuento(porcentaje);
    }

    private void calcularEnvio() {

        double envio = carrito.calcularEnvio();

        System.out.println(
                "Costo de envío: S/ " + envio);
    }

    private void realizarCompra() {

        if (carrito.getProductos().isEmpty()) {

            System.out.println(
                    "No puede realizar una compra con el carrito vacío.");

            return;
        }

        Compra compra = new Compra(carrito);

        tienda.agregarCompra(compra);

        System.out.println("\nCompra realizada correctamente.");

        compra.mostrarCompra();

        carrito.vaciarCarrito();
    }
}