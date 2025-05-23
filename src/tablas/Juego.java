package tablas;

public class Juego {
    private int id;
    private String nombre;
    private String genero;
    private float precio;
    private String fechaLanzamiento;

    public Juego(int id, String nombre, String genero, float precio, String fechaLanzamiento) {
        this.id = id;
        this.nombre = nombre;
        this.genero = genero;
        this.precio = precio;
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(String fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }
}
