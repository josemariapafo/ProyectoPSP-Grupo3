package bocateria.controlador;

import bocateria.Main;
import bocateria.exepcion.ExcepcionBocateria;
import bocateria.modelo.vo.UsuarioVO;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginControlador {
    @FXML
    private Label logInLbl;
    @FXML
    private Label usuarioLbl;
    @FXML
    private Label pwdLbl;
    @FXML
    private TextField usuario;
    @FXML
    private TextField pwd;
    @FXML
    private Button login;
    @FXML
    private Button regist;

    private UsuarioVO usuarioVO = new UsuarioVO();
    private Main mainApp;
    private Desktop desktop = Desktop.getDesktop();
    private URI uri = new URI ("www.geeksforgeeks.org");

    public LoginControlador() throws URISyntaxException {
    }

    @FXML
    private void init(){
    }
    public void setMainApp(Main main){
        this.mainApp = main;
    }

    @FXML
    public void login() throws ExcepcionBocateria, SQLException {
        if(usuario.getText().equals("") || pwd.getText().equals(""))

        usuarioVO.setUsuario(usuario.getText());
        usuarioVO.setContrasena(pwd.getText());
        System.out.println("Usuario cogido de los labels= "+ usuarioVO.toString());

        if(mainApp.getModel().usuarioLogueado(usuarioVO)!=null){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ACCESO CONCEDIDO");
            alert.setHeaderText("Acceso concedido");
            alert.setContentText("Has acertado el usuario y la contraseña");
            alert.showAndWait();
            mainApp.getPrimaryStage().close();
            mainApp.initVistaPrincipal();
        }else{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("ACCESO NO PERMITIDO");
            alert.setHeaderText("Error");
            alert.setContentText("Usuario o contraseña incorrectos");
            alert.showAndWait();
        }
    }

    @FXML
    public void handleReg(){
     /*   try {
            desktop.browse(uri);
        } catch (IOException e){
            e.printStackTrace();
        }*/


     /*   final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
        if (file != null) {
            openFile(file);
        }*/
        mainApp.initRegistro();
//        mainApp.initRegistroSandwich();
    }

    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(
                    LoginControlador.class.getName()).log(
                    Level.SEVERE, null, ex
            );
        }
    }

/*
    public void showRegister() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();

            loader.setLocation(VistaRegistroControlador.class.getResource("VistaRegistro.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Edit Person");
            dialogStage.initModality(Modality.WINDOW_MODAL);
//            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);

            // Set the usuarioVO into the controller.
            VistaRegistroControlador controller = loader.getController();
            controller.setDialogStage(dialogStage);

            // Show the dialog and wait until the user closes it
            dialogStage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
}
