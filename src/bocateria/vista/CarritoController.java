package bocateria.vista;

import bocateria.Main;
import bocateria.exepcion.ExcepcionBocateria;
import bocateria.modelo.Model;
import bocateria.modelo.vo.PedidoVO;
import bocateria.modelo.vo.ProductoVO;
import bocateria.modelo.vo.UsuarioVO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CarritoController {


    @FXML
    Label total;
    @FXML
    private TableView<ProductoVO> tablaProductos;
    @FXML
    private TableColumn<ProductoVO, String> columnaNombreProducto;
    @FXML
    private TableColumn<ProductoVO, Integer> columnaCantidadProducto;


    //TENDRÉ QUE RECIBIR UN ARRAYLIST DE TODOS LOS PRODUCTOS DE LA VISTA PRINCIPAL
    //TENDRE QUE RECIBIR EL USUARIO LOGUEADO

    private Main main;
    private Model modelo;
    private Stage dialogStage;
    private List<ProductoVO> listaProductos = new ArrayList<ProductoVO>();
    double totalPrecio = 0;
    UsuarioVO usuario = new UsuarioVO();

    public void setUsuario(UsuarioVO usuario) {
        this.usuario = usuario;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setMain(Main main) {
        this.main = main;
        this.modelo = main.getModel();
        this.usuario = main.getUsuario();
        tablaProductos.setItems(main.getCarritoData());
        listaProductos = new ArrayList<>(main.getCarritoData());
    }

    @FXML
    private void initialize() {

//        cargarLista();
        // Initialize the person table with the two columns.

        columnaNombreProducto.setCellValueFactory(cellData -> cellData.getValue().nombrePropertyProperty());
        columnaCantidadProducto.setCellValueFactory(cellData -> cellData.getValue().cantidadPropertyProperty().asObject());
        calcularTotal();
    }

//    public void cargarLista(){
//        ProductoVO p1 = new ProductoVO("Serranito","Buen serranito",null,5.8,5,20);
//        p1.setCodigo(1);
//        ProductoVO p2 = new ProductoVO("SerriBurguer","Especial de la casa",null,3.0,18,3);
//        p2.setCodigo(2);
//        ProductoVO p3 = new ProductoVO("Pizaa","Diforno dipiedra",null,4.0,25,3);
//        p3.setCodigo(3);
//        listaProductos.add(p1);
//        listaProductos.add(p2);
//        listaProductos.add(p3);
//        usuario.setUsuario("admin");
//    }

    /*public void visualizarNombreProductos(){
        ListView list = new ListView();
        list.getItems().add(listaProductos.get(0).getNombre()+" ");
        list.getItems().add(listaProductos.get(1).getNombre()+" ");
        list.getItems().add(listaProductos.get(2).getNombre()+" ");
        hBoxNombre = new HBox(list);

        Scene scene = new Scene(hBoxNombre);
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    public void visualizarPrecioProductos(){
        ListView list = new ListView();
        list.getItems().add(listaProductos.get(0).getCantidad()+" ");
        list.getItems().add(listaProductos.get(1).getCantidad()+" ");
        list.getItems().add(listaProductos.get(2).getCantidad()+" ");
        hBoxCantidad = new HBox(list);

        Scene scene = new Scene(hBoxNombre);
        dialogStage.setScene(scene);
        dialogStage.show();
    }

    public void visualizarListaProductos(){

        visualizarNombreProductos();
        visualizarPrecioProductos();
    }*/

    public void cargarComponentes() {
//        cargarLista();
        // visualizarListaProductos();
        calcularTotal();
    }

    public void calcularTotal() {
        for (ProductoVO p : listaProductos) {
            setTotalPrecio(getTotalPrecio() + (p.getPrecio() * p.getCantidad()));
        }
        total.setText(totalPrecio + " €");
    }

    public double getTotalPrecio() {
        return totalPrecio;
    }

    public void setTotalPrecio(double totalPrecio) {
        this.totalPrecio = totalPrecio;
    }

    @FXML
    public void hacerPedido() throws ExcepcionBocateria {
        int ultimaIdPedido = 0;
        //CREAMOS UN PEDIDO
        ultimaIdPedido = modelo.obtenerUltimaIdPedido();
        PedidoVO pedidoVO = new PedidoVO(ultimaIdPedido, usuario, listaProductos);
        //Calculamos cuanto será el total de todo el pedido
        System.out.printf("Total Pedido: " + totalPrecio);

        try {
            System.out.printf("Realizar un Pedido");
            modelo.insertarPedido(pedidoVO);
        } catch (ExcepcionBocateria excepcionBocateria) {
            excepcionBocateria.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //OBETENER LA ID DEL PEDIDO REALIZADO
        ultimaIdPedido = modelo.obtenerUltimaIdPedido();

        //HACEMOS UN PEDIDO_PRODUCTO POR CADA GRUPO DE PRODUCTOS QUE EL CLIENTE META EN EL CARRITO
        for (ProductoVO p : listaProductos) {
            modelo.insertarPedidoProducto(ultimaIdPedido, p.getCodigo(), p.getCantidad());
        }

        //INSERTAMOS EL PEDIDO EN LA TABLA PEDIDO-USUARIO
        modelo.insertarUsuarioPedido(usuario.getUsuario(), ultimaIdPedido);
    }

    @FXML
    public void cancelar() {
        System.exit(1);
    }
}
