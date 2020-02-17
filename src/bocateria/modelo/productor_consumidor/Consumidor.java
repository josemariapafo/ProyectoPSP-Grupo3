package bocateria.modelo.productor_consumidor;

import bocateria.modelo.vo.ProductoVO;
import java.util.ArrayList;
import java.util.List;

public class Consumidor extends Thread{
    private ListaComandas cola;
    private List<ProductoVO> listaProductos = new ArrayList<>();
    private int numeroComandas;

    public Consumidor(ListaComandas listaComandas, ArrayList<ProductoVO> lista) {
        this.listaProductos.addAll(listaComandas.get());
        this.listaProductos = lista;
    }

    public void run() {
            listaProductos.removeAll(listaProductos);
            listaProductos.addAll(cola.get()); //recoge la lista de la cola
    }
}
