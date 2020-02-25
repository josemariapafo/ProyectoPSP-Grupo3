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
    private String nombre;
    private String descripcion;
    private Image foto;
    private String rutaImg;
    private Double precio;
    private int stock;
    private int cantidad;
    public StringProperty nombreProperty;
    public IntegerProperty cantidadProperty;
    public IntegerProperty stockProperty;

    public int getStockProperty() {
        return stockProperty.get();
    }

    public IntegerProperty stockPropertyProperty() {
        return stockProperty;
    }

    public void setStockProperty(int stockProperty) {
        if (this.stockProperty == null)
            this.stockProperty = new SimpleIntegerProperty(stockProperty);
        else
            this.stockProperty.set(stockProperty);
    }


    public ProductoVO() {
    }

    public ProductoVO(String nombre, String descripcion, Image foto, Double precio, int stock, int cantidad) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.foto = foto;
        this.stock = stock;
        this.cantidad = cantidad;
        setStockProperty(stock);
        setNombreProperty(nombre);
        setCantidadProperty(cantidad);
    }

    public ProductoVO(String nombre, String descripcion, Image foto, Double precio, int stock) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.foto = foto;
        this.stock = stock;
        setNombreProperty(nombre);
        setStockProperty(stock);
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
        setStockProperty(stock);
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        setCantidadProperty(cantidad);
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

    public Image getFoto() {
        return foto;
    }

    public void setFoto(Image foto) {
        this.foto = foto;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getNombreProperty() {
        return nombreProperty.get();
    }

    public StringProperty nombrePropertyProperty() {
        return nombreProperty;
    }

    public void setNombreProperty(String nombreProperty) {
        if (this.nombreProperty == null)
            this.nombreProperty = new SimpleStringProperty(nombreProperty);
        else
            this.nombreProperty.set(nombreProperty);
    }

    public int getCantidadProperty() {
        return cantidadProperty.get();
    }

    public IntegerProperty cantidadPropertyProperty() {
        return cantidadProperty;
    }

    public void setCantidadProperty(int cantidadProperty) {
        if (this.cantidadProperty == null)
            this.cantidadProperty = new SimpleIntegerProperty(cantidadProperty);
        else
            this.cantidadProperty.set(cantidadProperty);
    }

    @Override
    public String toString() {
        return "ProductoVO{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", foto=" + foto +
                ", rutaImg='" + rutaImg + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                ", cantidad=" + cantidad +
                '}';
    }

    public String toString2() {
        return "ProductoVO{" +
                "codigo=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", foto=" + foto +
                ", rutaImg='" + rutaImg + '\'' +
                ", precio=" + precio +
                ", stock=" + stock +
                ", cantidad=" + cantidad +
                ", nombreProperty=" + nombreProperty.get() +
                ", cantidadProperty=" + cantidadProperty.get() +
                '}';
    }
}
