import java.io.*; 

import java.util.*; 

public class Gestor { 

    private ArrayList<Personaje> personajes = new ArrayList<>(); 

    private final String archivo = "personajes.txt"; 

    public Gestor() { 

        cargar(); 

    } 

    public void agregar(Personaje p) { 

        if (buscar(p.getNombre()) != null) { 

            System.out.println("El personaje ya existe."); 

            return; 

        } 

        if (p.getNombre().trim().isEmpty() 

                || p.getNombre().contains(",") 

                || p.getNombre().contains("\n") 

                || p.getNombre().contains("\r") 

                || p.getVida() <= 0 || p.getAtaque() <= 0 

                || p.getDefensa() <= 0 || p.getAlcance() <= 0) { 

            System.out.println("Datos no validos."); 

            return; 

        } 

        personajes.add(p); // Agrega a la lista 

        guardar(); // Guarda los cambios 

        System.out.println("Personaje agregado."); 

    } 

    public void mostrar() { 

        if (personajes.isEmpty()) { 

            System.out.println("No hay personajes."); 

            return; 

        } 

        for (Personaje p : personajes) { 

            System.out.println(p); 

        } 

    } 

    public Personaje buscar(String nombre) { 

        for (Personaje p : personajes) { 

            if (p.getNombre().equalsIgnoreCase(nombre)) { 

                return p; 

            } 

        } 

        return null; 

    } 

    public void modificar(String nombre, int vida, int ataque, int defensa, int alcance) { 

        Personaje p = buscar(nombre); 

        if (p == null) { 

            System.out.println("Personaje no encontrado."); 

            return; 

        } 

        if (vida <= 0 || ataque <= 0 

                || defensa <= 0 || alcance <= 0) { 

            System.out.println("Los atributos deben ser mayores que cero."); 

            return; 

        } 

        p.setVida(vida); 

        p.setAtaque(ataque); 

        p.setDefensa(defensa); 

        p.setAlcance(alcance); 

        guardar(); 

        System.out.println("Personaje modificado."); 

    } 

    public void eliminar(String nombre) { 

        Personaje p = buscar(nombre); 

        if (p != null) { 

            personajes.remove(p); 

            guardar(); // Actualiza el archivo 

            System.out.println("Personaje eliminado."); 

        } else { 

            System.out.println("Personaje no encontrado."); 

        } 

    } 

    public void guardar() { 

        try (PrintWriter pw = new PrintWriter( 

                new FileWriter(archivo))) { 

            for (Personaje p : personajes) { 

                pw.println(p.getNombre() + "," 

                        + p.getVida() + "," 

                        + p.getAtaque() + "," 

                        + p.getDefensa() + "," 

                        + p.getAlcance()); 

            } 

        } catch (IOException e) { 

            System.out.println("Error al guardar: " + e.getMessage()); 

        } 

    } 

    public void cargar() { 

        File f = new File(archivo); 

        if (!f.exists()) return; 

        try (BufferedReader br = new BufferedReader( 

                new FileReader(f))) { 

            String linea; 

            while ((linea = br.readLine()) != null) { 

                try { 

                    String[] d = linea.split(","); 

                    if (d.length != 5) continue; 

                    int vida = Integer.parseInt(d[1].trim()); 

                    int ataque = Integer.parseInt(d[2].trim()); 

                    int defensa = Integer.parseInt(d[3].trim()); 

                    int alcance = Integer.parseInt(d[4].trim()); 

                    if (vida <= 0 || ataque <= 0 

                            || defensa <= 0 || alcance <= 0) continue; 

                    String nombre = d[0].trim(); 

                    if (nombre.isEmpty() 

                            || buscar(nombre) != null) continue; 

                    personajes.add(new Personaje( 

                            nombre, vida, ataque, defensa, alcance)); 

                } catch (NumberFormatException e) { 

                    System.out.println("Registro invalido."); 

                } 

            } 

        } catch (IOException e) { 

            System.out.println("Error al leer: " + e.getMessage()); 

        } 

    } 

} 