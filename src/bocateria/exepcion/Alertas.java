package bocateria.exepcion;

import javafx.scene.control.Alert;

public class Alertas {

    public static void alertaInfo(String msg){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText("INFORMACION");
        alert.setContentText(msg.toUpperCase());
        alert.showAndWait();
    }
    public static void alertaConfirmacion(String msg){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText("CONFIRMADO");
        alert.setContentText(msg.toUpperCase());
        alert.showAndWait();
    }

    public static void alertaAviso(String msg){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Aviso");
            alert.setHeaderText("AVISO");
            alert.setContentText(msg.toUpperCase());
            alert.showAndWait();
    }

    public static void alertaError(String msg){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("ERROR");
        alert.setContentText(msg.toUpperCase());
        alert.showAndWait();
    }
}
