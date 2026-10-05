import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Item espada = new Item(
                "Espada de hierro",
                1,
                "Espada",
                "Una espada básica",
                "Hector"
        );

        Item pocion = new Item(
                "Poción",
                2,
                "Poción",
                "Poción para recuperar salud",
                "Hector"
        );

        List<Item> inventario = new ArrayList<>();

        inventario.add(espada);
        inventario.add(pocion);

        Jugador jugador = new Jugador(
                "Hector",
                100,
                1,
                inventario
        );

        jugador.usarObjeto(espada);

        Enemigo enemigo = new Enemigo(
                "Goblin",
                100,
                2,
                "Guerrero"
        );

        CombateView vista = new CombateView();

        CombateController controlador =
                new CombateController(
                        jugador,
                        enemigo,
                        vista
                );

        controlador.iniciarCombate();
    }
}