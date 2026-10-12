import java.io.IOException; 
import java.util.ArrayList; 

public class Main { 

    public static void main(String[] args) { 

        EmpleadoControlador controlador = new EmpleadoControlador(); 

        EmpleadoVista vista = new EmpleadoVista(); 

        try { 

            ArrayList<Empleado> lista = controlador.leerEmpleados(); 

            System.out.println("--- EMPLEADOS REGISTRADOS ---"); 

            if (lista.isEmpty()) { 

                vista.mostrarMensaje("No hay empleados."); 

            } 

            for (Empleado e : lista) { 

                vista.mostrarEmpleado(e); 

            } 

        } catch (IOException e) { 

            vista.mostrarMensaje("Error al leer: " + e.getMessage()); 

        } 

        int opcion; 

        do { 

            vista.mostrarMenu(); 

            opcion = vista.leerNumero("Opcion: "); 

            try { 

                switch (opcion) { 

                    case 1: 

                        ArrayList<Empleado> empleados = 

                                controlador.leerEmpleados(); 

                        if (empleados.isEmpty()) { 

                            vista.mostrarMensaje("No hay empleados."); 

                        } 

                        for (Empleado e : empleados) { 

                            vista.mostrarEmpleado(e); 

                        } 

                        break; 

                    case 2: 

                        Empleado nuevo = vista.ingresarEmpleado(); 

                        if (controlador.agregarEmpleado(nuevo)) { 

                            vista.mostrarMensaje("Empleado agregado."); 

                        } else { 

                            vista.mostrarMensaje("El numero ya existe."); 

                        } 

                        break; 

                    case 3: 

                        int buscar = vista.leerNumero("Numero: "); 

                        Empleado encontrado = 

                                controlador.buscarEmpleado(buscar); 

                        if (encontrado != null) { 

                            vista.mostrarEmpleado(encontrado); 

                        } else { 

                            vista.mostrarMensaje("Empleado no encontrado."); 

                        } 

                        break; 

                    case 4: 

                        int eliminar = vista.leerNumero("Numero: "); 

                        if (controlador.eliminarEmpleado(eliminar)) { 

                            vista.mostrarMensaje("Empleado eliminado."); 

                        } else { 

                            vista.mostrarMensaje("Empleado no encontrado."); 

                        } 

                        break; 

                    case 5: 

                        vista.mostrarMensaje("Programa finalizado."); 

                        break; 

                    default: 

                        vista.mostrarMensaje("Opcion incorrecta."); 

                } 

            } catch (IOException | IllegalArgumentException e) { 

                vista.mostrarMensaje("Error: " + e.getMessage()); 

            } 

        } while (opcion != 5); 
    } 
} 