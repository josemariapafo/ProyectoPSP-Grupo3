package bocateria.controlador;

import bocateria.Main;
import bocateria.exepcion.ExcepcionBocateria;
import bocateria.modelo.vo.PedidoVO;
import bocateria.modelo.vo.ProductoVO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;

public class CarritoController {

    @FXML
    ListView<ProductoVO> list = new ListView<ProductoVO>();
    @FXML
    Label total;
    Main mainApp;
    private Stage dialogStage;
    private ArrayList<ProductoVO> listaProductos = new ArrayList<ProductoVO>();
    double totalPrecio  =0;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    public void setMainApp(Main main){
        this.mainApp = main;
    }

    public void visualizarListaProductos(){
        ProductoVO p1 = new ProductoVO("Serranito","Buen serranito",null,5.8);
        p1.setStock(5);
        ProductoVO p2 = new ProductoVO("SerriBurguer","Especial de la casa",null,3.0);
        p1.setStock(18);
        listaProductos.add(p1);
        listaProductos.add(p2);
        list.setItems((ObservableList<ProductoVO>) listaProductos);
    }

    public void cargarComponentes(){
        visualizarListaProductos();
        calcularTotal();
    }

    public void calcularTotal(){
        for(int i = 0; i<listaProductos.size(); i++){
            totalPrecio = totalPrecio + listaProductos.get(i).getPrecio();
        }
        total.setText(totalPrecio+"€");
    }

    @FXML
    public void hacerPedido(){
        //CREAMOS UN PEDIDO
        PedidoVO pedidoVO = new PedidoVO();
        pedidoVO.setTotal(totalPrecio);
        try {
            mainApp.getModel().insertarPedido(pedidoVO);
        } catch (ExcepcionBocateria excepcionBocateria) {
            excepcionBocateria.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //HACEMOS UN PEDIDO_PRODUCTO POR CADA GRUPO DE PRODUCTOS QUE EL CLIENTE META EN EL CARRITO
        for (int i = 0; i<listaProductos.size(); i++){
            mainApp.getModel();
        }
    }


}
