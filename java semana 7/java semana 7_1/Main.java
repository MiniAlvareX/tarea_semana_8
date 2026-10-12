import java.util.Scanner; 

public class Main { 

    static Scanner sc = new Scanner(System.in); 

    public static int leerNumero(String mensaje) { 

        while (true) { 

            try { 

                System.out.print(mensaje); 

                int numero = Integer.parseInt(sc.nextLine()); 

                if (numero > 0) return numero; 

                System.out.println("Debe ser mayor que cero."); 

            } catch (NumberFormatException e) { 

                System.out.println("Ingrese un numero valido."); 

            } 

        } 

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

            System.out.println("5. Salir"); 

            opcion = leerNumero("Opcion: "); 

            switch (opcion) { 

                case 1: 

                    gestor.mostrar(); // Muestra la lista 

                    break; 

                case 2: 

                    System.out.print("Nombre: "); 

                    String nombre = sc.nextLine(); 

                    int vida = leerNumero("Vida: "); 

                    int ataque = leerNumero("Ataque: "); 

                    int defensa = leerNumero("Defensa: "); 

                    int alcance = leerNumero("Alcance: "); 

                    gestor.agregar(new Personaje( nombre, vida, ataque, defensa, alcance)); 

                    break; 

                case 3: 

                    System.out.print("Nombre a modificar: "); 

                    String modificar = sc.nextLine(); 

                    if (gestor.buscar(modificar) == null) { 

                        System.out.println("Personaje no encontrado."); 

                        break; 

                    } 

                    int v = leerNumero("Nueva vida: "); 

                    int a = leerNumero("Nuevo ataque: "); 

                    int d = leerNumero("Nueva defensa: "); 

                    int al = leerNumero("Nuevo alcance: "); 

                    gestor.modificar(modificar, v, a, d, al); 

                    break; 

                case 4: 

                    System.out.print("Nombre a eliminar: "); 

                    String eliminar = sc.nextLine(); 

                    gestor.eliminar(eliminar); 

                    break; 

                case 5: 

                    System.out.println("Programa finalizado."); 

                    break; 

                default: 

                    System.out.println("Opcion incorrecta."); 

            } 

        } while (opcion != 5); 

    } 

} 