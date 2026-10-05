public class InventarioController {
    private InventarioModel modelo;
    private InventarioView vista;

    public InventarioController(InventarioModel modelo, InventarioView vista) {
        this.modelo = modelo;
        this.vista = vista;
    }

    public void agregarItem(Item item) {
        modelo.agregarItem(item);
        vista.mostrarMensaje("Item agregado correctamente.");
    }

    public void eliminarItem(Item item) {
        modelo.eliminarItem(item);
        vista.mostrarMensaje("Item eliminado correctamente.");
    }

    public void verInventario() {
        vista.mostrarInventario(modelo.obtenerItems());
    }

    public void mostrarDetalles(Item item) {
        vista.mostrarDetalles(item);
    }

    public void buscarItem(String nombre) {
        vista.mostrarInventario(modelo.buscarItem(nombre));
    }
}