
package bocateria.controlador;

import bocateria.Main;
import bocateria.exepcion.ExcepcionBocateria;
import bocateria.modelo.vo.UsuarioVO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.regex.Pattern;

public class RegistroControlador {

    private Stage dialogStage;
    private Main main;

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
    public void handleAceptar() throws ExcepcionBocateria {
        UsuarioVO u = null;
        if(compruebaPwd() && compruebaEmail(email.getText())) {
            u = new UsuarioVO(nombre.getText(),apellido.getText(),email.getText(),usuario.getText(),pwd.getText(),direccion.getText(),localidad.getText(),telefono.getText());
        }
        if (main.getModel().altaUsuario(u)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alta realizada con éxito");
            alert.setHeaderText("Alta realizada con éxito");
            alert.setContentText("SE REALIZÓ EL ALTA");
            alert.showAndWait();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NO SE REALIZÓ EL ALTA");
            alert.setHeaderText("ESE NOMBRE DE USUARIO YA EXISTE");
            alert.setContentText("EL NOMBRE DE USUARIO YA EXISTE");
            alert.showAndWait();

        }
    }
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    public void handleBorrar(){
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
    }

    private boolean compruebaPwd(){
        return pwd.getText().equals(pwd2.getText());
    }

    public static boolean compruebaEmail(String email)
    {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
}
