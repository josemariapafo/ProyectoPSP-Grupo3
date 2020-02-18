package bocateria.exepcion;

import javafx.scene.control.Alert;

public class Alertas {
    private static Alert alert;

    // Alerta de INFORMACIÓN a la que sólo le pasamos el mensaje
    public void info(String msg) {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText("INFORMACION");
        alert.setContentText(msg.toUpperCase());
        alert.showAndWait();
    }

    // Alerta de INFORMACIÓN a la que le pasamos la cabecera y el mensaje
    public void info(String header, String msg) {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Información");
        alert.setHeaderText("INFORMACION");
        alert.setContentText(msg.toUpperCase());
        alert.showAndWait();

    }

    // Alerta de INFORMACIÓN a la que le pasamos todos los parametros
    public void info(String title, String header, String msg) {
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(msg.toUpperCase());
        alert.showAndWait();
    }

    // Alerta de CONFIRMACIÓN a la que sólo le pasamos el mensaje
    public void confirmacion(String msg) {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText("CONFIRMADO");
        alert.setContentText(msg.toUpperCase());
        alert.showAndWait();
    }

    // Alerta de CONFIRMACIÓN a la que le pasamos la cabecera y el mensaje
    public void confirmacion(String header, String msg) {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText("CONFIRMADO");
        alert.setContentText(msg.toUpperCase());
        alert.showAndWait();
    }

    // Alerta de CONFIRMACIÓN a la que le pasamos todos los parametros
    public void confirmacion(String title, String header, String msg) {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmación");
        alert.setHeaderText("CONFIRMADO");
        alert.setContentText(msg.toUpperCase());
        alert.showAndWait();
    }

    // Alerta de AVISO a la que sólo le pasamos el mensaje
    public void aviso(String msg) {
        alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText("AVISO");
        alert.setContentText(msg.toUpperCase());
        alert.showAndWait();
    }

    // Alerta de AVISO a la que le pasamos la cabecera y el mensaje
    public void aviso(String header, String msg) {
        alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText("AVISO");
        alert.setContentText(msg.toUpperCase());
        alert.showAndWait();
    }

    // Alerta de AVISO a la que le pasamos todos los parametros
    public void aviso(String title, String header, String msg) {
        alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Aviso");
        alert.setHeaderText("AVISO");
        alert.setContentText(msg.toUpperCase());
        alert.showAndWait();
    }

    // Alerta de ERROR a la que sólo le pasamos el mensaje
    public void error(String msg) {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("ERROR");
        alert.setContentText(msg.toUpperCase());
        alert.showAndWait();
    }

    // Alerta de ERROR a la que le pasamos la cabecera y el mensaje
    public void error(String header, String msg) {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("ERROR");
        alert.setContentText(msg.toUpperCase());
        alert.showAndWait();
    }

    // Alerta de ERROR a la que le pasamos todos los parametros
    public void error(String title, String header, String msg) {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("ERROR");
        alert.setContentText(msg.toUpperCase());
        alert.showAndWait();
    }

    // Alerta personalizada
    public void custom(int type, String title, String header, String msg) {
        switch (type) {
            case 1:
                alert = new Alert(Alert.AlertType.INFORMATION);
                break;
            case 2:
                alert = new Alert(Alert.AlertType.WARNING);
                break;
            case 3:
                alert = new Alert(Alert.AlertType.CONFIRMATION);
                break;
            case 4:
                alert = new Alert(Alert.AlertType.ERROR);
                break;
            default:
                alert = new Alert(Alert.AlertType.NONE);
                break;
        }
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(msg.toUpperCase());
        alert.showAndWait();
    }

    // Nos sirve para poder realizar una acción en base a la respuesta generada por el usuario en la ventana de la alerta
    // (Sabremos que botón de la alerta ha pulsado)
    public Alert getAlert() {
        return alert;
    }
}
