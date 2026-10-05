public class Item {
    private String nombre;
    private int cantidad;
    private String tipo;
    private String descripcion;
    private String userItem;

    public Item(String nombre, int cantidad, String tipo,
                String descripcion, String userItem) {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.userItem = userItem;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getTipo() {
        return tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getUserItem() {
        return userItem;
    }

    // Daño según el objeto equipado
    public int getDanio() {
        if (tipo.equalsIgnoreCase("Espada")) {
            return 30;
        } else if (tipo.equalsIgnoreCase("Arco")) {
            return 25;
        } else if (tipo.equalsIgnoreCase("Hacha")) {
            return 35;
        }

        return 10;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre +
               ", Cantidad: " + cantidad +
               ", Tipo: " + tipo;
    }
}