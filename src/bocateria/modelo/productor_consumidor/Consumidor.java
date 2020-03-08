package bocateria.modelo.productor_consumidor;

import bocateria.Main;
import bocateria.controlador.ComandaController;
import bocateria.modelo.vo.PedidoVO;
import bocateria.modelo.vo.ProductoVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Consumidor extends Thread {
    private ListaComandas cola;
    private ComandaController controller;
    private List<PedidoVO> listaPedidos = new ArrayList<>();
    private ObservableList<PedidoVO> pedidos = FXCollections.observableArrayList();

    public Consumidor(ListaComandas cola, ComandaController controller) {
        this.cola = cola;
        this.controller = controller;
        System.out.println("Entra constructor consumidor");
    }


    public void run() {
        System.out.println("Entra run consumidor");
        pedidos.clear();
        pedidos.addAll(cola.get());
        controller.setItems(pedidos);
//        pedidos.clear();
    }

    public void clear() {
        pedidos.clear();
    }
}
