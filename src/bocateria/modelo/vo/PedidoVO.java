package bocateria.modelo.vo;

import java.util.Date;

public class PedidoVO {

    int pedidoId;
    double total;
    Date date;


    public PedidoVO(int pedidoId, double total, Date date) {
        this.pedidoId = pedidoId;
        this.total = total;
        this.date = date;
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
}
