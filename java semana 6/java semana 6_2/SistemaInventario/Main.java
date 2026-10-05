public class Main {
    public static void main(String[] args) {

        // Crear Modelo y Vista
        InventarioModel modelo = new InventarioModel();
        InventarioView vista = new InventarioView();

        // Crear Controlador
        InventarioController controlador =
                new InventarioController(modelo, vista);

        // Crear items
        Item item1 = new Item(
                "Laptop",
                10,
                "Computadora",
                "Laptop para oficina",
                "Hector"
        );

        Item item2 = new Item(
                "Mouse",
                25,
                "Periférico",
                "Mouse inalámbrico",
                "Hector"
        );

        // Agregar items
        controlador.agregarItem(item1);
        controlador.agregarItem(item2);

        // Mostrar inventario
        controlador.verInventario();

        // Mostrar detalles
        controlador.mostrarDetalles(item1);

        // Buscar item
        System.out.println("\n--- BUSCAR ITEM ---");
        controlador.buscarItem("Mouse");

        // Eliminar item
        controlador.eliminarItem(item1);

        // Mostrar inventario actualizado
        controlador.verInventario();
    }
}