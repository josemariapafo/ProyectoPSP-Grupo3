package bocateria.modelo.productor_consumidor;

import bocateria.Main;
import bocateria.exepcion.Alertas;
import bocateria.modelo.vo.PedidoVO;
import bocateria.modelo.vo.ProductoVO;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ListaComandas {
    private List<PedidoVO> listaPedidos = new ArrayList<>();
    private boolean disponible = false; //inicialmente cola vacia
    private int numeroComandas = 5;
    private Main main;

    public ListaComandas(Main main) {
        this.main = main;
    }

    public synchronized List<PedidoVO> get() {
//        try{
//            wait(1500);
//        }catch (InterruptedException e){
//            e.printStackTrace();
//        }
        if (listaPedidos.size()>0) {        //hay numero en la cola
            disponible = false; //se pone cola vacia
            List<PedidoVO> pedidos = new ArrayList<>(listaPedidos);
            listaPedidos.clear();
            notify();
            return pedidos;      //se devuelve
        }
        notify();
        return listaPedidos;
    }

    public synchronized boolean put(PedidoVO pedido) {
        try{
            wait(1500);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        if (listaPedidos.size() < numeroComandas) {
            listaPedidos.add(pedido);
            main.getModel().setHuecoEnCola(true);
            disponible = true;
        } else {
            main.getModel().setHuecoEnCola(false);
            disponible = false; //disponible para consumir, cola llena
        }
        notify();
        return disponible;
    }
}
