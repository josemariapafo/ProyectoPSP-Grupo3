package bocateria.controlador;

import bocateria.Main;
import bocateria.exepcion.Alertas;
import bocateria.modelo.Model;
import bocateria.modelo.util.Comprueba;
import bocateria.modelo.vo.ProductoVO;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class RegisterSandwichController {
    private Main mainApp;
    private Model modelo;
    private Alertas alerta;
    private Stage dialogStage;

    /*VpalController controller;*/

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
            alerta.aviso("Rellene todos los campos");
        else {
            p.setNombre(nombre.getText());
            p.setDescripcion(descripcion.getText());
            p.setStock(Integer.parseInt(stock.getText()));
            p.setRutaImg(rutaImg.getText());
            p.setPrecio(Double.valueOf(precio.getText()));
            if (modelo.altaProducto(p)) {
                alerta.info("Alta realizada con éxito","Alta realizada con éxito","SE REALIZÓ EL ALTA");
                dialogStage.close();
                /*controller.setProductos();*/
            } else {
                alerta.error("NO SE REALIZÓ EL ALTA","NO SE REALIZÓ EL ALTA","NO SE REALIZÓ EL ALTA");
            }
        }
    }

    boolean checkDatos() {
        return checkNombre() && checkPrecio() && checkDescripcion() && checkStock() && checkRutaImg();
    }

    boolean checkRutaImg() {
        if (Comprueba.vacio(rutaImg.getText())) {
            alerta.aviso("Elige una imagen");
            return false;
        }
        return true;
    }

    boolean checkStock() {
        if (Comprueba.vacio(stock.getText())) {
            alerta.aviso("El campo precio está vacio");
            return false;
        } else {
            try {
                int i = Integer.parseInt(stock.getText());
            } catch (NumberFormatException e) {
                alerta.aviso("El precio no es un número");
                return false;
            }
            return true;
        }

    }

    boolean checkNombre() {
        if (Comprueba.vacio(nombre.getText())) {
            alerta.aviso("El nombre debe contener texto");
            return false;
        }
        if (Comprueba.longitud(nombre.getText(),30)) {
            alerta.aviso("El nombre debe contener 30 o menos caracteres");
            return false;
        }
        return true;

    }

    boolean checkDescripcion() {

        if (Comprueba.vacio(descripcion.getText())) {
            alerta.aviso("La descripcion debe contener texto");
            return false;
        }
        if (Comprueba.longitud(descripcion.getText(),30)){
            alerta.aviso("La descripcion debe contener 30 o menos caracteres");
            return false;
        }
        return true;
    }

    boolean checkPrecio() {
        if (Comprueba.vacio(precio.getText())) {
            alerta.aviso("El campo precio está vacio");
            return false;
        } else {
            try {
                Double d = Double.parseDouble(precio.getText());
            } catch (NumberFormatException e) {
                alerta.aviso("El precio no es un número");
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
        this.alerta = main.getAlerta();
        this.modelo = main.getModel();
    }

   /*public VpalController getController() {
        return controller;
    }

    public void setController(VpalController controller) {
        this.controller = controller;
    }*/
}
