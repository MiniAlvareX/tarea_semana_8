import java.io.*; 
import java.util.*; 
import javax.swing.JFileChooser; 

public class ContadorPalabras { 

    public static void main(String[] args) { 

        Scanner sc = new Scanner(System.in); 

        JFileChooser selector = new JFileChooser(); 

        String respuesta; 

        do { 

            int opcion = selector.showOpenDialog(null); 

            if (opcion != JFileChooser.APPROVE_OPTION) { 

                System.out.println("No se selecciono archivo."); 

                break; 

            } 

            File archivo = selector.getSelectedFile(); 

            int lineas = 0; 

            int palabras = 0; 

            int caracteres = 0; 

            HashMap<String, Integer> frecuencia = new HashMap<>(); 

            try (BufferedReader br = new BufferedReader( 

                    new FileReader(archivo))) { 

                String linea; 

                while ((linea = br.readLine()) != null) { 

                    lineas++;  

                    caracteres += linea.length(); 

                    StringBuilder palabra = new StringBuilder(); 

                    for (int i = 0; i <= linea.length(); i++) { 

                        char c = i < linea.length() 

                                ? linea.charAt(i) : ' '; 

                        if (Character.isLetterOrDigit(c)) { 

                            palabra.append(c); 

                        } else if (palabra.length() > 0) { 

                            String texto = palabra.toString() 

                                    .toLowerCase(Locale.ROOT); 

                            palabras++; 

                            frecuencia.put(texto, 

                                    frecuencia.getOrDefault(texto, 0) + 1); 

                            palabra.setLength(0); 

                        } 

                    } 

                } 

                double promedio = lineas == 0 ? 0 : 

                        (double) palabras / lineas; 

                System.out.println("\n--- RESULTADOS ---"); 

                System.out.println("Archivo: " + archivo.getName()); 

                System.out.println("Total de lineas: " + lineas); 

                System.out.println("Total de palabras: " + palabras); 

                System.out.println("Total de caracteres: " + caracteres); 

                System.out.printf("Promedio de palabras: %.2f%n", promedio); 

                ArrayList<Map.Entry<String, Integer>> lista = 

                        new ArrayList<>(frecuencia.entrySet()); 

                lista.sort((a, b) -> { 

                    int resultado = b.getValue().compareTo(a.getValue()); 

                    return resultado != 0 ? resultado 

                            : a.getKey().compareTo(b.getKey()); 

                }); 

                System.out.println("\n--- PALABRAS MAS FRECUENTES ---"); 

                int limite = Math.min(10, lista.size()); 

                for (int i = 0; i < limite; i++) { 

                    Map.Entry<String, Integer> dato = lista.get(i); 

                    System.out.println(dato.getKey() + ": " 

                            + dato.getValue()); 

                } 

            } catch (FileNotFoundException e) { 

                System.out.println("El archivo no existe o no se puede abrir."); 

            } catch (IOException e) { 

                System.out.println("Error al leer: " + e.getMessage()); 

            } 

            System.out.print("\n¿Leer otro archivo? (s/n): "); 

            respuesta = sc.nextLine(); 

        } while (respuesta.equalsIgnoreCase("s")); 

        System.out.println("Programa finalizado."); 

    } 

} 