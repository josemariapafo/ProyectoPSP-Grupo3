package bocateria;

import bocateria.exepcion.Alertas;
import bocateria.modelo.vo.PedidoVO;
import bocateria.vista.CarritoController;
import bocateria.controlador.LoginControlador;
import bocateria.modelo.vo.ProductoVO;
import bocateria.modelo.vo.UsuarioVO;
import bocateria.controlador.VpalController;
import bocateria.controlador.RegistroControlador;
import bocateria.modelo.Model;
import bocateria.controlador.RegisterSandwichController;
import bocateria.vista.ComandaController;
import bocateria.vista.WriteMailController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main extends Application {
    Stage primaryStage;
    AnchorPane rootLayout;
    Model model = new Model();
    UsuarioVO usuario;
    private Alertas alerta = new Alertas();
    //DATOS DEL CARRITO
    List<ProductoVO> listaCarrito = new ArrayList<>();
    private ObservableList<ProductoVO> listaCarro = FXCollections.observableArrayList();
    //DATOS DE LAS COMANDAS PUESTAS EN MARCHA
    List<PedidoVO> listaComandas = new ArrayList<>();
    private ObservableList<PedidoVO> listaComanda = FXCollections.observableArrayList();

    public ObservableList<ProductoVO> getCarritoData() {
        return listaCarro;
    }

    public ObservableList<PedidoVO> getComandaData() {
        return listaComanda;
    }
    public void setListaComanda(List<PedidoVO> listaComanda) {
        this.listaComandas = listaComanda;
        getComandaData().addAll(listaComandas);
    }

    public Model getModel() {
        return model;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        showLogIn();

//        Parent root = FXMLLoader.load(getClass().getResource("VistaLogin.fxml"));
//        primaryStage.setTitle("Hello World");
//        primaryStage.setScene(new Scene(root, 300, 275));
//        primaryStage.show();
    }


    public void showLogIn() {
        try {
            // Load login overview.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("vista/VistaLogin.fxml"));
            AnchorPane vistaLogin = loader.load();

            Scene scene = new Scene(vistaLogin);
            primaryStage.setScene(scene);

            // Give the controller access to the main app.
            LoginControlador controller = loader.getController();
            controller.setMainApp(this);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al iniciar la ventana login");
        }
    }

    public void initRegistro() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("vista/VistaRegistro.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the usuarioVO into the controller.
            RegistroControlador controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initRegistroSandwich(/*VpalController c*/) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("vista/VistaRegisterSandwich.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the usuarioVO into the controller.
            RegisterSandwichController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMainApp(this);
            /*controller.setController(c);*/
            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void initVistaPrincipal() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("vista/VistaPpal.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Vista Principal");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the usuarioVO into the controller.
            VpalController controller = loader.getController();
            controller.setMainApp(this);
            controller.setUsuario(getUsuario());
            controller.setProductos();
            controller.load();
            //controller.setDialogStage(dialogStage);

            // Show the dialog and wait until the user closes it
            dialogStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initEnviaCorreo() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("vista/VistaWriteMail.fxml"));
            AnchorPane page = loader.load();
            Stage dialogStage = new Stage();

            dialogStage.setTitle("Enviar un correo");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            WriteMailController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMain(this);
            dialogStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initVistaCarrito() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("vista/VistaCarrito.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Carrito");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the usuarioVO into the controller.
            CarritoController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMain(this);
            controller.calcularTotal();
            //controller.cargarComponentes();
            //controller.setDialogStage(dialogStage);

            // Show the dialog and wait until the user closes it
            dialogStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void initVistaComanda() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("vista/VistaComanda.fxml"));
            AnchorPane page = loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Carrito");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the usuarioVO into the controller.
            ComandaController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setMain(this);
            controller.setDialogStage(dialogStage);

            // Show the dialog and wait until the user closes it
            dialogStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setUsuario(UsuarioVO u) {
        this.usuario = u;
    }

    public UsuarioVO getUsuario() {
        return usuario;
    }

    public List<ProductoVO> getListaCarrito() {
        return listaCarrito;
    }

    public void setListaCarrito(List<ProductoVO> listaCarrito) {
        this.listaCarrito = listaCarrito;
        getCarritoData().addAll(listaCarrito);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Alertas getAlerta() {
        return alerta;
    }

    public void setAlerta(Alertas alert) {
        this.alerta = alert;
    }

}
