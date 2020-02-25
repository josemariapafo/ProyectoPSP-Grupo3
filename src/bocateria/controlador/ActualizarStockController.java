package bocateria.controlador;

import bocateria.Main;
import bocateria.exepcion.ExcepcionBocateria;
import bocateria.modelo.Model;
import bocateria.modelo.vo.ProductoVO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ActualizarStockController {
    @FXML
    private TextField nombre;
    @FXML
    private TextArea descripcion;
    @FXML
    private TextField precio;
    @FXML
    private TextField stock;

    @FXML
    private Button volver;
    @FXML
    private Button cancelar;
    @FXML
    private Button aceptar;
    @FXML
    private Button modificar;
    @FXML
    private Button mas;
    @FXML
    private Button menos;
    @FXML
    private TableView<ProductoVO> tablaProductos;
    @FXML
    private TableColumn<ProductoVO, String> columnaNombreProducto;
    @FXML
    private TableColumn<ProductoVO, Integer> columnaStockProducto;

    private ProductoVO producto;
    private Stage dialogStage;
    private Main main;
    private Model model;
    private List<ProductoVO> listaProductos;

    @FXML
    private void initialize() {
        showProductData(null);
        tablaProductos.getSelectionModel().selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> showProductData(newValue));
        columnaNombreProducto.setCellValueFactory(cellData -> cellData.getValue().nombrePropertyProperty());
        columnaStockProducto.setCellValueFactory(cellData -> cellData.getValue().stockPropertyProperty().asObject());
    }

    public void setMain(Main main) {
        this.main = main;
        this.model = main.getModel();
        main.getProductData().clear();
        listaProductos = model.obtenerTodosProductos();
        main.getProductData().addAll(listaProductos);
        tablaProductos.setItems(main.getProductData());
        listaProductos = new ArrayList<>(main.getCarritoData());
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void handleVolver() {
        dialogStage.close();
    }

    @FXML
    private void handleCancelar() {
        clearData();
        habilitaBotonesModificar();
    }

    @FXML
    private void handleModificar() {
        deshabilitaBotonesModificar();
    }

    @FXML
    private void handleAceptar() throws ExcepcionBocateria, SQLException {
        habilitaBotonesModificar();
        producto.setNombre(nombre.getText());
        producto.setDescripcion(descripcion.getText());
        producto.setPrecio(Double.parseDouble(precio.getText()));
        producto.setStock(Integer.parseInt(stock.getText()));
        model.actualizarProductoSinFoto(producto);
    }

    private void habilitaBotonesModificar(){
        // Campos de texto deshabilitados
        nombre.setDisable(true);
        descripcion.setDisable(true);
        precio.setDisable(true);
        // Botones sumar y restar stock invisibles
        mas.setVisible(false);
        menos.setVisible(false);
        // Botones Modificar y Volver visibles
        modificar.setVisible(true);
        modificar.setDisable(false);
        volver.setDisable(false);
        volver.setVisible(true);
        // Botones Aceptar y Cancelar
        aceptar.setVisible(false);
        aceptar.setDisable(true);
        cancelar.setDisable(true);
        cancelar.setVisible(false);
    }

    private void deshabilitaBotonesModificar(){
        // Campos de texto habilitados para escribir
        nombre.setDisable(false);
        descripcion.setDisable(false);
        precio.setDisable(false);
        // Botones de sumar y restar cantidad visibles
        mas.setVisible(true);
        menos.setVisible(true);
        // Botones Modificar y Volver invisibles
        modificar.setVisible(false);
        modificar.setDisable(true);
        volver.setDisable(true);
        volver.setVisible(false);
        // Botones Aceptar y Cancelar visibles
        aceptar.setVisible(true);
        aceptar.setDisable(false);
        cancelar.setVisible(true);
        cancelar.setDisable(false);

    }

    @FXML
    private void handleMas() {
        producto.setStock(producto.getStock() + 1);
        stock.setText(String.valueOf(producto.getStock()));
    }

    @FXML
    private void handleMenos() {
        if (producto.getStock() != 0) {
            producto.setStock(producto.getStock() - 1);
            stock.setText(String.valueOf(producto.getStock()));
        }
    }

    private void showProductData(ProductoVO p) {
        if (p != null) {
            producto = p;
            nombre.setText(p.getNombre());
            descripcion.setText(p.getDescripcion());
            precio.setText(String.valueOf(p.getPrecio()));
            stock.setText(String.valueOf(p.getStock()));
        } else
            clearData();
    }

    private void clearData() {
        nombre.setText("");
        descripcion.setText("");
        precio.setText("");
        stock.setText("");
    }
}
