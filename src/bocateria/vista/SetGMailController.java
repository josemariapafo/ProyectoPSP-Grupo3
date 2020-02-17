package bocateria.vista;

import bocateria.Main;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class SetGMailController {
    @FXML
    CheckBox sameMail;
    @FXML
    Label regMail;
    @FXML
    TextField gMailValue;
    @FXML
    TextField gPwdValue;

    private Main main;

    public void setMain (Main main){
        this.main = main;
    }

    public void checkSameMail(){
        if(sameMail.isSelected()){

            gMailValue.setText(regMail.getText());
            gMailValue.setDisable(true);
        } else{
            gMailValue.setText("");
            gMailValue.setDisable(false);
        }

    }
}
