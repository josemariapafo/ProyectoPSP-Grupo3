package bocateria.controlador;

import bocateria.Main;
import bocateria.exepcion.Alertas;
import bocateria.exepcion.ExcepcionBocateria;
import bocateria.modelo.vo.ProductoVO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class RegisterSandwichController {
    Main mainApp;
    private Stage dialogStage;

    @FXML
    TextField nombre;

    @FXML
    TextField precio;

    @FXML
    TextField rutaImg;

    @FXML
    TextArea descripcion;

    @FXML
    TextField stock;

    @FXML
    public void handleImagen() {
        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(dialogStage);
        if (file != null)
            rutaImg.setText(file.getAbsolutePath());
    }

    @FXML
    public void handleEnviar() {
        ProductoVO p = new ProductoVO();
        if (!checkDatos())
            Alertas.alertaAviso("Rellene todos los campos");
        else {
            p.setNombre(nombre.getText());
            p.setDescripcion(descripcion.getText());
            p.setStock(Integer.parseInt(stock.getText()));
            p.setRutaImg(rutaImg.getText());
            p.setPrecio(Double.valueOf(precio.getText()));
            if (mainApp.getModel().altaProducto(p)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Alta realizada con éxito");
                alert.setHeaderText("Alta realizada con éxito");
                alert.setContentText("SE REALIZÓ EL ALTA");
                alert.showAndWait();
                dialogStage.close();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("NO SE REALIZÓ EL ALTA");
                alert.setHeaderText("NO SE REALIZÓ EL ALTA");
                alert.setContentText("NO SE REALIZÓ EL ALTA");
                alert.showAndWait();
            }
        }
    }

    boolean checkDatos() {
        return checkNombre() && checkPrecio() && checkDescripcion() && checkStock() && checkRutaImg();
    }

    boolean checkRutaImg() {
        if (rutaImg.getText().equals("")) {
            Alertas.alertaAviso("Elige una imagen");
            return false;
        }
        return true;
    }

    boolean checkStock() {
        if (stock.getText().equals("")) {
            Alertas.alertaAviso("El campo precio está vacio");
            return false;
        } else {
            try {
                int i = Integer.parseInt(stock.getText());
            } catch (NumberFormatException e) {
                Alertas.alertaAviso("El precio no es un número");
                return false;
            }
            return true;
        }

    }

    boolean checkNombre() {
        if (nombre.getText().equals("")) {
            Alertas.alertaAviso("El nombre debe contener texto");
            return false;
        }
        if (nombre.getText().length() > 30) {
            Alertas.alertaAviso("El nombre debe contener 30 o menos caracteres");
            return false;
        }
        return true;

    }

    boolean checkDescripcion() {

        if (descripcion.getText().equals("")) {
            Alertas.alertaAviso("La descripcion debe contener texto");
            return false;
        }
        if (descripcion.getText().length() > 30) {
            Alertas.alertaAviso("La descripcion debe contener 30 o menos caracteres");
            return false;
        }
        return true;
    }

    boolean checkPrecio() {
        if (precio.getText().equals("")) {
            Alertas.alertaAviso("El campo precio está vacio");
            return false;
        } else {
            try {
                Double d = Double.parseDouble(precio.getText());
            } catch (NumberFormatException e) {
                Alertas.alertaAviso("El precio no es un número");
                return false;
            }
            return true;
        }
    }

    @FXML
    public void handleBorrar() {
        nombre.setText("");
        descripcion.setText("");
        rutaImg.setText("");
        precio.setText("");
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setMainApp(Main main) {
        this.mainApp = main;
    }
}
