import java.io.*; 
import java.nio.file.*; 
import java.util.*; 

public class EmpleadoControlador { 

    private final String archivo = "empleados.txt"; 

    public ArrayList<Empleado> leerEmpleados() throws IOException { 

        ArrayList<Empleado> lista = new ArrayList<>(); 

        File f = new File(archivo); 

        if (!f.exists()) return lista; 

        try (BufferedReader br = new BufferedReader( 

                new FileReader(f))) { 

            String linea; 

            while ((linea = br.readLine()) != null) { 

                String[] datos = linea.split(";", -1); 

                if (datos.length != 3) { 

                    throw new IOException("Registro incorrecto."); 

                } 

                try { 

                    int numero = Integer.parseInt(datos[0]); 

                    String nombre = datos[1]; 

                    double sueldo = Double.parseDouble(datos[2]); 

                    lista.add(new Empleado( 

                            numero, nombre, sueldo)); 

                } catch (NumberFormatException e) { 

                    throw new IOException("Datos incorrectos.", e); 

                } 

            } 

        } 

        return lista; 

    } 

    public boolean agregarEmpleado(Empleado empleado) 

            throws IOException { 

        if (empleado.getNumero() <= 0 

                || empleado.getNombre().trim().isEmpty() 

                || empleado.getNombre().contains(";") 

                || empleado.getNombre().contains("\n") 

                || empleado.getNombre().contains("\r") 

                || !Double.isFinite(empleado.getSueldo()) 

                || empleado.getSueldo() < 0) { 

            throw new IllegalArgumentException("Datos no validos."); 

        } 

        if (buscarEmpleado(empleado.getNumero()) != null) { 

            return false; 

        } 

        try (BufferedWriter bw = new BufferedWriter( 

                new FileWriter(archivo, true))) { 

            bw.write(empleado.getNumero() + ";" 

                    + empleado.getNombre() + ";" 

                    + empleado.getSueldo()); 

            bw.newLine();

        } 

        return true; 

    } 

    public Empleado buscarEmpleado(int numero) throws IOException { 

        for (Empleado e : leerEmpleados()) { 

            if (e.getNumero() == numero) { 

                return e; 

            } 

        } 

        return null; 

    } 

    public boolean eliminarEmpleado(int numero) throws IOException { 

        ArrayList<Empleado> lista = leerEmpleados(); 

        boolean eliminado = lista.removeIf( 

                e -> e.getNumero() == numero); 

        if (!eliminado) return false; 

        Path destino = Paths.get(archivo); 

        Path temporal = Files.createTempFile( 

                destino.toAbsolutePath().getParent(), 

                "empleados_", ".tmp"); 

        try { 

            try (BufferedWriter bw = Files.newBufferedWriter(temporal)) { 

                for (Empleado e : lista) { 

                    bw.write(e.getNumero() + ";" 

                            + e.getNombre() + ";" 

                            + e.getSueldo()); 

                    bw.newLine(); 

                } 

            } 

            try { 

                Files.move(temporal, destino, 

                        StandardCopyOption.REPLACE_EXISTING, 

                        StandardCopyOption.ATOMIC_MOVE); 

            } catch (AtomicMoveNotSupportedException ex) { 

                Files.move(temporal, destino, 

                        StandardCopyOption.REPLACE_EXISTING); 

            } 

        } finally { 

            Files.deleteIfExists(temporal); 

        } 

        return true; 

    } 
} 