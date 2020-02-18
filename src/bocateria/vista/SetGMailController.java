package bocateria.vista;

import bocateria.Main;
import bocateria.modelo.Model;
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

    private Main main;
    private Model model;
    private UsuarioVO user;

    public void setMain (Main main, UsuarioVO user){
        this.main = main;
        this.model = main.getModel();
        this.user = user;
    }

    // Acción que se realiza al pulsar sobre el checkbox de la VistaSetGMail
    @FXML
    public void checkSameMail(){
        if(sameMail.isSelected()){ // Si seleccionada
            // if(checkIfGmail(regMail.getText()))
            gMailValue.setText(regMail.getText()); // Da el mismo valor de la etiqueta regMail a la casila gMailValue
            gMailValue.setDisable(true); // Deshabilita la casilla gMailValue
        } else{ // Si deseleccionada
            gMailValue.setText(""); // Ponemos el campo vacio
            gMailValue.setDisable(false); // Habilitamos el campo gMailValue para poder escribir en el
        }

    }
    @FXML
    public void acceptChanges(){

    }
    @FXML
    public void cancelChanges(){
        gMailValue.setText("");
        gPwdValue.setText("");
        dialogStage.close();
    }
}
