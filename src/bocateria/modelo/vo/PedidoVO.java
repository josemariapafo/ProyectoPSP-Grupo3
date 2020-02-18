package bocateria.modelo.vo;

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


    public PedidoVO(int pedidoId, UsuarioVO user, List<ProductoVO> listaProductos) {
        this.total = 0;
        this.pedidoId = pedidoId;
        this.date = new Date();
        this.usuario = user;
        this.listaProductos = listaProductos;
        for(ProductoVO p : this.listaProductos){
            this.total += p.getPrecio() * p.getCantidad();
        }
    }

    public PedidoVO() {
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
}
