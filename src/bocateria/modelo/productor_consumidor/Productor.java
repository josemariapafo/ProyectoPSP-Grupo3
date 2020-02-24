package bocateria.modelo.productor_consumidor;

import bocateria.Main;
import bocateria.modelo.vo.PedidoVO;
import bocateria.modelo.vo.ProductoVO;

import java.util.ArrayList;
import java.util.List;

public class Productor extends Thread{
    private ListaComandas cola;
    private PedidoVO pedido = new PedidoVO();

    public Productor(PedidoVO pedido) {
        this.pedido=pedido;
    }

    public void run() {
        cola.put(pedido);
    }
}
