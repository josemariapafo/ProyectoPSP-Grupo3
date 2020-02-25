package bocateria.controlador;

import bocateria.Main;
import bocateria.exepcion.Alertas;
import bocateria.modelo.Model;
import bocateria.modelo.util.Comprueba;
import bocateria.modelo.vo.MailVO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.spec.InvalidKeySpecException;

public class WriteMailController {
    @FXML
    TextField txDest;
    @FXML
    TextField txAsunto;
    @FXML
    TextArea txMsj;
    @FXML
    Button send;
    @FXML
    Button cancel;
    @FXML
    Button erase;
    private Alertas alerta;
    private Main main;
    private Model modelo;
    private Stage dialogStage;
    public void setMain(Main main){
        this.main = main;
        this.modelo = main.getModel();
        this.alerta = main.getAlerta();
    }
    public void setDialogStage(Stage dialogStage){
        this.dialogStage = dialogStage;
    }
    @FXML
    public void initialize() {

    }

    @FXML
    public void handleSend() throws UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyStoreException, InvalidKeyException, InvalidKeySpecException {
        boolean error = false;
        if(Comprueba.vacio(txDest.getText())){
            alerta.aviso("CAMPO DESTINATARIO VACIO");
            error = true;
        } else {
            if(!Comprueba.email(txDest.getText())){
                alerta.error("EMAIL INVÁLIDO");
                error = true;
            }
        }
        if(Comprueba.vacio(txAsunto.getText()))
            alerta.info("El asunto está vacio, se enviará sin asunto");
        if(Comprueba.vacio(txMsj.getText())){
            error = true;
            alerta.info("Envíe algún mensaje");
        }
        if(!error){
            modelo.sendMail(new MailVO(txDest.getText(),txAsunto.getText(),txMsj.getText()));
            main.getAlerta().confirmacion("Correo envíado con éxito a "+txDest.getText());
            dialogStage.close();
        }
    }
    @FXML
    public void handleCancel(){
        dialogStage.close();
        main.initVistaPrincipal();
    }
    @FXML
    public void handleErase(){
        txDest.setText("");
        txAsunto.setText("");
        txMsj.setText("");
    }
}
