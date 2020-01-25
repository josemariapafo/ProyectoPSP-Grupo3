package bocateria.controlador;

import bocateria.Main;
import bocateria.exepcion.ExcepcionBocateria;
import bocateria.modelo.vo.PedidoVO;
import bocateria.modelo.vo.ProductoVO;
import bocateria.modelo.vo.UsuarioVO;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarritoController {

    @FXML
    ListView<ProductoVO> list = new ListView<ProductoVO>();
    @FXML
    Label total;
    Main mainApp;
    private Stage dialogStage;
    private List<ProductoVO> listaProductos = new ArrayList<ProductoVO>();
    double totalPrecio  = 0;
    UsuarioVO usuario = new UsuarioVO();

    public void setUsuario(UsuarioVO usuario) {
        this.usuario = usuario;
    }

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
//        listaProductos = mainApp.getListaCarrito();
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
    public void hacerPedido() throws ExcepcionBocateria {
        //CREAMOS UN PEDIDO
        int ultimaId=0;
        PedidoVO pedidoVO = new PedidoVO();
        pedidoVO.setTotal(totalPrecio);
        try {
            mainApp.getModel().insertarPedido(pedidoVO);
        } catch (ExcepcionBocateria excepcionBocateria) {
            excepcionBocateria.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //OBETENER LA ID DEL PEDIDO REALIZADO
        ultimaId = mainApp.getModel().obtenerUltimaIdPedido();
        //HACEMOS UN PEDIDO_PRODUCTO POR CADA GRUPO DE PRODUCTOS QUE EL CLIENTE META EN EL CARRITO
        for (int i = 0; i<listaProductos.size(); i++){
            if(listaProductos.get(i).getCantidad()!=0){
                mainApp.getModel().insertarPedidoProducto(ultimaId,listaProductos.get(i).getCodigo(),listaProductos.get(i).getCantidad());
            }
        }
        //INSERTAMOS EL PEDIDO EN LA TABLA PEDIDO USUARIO
        mainApp.getModel().insertarUsuarioPedido(usuario.getUsuario(),ultimaId);
    }


}
