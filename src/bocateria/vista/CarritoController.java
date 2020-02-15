package bocateria.vista;

import bocateria.Main;
import bocateria.exepcion.ExcepcionBocateria;
import bocateria.modelo.vo.PedidoVO;
import bocateria.modelo.vo.ProductoVO;
import bocateria.modelo.vo.UsuarioVO;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

    //TENDRÉ QUE RECIBIR UN ARRAYLIST DE TODOS LOS PRODUCTOS DE LA VISTA PRINCIPAL
    //TENDRE QUE RECIBIR EL USUARIO LOGUEADO

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
        ProductoVO p1 = new ProductoVO("Serranito","Buen serranito",null,5.8,5,20);
        p1.setCodigo(1);
        ProductoVO p2 = new ProductoVO("SerriBurguer","Especial de la casa",null,3.0,18,3);
        p2.setCodigo(2);
        ProductoVO p3 = new ProductoVO("Pizaa","Diforno dipiedra",null,4.0,25,3);
        p3.setCodigo(3);
        listaProductos.add(p1);
        listaProductos.add(p2);
        listaProductos.add(p3);
        usuario.setUsuario("admin");
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
        //visualizarListaProductos();
        calcularTotal();
    }

    public void calcularTotal(){
        for(int i = 0; i<listaProductos.size(); i++){
            totalPrecio = totalPrecio + listaProductos.get(i).getPrecio()*listaProductos.get(i).getCantidad();
        }
        total.setText(totalPrecio+"€");
    }

    @FXML
    public void hacerPedido() throws ExcepcionBocateria {
        System.out.printf("Buenas");
        //CREAMOS UN PEDIDO
        int ultimaIdPedido=0;
        PedidoVO pedidoVO = new PedidoVO();
        //Calculamos cuanto será el total de todo el pedido
        System.out.printf("Total Pedido: "+totalPrecio);
        pedidoVO.setTotal(totalPrecio);
        long tiempo = System.currentTimeMillis();
        pedidoVO.setDate(tiempo);
        try {

            System.out.printf("Realizar un Pedido");
            mainApp.getModel().insertarPedido(pedidoVO);
        } catch (ExcepcionBocateria excepcionBocateria) {
            excepcionBocateria.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //OBETENER LA ID DEL PEDIDO REALIZADO
        ultimaIdPedido = mainApp.getModel().obtenerUltimaIdPedido();


        //HACEMOS UN PEDIDO_PRODUCTO POR CADA GRUPO DE PRODUCTOS QUE EL CLIENTE META EN EL CARRITO
        for (int i = 0; i<listaProductos.size(); i++){
            if(listaProductos.get(i).getCantidad()!=0){
                mainApp.getModel().insertarPedidoProducto(ultimaIdPedido,listaProductos.get(i).getCodigo(),listaProductos.get(i).getCantidad());
            }
        }
        //INSERTAMOS EL PEDIDO EN LA TABLA PEDIDO-USUARIO
        mainApp.getModel().insertarUsuarioPedido(usuario.getUsuario(),ultimaIdPedido);
    }
}
