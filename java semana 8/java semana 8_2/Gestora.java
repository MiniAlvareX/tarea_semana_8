
import java.sql.*;
import java.util.Arrays;
import java.util.Comparator;

public class Gestora {

    private Estudiante[] registros;
    private int cantidad;

    public Gestora(Connection conexion) throws SQLException {

        try (Statement st = conexion.createStatement();
             ResultSet rs = st.executeQuery(
                     "SELECT COUNT(*) FROM estudiantes")) {

            rs.next();
            cantidad = rs.getInt(1);
        }

        registros = new Estudiante[cantidad];

        // Cargar los registros en el arreglo de objetos.
        String sql = "SELECT id, nombre, carrera, edad FROM estudiantes";

        try (Statement st = conexion.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            int i = 0;

            while (rs.next()) {
                registros[i++] = new Estudiante(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("carrera"),
                    rs.getInt("edad")
                );
            }
        }
    }

    private boolean campoValido(String campo) {
        return campo.equalsIgnoreCase("id")
            || campo.equalsIgnoreCase("nombre")
            || campo.equalsIgnoreCase("carrera")
            || campo.equalsIgnoreCase("edad");
    }

    private int comparar(Object a, Object b) {
        if (a instanceof Number && b instanceof Number) {
            return Double.compare(
                ((Number) a).doubleValue(),
                ((Number) b).doubleValue()
            );
        }

        return String.valueOf(a).compareToIgnoreCase(
            String.valueOf(b)
        );
    }

    private boolean cumpleCondicion(
            Estudiante e, String campo,
            String operador, String valor) {

        if (campo == null || campo.isBlank()) {
            return true;
        }

        Object dato = e.obtenerCampo(campo);
        int comparacion;

        if (dato instanceof Number) {
            try {
                comparacion = comparar(
                    dato, Double.parseDouble(valor));
            } catch (NumberFormatException ex) {
                System.out.println(
                    "El valor de la condicion debe ser numerico.");
                return false;
            }
        } else {
            comparacion = comparar(dato, valor);
        }

        switch (operador) {
            case "=":
            case "==":
                return comparacion == 0;
            case "!=":
                return comparacion != 0;
            case ">":
                return comparacion > 0;
            case "<":
                return comparacion < 0;
            case ">=":
                return comparacion >= 0;
            case "<=":
                return comparacion <= 0;
            case "contiene":
                return String.valueOf(dato)
                    .toLowerCase()
                    .contains(valor.toLowerCase());
            default:
                return false;
        }
    }

    public void consultar(
            String[] campos,
            String campoCondicion,
            String operador,
            String valor,
            String campoOrden,
            boolean ascendente,
            int limite) {

        for (String campo : campos) {
            if (!campoValido(campo)) {
                System.out.println(
                    "Campo no valido: " + campo);
                return;
            }
        }

        if (campoCondicion != null
                && !campoCondicion.isBlank()
                && !campoValido(campoCondicion)) {
            System.out.println("Campo de condicion no valido.");
            return;
        }

        if (campoOrden != null
                && !campoOrden.isBlank()
                && !campoValido(campoOrden)) {
            System.out.println("Campo de orden no valido.");
            return;
        }

        Estudiante[] resultado = new Estudiante[cantidad];
        int total = 0;

        for (Estudiante e : registros) {
            if (cumpleCondicion(
                    e, campoCondicion, operador, valor)) {
                resultado[total++] = e;
            }
        }

        if (campoOrden != null && !campoOrden.isBlank()) {
            final String campo = campoOrden;

            Comparator<Estudiante> comparador =
                (a, b) -> comparar(
                    a.obtenerCampo(campo),
                    b.obtenerCampo(campo)
                );

            if (!ascendente) {
                comparador = comparador.reversed();
            }

            Arrays.sort(resultado, 0, total, comparador);
        }

        int mostrar = limite <= 0
            ? total
            : Math.min(limite, total);

        // Imprimir solamente los campos elegidos.
        for (String campo : campos) {
            System.out.printf("%-22s", campo.toUpperCase());
        }
        System.out.println();

        for (int i = 0; i < mostrar; i++) {
            for (String campo : campos) {
                System.out.printf("%-22s",
                    resultado[i].obtenerCampo(campo));
            }
            System.out.println();
        }

        System.out.println(
            "Registros mostrados: " + mostrar);
    }
}
