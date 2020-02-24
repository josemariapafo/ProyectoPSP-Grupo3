package bocateria.modelo.productor_consumidor;

import bocateria.modelo.vo.PedidoVO;
import bocateria.modelo.vo.ProductoVO;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ListaComandas {
    private List<PedidoVO> listaPedidos = new ArrayList<PedidoVO>();
    private boolean disponible = false;//inicialmente cola vacia
    private int numeroComandas = 5;

    public synchronized List<PedidoVO> get() {
        if (disponible) {      //hay numero en la cola
            disponible = false; //se pone cola vacia
            return listaPedidos;      //se devuelve
        }
        return null;//no hay numero disponible, cola vacia
    }

    public synchronized boolean put(PedidoVO pedido) {
        if (listaPedidos.size() < numeroComandas) {
            //coloca valor en la cola
            listaPedidos.add(pedido);
            disponible = true;
        } else {
            System.out.println("Cola llena");
            disponible = false; //disponible para consumir, cola llena
        }
        return disponible;
    }
}
