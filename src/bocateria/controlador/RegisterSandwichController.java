package bocateria.controlador;

import bocateria.Main;
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
    public void handleImagen() {
        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(dialogStage);
        if (file != null)
            rutaImg.setText(file.getAbsolutePath());
    }

    @FXML
    public void handleEnviar() throws ExcepcionBocateria {
        System.out.println("HAGO CLICK INICIO METODO");
        ProductoVO p = new ProductoVO();
        p.setNombre(nombre.getText());
        p.setDescripcion(descripcion.getText());
        p.setRutaImg(rutaImg.getText());
        p.setPrecio(Double.valueOf(precio.getText()));
        if (mainApp.getModel().altaProducto(p)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alta realizada con éxito");
            alert.setHeaderText("Alta realizada con éxito");
            alert.setContentText("SE REALIZÓ EL ALTA");
            alert.showAndWait();

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("NO SE REALIZÓ EL ALTA");
            alert.setHeaderText("NO SE REALIZÓ EL ALTA");
            alert.setContentText("NO SE REALIZÓ EL ALTA");
            alert.showAndWait();

        }
        System.out.println("HAGO CLICK FIN METODO");
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
