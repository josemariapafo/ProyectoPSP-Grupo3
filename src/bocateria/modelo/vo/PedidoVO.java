package bocateria.modelo.vo;

import java.math.BigInteger;
import java.util.Date;

public class PedidoVO {

    int pedidoId;
    double total;
    long date;


    public PedidoVO(int pedidoId, double total, long date) {
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

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }
}
