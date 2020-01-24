package bocateria.modelo.vo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;

public class ProductoVO {
    int codigo;
    String nombre;
    String descripcion;
    Blob foto;
    String rutaImg;
    Double precio;

    public ProductoVO() {
    }

    public ProductoVO(String nombre, String descripcion, Blob foto, Double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.foto = foto;
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

    public String getRutaImg() {
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
