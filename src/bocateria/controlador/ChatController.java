package bocateria.controlador;

import bocateria.Main;
import bocateria.modelo.Model;
import bocateria.modelo.vo.UsuarioVO;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;

public class ChatController implements Runnable {

    static MulticastSocket ms = null;
    static byte[] buf = new byte[1000];
    static InetAddress grupo = null;
    static int Puerto = 12345;// Puerto multicast

    boolean repetir = true;
    String nombre;

    private Main main;
    private Model modelo;
    private UsuarioVO usuario;

    @FXML
    private TextField txMsj;
    @FXML
    private TextArea chatBox;
    private Stage dialogStage;

    @FXML
    private void initialize() throws IOException {
        setMs(new MulticastSocket(12345));
        setGrupo(InetAddress.getByName("225.0.0.1"));
        getMs().joinGroup(getGrupo());
    }
    public void ingreso(){

        String texto = "*** Ingresó al chat: " + nombre.toUpperCase() + " ***";
        try {
            // ENVIANDO DESPEDIDA AL GRUPO
            DatagramPacket paquete = new DatagramPacket(texto.getBytes(),
                    texto.length(), grupo, Puerto);
            ms.send(paquete);
            System.out.println("Ingresó al chat: " + nombre);
//            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    private void handleEnviar() {
        if (txMsj.getText().equals(""))
            main.getAlerta().aviso("Introduzca algún mensaje");
        else {
            String texto = nombre + ">> " + txMsj.getText();

            try {
                // ENVIANDO mensaje al grupo
                DatagramPacket paquete = new DatagramPacket(texto.getBytes(),
                        texto.length(), grupo, Puerto);
                ms.send(paquete);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                txMsj.setText("");
            }
        }
    }

    @FXML
    private void handleSalir() {
        String texto = "*** Abandona el chat: " + nombre + " ***";
        try {
            // ENVIANDO DESPEDIDA AL GRUPO
            DatagramPacket paquete = new DatagramPacket(texto.getBytes(),
                    texto.length(), grupo, Puerto);
            ms.send(paquete);
            ms.close();
            repetir = false;
            System.out.println("Abandona el chat: " + nombre);
//            System.exit(0);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.dialogStage.close();
    }

    @Override
    public void run() {
        while (repetir) {
            try {
                DatagramPacket p = new DatagramPacket(buf, buf.length);
                ms.receive(p);
                String texto = new String(p.getData(), 0, p.getLength());
                chatBox.appendText(texto + "\n");
            } catch (SocketException s) {
                System.out.println(s.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static MulticastSocket getMs() {
        return ms;
    }

    public static InetAddress getGrupo() {
        return grupo;
    }

    public static void setMs(MulticastSocket ms) {
        ChatController.ms = ms;
    }


    public static void setGrupo(InetAddress grupo) {
        ChatController.grupo = grupo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setMain(Main main) {
        this.main = main;
        this.modelo = main.getModel();
        this.usuario = main.getUsuario();
        this.nombre = usuario.getUsuario();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
}
