package bocateria.controlador;

import bocateria.Main;
import bocateria.exepcion.Alertas;
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
    private Alertas alerta;
    private UsuarioVO usuarioVO = new UsuarioVO();
    private Main mainApp;
    private Desktop desktop = Desktop.getDesktop();
    private URI uri = new URI("www.geeksforgeeks.org");

    public LoginControlador() throws URISyntaxException {
    }

    @FXML
    private void initialize() {

    }

    public void setMainApp(Main main) {
        this.mainApp = main;
        this.alerta = main.getAlerta();
    }

    @FXML
    public void login() throws ExcepcionBocateria, SQLException {
        if (usuario.getText().equals("") || pwd.getText().equals(""))
            alerta.aviso("Algún campo está vacio");

        usuarioVO.setUsuario(usuario.getText());
        usuarioVO.setContrasena(pwd.getText());
        System.out.println("Usuario cogido de los labels= " + usuarioVO.toString());

        if (mainApp.getModel().usuarioLogueado(usuarioVO) != null) {
            mainApp.setUsuario(mainApp.getModel().obtenerUsuario(usuarioVO));
            mainApp.getPrimaryStage().close();
            mainApp.initVistaPrincipal();
        } else {
            mainApp.getAlerta().info("ACCESO NO PERMITIDO","Error","Usuario o contraseña incorrectos");
        }
    }

    @FXML
    public void handleReg() {
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

    /**
     * Fue un método usado de prueba
     * @param file
     */
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
}
