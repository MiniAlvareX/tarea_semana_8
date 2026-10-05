import java.util.List;

public class Jugador {
    private String nombre;
    private int salud;
    private int nivel;
    private List<Item> inventario;
    private Item objetoEquipado;

    public Jugador(String nombre, int salud, int nivel,
                   List<Item> inventario) {
        this.nombre = nombre;
        this.salud = salud;
        this.nivel = nivel;
        this.inventario = inventario;
    }

    public void atacar(Enemigo enemigo) {
        if (objetoEquipado != null) {
            int danio = objetoEquipado.getDanio();
            enemigo.recibirDanio(danio);

            System.out.println(nombre + " ataca con "
                    + objetoEquipado.getNombre()
                    + " y causa " + danio + " de daño.");
        } else {
            System.out.println(nombre + " no tiene un objeto equipado.");
        }
    }

    public void usarObjeto(Item objeto) {
        if (inventario.contains(objeto)) {
            objetoEquipado = objeto;

            System.out.println(nombre + " equipó: "
                    + objeto.getNombre());
        }
    }

    public void recibirDanio(int danio) {
        salud -= danio;

        if (salud < 0) {
            salud = 0;
        }

        System.out.println(nombre + " recibió "
                + danio + " de daño.");
    }

    public String getNombre() {
        return nombre;
    }

    public int getSalud() {
        return salud;
    }

    public int getNivel() {
        return nivel;
    }

    public Item getObjetoEquipado() {
        return objetoEquipado;
    }

    public boolean estaVivo() {
        return salud > 0;
    }
}