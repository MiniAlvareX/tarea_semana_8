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

    @Override
    public String toString() {
        return "Nombre: " + nombre +
               ", Cantidad: " + cantidad +
               ", Tipo: " + tipo +
               ", Descripción: " + descripcion +
               ", Usuario: " + userItem;
    }
}