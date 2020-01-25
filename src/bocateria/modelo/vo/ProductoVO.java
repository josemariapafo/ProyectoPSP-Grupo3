package bocateria.modelo.vo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Blob;

public class ProductoVO {
    private int codigo;
    private StringProperty nombre;
    private String descripcion;
    private Image foto;
    private String rutaImg;
    private Double precio;
    private int stock;
    private IntegerProperty cantidad;

    public ProductoVO() {
    }

    public ProductoVO(String nombre, String descripcion, Image foto, Double precio) {
        this.nombre =  new SimpleStringProperty(nombre);
        this.descripcion = descripcion;
        this.precio = precio;
        this.foto = foto;
        this.cantidad = new SimpleIntegerProperty(0);
    }

    public StringProperty getNombreProperty(){
        return nombre;
    }
    public IntegerProperty getCantidadProperty(){
        return cantidad;
    }
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getCantidad() {
        return cantidad.get();
    }

    public void setCantidad(int cantidad) {
        this.cantidad.set(cantidad);
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
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

    public Image getFoto() { return foto; }

    public void setFoto(Image foto) { this.foto = foto; }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
