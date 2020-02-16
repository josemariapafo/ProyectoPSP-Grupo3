package bocateria.modelo.productor_consumidor;

import bocateria.modelo.vo.ProductoVO;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ListaComandas {
    private ArrayList<ProductoVO> listaProductos = new ArrayList<ProductoVO>();
    private boolean disponible = false;//inicialmente cola vacia
    private int numeroComandas=5;

    public ArrayList<ProductoVO> get() {
        if(disponible) {      //hay numero en la cola
            disponible = false; //se pone cola vacia
            return listaProductos;      //se devuelve
        }
        return null;//no hay numero disponible, cola vacia
    }

    public void put(ArrayList<ProductoVO> listaProducto) {
        boolean añadir = true;
        if(listaProductos.size()<numeroComandas){
            //coloca valor en la cola
            for(int i = 0; i<listaProducto.size(); i++){
                for(int j = 0; j<listaProductos.size(); i++) {
                    if (listaProductos.get(i).equals(listaProducto.get(i))) {
                        listaProductos.remove(listaProducto.get(i));
                        añadir = false;
                    }
                }
                if(añadir){
                    listaProductos.add(listaProducto.get(i));
                }
                añadir = true;
            }
            disponible = true;                      //disponible para consumir, cola llena
        }
    }
}
