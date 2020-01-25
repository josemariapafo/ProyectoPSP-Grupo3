package bocateria.controlador;

import bocateria.Main;
import javafx.fxml.FXML;

import java.awt.*;

public class VpalController {
    @FXML
    private Button comprar;

    private Main mainApp;

    public void setMainApp(Main main){
        this.mainApp = main;
    }

    @FXML
    public void pulsarBotonComprar(){
        try {
            mainApp.initVistaCarrito();
        }catch(Exception e){
            System.out.println("Error al abrir el carrito");
        }
    }
}
