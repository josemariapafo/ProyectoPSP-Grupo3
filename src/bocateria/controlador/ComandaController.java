package bocateria.controlador;

import bocateria.Main;
import bocateria.exepcion.ExcepcionBocateria;
import bocateria.modelo.Model;
import bocateria.modelo.productor_consumidor.Consumidor;
import bocateria.modelo.productor_consumidor.ListaComandas;
import bocateria.modelo.vo.PedidoVO;
import bocateria.modelo.vo.ProductoVO;
import javafx.collections.ObservableList;
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
    private ListaComandas colaComandas;
    List<PedidoVO> listaPedidos = new ArrayList<>();

    /**
     * Define la instancia del Stage para poder cerrar la ventana desde nuestro botón de Salir
     *
     * @param dialogStage instancia del Stage que abre la vista de la comanda
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Se instancia el main para obtener la instancia del modelo que alli existe, además añadimos a la tabla de pedidos los pedidos que se han realizado a día de hoy
     *
     * @param main instancia del main de nuestra aplicacion
     * @throws ExcepcionBocateria
     * @throws SQLException
     */
    public void setMain(Main main) throws ExcepcionBocateria, SQLException {
        this.mainApp = main;
        this.modelo = main.getModel();
        this.colaComandas = main.getColaComandas();
//        mainApp.getComandaData().clear();
//        mainApp.getComandaData().addAll(obtenerPedidosHoy());
//        tablaPedidos.setItems(mainApp.getComandaData());
//        listaPedidos = new ArrayList<>(main.getComandaData());
        new Thread(new Consumidor(main.getColaComandas(),this)).start();
    }

    public void setItems(ObservableList<PedidoVO> pedidos){
        tablaPedidos.setItems(pedidos);
        listaPedidos = new ArrayList<>(pedidos);
    }
                         /**
     * Llamado al instanciar el controlador de la vista comandas
     * Sirve para rellenar las columnas y define las pulsaciones en los registros de la tabla de pedidos
     */
                         @FXML
    private void initialize() {
        showProductData(null);
        tablaPedidos.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> showProductData(newValue));
        columnaPedidoId.setCellValueFactory(cellData -> cellData.getValue().pedidoIdProperyProperty().asObject());
        columnaNombrePedido.setCellValueFactory(cellData -> cellData.getValue().usuarioPropertyProperty());

    }

    /**
     * Muestra en la tabla de productos los productos del pedido seleccionado
     *
     * @param newValue es el pedido seleccionado
     */
    private void showProductData(PedidoVO newValue) {
        if (newValue != null) {
            List<ProductoVO> lista = new ArrayList<>(newValue.getListaProductos());
            mainApp.getCarritoData().clear();
            mainApp.getCarritoData().addAll(lista);

            tablaProductos.setItems(mainApp.getCarritoData());
            nombreProd.setText(newValue.getUsuario().getUsuario());
            totalProd.setText(newValue.getTotal() + " €");

            columnaNombreProducto.setCellValueFactory(cellData -> cellData.getValue().nombrePropertyProperty());
            columnaCantidadProducto.setCellValueFactory(cellData -> cellData.getValue().cantidadPropertyProperty().asObject());
        }
    }


    public List<PedidoVO> obtenerPedidosHoy() throws ExcepcionBocateria, SQLException {
        return modelo.obtenerPedidosHoy();
    }

    @FXML
    private void handleCerrar() {
        dialogStage.close();
        mainApp.initVistaPrincipal();
    }
}
