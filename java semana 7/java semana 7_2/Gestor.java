import java.io.*; 
import java.util.*; 

public class Gestor { 

    private ArrayList<Personaje> personajes = new ArrayList<>(); 

    private final String archivo = "personajes.txt"; 

    public Gestor() { 

        cargar(); 

        if (personajes.isEmpty()) { 

            cargarAleatorios(); 

        } 

    } 

    public void agregar(Personaje p) { 

        if (buscar(p.getNombre()) != null) { 

            System.out.println("El personaje ya existe."); 

            return; 

        } 

        if (!valido(p)) { 

            System.out.println("Datos no validos."); 

            return; 

        } 

        personajes.add(p); 

        guardar(); 

        System.out.println("Personaje agregado."); 

    } 

    private boolean valido(Personaje p) { 

        return p.getNombre() != null 

                && !p.getNombre().trim().isEmpty() 

                && !p.getNombre().contains(",") 

                && !p.getNombre().contains("\n") 

                && !p.getNombre().contains("\r") 

                && p.getVida() > 0 

                && p.getAtaque() > 0 

                && p.getDefensa() > 0 

                && p.getAlcance() > 0 

                && p.getNivel() > 0;

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

        if (vida <= 0 || ataque <= 0 || 

                defensa <= 0 || alcance <= 0) { 

            System.out.println("Valores incorrectos."); 

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

        if (p == null) { 

            System.out.println("Personaje no encontrado."); 

            return; 

        } 

        personajes.remove(p); 

        guardar();

        System.out.println("Personaje eliminado."); 

    } 

    public void ordenar(int opcion) { 

        ArrayList<Personaje> lista = new ArrayList<>(personajes); 

        switch (opcion) { 

            case 1: 

                lista.sort((a, b) -> 

                    Integer.compare(b.getVida(), a.getVida())); 

                break; 

            case 2: 

                lista.sort((a, b) -> 

                    Integer.compare(b.getAtaque(), a.getAtaque())); 

                break; 

            case 3: 

                lista.sort((a, b) -> 

                    Integer.compare(b.getDefensa(), a.getDefensa())); 

                break; 

            case 4: 

                lista.sort((a, b) -> 

                    Integer.compare(b.getAlcance(), a.getAlcance())); 

                break; 

            default: 

                System.out.println("Atributo incorrecto."); 

                return; 

        } 

        for (Personaje p : lista) { 

            System.out.println(p); 

        } 

    } 

    public void cargarAleatorios() { 

        String[] nombres = {"Caballero", "Guerrero", "Arquero"}; 

        Random random = new Random(); 

        int agregados = 0; 

        for (String nombre : nombres) { 

            if (buscar(nombre) == null) { 

                int vida = random.nextInt(10) + 1; 

                int ataque = random.nextInt(10) + 1; 

                int defensa = random.nextInt(10) + 1; 

                int alcance = random.nextInt(10) + 1; 

                personajes.add(new Personaje( 

                        nombre, vida, ataque, defensa, alcance)); 

                agregados++; 

            } 

        } 

        if (agregados > 0) guardar(); 

        System.out.println("Personajes nuevos: " + agregados); 

    } 

    public void actualizarAtributo(String nombre, int opcion, int valor) { 

        Personaje p = buscar(nombre); 

        if (p == null) { 

            System.out.println("Personaje no encontrado."); 

            return; 

        } 

        if (valor <= 0) { 

            System.out.println("El valor debe ser positivo."); 

            return; 

        } 

        switch (opcion) { 

            case 1: p.setVida(valor); break; 

            case 2: p.setAtaque(valor); break; 

            case 3: p.setDefensa(valor); break; 

            case 4: p.setAlcance(valor); break; 

            default: 

                System.out.println("Atributo incorrecto."); 

                return; 

        } 

        guardar(); 

        System.out.println("Atributo actualizado."); 

    } 

    public void estadisticas() { 

        int total = personajes.size(); 

        if (total == 0) { 

            System.out.println("No hay personajes."); 

            return; 

        } 

        double vida = 0, ataque = 0, defensa = 0, alcance = 0; 

        for (Personaje p : personajes) { 

            vida += p.getVida(); 

            ataque += p.getAtaque(); 

            defensa += p.getDefensa(); 

            alcance += p.getAlcance(); 

        } 

        System.out.println("Total de personajes: " + total); 

        System.out.printf("Vida promedio: %.2f%n", vida / total); 

        System.out.printf("Ataque promedio: %.2f%n", ataque / total); 

        System.out.printf("Defensa promedio: %.2f%n", defensa / total); 

        System.out.printf("Alcance promedio: %.2f%n", alcance / total); 

    } 

    public void importar(String ruta) { 

        int agregados = 0; 

        try (BufferedReader br = new BufferedReader( 

                new FileReader(ruta))) { 

            String linea; 

            while ((linea = br.readLine()) != null) { 

                try { 

                    String[] d = linea.split(","); 

                    if (d.length != 5 && d.length != 6) continue; 

                    String nombre = d[0].trim(); 

                    int vida = Integer.parseInt(d[1].trim()); 

                    int ataque = Integer.parseInt(d[2].trim()); 

                    int defensa = Integer.parseInt(d[3].trim()); 

                    int alcance = Integer.parseInt(d[4].trim()); 

                    int nivel = d.length == 6 

                            ? Integer.parseInt(d[5].trim()) : 1; 

                    Personaje p = new Personaje( 
                        nombre, vida, ataque, defensa, alcance, nivel); 

                    if (valido(p) && buscar(nombre) == null) { 

                        personajes.add(p); 

                        agregados++; 

                    } 

                } catch (NumberFormatException e) { 

                    System.out.println("Registro incorrecto."); 

                } 

            } 

            if (agregados > 0) guardar(); 

            System.out.println("Personajes importados: " + agregados); 

        } catch (IOException e) { 

            System.out.println("Error al importar: " + e.getMessage()); 

        } 

    } 

    public void subirNivel(String nombre) { 

        Personaje p = buscar(nombre); 

        if (p == null) { 

            System.out.println("Personaje no encontrado."); 

            return; 

        } 

        p.subirNivel(); 

        guardar(); 

        System.out.println("Nivel aumentado a: " + p.getNivel()); 

    } 

    public void guardar() { 

        try (PrintWriter pw = new PrintWriter( 

                new FileWriter(archivo))) { 

            for (Personaje p : personajes) { 

                pw.println(p.getNombre() + "," 

                        + p.getVida() + "," 

                        + p.getAtaque() + "," 

                        + p.getDefensa() + "," 

                        + p.getAlcance() + "," 

                        + p.getNivel()); 

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

                    if (d.length != 5 && d.length != 6) continue; 

                    int nivel = d.length == 6 

                            ? Integer.parseInt(d[5].trim()) : 1; 

                    Personaje p = new Personaje( 

                        d[0].trim(), 

                        Integer.parseInt(d[1].trim()), 

                        Integer.parseInt(d[2].trim()), 

                        Integer.parseInt(d[3].trim()), 

                        Integer.parseInt(d[4].trim()), 

                        nivel); 
                    if (valido(p) && buscar(p.getNombre()) == null) { 

                        personajes.add(p); 

                    } 

                } catch (NumberFormatException e) { 

                    System.out.println("Registro incorrecto."); 

                } 

            } 

        } catch (IOException e) { 

            System.out.println("Error al leer: " + e.getMessage()); 

        } 
    } 
} 