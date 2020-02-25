package bocateria.controlador;

import bocateria.Main;
import bocateria.controlador.SendMailController;
import bocateria.exepcion.ExcepcionBocateria;
import bocateria.modelo.Model;
import bocateria.modelo.vo.MailVO;
import bocateria.modelo.vo.PedidoVO;
import bocateria.modelo.vo.ProductoVO;
import bocateria.modelo.vo.UsuarioVO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.spec.InvalidKeySpecException;
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

    private Main main;
    private Model modelo;
    private Stage dialogStage;
    private SendMailController sendMail;
    private List<ProductoVO> listaProductos = new ArrayList<>();
    double totalPrecio = 0;

    UsuarioVO usuario = new UsuarioVO();

    public void setUsuario(UsuarioVO usuario) {
        this.usuario = usuario;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    //Método que introduce los datos del carrito en la tabla productos
    public void setMain(Main main) {
        this.main = main;
        this.modelo = main.getModel();
        this.usuario = main.getUsuario();
        tablaProductos.setItems(main.getCarritoData());
        listaProductos = new ArrayList<>(main.getCarritoData());
    }


    @FXML
    private void initialize() {
        columnaNombreProducto.setCellValueFactory(cellData -> cellData.getValue().nombrePropertyProperty());
        columnaCantidadProducto.setCellValueFactory(cellData -> cellData.getValue().cantidadPropertyProperty().asObject());
    }

    // Calcula el precio total de la lista de producto del carrito
    public void calcularTotal() {
        for (ProductoVO p : listaProductos) {
            setTotalPrecio(getTotalPrecio() + (p.getPrecio() * p.getCantidad()));
        }
        total.setText(getTotalPrecio() + " €");
    }

    public double getTotalPrecio() {
        return totalPrecio;
    }

    public void setTotalPrecio(double totalPrecio) {
        this.totalPrecio = totalPrecio;
    }

    @FXML
    public void hacerPedido() throws ExcepcionBocateria, UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyStoreException, InvalidKeyException, InvalidKeySpecException, SQLException {
        System.out.println("HACER PEDIDO!!");
        boolean done = true;
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
        //HACEMOS UN INSERT EN PEDIDO_PRODUCTO POR CADA GRUPO DE PRODUCTOS QUE EL CLIENTE META EN EL CARRITO
        for (ProductoVO p : listaProductos) {
            modelo.insertarPedidoProducto(ultimaIdPedido, p.getCodigo(), p.getCantidad());
            modelo.stockDown(p);
        }
        //INSERTAMOS EL PEDIDO EN LA TABLA PEDIDO-USUARIO
        modelo.insertarUsuarioPedido(usuario.getUsuario(), ultimaIdPedido);
        main.getAlerta().info("Pedido realizado con éxito");
//        System.out.println("Acuerdate de descomentar la linea del correo para que\nla aplicación vuelva a enviar correos ;)");
        modelo.sendMail(new MailVO(pedidoVO));
        cancelar();
    }

    @FXML
    public void cancelar() {
        dialogStage.close();
        main.initVistaPrincipal();
    }
}
