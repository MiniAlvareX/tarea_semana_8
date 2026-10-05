import java.util.Random;

public class CombateController {

    private Jugador jugador;
    private Enemigo enemigo;
    private CombateView vista;
    private Random random;

    public CombateController(Jugador jugador,
                             Enemigo enemigo,
                             CombateView vista) {

        this.jugador = jugador;
        this.enemigo = enemigo;
        this.vista = vista;
        this.random = new Random();
    }

    public void iniciarCombate() {

        vista.mostrarMensaje("¡Comienza el combate!");

        while (jugador.estaVivo() && enemigo.estaVivo()) {

            vista.mostrarEstado(jugador, enemigo);

            // Turno del jugador
            jugador.atacar(enemigo);

            if (!enemigo.estaVivo()) {
                vista.mostrarMensaje(
                        "¡El jugador derrotó al enemigo!");
                break;
            }

            accionEnemigo();

            if (!jugador.estaVivo()) {
                vista.mostrarMensaje(
                        "El jugador ha sido derrotado.");
            }
        }
    }

    private void accionEnemigo() {

        int accion = random.nextInt(2);

        if (accion == 0) {
            vista.mostrarMensaje(
                    enemigo.getNombre() + " decide atacar.");

            enemigo.atacar(jugador);

        } else {
            vista.mostrarMensaje(
                    enemigo.getNombre()
                    + " decide esperar este turno.");
        }
    }
}