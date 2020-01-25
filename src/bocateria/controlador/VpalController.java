package bocateria.controlador;

import bocateria.Main;
import bocateria.modelo.vo.ProductoVO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class VpalController {
    private List<ProductoVO> productos = new ArrayList<>();
    private Main mainApp;

    @FXML
    private Button comprar;

    @FXML
    private Pane pane00;
    @FXML
    private Pane pane01;
    @FXML
    private Pane pane02;
    @FXML
    private Pane pane03;
    @FXML
    private Pane pane04;
    @FXML
    private Pane pane10;
    @FXML
    private Pane pane11;
    @FXML
    private Pane pane12;
    @FXML
    private Pane pane13;
    @FXML
    private Pane pane14;


    public void setMainApp(Main main){
        this.mainApp = main;
    }

    private List<ProductoVO> getProductos() {
        return productos;
    }

    void loadUI(){
        setProductos();
        Parent root = null;
        try{
            root = FXMLLoader.load(Main.class.getResource("VistaProducto.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setProductos() {
        this.productos = mainApp.getModel().obtenerTodosProductos();
    }

    @FXML
    public void pulsarBotonComprar(){
        try {
            mainApp.initVistaCarrito();
        }catch(Exception e){
            System.out.println("Error al abrir el carrito");
        }
    }
}
