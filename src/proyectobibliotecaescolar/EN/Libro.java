package proyectobibliotecaescolar.EN;

public class Libro {
    private int id;
    private String nombre;
    private String autor;
    private String publicacion;

    public Libro() {
    }

    public Libro(int id, String nombre, String autor, String publicacion) {
        this.id = id;
        this.nombre = nombre;
        this.autor = autor;
        this.publicacion = publicacion;
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

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getPublicacion() {
        return publicacion;
    }

    public void setPublicacion(String publicacion) {
        this.publicacion = publicacion;
    }
}
