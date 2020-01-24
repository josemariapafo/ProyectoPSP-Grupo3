package bocateria;

import bocateria.controlador.CarritoController;
import bocateria.controlador.LoginControlador;
import bocateria.controlador.VpalController;
import bocateria.controlador.RegistroControlador;
import bocateria.modelo.dao.bd.BDManager;
import bocateria.controlador.RegisterSandwichController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class Main extends Application {
    Stage primaryStage;
    AnchorPane rootLayout;
    BDManager bdManager;

    {
        try {
            bdManager = new BDManager();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public BDManager getBdManager() {
        return bdManager;
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public void setBdManager(BDManager bdManager) {
        this.bdManager = bdManager;
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
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
            AnchorPane vistaLogin = (AnchorPane) loader.load();

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

    public void initRegistro(){
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

    public void initRegistroSandwich(){
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

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void initVistaPrincipal(){
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
            //controller.setMainApp(this);
            //controller.setDialogStage(dialogStage);

            // Show the dialog and wait until the user closes it
            dialogStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void initVistaCartera(){
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
            //controller.setDialogStage(dialogStage);

            // Show the dialog and wait until the user closes it
            dialogStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }


}
