package bocateria.modelo.vo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;

public class ProductoVO {
    private int codigo;
    private String nombre;
    private String descripcion;
    private Blob foto;
    private String rutaImg;
    private Double precio;
    private int stock;
    private int cantidad;

    public ProductoVO() {
    }

    public ProductoVO(String nombre, String descripcion, Blob foto, Double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.foto = foto;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    // Sirve para poder insertar la imagen en la base de datos
    public InputStream getInputStream() throws FileNotFoundException {
        return new FileInputStream(getRutaImg());
    }

    private String getRutaImg() {
        return rutaImg;
    }

    public void setRutaImg(String rutaImg) {
        this.rutaImg = rutaImg;
    }

    public Blob getFoto() { return foto; }

    public void setFoto(Blob foto) { this.foto = foto; }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
