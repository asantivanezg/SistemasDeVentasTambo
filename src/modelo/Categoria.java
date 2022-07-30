package modelo;

/**
 *
 * @author Eguiguren
 */
public class Categoria {

    //Atributos
    private int idCategoria;
    private String descripcion;
    private int estado;

    //Constructor
    public Categoria() {
        this.idCategoria = 0;
        this.descripcion = "";
        this.estado = 0;
    }

    public Categoria(int idCategoria, String descripcion) {
        this.idCategoria = idCategoria;
        this.descripcion = descripcion;
    }

    public Categoria(int idCategoria, String descripcion, int estado) {
        this.idCategoria = idCategoria;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    //Getters and Setters
    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return descripcion;
    }

}
