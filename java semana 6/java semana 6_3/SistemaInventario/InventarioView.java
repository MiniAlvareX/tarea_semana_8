import java.util.List;

public class InventarioView {

    public void mostrarInventario(List<Item> items) {
        System.out.println("\n INVENTARIO ");

        for (Item item : items) {
            System.out.println(item);
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarDetalles(Item item) {
        System.out.println("\n DETALLES DEL ITEM ");
        System.out.println("Nombre: " + item.getNombre());
        System.out.println("Cantidad: " + item.getCantidad());
        System.out.println("Tipo: " + item.getTipo());
        System.out.println("Descripción: " + item.getDescripcion());
        System.out.println("Usuario: " + item.getUserItem());
    }
}