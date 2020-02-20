package bocateria.modelo.vo;

import javafx.beans.property.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

public class PedidoVO {

    private int pedidoId;
    private double total;
    private UsuarioVO usuario;
//    private long date;
    private Date date;
    private List<ProductoVO> listaProductos;
    //ATRIBUTOS PROPERTY
    private IntegerProperty pedidoIdPropery;
    private StringProperty usuarioProperty;



    public PedidoVO(int pedidoId, UsuarioVO user, List<ProductoVO> listaProductos) {
        this.total = 0;
        this.pedidoId = pedidoId;
        this.date = new Date();
        this.usuario = user;
        this.listaProductos = listaProductos;
        for(ProductoVO p : this.listaProductos){
            this.total += p.getPrecio() * p.getCantidad();
        }
        setPedidoIdPropery(pedidoId);
        setUsuarioProperty(user.getUsuario());
    }

    public int getPedidoIdPropery() {
        return pedidoIdPropery.get();
    }

    public IntegerProperty pedidoIdProperyProperty() {
        return pedidoIdPropery;
    }

    public String getUsuarioProperty() {
        return usuarioProperty.get();
    }

    public StringProperty usuarioPropertyProperty() {
        return usuarioProperty;
    }

    public PedidoVO() {
    }

    @Override
    public String toString() {
        return "PedidoVO{" +
                "pedidoId=" + pedidoId +
                ", total=" + total +
                ", usuario=" + usuario +
                ", date=" + date +
                ", listaProductos=" + listaProductos +
                '}';
    }



    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public UsuarioVO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioVO usuario) {
        this.usuario = usuario;
    }

    public List<ProductoVO> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<ProductoVO> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public void setPedidoIdPropery(int id) {
        if(this.pedidoIdPropery == null)
            this.pedidoIdPropery = new SimpleIntegerProperty(id);
        else
            this.pedidoIdPropery.set(id);
    }

    public void setUsuarioProperty(String nombre) {
        if(this.usuarioProperty == null)
            this.usuarioProperty = new SimpleStringProperty(nombre);
        else
            this.usuarioProperty.set(nombre);
    }

}
