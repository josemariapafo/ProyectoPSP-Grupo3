package bocateria.vista;

import bocateria.Main;
import bocateria.exepcion.Alertas;
import bocateria.modelo.Model;
import bocateria.modelo.util.Comprueba;
import bocateria.modelo.vo.UsuarioGMail;
import bocateria.modelo.vo.UsuarioVO;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SetGMailController {
    @FXML
    private Stage dialogStage;
    @FXML
    CheckBox sameMail;
    @FXML
    Label regMail;
    @FXML
    TextField gMailValue;
    @FXML
    TextField gPwdValue;

    private Alertas alert = new Alertas();
    private Main main;
    private Model model;
    private UsuarioVO usuario;

    @FXML
    public void initialize() {
        sameMail.setDisable(false);
    }

    public void setMain(Main main) {
        this.main = main;
        this.model = main.getModel();
        this.usuario = main.getUsuario(); // Usuario logueado en nuestra aplicación
        /*
        regMail.setText(this.usuario.getEmail());
        if(!Comprueba.gmail(regMail.getText())){
            sameMail.setDisable(true);
        }*/
    }

    // Acción que se realiza al pulsar sobre el checkbox de la VistaSetGMail
    @FXML
    public void checkSameMail() {
        if (sameMail.isSelected()) { // Si seleccionada
            if (Comprueba.gmail(regMail.getText())) {
                gMailValue.setText(regMail.getText()); // Da el mismo valor de la etiqueta regMail a la casila gMailValue
                gMailValue.setDisable(true); // Deshabilita la casilla gMailValue
            } else {
                alert.aviso("Su correo no es de G-Mail, por favor use uno para poder usar este servicio");
            }
        } else { // Si deseleccionada
            gMailValue.setText(""); // Ponemos el campo vacio
            gMailValue.setDisable(false); // Habilitamos el campo gMailValue para poder escribir en el
        }

    }

    @FXML
    public void acceptChanges() {
        if (Comprueba.email(gMailValue.getText()) && Comprueba.gmail(gMailValue.getText())) {
            if (gPwdValue.getText() != null) {
                usuario.setgMailVal(gMailValue.getText());
                usuario.setgPwdVal(gPwdValue.getText());
                usuario.setGmailSetted(true);
                UsuarioGMail user = new UsuarioGMail(usuario.getUsuario(), gMailValue.getText(), gPwdValue.getText());
            }
        }
//        model.setGMail();
    }

    @FXML
    public void cancelChanges() {
        gMailValue.setText("");
        gPwdValue.setText("");
        dialogStage.close();
    }
}
