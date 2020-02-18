package bocateria.vista;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class WriteMailController {
    @FXML
    Button send;
    @FXML
    Button cancel;
    @FXML
    Button erase;


    private String server = "smtp.gmail.com";
    private String username = "scuesta.test@gmail.com";
    private String password = "testpwd1";
    private int puerto = 587;
    private String remitente = "scuesta.test@gmail.com";
    private String destino = "sebastian.cuesta.molto@gmail.com";
    private String asunto = "Prueba 3 de SMTPClient con GMAIL y SIN TLS";
    private String mensaje = "Este mensaje no va con TLS.\n\nEste es el tercer mensaje que envio a mi cuenta de correo personal.\nA través de un programa en Java.\nUn Saludo!";
    private String resp = "";

    @FXML
    public void initialize() {

    }
}
