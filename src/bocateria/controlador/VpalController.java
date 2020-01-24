package bocateria.controlador;

import bocateria.Main;
import javafx.fxml.FXML;

public class VpalController {
    private Main mainApp;

    public void setMainApp(Main main){
        this.mainApp = main;
    }

    @FXML
    public void pulsarBotonComprar(){
        try {
            mainApp.initVistaCartera();
        }catch(Exception e){
            System.out.println("Error al abrir el carrito");
        }
    }
}
