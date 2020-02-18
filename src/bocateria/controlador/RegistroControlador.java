
package bocateria.controlador;

import bocateria.Main;
import bocateria.exepcion.Alertas;
import bocateria.exepcion.ExcepcionBocateria;
import bocateria.modelo.util.Comprueba;
import bocateria.modelo.vo.UsuarioVO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class RegistroControlador {

    private Stage dialogStage;
    private Main main;
    private Alertas alerta;

    @FXML
    TextField usuario;
    @FXML
    PasswordField pwd;
    @FXML
    PasswordField pwd2;
    @FXML
    TextField nombre;
    @FXML
    TextField apellido;
    @FXML
    TextField direccion;
    @FXML
    TextField localidad;
    @FXML
    TextField telefono;
    @FXML
    TextField email;
    @FXML
    CheckBox admin;

    @FXML
    public void handleAceptar() throws ExcepcionBocateria, SQLException {
        UsuarioVO u = null;
        boolean p, e;

        p = Comprueba.pwd(pwd.getText(),pwd2.getText());
        e = Comprueba.email(email.getText());
        if(!p)
            alerta.error("Las contraseñas no coinciden");
        if(!e)
            alerta.error("E-Mail no válido");
        if (p && e) {
            u = new UsuarioVO(nombre.getText(), apellido.getText(), email.getText(), usuario.getText(), pwd.getText(), direccion.getText(), localidad.getText(), telefono.getText());
        }

        if (u != null) {
            if (main.getModel().altaUsuario(u)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alta realizada con éxito");
                alert.setHeaderText("Alta realizada con éxito");
                alert.setContentText("SE REALIZÓ EL ALTA");
                alert.showAndWait();
                dialogStage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("NO SE REALIZÓ EL ALTA");
                alert.setHeaderText("ESE NOMBRE DE USUARIO YA EXISTE");
                alert.setContentText("EL NOMBRE DE USUARIO YA EXISTE");
                alert.showAndWait();
            }
        }
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public Stage getStage(){
        return dialogStage;
    }

    @FXML
    public void handleBorrar() {
        usuario.setText("");
        pwd.setText("");
        pwd2.setText("");
        nombre.setText("");
        apellido.setText("");
        direccion.setText("");
        localidad.setText("");
        telefono.setText("");
        email.setText("");
    }

    public void setMainApp(Main main) {
        this.main = main;
        this.alerta = main.getAlerta();
    }


    // SE CAMBIARON A UNA CLASE LLAMADA COMPRUEBA
    /*private boolean compruebaPwd() {
        return pwd.getText().equals(pwd2.getText());
    }

    private static boolean compruebaEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null){
            Alertas.alertaError("E-Mail VACIO");
            return false;
        }
        return pat.matcher(email).matches();
    } */
}
