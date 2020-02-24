package bocateria.modelo.productor_consumidor;

import bocateria.modelo.vo.PedidoVO;
import bocateria.modelo.vo.ProductoVO;
import java.util.ArrayList;
import java.util.List;

public class Consumidor extends Thread{
    private ListaComandas cola;
    private List<PedidoVO> listaPedidos = new ArrayList<>();

    public Consumidor(ListaComandas cola) {
        this.listaPedidos.addAll(cola.get());
    }

    public void run() {
        listaPedidos.removeAll(listaPedidos);
        listaPedidos.addAll(cola.get());    //recoge la lista de la cola
    }
}
