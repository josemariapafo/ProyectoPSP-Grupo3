package bocateria.controlador;

import bocateria.Main;
import bocateria.exepcion.Alertas;
import bocateria.modelo.vo.MailVO;
import bocateria.modelo.vo.UsuarioVO;
import org.apache.commons.net.smtp.AuthenticatingSMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.apache.commons.net.smtp.SimpleSMTPHeader;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import java.io.IOException;
import java.io.Writer;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.spec.InvalidKeySpecException;

public class SendMailController {
    private Main main;
    private Alertas alerta;
    private MailVO correo;
    private UsuarioVO user;

    // se crea cliente SMTP seguro
    AuthenticatingSMTPClient client = new AuthenticatingSMTPClient();

    // datos del usuario y del servidor
    private final String server = "smtp.gmail.com";
    private final String username = "scuesta.test@gmail.com";
    private final String password = "testpwd1";
    private final int puerto = 587;
    private final String remitente = "scuesta.test@gmail.com";
    private String destinatario, asunto, mensaje;

    public SendMailController(MailVO correo) {
        this.correo = correo;
        this.destinatario = correo.getDestinatario();
        this.asunto = correo.getAsunto();
        this.mensaje = correo.getMensaje();
    }

    public void setMain(Main main) {
        this.main = main;
        this.alerta = main.getAlerta();
    }


    public void sendMail() {
        /* ======================== INICIO ENVIO ======================== */
        System.out.println("\n\nIniciando envio del correo con los datos anteriores........\n");
        try {
            int respuesta;

            // Creación de la clave para establecer un canal seguro
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(null, null);
            KeyManager km = kmf.getKeyManagers()[0];

            // nos conectamos al servidor SMTP
            client.connect(server, puerto);
            System.out.println("1 - " + client.getReplyString());

            // se establece la clave para la comunicación segura
            client.setKeyManager(km);

            respuesta = client.getReplyCode();
            if (!SMTPReply.isPositiveCompletion(respuesta)) {
                client.disconnect();
                alerta.info("Conexión rechazada");
                System.err.println("CONEXIÓN RECHAZADA.");
                System.exit(1);
            }
            // se envía el commando EHLO
            client.ehlo(server);// necesario
            System.out.println("2 - " + client.getReplyString());

            System.out.println("\n =========  CON TLS  =========");
            // NECESITA NEGOCIACIÓN TLS - MODO NO IMPLICITO
            // Se ejecuta el comando STARTTLS y se comprueba si es true
            if (client.execTLS()) {
                System.out.println("3 - " + client.getReplyString());

                // se realiza la autenticación con el servidor
                if (client.auth(AuthenticatingSMTPClient.AUTH_METHOD.LOGIN, username, password)) {
                    System.out.println("4 - " + client.getReplyString());
                    // se crea la cabecera
                    SimpleSMTPHeader cabecera = new SimpleSMTPHeader(remitente, destinatario, asunto);

                    // el nombre de usuario y el email de origen coinciden
                    client.setSender(remitente);
                    client.addRecipient(destinatario);
                    System.out.println("5 - " + client.getReplyString());

                    // se envia DATA
                    Writer writer = client.sendMessageData();
                    if (writer == null) { // fallo
                        System.out.println("FALLO AL ENVIAR DATA.");
                        System.exit(1);
                    }
                    /**/
                    writer.write(cabecera.toString()); // cabecera
                    writer.write(mensaje);// luego mensaje
                    writer.close();
                    System.out.println("6 - " + client.getReplyString());

                    boolean exito = client.completePendingCommand();
                    System.out.println("7 - " + client.getReplyString());

                    if (!exito) { // fallo
                        System.out.println("FALLO AL FINALIZAR TRANSACCIÓN.");
                        System.exit(1);
                    } else
                        System.out.println("MENSAJE ENVIADO CON EXITO......");

                } else
                    System.out.println("USUARIO NO AUTENTICADO.");
            } else
                System.out.println("FALLO AL EJECUTAR  STARTTLS.");

        } catch (
                IOException | NoSuchAlgorithmException e) {
            System.err.println("Could not connect to server.");
            e.printStackTrace();
            System.exit(1);
        } catch (InvalidKeyException | UnrecoverableKeyException | KeyStoreException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        try {
            client.disconnect();
        } catch (IOException f) {
            f.printStackTrace();
        }

        System.out.println("Fin de envío.");
    }
}
