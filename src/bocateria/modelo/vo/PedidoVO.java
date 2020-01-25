package bocateria.modelo.vo;

public class PedidoVO {
    String usuario;
    int pedidoId;
    Double precio;
    int cantidad;
    double total;

    public PedidoVO(String usuario, int pedidoId, Double precio, int cantidad) {
        this.usuario = usuario;
        this.pedidoId = pedidoId;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public PedidoVO() {
    }

    public String getUsuario() {
        return usuario;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public int getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(int pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}
