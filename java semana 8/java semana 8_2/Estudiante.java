
public class Estudiante {

    private int id;
    private String nombre;
    private String carrera;
    private int edad;

    public Estudiante(int id, String nombre,
                      String carrera, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.carrera = carrera;
        this.edad = edad;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCarrera() {
        return carrera;
    }

    public int getEdad() {
        return edad;
    }

    public Object obtenerCampo(String campo) {
        switch (campo.toLowerCase()) {
            case "id":
                return id;
            case "nombre":
                return nombre;
            case "carrera":
                return carrera;
            case "edad":
                return edad;
            default:
                throw new IllegalArgumentException(
                    "Campo no valido: " + campo);
        }
    }
}
