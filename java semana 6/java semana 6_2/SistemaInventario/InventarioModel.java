import java.util.ArrayList;
import java.util.List;

public class InventarioModel {
    private List<Item> items;

    public InventarioModel() {
        items = new ArrayList<>();
    }

    public void agregarItem(Item item) {
        items.add(item);
    }

    public void eliminarItem(Item item) {
        items.remove(item);
    }

    public List<Item> obtenerItems() {
        return items;
    }

    public List<Item> buscarItem(String nombre) {
        List<Item> resultados = new ArrayList<>();

        for (Item item : items) {
            if (item.getNombre().equalsIgnoreCase(nombre)) {
                resultados.add(item);
            }
        }

        return resultados;
    }
}