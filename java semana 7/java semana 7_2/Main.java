import java.util.Scanner; 

public class Main { 

    static Scanner sc = new Scanner(System.in); 

    public static int leerNumero(String mensaje) { 

        while (true) { 

            try { 

                System.out.print(mensaje); 

                return Integer.parseInt(sc.nextLine()); 

            } catch (NumberFormatException e) { 

                System.out.println("Ingrese un numero valido."); 

            } 

        } 

    } 

    public static int leerPositivo(String mensaje) { 

        int valor; 

        do { 

            valor = leerNumero(mensaje); 

            if (valor <= 0) { 

                System.out.println("Debe ser mayor que cero."); 

            } 

        } while (valor <= 0); 

        return valor; 

    } 

    public static void main(String[] args) { 

        Gestor gestor = new Gestor(); 

        int opcion; 

        do { 

            System.out.println("\n--- GESTOR DE PERSONAJES ---"); 

            System.out.println("1. Mostrar personajes"); 

            System.out.println("2. Agregar personaje"); 

            System.out.println("3. Modificar personaje"); 

            System.out.println("4. Eliminar personaje"); 

            System.out.println("5. Ordenar por atributo"); 

            System.out.println("6. Cargar personajes aleatorios"); 

            System.out.println("7. Actualizar un atributo"); 

            System.out.println("8. Mostrar estadisticas"); 

            System.out.println("9. Importar personajes"); 

            System.out.println("10. Subir nivel"); 

            System.out.println("0. Salir"); 

            opcion = leerNumero("Opcion: "); 

            switch (opcion) { 

                case 1: 

                    gestor.mostrar(); 

                    break; 

                case 2: 

                    System.out.print("Nombre: "); 

                    String nombre = sc.nextLine(); 

                    int vida = leerPositivo("Vida: "); 

                    int ataque = leerPositivo("Ataque: "); 

                    int defensa = leerPositivo("Defensa: "); 

                    int alcance = leerPositivo("Alcance: "); 

                    gestor.agregar(new Personaje( 

                        nombre, vida, ataque, defensa, alcance)); 

                    break; 

                case 3: 

                    System.out.print("Nombre a modificar: "); 

                    String modificar = sc.nextLine(); 

                    if (gestor.buscar(modificar) == null) { 

                        System.out.println("Personaje no encontrado."); 

                        break; 

                    } 

                    int v = leerPositivo("Nueva vida: "); 

                    int a = leerPositivo("Nuevo ataque: "); 

                    int d = leerPositivo("Nueva defensa: "); 

                    int al = leerPositivo("Nuevo alcance: "); 

                    gestor.modificar(modificar, v, a, d, al); 

                    break; 

                case 4: 

                    System.out.print("Nombre a eliminar: "); 

                    gestor.eliminar(sc.nextLine()); 

                    break; 

                case 5: 

                    System.out.println("1.Vida 2.Ataque 3.Defensa 4.Alcance"); 

                    int orden = leerNumero("Atributo: "); 

                    gestor.ordenar(orden);

                    break; 

                case 6: 

                    gestor.cargarAleatorios(); 

                    break; 

                case 7: 

                    System.out.print("Nombre: "); 

                    String personaje = sc.nextLine(); 

                    System.out.println("1.Vida 2.Ataque 3.Defensa 4.Alcance"); 

                    int atributo = leerNumero("Atributo: "); 

                    int valor = leerPositivo("Nuevo valor: "); 

                    gestor.actualizarAtributo( 

                        personaje, atributo, valor); 

                    break; 

                case 8: 

                    gestor.estadisticas(); 

                    break; 

                case 9: 

                    System.out.print("Ruta del archivo: "); 

                    String ruta = sc.nextLine(); 

                    gestor.importar(ruta); 

                    break; 

                case 10: 

                    System.out.print("Nombre: "); 

                    gestor.subirNivel(sc.nextLine()); 

                    break; 

                case 0: 

                    System.out.println("Programa finalizado."); 

                    break; 

                default: 

                    System.out.println("Opcion incorrecta."); 

            } 

        } while (opcion != 0); 

    } 

} 