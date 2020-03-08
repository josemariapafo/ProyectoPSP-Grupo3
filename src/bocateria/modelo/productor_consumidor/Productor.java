package bocateria.modelo.productor_consumidor;

import bocateria.Main;
import bocateria.modelo.vo.PedidoVO;

public class Productor extends Thread {

    private ListaComandas cola;
    private PedidoVO pedido;
    private boolean huecoEnCola;

    public Productor(PedidoVO pedido, Main main) {
        this.pedido = pedido;
        this.cola = main.getColaComandas();
        System.out.println("Entra constructor productor");
    }



    public void run() {
        System.out.println("Entra run productor");
        boolean hueco = cola.put(pedido);
        System.out.println("Cola put return => "+hueco);
        setHueco(hueco);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setHueco(boolean hueco){
        System.out.println("Hay hueco: "+hueco);
        this.huecoEnCola = hueco;
    }

    public boolean hayHueco(){
        System.out.println("El hueco devuelto es => "+huecoEnCola);
        return huecoEnCola;
    }
}
