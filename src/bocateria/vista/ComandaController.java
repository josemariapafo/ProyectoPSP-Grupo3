package bocateria.vista;

import bocateria.Main;
import bocateria.modelo.vo.ProductoVO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ComandaController {

    @FXML
    Button listoButton;
    @FXML
    private TableView<ProductoVO> tablaProductos;
    @FXML
    private TableColumn<ProductoVO, String> columnaNombreProducto;
    @FXML
    private TableColumn<ProductoVO, Integer> columnaCantidadProducto;

    Main mainApp;
    private Stage dialogStage;
    private List<ProductoVO> listaComandas = new ArrayList<ProductoVO>();

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setMainApp(Main main){
        this.mainApp = main;
        tablaProductos.setItems(main.getCarritoData());
        listaComandas = new ArrayList<>(main.getComandaData());
    }

    @FXML
    private void initialize() {
//        cargarLista();
        // Initialize the person table with the two columns.

        columnaNombreProducto.setCellValueFactory(cellData -> cellData.getValue().nombrePropertyProperty());
        columnaCantidadProducto.setCellValueFactory(cellData -> cellData.getValue().cantidadPropertyProperty().asObject());
    }
}
