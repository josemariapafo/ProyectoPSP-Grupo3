package bocateria.vista;

import bocateria.Main;
import bocateria.modelo.dao.bd.BDPedido;
import bocateria.modelo.vo.PedidoVO;
import bocateria.modelo.vo.ProductoVO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ComandaController {

    @FXML
    private TableView<PedidoVO> tablaPedidos;
    @FXML
    private TableColumn<PedidoVO, Integer> columnaPedidoId;
    @FXML
    private TableColumn<PedidoVO, String> columnaNombrePedido;

    @FXML
    private TableView<PedidoVO> tablaProductos;
    @FXML
    private TableColumn<PedidoVO, Integer> columnaNombreProducto;
    @FXML
    private TableColumn<PedidoVO, String> columnaCantidadProducto;


    Main mainApp;
    private Stage dialogStage;
    private List<ProductoVO> listaComandas = new ArrayList<ProductoVO>();
    private BDPedido bdPedido;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setMain(Main main){
        this.mainApp = main;
        //tablaProductos.setItems(main.getCarritoData());
        //listaComandas = new ArrayList<>(main.getComandaData());
    }

    @FXML
    private void initialize() {
//        cargarLista();
        // Initialize the person table with the two columns.

        //columnaNombreProducto.setCellValueFactory(cellData -> cellData.getValue().nombrePropertyProperty());
       // columnaCantidadProducto.setCellValueFactory(cellData -> cellData.getValue().cantidadPropertyProperty().asObject());
    }
    public void obtenerPedidosHoy(){
        List<PedidoVO> pedidos = bdPedido.obtenerTodosPedidosHoy();
        for(int i = 0; i<pedidos.size(); i++){
            System.out.println(pedidos.get(i));
        }
    }

}
