package bocateria.vista;

import bocateria.Main;
import bocateria.controlador.SendMailController;
import bocateria.exepcion.ExcepcionBocateria;
import bocateria.modelo.Model;
import bocateria.modelo.dao.bd.BDManager;
import bocateria.modelo.dao.bd.BDPedido;
import bocateria.modelo.vo.PedidoProductoVO;
import bocateria.modelo.vo.PedidoVO;
import bocateria.modelo.vo.ProductoVO;
import bocateria.modelo.vo.UsuarioVO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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


    private Main mainApp;
    private Stage dialogStage;
    private BDPedido bdPedido = new BDPedido();
    private BDManager bdManager;
    List<PedidoVO> listaPedidos = new ArrayList<>();

    //-----------------------------------------------

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

    public void setMain(Main main) {
        this.mainApp = main;
        listaPedidos = mainApp.getComandaData();
        tablaPedidos.setItems(mainApp.getComandaData());
       // listaComandas
        //tablaProductos.setItems(main.getCarritoData());
        //pedidoProductoVO = new ArrayList<>(main.getCarritoData());
    }

    @FXML
    private void initialize() {
        // Initialize the person table with the two columns.
       //Date date = new Date();

       columnaPedidoId.setCellValueFactory(cellData -> cellData.getValue().pedidoIdProperyProperty().asObject());
      columnaNombrePedido.setCellValueFactory(cellData -> cellData.getValue().usuarioPropertyProperty());
    }
    public List<PedidoVO> obtenerPedidosHoy(){
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
       //Añadir lista de Productos al pedido y añadir el nombre del usuario
        for(int i = 0; i<pedidosFechaHoy.size(); i++){
            List<ProductoVO> productoVOS = new ArrayList<>();
            UsuarioVO usuario = new UsuarioVO();
            try {
                usuario.setUsuario(bdManager.getPedidoDAO().obtenerUsuarioDelPedido(pedidosFechaHoy.get(i).getPedidoId()));
                pedidosFechaHoy.get(i).setUsuario(usuario);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error al guardar el usuario");
            }

            for(int j = 0; j<pedidoProductoVO.get(i).size();j++){
                try {
                    productoVOS.add(bdManager.getProductoDAO().obtenerProductoMedianteID(pedidoProductoVO.get(i).get(j).getIdProducto()));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            pedidosFechaHoy.get(i).setListaProductos(productoVOS);
        }

        //MOSTRAR POR CADA PEDIDO TODO SUS ATRIBUTOS
        System.out.println("MOSTRAR POR CADA PEDIDO TODO SUS ATRIBUTOS");
        for(int i = 0; i<pedidosFechaHoy.size(); i++){
            System.out.println(pedidosFechaHoy.get(i).getPedidoId()+" "+pedidosFechaHoy.get(i).getDate()+" "+pedidosFechaHoy.get(i).getTotal()+" "+pedidosFechaHoy.get(i).getUsuario().getUsuario());
            for(int j= 0; j<pedidosFechaHoy.get(i).getListaProductos().size(); j++){
                System.out.println("   "+pedidosFechaHoy.get(i).getListaProductos().get(j));
            }
        }
        //mainApp.setListaComanda(pedidosFechaHoy);
        return pedidosFechaHoy;
    }

}
