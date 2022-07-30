package modelo;

/**
 *
 * @author Eguiguren
 */
public class Producto {

    //Atributos
    private int idProducto;
    private String nombre;
    private int cantidad;
    private double precio;
    private String descripcion;
    private int porcentajeIgv;
    private Categoria categoria;
    private int estado;

    //Constructor
//    public Producto() {
//        this.idProducto = 0;
//        this.nombre = "";
//        this.cantidad = 0;
//        this.precio = 0.0;
//        this.descripcion = "";
//        this.porcentajeIgv = 0;
//        this.categoria = 0;
//        this.estado = 0;
//    }
    public Producto() {
    }

    public Producto(int idProducto, String nombre) {
        this.idProducto = idProducto;
        this.nombre = nombre;
    }

    //Constructor sobrecargado
    public Producto(int idProducto, String nombre, int cantidad, double precio,
            String descripcion, int porcentajeIgv,
            Categoria categoria, int estado) {

        this.idProducto = idProducto;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.descripcion = descripcion;
        this.porcentajeIgv = porcentajeIgv;
        this.categoria = categoria;
        this.estado = estado;
    }

    //Getters and Setters
    public int getIdProducto() {
        return idProducto;
    }//Get para obtener

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }//Set para insertar

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPorcentajeIgv() {
        return porcentajeIgv;
    }

    public void setPorcentajeIgv(int porcentajeIgv) {
        this.porcentajeIgv = porcentajeIgv;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Object[] registroProducto() {
        Object[] fila = {idProducto, nombre, cantidad, precio, descripcion, calcularIgv(precio, porcentajeIgv), categoria.getDescripcion(), estado};
        return fila;
    }

    @Override
    public String toString() {
        return nombre;
    }

    private double calcularIgv(double precio, int igv) {
        double IGV = 0;
        int p_igv = igv;

        switch (p_igv) {
            case 0 ->
                IGV = 0.0;
            case 18 ->
                IGV = precio * 0.18;
            default -> {
            }
        }
        //redondear decimales
        IGV = (double) Math.round(IGV * 100) / 100;
        return IGV;
    }
}
