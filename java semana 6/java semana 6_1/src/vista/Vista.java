package vista;

import java.util.Scanner;

public class Vista {

    private Scanner scanner;

    public Vista() {
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {

        System.out.println("\n==============================");
        System.out.println("       CARRITO DE COMPRAS");
        System.out.println("==============================");
        System.out.println("1. Agregar producto");
        System.out.println("2. Listar productos");
        System.out.println("3. Agregar producto al carrito");
        System.out.println("4. Ver carrito");
        System.out.println("5. Eliminar producto del carrito");
        System.out.println("6. Aplicar descuento");
        System.out.println("7. Calcular envío");
        System.out.println("8. Ver historial de compras");
        System.out.println("9. Realizar compra");
        System.out.println("0. Salir");
        System.out.println("==============================");
    }

    public int leerEntero(String mensaje) {

        System.out.print(mensaje);

        return scanner.nextInt();
    }

    public double leerDouble(String mensaje) {

        System.out.print(mensaje);

        return scanner.nextDouble();
    }

    public String leerString(String mensaje) {

        scanner.nextLine();

        System.out.print(mensaje);

        return scanner.nextLine();
    }
}