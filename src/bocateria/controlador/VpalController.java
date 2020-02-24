package bocateria.controlador;

import bocateria.Main;
import bocateria.exepcion.Alertas;
import bocateria.exepcion.ExcepcionBocateria;
import bocateria.modelo.Model;
import bocateria.modelo.vo.ProductoVO;
import bocateria.modelo.vo.UsuarioVO;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class VpalController {
    private List<ProductoVO> productos = new ArrayList<>();
    private Main mainApp;
    private Model modelo;
    private Alertas alerta;
    private UsuarioVO usuario;
    private int indexActual = 0;
    private int indexInicial;

    private ProductoVO p;

    @FXML
    private Button regSandwich;

    @FXML
    private Button clienteFTP;
    @FXML
    private Button atras;
    @FXML
    private Button delante;
    @FXML
    private Button comprar;

    @FXML
    private Button consultarComandas;
    @FXML
    private Button chatear;

    @FXML
    Button sendMail;
    @FXML
    VBox v00;
    @FXML
    VBox v01;
    @FXML
    VBox v02;
    @FXML
    VBox v03;
    @FXML
    VBox v04;
    @FXML
    VBox v10;
    @FXML
    VBox v11;
    @FXML
    VBox v12;
    @FXML
    VBox v13;
    @FXML
    VBox v14;
    @FXML
    HBox h00;
    @FXML
    HBox h01;
    @FXML
    HBox h02;
    @FXML
    HBox h03;
    @FXML
    HBox h04;
    @FXML
    HBox h10;
    @FXML
    HBox h11;
    @FXML
    HBox h12;
    @FXML
    HBox h13;
    @FXML
    HBox h14;
    @FXML
    HBox hBot;
    @FXML
    Label noStock00;
    @FXML
    Label noStock01;
    @FXML
    Label noStock02;
    @FXML
    Label noStock03;
    @FXML
    Label noStock04;
    @FXML
    Label noStock10;
    @FXML
    Label noStock11;
    @FXML
    Label noStock12;
    @FXML
    Label noStock13;
    @FXML
    Label noStock14;
    @FXML
    ImageView img00;
    @FXML
    ImageView img01;
    @FXML
    ImageView img02;
    @FXML
    ImageView img03;
    @FXML
    ImageView img04;
    @FXML
    ImageView img10;
    @FXML
    ImageView img11;
    @FXML
    ImageView img12;
    @FXML
    ImageView img13;
    @FXML
    ImageView img14;
    @FXML
    Label nombre00, precio00, descripcion00, cantidad00;
    @FXML
    Label nombre01, precio01, descripcion01, cantidad01;
    @FXML
    Label nombre02, precio02, descripcion02, cantidad02;
    @FXML
    Label nombre03, precio03, descripcion03, cantidad03;
    @FXML
    Label nombre04, precio04, descripcion04, cantidad04;
    @FXML
    Label nombre10, precio10, descripcion10, cantidad10;
    @FXML
    Label nombre11, precio11, descripcion11, cantidad11;
    @FXML
    Label nombre12, precio12, descripcion12, cantidad12;
    @FXML
    Label nombre13, precio13, descripcion13, cantidad13;
    @FXML
    Label nombre14, precio14, descripcion14, cantidad14;


    public void setMainApp(Main main) {
        this.mainApp = main;
        this.modelo = main.getModel();
        this.alerta = main.getAlerta();
    }

    private List<ProductoVO> getProductos() {
        return productos;
    }


    public void load() throws ExcepcionBocateria, SQLException {
        if (modelo.compruebaAdmin(usuario))
            loadAdminUI();
        if(getIndexActual() == 0)
            atras.setDisable(true);
        else
            atras.setDisable(false);
        setIndexInicial(getIndexActual());
        despejaPanel();
        if (getProductos().size() < 11)
            hBot.setVisible(false);
        for (int panel = 0; panel < 10 && (getIndexActual() < getProductos().size()); panel++) {
            loadPanel(panel);
            setIndexActual(getIndexActual() + 1);
        }
    }

    /**
     * Carga la siguiente página de productos en la vista principal
     * @throws ExcepcionBocateria
     * @throws SQLException
     */
    @FXML
    public void loadUI() throws ExcepcionBocateria, SQLException {
        if(getIndexActual() == 0)
            atras.setDisable(true);
        else
            atras.setDisable(false);
        if(getIndexActual()==getProductos().size()-1)
            delante.setDisable(true);
        else
            delante.setDisable(false);
        setIndexInicial(getIndexActual());
        despejaPanel();
        for (int panel = 0; panel < 10 && (getIndexActual() < getProductos().size()); panel++) {
            loadPanel(panel);
            setIndexActual(getIndexActual() + 1);
        }
    }

    @FXML
    public void loadUIBack() throws ExcepcionBocateria, SQLException {
        /*despejaPanel();

        for (int panel = 9; panel > -1 && (getIndexActual() > -1); panel++) {
            loadPanel(panel);
            setIndexActual(getIndexActual() - 1);
        }
        setIndexInicial(getIndexActual());*/
        setIndexActual(getIndexActual() - 11);
        setIndexInicial(getIndexActual());
        loadUI();
    }


    private void loadAdminUI() {
        regSandwich.setVisible(true);
        regSandwich.setDisable(false);
        sendMail.setVisible(true);
        sendMail.setDisable(false);
        clienteFTP.setDisable(false);
        clienteFTP.setVisible(true);
        consultarComandas.setDisable(false);
        consultarComandas.setVisible(true);
    }

    public void setProductos() {
        this.productos = modelo.obtenerTodosProductos();
    }

    private void despejaPanel() {
        v00.setVisible(false);
        v01.setVisible(false);
        v02.setVisible(false);
        v03.setVisible(false);
        v04.setVisible(false);
        v10.setVisible(false);
        v11.setVisible(false);
        v12.setVisible(false);
        v13.setVisible(false);
        v14.setVisible(false);
    }

    @FXML
    public void pulsarBotonComprar() {
        try {
            if (listaCompra().size() != 0) {
                mainApp.getCarritoData().clear();
                mainApp.getCarritoData().addAll(listaCompra());
                mainApp.initVistaCarrito();
            } else
                alerta.aviso("Seleccione algún producto");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error al abrir el carrito");
        }
    }
    @FXML
    public void pulsarBotonConsultarComandas(){
        mainApp.initVistaComanda();
    }
    @FXML
    public void pulsarBotonChatear(){
            mainApp.initChat();
    }

    @FXML
    public void handleEnviarCorreo() {
        mainApp.initEnviaCorreo();
    }

    @FXML
    public void initRegistrarSandwich() {
        mainApp.initRegistroSandwich(/*this*/);
    }

    @FXML
    void botonSuma00() {
        p = getProductos().get(getIndexInicial());
        if (p.getCantidad() < p.getStock()) {
            cantidad00.setText("" + (Integer.parseInt(cantidad00.getText()) + 1));
            p.setCantidad(p.getCantidad() + 1);
            System.out.println(p);
        }
    }

    @FXML
    void botonSuma01() {
        p = getProductos().get(getIndexInicial() + 1);
        if (p.getCantidad() < p.getStock()) {
            cantidad01.setText("" + (Integer.parseInt(cantidad01.getText()) + 1));
            p.setCantidad(p.getCantidad() + 1);
            System.out.println(p);
        }
    }

    @FXML
    void botonSuma02() {
        p = getProductos().get(getIndexInicial() + 2);
        if (p.getCantidad() < p.getStock()) {
            cantidad02.setText("" + (Integer.parseInt(cantidad02.getText()) + 1));
            p.setCantidad(p.getCantidad() + 1);
            System.out.println(p);
        }
    }

    @FXML
    void botonSuma03() {
        p = getProductos().get(getIndexInicial() + 3);
        if (p.getCantidad() < p.getStock()) {
            cantidad03.setText("" + (Integer.parseInt(cantidad03.getText()) + 1));
            p.setCantidad(p.getCantidad() + 1);
            System.out.println(p);
        }
    }

    @FXML
    void botonSuma04() {
        p = getProductos().get(getIndexInicial() + 4);
        if (p.getCantidad() < p.getStock()) {
            cantidad04.setText("" + (Integer.parseInt(cantidad04.getText()) + 1));
            p.setCantidad(p.getCantidad() + 1);
            System.out.println(p);
        }
    }

    @FXML
    void botonSuma10() {
        p = getProductos().get(getIndexInicial() + 5);
        if (p.getCantidad() < p.getStock()) {
            cantidad10.setText("" + (Integer.parseInt(cantidad10.getText()) + 1));
            p.setCantidad(p.getCantidad() + 1);
            System.out.println(p);
        }
    }

    @FXML
    void botonSuma11() {
        p = getProductos().get(getIndexInicial() + 6);
        if (p.getCantidad() < p.getStock()) {
            cantidad11.setText("" + (Integer.parseInt(cantidad11.getText()) + 1));
            p.setCantidad(p.getCantidad() + 1);
            System.out.println(p);
        }
    }

    @FXML
    void botonSuma12() {
        p = getProductos().get(getIndexInicial() + 7);
        if (p.getCantidad() < p.getStock()) {
            cantidad12.setText("" + (Integer.parseInt(cantidad12.getText()) + 1));
            p.setCantidad(p.getCantidad() + 1);
            System.out.println(p);
        }
    }

    @FXML
    void botonSuma13() {
        p = getProductos().get(getIndexInicial() + 8);
        if (p.getCantidad() < p.getStock()) {
            cantidad13.setText("" + (Integer.parseInt(cantidad13.getText()) + 1));
            p.setCantidad(p.getCantidad() + 1);
            System.out.println(p);
        }
    }

    @FXML
    void botonSuma14() {
        p = getProductos().get(getIndexInicial() + 9);
        if (p.getCantidad() < p.getStock()) {
            cantidad14.setText("" + (Integer.parseInt(cantidad14.getText()) + 1));
            p.setCantidad(p.getCantidad() + 1);
            System.out.println(p);
        }
    }

    @FXML
    void botonResta00() {
        p = getProductos().get(getIndexInicial());
        if (p.getCantidad() > 0) {
            cantidad00.setText("" + (Integer.parseInt(cantidad00.getText()) - 1));
            p.setCantidad(p.getCantidad() - 1);
            System.out.println(p);
        }
    }

    @FXML
    void botonResta01() {
        p = getProductos().get(getIndexInicial() + 1);
        if (p.getCantidad() > 0) {
            cantidad01.setText("" + (Integer.parseInt(cantidad01.getText()) - 1));
            p.setCantidad(p.getCantidad() - 1);
            System.out.println(p);
        }
    }

    @FXML
    void botonResta02() {
        p = getProductos().get(getIndexInicial() + 2);
        if (p.getCantidad() > 0) {
            cantidad02.setText("" + (Integer.parseInt(cantidad02.getText()) - 1));
            p.setCantidad(p.getCantidad() - 1);
            System.out.println(p);
        }
    }

    @FXML
    void botonResta03() {
        p = getProductos().get(getIndexInicial() + 3);
        if (p.getCantidad() > 0) {
            cantidad03.setText("" + (Integer.parseInt(cantidad03.getText()) - 1));
            p.setCantidad(p.getCantidad() - 1);
            System.out.println(p);
        }
    }

    @FXML
    void botonResta04() {
        p = getProductos().get(getIndexInicial() + 4);
        if (p.getCantidad() > 0) {
            cantidad04.setText("" + (Integer.parseInt(cantidad04.getText()) - 1));
            p.setCantidad(p.getCantidad() - 1);
            System.out.println(p);
        }
    }

    @FXML
    void botonResta10() {
        p = getProductos().get(getIndexInicial() + 5);
        if (p.getCantidad() > 0) {
            cantidad10.setText("" + (Integer.parseInt(cantidad10.getText()) - 1));
            p.setCantidad(p.getCantidad() - 1);
            System.out.println(p);
        }
    }

    @FXML
    void botonResta11() {
        p = getProductos().get(getIndexInicial() + 6);
        if (p.getCantidad() > 0) {
            cantidad12.setText("" + (Integer.parseInt(cantidad11.getText()) - 1));
            p.setCantidad(p.getCantidad() - 1);
            System.out.println(p);
        }
    }

    @FXML
    void botonResta12() {
        p = getProductos().get(getIndexInicial() + 7);
        if (p.getCantidad() > 0) {
            cantidad12.setText("" + (Integer.parseInt(cantidad12.getText()) - 1));
            p.setCantidad(p.getCantidad() - 1);
            System.out.println(p);
        }
    }

    @FXML
    void botonResta13() {
        p = getProductos().get(getIndexInicial() + 8);
        if (p.getCantidad() > 0) {
            cantidad13.setText("" + (Integer.parseInt(cantidad13.getText()) - 1));
            p.setCantidad(p.getCantidad() - 1);
            System.out.println(p);
        }
    }

    @FXML
    void botonResta14() {
        p = getProductos().get(getIndexInicial() + 9);
        if (p.getCantidad() > 0) {
            cantidad14.setText("" + (Integer.parseInt(cantidad14.getText()) - 1));
            p.setCantidad(p.getCantidad() - 1);
            System.out.println(p);
        }
    }


    private List<ProductoVO> listaCompra() {
        List<ProductoVO> listaCompra = new ArrayList<>();
        System.out.println("Lista de la compra");
        for (ProductoVO p : getProductos()) {
            if (p.getCantidad() > 0) {
                p.setCantidadProperty(p.getCantidad());
                p.setNombreProperty(p.getNombre());
                listaCompra.add(p);
                System.out.println(p);
            }
        }
        return listaCompra;
    }

    @FXML
    private void abrirClienteFTP() {
        mainApp.initFTPClient();
        try {
            clienteFTPBasico cliente = new clienteFTPBasico();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al abrir el cliente ftp");
        }
    }

    private void loadPanel(int n) {
        List<ProductoVO> list = getProductos();
        switch (n) {
            case 0:
                v00.setVisible(true);
                img00.setImage(list.get(getIndexActual()).getFoto());
                nombre00.setText(list.get(getIndexActual()).getNombre());
                precio00.setText(String.valueOf(list.get(getIndexActual()).getPrecio()));
                descripcion00.setText(list.get(getIndexActual()).getDescripcion());
                if (list.get(getIndexActual()).getStock() == 0) {
                    h00.setVisible(false);
                    noStock00.setVisible(true);
                } else {
                    h00.setVisible(true);
                    noStock00.setVisible(false);
                }
                break;
            case 1:
                v01.setVisible(true);
                img01.setImage(list.get(getIndexActual()).getFoto());
                nombre01.setText(list.get(getIndexActual()).getNombre());
                precio01.setText(String.valueOf(list.get(getIndexActual()).getPrecio()));
                descripcion01.setText(list.get(getIndexActual()).getDescripcion());
                if (list.get(getIndexActual()).getStock() == 0) {
                    h01.setVisible(false);
                    noStock01.setVisible(true);
                } else {
                    h01.setVisible(true);
                    noStock01.setVisible(false);
                }
                break;
            case 2:
                v02.setVisible(true);
                img02.setImage(list.get(getIndexActual()).getFoto());
                nombre02.setText(list.get(getIndexActual()).getNombre());
                precio02.setText(String.valueOf(list.get(getIndexActual()).getPrecio()));
                descripcion02.setText(list.get(getIndexActual()).getDescripcion());
                if (list.get(getIndexActual()).getStock() == 0) {
                    h02.setVisible(false);
                    noStock02.setVisible(true);
                } else {
                    h02.setVisible(true);
                    noStock02.setVisible(false);
                }
                break;
            case 3:
                v03.setVisible(true);
                img03.setImage(list.get(getIndexActual()).getFoto());
                nombre03.setText(list.get(getIndexActual()).getNombre());
                precio03.setText(String.valueOf(list.get(getIndexActual()).getPrecio()));
                descripcion03.setText(list.get(getIndexActual()).getDescripcion());
                if (list.get(getIndexActual()).getStock() == 0) {
                    h03.setVisible(false);
                    noStock03.setVisible(true);
                } else {
                    h03.setVisible(true);
                    noStock03.setVisible(false);
                }
                break;
            case 4:
                v04.setVisible(true);
                img04.setImage(list.get(getIndexActual()).getFoto());
                nombre04.setText(list.get(getIndexActual()).getNombre());
                precio04.setText(String.valueOf(list.get(getIndexActual()).getPrecio()));
                descripcion04.setText(list.get(getIndexActual()).getDescripcion());
                if (list.get(getIndexActual()).getStock() == 0) {
                    h04.setVisible(false);
                    noStock04.setVisible(true);
                } else {
                    h04.setVisible(true);
                    noStock04.setVisible(false);
                }
                break;
            case 5:
                v10.setVisible(true);
                img10.setImage(list.get(getIndexActual()).getFoto());
                nombre10.setText(list.get(getIndexActual()).getNombre());
                precio10.setText(String.valueOf(list.get(getIndexActual()).getPrecio()));
                descripcion10.setText(list.get(getIndexActual()).getDescripcion());
                if (list.get(getIndexActual()).getStock() == 0) {
                    h10.setVisible(false);
                    noStock10.setVisible(true);
                } else {
                    h10.setVisible(true);
                    noStock10.setVisible(false);
                }
                break;
            case 6:
                v11.setVisible(true);
                img11.setImage(list.get(getIndexActual()).getFoto());
                nombre11.setText(list.get(getIndexActual()).getNombre());
                precio11.setText(String.valueOf(list.get(getIndexActual()).getPrecio()));
                descripcion11.setText(list.get(getIndexActual()).getDescripcion());
                if (list.get(getIndexActual()).getStock() == 0) {
                    h11.setVisible(false);
                    noStock11.setVisible(true);
                } else {
                    h11.setVisible(true);
                    noStock11.setVisible(false);
                }
                break;
            case 7:
                v12.setVisible(true);
                img12.setImage(list.get(getIndexActual()).getFoto());
                nombre12.setText(list.get(getIndexActual()).getNombre());
                precio12.setText(String.valueOf(list.get(getIndexActual()).getPrecio()));
                descripcion12.setText(list.get(getIndexActual()).getDescripcion());
                if (list.get(getIndexActual()).getStock() == 0) {
                    h12.setVisible(false);
                    noStock12.setVisible(true);
                } else {
                    h12.setVisible(true);
                    noStock12.setVisible(false);
                }
                break;
            case 8:
                v13.setVisible(true);
                img13.setImage(list.get(getIndexActual()).getFoto());
                nombre13.setText(list.get(getIndexActual()).getNombre());
                precio13.setText(String.valueOf(list.get(getIndexActual()).getPrecio()));
                descripcion13.setText(list.get(getIndexActual()).getDescripcion());
                if (list.get(getIndexActual()).getStock() == 0) {
                    h13.setVisible(false);
                    noStock13.setVisible(true);
                } else {
                    h13.setVisible(true);
                    noStock13.setVisible(false);
                }
                break;
            case 9:
                v14.setVisible(true);
                img14.setImage(list.get(getIndexActual()).getFoto());
                nombre14.setText(list.get(getIndexActual()).getNombre());
                precio14.setText(String.valueOf(list.get(getIndexActual()).getPrecio()));
                descripcion14.setText(list.get(getIndexActual()).getDescripcion());
                if (list.get(getIndexActual()).getStock() == 0) {
                    h14.setVisible(false);
                    noStock14.setVisible(true);
                } else {
                    h14.setVisible(true);
                    noStock14.setVisible(false);
                }
                break;
        }
    }

    public int getIndexActual() {
        return indexActual;
    }

    public void setIndexActual(int indexActual) {
        this.indexActual = indexActual;
    }

    public UsuarioVO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioVO usuario) {
        this.usuario = usuario;
    }

    public int getIndexInicial() {
        return indexInicial;
    }

    public void setIndexInicial(int indexInicial) {
        this.indexInicial = indexInicial;
    }
}
