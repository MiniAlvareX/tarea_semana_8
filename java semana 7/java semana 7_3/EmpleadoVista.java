import java.util.*; 

public class EmpleadoVista { 

    private Scanner sc = new Scanner(System.in); 

    public void mostrarMenu() { 

        System.out.println("\n--- MENU DE EMPLEADOS ---"); 

        System.out.println("1. Listar empleados"); 

        System.out.println("2. Agregar empleado"); 

        System.out.println("3. Buscar empleado"); 

        System.out.println("4. Eliminar empleado"); 

        System.out.println("5. Salir"); 

    } 

    public int leerNumero(String mensaje) { 

        while (true) { 

            try { 

                System.out.print(mensaje); 

                return Integer.parseInt(sc.nextLine()); 

            } catch (NumberFormatException e) { 

                System.out.println("Ingrese un numero valido."); 

            } 

        } 

    } 

    public double leerSueldo() { 

        while (true) { 

            try { 

                System.out.print("Sueldo: "); 

                double sueldo = Double.parseDouble(sc.nextLine()); 

                if (sueldo >= 0 && Double.isFinite(sueldo)) { 

                    return sueldo; 

                } 

                System.out.println("Sueldo no valido."); 

            } catch (NumberFormatException e) { 

                System.out.println("Ingrese un sueldo valido."); 

            } 

        } 

    } 

    public Empleado ingresarEmpleado() { 

        int numero; 

        do { 

            numero = leerNumero("Numero: "); 

            if (numero <= 0) { 

                System.out.println("Debe ser mayor que cero."); 

            } 

        } while (numero <= 0); 

        String nombre; 

        do { 

            System.out.print("Nombre: "); 

            nombre = sc.nextLine().trim(); 

            if (nombre.isEmpty() || nombre.contains(";")) { 

                System.out.println("Nombre no valido."); 

            } 

        } while (nombre.isEmpty() || nombre.contains(";")); 

        double sueldo = leerSueldo(); 

        return new Empleado(numero, nombre, sueldo); 

    } 

    public void mostrarEmpleado(Empleado empleado) { 

        System.out.println(empleado); 

    } 

    public void mostrarMensaje(String mensaje) { 

        System.out.println(mensaje); 

    } 
} 