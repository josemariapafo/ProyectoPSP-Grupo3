package bocateria.vista;

import bocateria.Main;
import bocateria.exepcion.ExcepcionBocateria;
import bocateria.modelo.vo.PedidoVO;
import bocateria.modelo.vo.ProductoVO;
import bocateria.modelo.vo.UsuarioVO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.util.ArrayList;

public class CarritoController {

    @FXML
    HBox hbox;
    @FXML
    Label total;
    @FXML
    TableView<ProductoVO> tableView;
    @FXML
    TableColumn<ProductoVO, String> nombreColum;
    @FXML
    TableColumn<ProductoVO, String> cantidadColum;
    @FXML
    HBox hBoxNombre;
    @FXML
    HBox hBoxCantidad;


    Main mainApp;
    private Stage dialogStage;
    private ArrayList<ProductoVO> listaProductos = new ArrayList<ProductoVO>();
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

    public void cargarLista(){
        ProductoVO p1 = new ProductoVO("Serranito","Buen serranito",null,5.8);
        p1.setStock(5);
        p1.setCantidad(56);
        ProductoVO p2 = new ProductoVO("SerriBurguer","Especial de la casa",null,3.0);
        p2.setStock(18);
        p2.setCantidad(23);
        listaProductos.add(p1);
        listaProductos.add(p2);
    }
    public void visualizarNombreProductos(){
        ListView list = new ListView();
        list.getItems().add(listaProductos.get(0).getNombre()+" ");
        list.getItems().add(listaProductos.get(1).getNombre()+" ");
        hBoxNombre = new HBox(list);

        Scene scene = new Scene(hBoxNombre);
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    public void visualizarPrecioProductos(){
        ListView list = new ListView();
        list.getItems().add(listaProductos.get(0).getCantidad()+" ");
        list.getItems().add(listaProductos.get(1).getCantidad()+" ");
        hBoxCantidad = new HBox(list);

        Scene scene = new Scene(hBoxNombre);
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    public void visualizarListaProductos(){

        visualizarNombreProductos();
        visualizarPrecioProductos();
    }

    public void cargarComponentes(){
        cargarLista();
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
