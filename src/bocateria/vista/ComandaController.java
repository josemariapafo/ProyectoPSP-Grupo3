package bocateria.vista;

import bocateria.Main;
import bocateria.exepcion.ExcepcionBocateria;
import bocateria.modelo.dao.bd.BDManager;
import bocateria.modelo.dao.bd.BDPedido;
import bocateria.modelo.vo.PedidoProductoVO;
import bocateria.modelo.vo.PedidoVO;
import bocateria.modelo.vo.ProductoVO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.sql.Array;
import java.sql.SQLException;
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
    private BDPedido bdPedido = new BDPedido();
    private BDManager bdManager;

    {
        try {
            bdManager = new BDManager();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al cargar BDManager");

        }
    }

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
        List<PedidoVO> pedidosFechaHoy = bdManager.getPedidoDAO().obtenerTodosPedidosHoy();
        for(int i = 0; i<pedidosFechaHoy.size(); i++){
            System.out.println(pedidosFechaHoy.get(i));
        }

        System.out.println("Obtenemos por cada pedido su array de productos");
        List<List<PedidoProductoVO>> pedidoProductoVO = new ArrayList<>();
        for(int i = 0; i<pedidosFechaHoy.size(); i++){
            try {
                pedidoProductoVO.add(bdManager.getPedidoDAO().obtenerPedidoProductoList(pedidosFechaHoy.get(i)));
            } catch (ExcepcionBocateria excepcionBocateria) {
                excepcionBocateria.printStackTrace();
                System.out.println("Error al obtener de cada pedido sus productos");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
       // Mostrar la lista de productos por cada pedido
        System.out.println("Por cada pedido , sus productos");

       for(int i = 0; i<pedidoProductoVO.size();i++){
            for(int j = 0; j<pedidoProductoVO.get(i).size(); j++){
                System.out.println(pedidoProductoVO.get(i).get(j));
            }
        }

       //Añadir lista de Productos al pedido
        for(int i = 0; i<pedidosFechaHoy.size(); i++){
            List<ProductoVO> productoVOS = new ArrayList<>();
            for(int j = 0; j<pedidoProductoVO.size();j++){
                try {
                    productoVOS.add(bdManager.getProductoDAO().obtenerProductoMedianteID(pedidoProductoVO.get(i).get(j).getIdProducto()));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            pedidosFechaHoy.get(i).setListaProductos(productoVOS);
        }

        //MOSTRAR POR CADA PEDIDO TODO SUS ATRIBUTOS
        for(int i = 0; i<pedidosFechaHoy.size(); i++){
            System.out.println(pedidosFechaHoy.get(i).getPedidoId()+" "+pedidosFechaHoy.get(i).getDate()+" "+pedidosFechaHoy.get(i).getTotal()+" "+pedidosFechaHoy.get(i).getUsuario());
        }


    }

}
