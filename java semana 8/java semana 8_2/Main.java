
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        String url = "jdbc:sqlite:universidad.db";

        try (Connection conexion =
                 DriverManager.getConnection(url)) {

            Gestora gestora = new Gestora(conexion);
            int opcion;

            do {
                System.out.println(
                    "\n CONSULTAS EN JAVA ");
                System.out.println("1. Realizar consulta");
                System.out.println("2. Salir");
                System.out.print("Seleccione una opcion: ");

                opcion = leerEntero();

                switch (opcion) {
                    case 1:
                        realizarConsulta(gestora);
                        break;
                    case 2:
                        System.out.println("Programa finalizado.");
                        break;
                    default:
                        System.out.println("Opcion no valida.");
                }

            } while (opcion != 2);

        } catch (SQLException e) {
            System.out.println(
                "Error de base de datos: " + e.getMessage());
            System.out.println(
                "Verifique la ruta y la existencia de universidad.db.");
        }

        sc.close();
    }

    static int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un numero entero: ");
            }
        }
    }

    static void realizarConsulta(Gestora gestora) {

        System.out.println(
            "\nCampos disponibles: id, nombre, carrera, edad");

        System.out.print(
            "Campos que desea mostrar (separados por coma): ");
        String entradaCampos = sc.nextLine();

        String[] campos = entradaCampos.split(",");
        for (int i = 0; i < campos.length; i++) {
            campos[i] = campos[i].trim();
        }

        System.out.print(
            "Campo para filtrar (Enter para ninguno): ");
        String campoCondicion = sc.nextLine().trim();

        String operador = "";
        String valor = "";

        if (!campoCondicion.isEmpty()) {
            System.out.println(
                "Operadores: =, !=, >, <, >=, <=, contiene");
            System.out.print("Operador: ");
            operador = sc.nextLine().trim();

            System.out.print("Valor de la condicion: ");
            valor = sc.nextLine().trim();
        }

        System.out.print(
            "Campo para ordenar (Enter para ninguno): ");
        String campoOrden = sc.nextLine().trim();

        boolean ascendente = true;

        if (!campoOrden.isEmpty()) {
            System.out.print(
                "Orden (A = ascendente, D = descendente): ");

            String orden = sc.nextLine().trim();

            if (!orden.equalsIgnoreCase("A")
                    && !orden.equalsIgnoreCase("D")) {
                System.out.println("Orden no valido.");
                return;
            }

            ascendente = orden.equalsIgnoreCase("A");
        }

        System.out.print(
            "Limite de registros (0 = todos): ");
        int limite = leerEntero();

        if (limite < 0) {
            System.out.println("El limite no puede ser negativo.");
            return;
        }

        gestora.consultar(
            campos,
            campoCondicion,
            operador,
            valor,
            campoOrden,
            ascendente,
            limite
        );
    }
}
