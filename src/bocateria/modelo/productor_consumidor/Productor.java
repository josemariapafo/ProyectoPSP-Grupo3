package bocateria.modelo.productor_consumidor;

import bocateria.Main;
import bocateria.modelo.vo.ProductoVO;

import java.util.ArrayList;
import java.util.List;

public class Productor extends Thread{
    private ListaComandas cola;
    private List<ProductoVO> listaProductos = new ArrayList<>();

    public Productor() {

    }

    public void run() {
        Main main = new Main();
        main.getComandaData().addAll();
    }
}
