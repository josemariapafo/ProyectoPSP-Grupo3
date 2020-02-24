package bocateria.controlador;

import bocateria.Main;
import bocateria.exepcion.ExcepcionBocateria;
import bocateria.modelo.Model;
import bocateria.modelo.vo.PedidoVO;
import bocateria.modelo.vo.ProductoVO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;


import java.sql.SQLException;
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
    private TableView<ProductoVO> tablaProductos;
    @FXML
    private TableColumn<ProductoVO, String> columnaNombreProducto;
    @FXML
    private TableColumn<ProductoVO, Integer> columnaCantidadProducto;

    private Main mainApp;
    private Model modelo;
    private Stage dialogStage;
    List<PedidoVO> listaPedidos = new ArrayList<>();


    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setMain(Main main) throws ExcepcionBocateria, SQLException {
        this.mainApp = main;
        this.modelo = main.getModel();
        mainApp.getComandaData().clear();
        mainApp.getComandaData().addAll(obtenerPedidosHoy());
        tablaPedidos.setItems(mainApp.getComandaData());
        listaPedidos = new ArrayList<>(main.getComandaData());
    }

    @FXML
    private void initialize() {
        showProductData(null);
        tablaPedidos.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> showProductData(newValue));
        columnaPedidoId.setCellValueFactory(cellData -> cellData.getValue().pedidoIdProperyProperty().asObject());
        columnaNombrePedido.setCellValueFactory(cellData -> cellData.getValue().usuarioPropertyProperty());
    }

    private void showProductData(PedidoVO newValue) {
        if (newValue != null) {
            List<ProductoVO> lista = new ArrayList<>(newValue.getListaProductos());
            mainApp.getCarritoData().clear();
            mainApp.getCarritoData().addAll(lista);

            tablaProductos.setItems(mainApp.getCarritoData());
            nombreProd.setText(newValue.getUsuario().getUsuario());
            totalProd.setText("" + newValue.getTotal());

            columnaNombreProducto.setCellValueFactory(cellData -> cellData.getValue().nombrePropertyProperty());
            columnaCantidadProducto.setCellValueFactory(cellData -> cellData.getValue().cantidadPropertyProperty().asObject());
        }
    }

    @FXML
    public List<PedidoVO> obtenerPedidosHoy() throws ExcepcionBocateria, SQLException {
        return modelo.obtenerPedidosHoy();
    }

    @FXML
    private void handleCerrar() {
        dialogStage.close();
    }

}
