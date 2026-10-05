public class CombateView {

    public void mostrarEstado(Jugador jugador, Enemigo enemigo) {
        System.out.println("\n ESTADO DEL COMBATE ");

        System.out.println("Jugador: " + jugador.getNombre());
        System.out.println("Salud: " + jugador.getSalud());
        System.out.println("Nivel: " + jugador.getNivel());

        if (jugador.getObjetoEquipado() != null) {
            System.out.println("Objeto equipado: "
                    + jugador.getObjetoEquipado().getNombre());
        } else {
            System.out.println("Objeto equipado: Ninguno");
        }

        System.out.println();

        System.out.println("Enemigo: " + enemigo.getNombre());
        System.out.println("Tipo: " + enemigo.getTipo());
        System.out.println("Salud: " + enemigo.getSalud());
        System.out.println("Nivel: " + enemigo.getNivel());

        System.out.println(" ");
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}