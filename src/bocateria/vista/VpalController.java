package bocateria.vista;

import bocateria.Main;
import bocateria.modelo.vo.ProductoVO;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class VpalController {
    private List<ProductoVO> productos = new ArrayList<>();
    private Main mainApp;
    private int index = 0;
    @FXML
    private Button comprar;
    @FXML
    ImageView img00;
    @FXML
    ImageView img01;
    @FXML
    ImageView img02;
    @FXML
    ImageView img03;
    @FXML
    ImageView img04;
    @FXML
    ImageView img10;
    @FXML
    ImageView img11;
    @FXML
    ImageView img12;
    @FXML
    ImageView img13;
    @FXML
    ImageView img14;
    @FXML
    Label nombre00,precio00,descripcion00, cantidad00;
    @FXML
    Label nombre01,precio01,descripcion01, cantidad01;
    @FXML
    Label nombre02,precio02,descripcion02, cantidad02;
    @FXML
    Label nombre03,precio03,descripcion03, cantidad03;
    @FXML
    Label nombre04,precio04,descripcion04, cantidad04;
    @FXML
    Label nombre10,precio10,descripcion10, cantidad10;
    @FXML
    Label nombre11,precio11,descripcion11, cantidad11;
    @FXML
    Label nombre12,precio12,descripcion12, cantidad12;
    @FXML
    Label nombre13,precio13,descripcion13, cantidad13;
    @FXML
    Label nombre14,precio14,descripcion14, cantidad14;



    public void setMainApp(Main main){
        this.mainApp = main;
    }

    private List<ProductoVO> getProductos() {
        return productos;
    }

    public void loadUI() throws IOException {
        setProductos();
        List<ProductoVO> list = getProductos();
        int index;
        FXMLLoader loader = new FXMLLoader();
        for(int paneles=0; paneles< 10 && (paneles < list.size()); paneles++){
            index = getIndex();
            switch (paneles){
                case 0:
                    img00.getLoadingImageIcon();
                    nombre00.setText(list.get(index).getNombre());
                    precio00.setText(String.valueOf(list.get(index).getPrecio()));
                    descripcion00.setText(list.get(index).getDescripcion());
                    break;
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;

            }
            setIndex(getIndex()+1);
        }
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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
