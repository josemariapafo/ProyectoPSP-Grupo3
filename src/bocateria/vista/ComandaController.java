package bocateria.vista;

import bocateria.Main;
import bocateria.modelo.Model;
import bocateria.modelo.vo.PedidoVO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;


import java.util.ArrayList;
import java.util.List;

public class ComandaController {

    @FXML
    Label nombreProd;
    @FXML
    Label totalProd;
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


    private Main mainApp;
    private Model modelo;
    private Stage dialogStage;
    List<PedidoVO> listaPedidos = new ArrayList<>();


    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setMain(Main main) {
        this.mainApp = main;
        this.modelo = main.getModel();
        mainApp.getComandaData().clear();
        mainApp.getComandaData().addAll(obtenerPedidosHoy());
        tablaPedidos.setItems(mainApp.getComandaData());
        listaPedidos = new ArrayList<>(main.getComandaData());
    }
    @FXML
    private void initialize() {
      columnaPedidoId.setCellValueFactory(cellData -> cellData.getValue().pedidoIdProperyProperty().asObject());
      columnaNombrePedido.setCellValueFactory(cellData -> cellData.getValue().usuarioPropertyProperty());
    }

    @FXML
    public List<PedidoVO> obtenerPedidosHoy(){
        return modelo.obtenerPedidosHoy();
    }

    @FXML
    private void handleCerrar(){
        dialogStage.close();
    }

}
