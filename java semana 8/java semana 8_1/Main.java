import java.sql.*;
import java.util.Scanner;
public class Main {

    static Scanner sc = new Scanner(System.in);
    static Connection conexion;

    static final String URL = "jdbc:sqlite:universidad.db";
    static final String CLAVE = "1234";

    public static void main(String[] args) {

        try {
            conexion = DriverManager.getConnection(URL);
            crearTabla();

            int opcion;

            do {
                System.out.println("\n===== GESTOR DE ESTUDIANTES =====");
                System.out.println("1. Ingresar estudiante");
                System.out.println("2. Borrar estudiante");
                System.out.println("3. Actualizar estudiante");
                System.out.println("4. Mostrar estudiantes");
                System.out.println("5. Salir");
                System.out.print("Seleccione una opcion: ");

                opcion = leerEntero();

                switch (opcion) {
                    case 1:
                        ingresar();
                        break;
                    case 2:
                        borrar();
                        break;
                    case 3:
                        actualizar();
                        break;
                    case 4:
                        mostrar();
                        break;
                    case 5:
                        System.out.println("Programa finalizado.");
                        break;
                    default:
                        System.out.println("Opcion no valida.");
                }

            } while (opcion != 5);

            conexion.close();
            sc.close();

        } catch (SQLException e) {
            System.out.println("Error de base de datos: "
                    + e.getMessage());
        }
    }
    static void crearTabla() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS estudiantes (" +
        "id INTEGER PRIMARY KEY, " +
        "nombre TEXT NOT NULL, " +
        "carrera TEXT NOT NULL, " +
        "edad INTEGER NOT NULL)";       

        try (Statement st = conexion.createStatement()) {
            st.execute(sql);
        }
    }

    static int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Ingrese un numero entero: ");
            }
        }
    }

    static boolean confirmar() throws SQLException {

        System.out.print("Ingrese la clave para confirmar: ");
        String claveIngresada = sc.nextLine();

        if (CLAVE.equals(claveIngresada)) {
            conexion.commit();
            System.out.println("Operacion confirmada correctamente.");
            return true;
        } else {
            conexion.rollback();
            System.out.println("Clave incorrecta. Cambios revertidos.");
            return false;
        }
    }

    static void ingresar() throws SQLException {

        conexion.setAutoCommit(false);

        try {
            System.out.print("ID: ");
            int id = leerEntero();

            System.out.print("Nombre: ");
            String nombre = sc.nextLine();

            System.out.print("Carrera: ");
            String carrera = sc.nextLine();

            System.out.print("Edad: ");
            int edad = leerEntero();

            if (id <= 0 || edad <= 0
                    || nombre.isBlank() || carrera.isBlank()) {
                System.out.println("Datos no validos.");
                conexion.rollback();
                return;
            }

            String sql = "INSERT INTO estudiantes " +
        "(id, nombre, carrera, edad) VALUES (?, ?, ?, ?)";

            try (PreparedStatement ps =
                         conexion.prepareStatement(sql)) {
                ps.setInt(1, id);
                ps.setString(2, nombre);
                ps.setString(3, carrera);
                ps.setInt(4, edad);

                ps.executeUpdate();
            }
            confirmar();

        } catch (SQLException e) {
            conexion.rollback();
            System.out.println("No se pudo insertar: "
                    + e.getMessage());
        } finally {
            conexion.setAutoCommit(true);
        }
    }

    static void borrar() throws SQLException {

        conexion.setAutoCommit(false);

        try {
            System.out.print("ID del estudiante a borrar: ");
            int id = leerEntero();

            String sql = "DELETE FROM estudiantes WHERE id = ?";

            try (PreparedStatement ps =
                         conexion.prepareStatement(sql)) {

                ps.setInt(1, id);

                int filas = ps.executeUpdate();

                if (filas == 0) {
                    System.out.println("No existe ese estudiante.");
                    conexion.rollback();
                    return;
                }
            }

            confirmar();

        } catch (SQLException e) {
            conexion.rollback();
            System.out.println("No se pudo borrar: "
                    + e.getMessage());
        } finally {
            conexion.setAutoCommit(true);
        }
    }

    static void actualizar() throws SQLException {

        conexion.setAutoCommit(false);

        try {
            System.out.print("ID del estudiante a actualizar: ");
            int id = leerEntero();

            System.out.print("Nuevo nombre: ");
            String nombre = sc.nextLine();

            System.out.print("Nueva carrera: ");
            String carrera = sc.nextLine();

            System.out.print("Nueva edad: ");
            int edad = leerEntero();

            if (edad <= 0 || nombre.isBlank()
                    || carrera.isBlank()) {
                System.out.println("Datos no validos.");
                conexion.rollback();
                return;
            }

            String sql = "UPDATE estudiantes " +
        "SET nombre = ?, carrera = ?, edad = ? WHERE id = ?";

            try (PreparedStatement ps =
                         conexion.prepareStatement(sql)) {

                ps.setString(1, nombre);
                ps.setString(2, carrera);
                ps.setInt(3, edad);
                ps.setInt(4, id);

                int filas = ps.executeUpdate();

                if (filas == 0) {
                    System.out.println("No existe ese estudiante.");
                    conexion.rollback();
                    return;
                }
            }

            confirmar();

        } catch (SQLException e) {
            conexion.rollback();
            System.out.println("No se pudo actualizar: "
                    + e.getMessage());
        } finally {
            conexion.setAutoCommit(true);
        }
    }

    static void mostrar() throws SQLException {

        String sql = "SELECT * FROM estudiantes ORDER BY id";

        try (Statement st = conexion.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            System.out.println("\n LISTA DE ESTUDIANTES ");
            System.out.printf("%-8s %-20s %-25s %-8s%n",
                    "ID", "NOMBRE", "CARRERA", "EDAD");

            System.out.println(
                    " ");

            boolean hayDatos = false;
            while (rs.next()) {
                hayDatos = true;

                System.out.printf("%-8d %-20s %-25s %-8d%n",
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("carrera"),
                        rs.getInt("edad"));
            }

            if (!hayDatos) {
                System.out.println("No hay estudiantes registrados.");
            }
        }
    }
}
