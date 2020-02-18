package bocateria.modelo;

import bocateria.Main;
import bocateria.modelo.vo.UsuarioVO;
import org.apache.commons.net.smtp.AuthenticatingSMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.apache.commons.net.smtp.SimpleSMTPHeader;
import util.Read;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import java.io.IOException;
import java.io.Writer;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.spec.InvalidKeySpecException;

public class Mailing_Fake {
    public static void main(String[] args) throws NoSuchAlgorithmException, UnrecoverableKeyException,
            KeyStoreException, InvalidKeyException, InvalidKeySpecException {
        private Main main;
        private UsuarioVO user;
        // se crea cliente SMTP seguro
        AuthenticatingSMTPClient client = new AuthenticatingSMTPClient();

        // datos del usuario y del servidor
        String server = "smtp.gmail.com";
        String username = "scuesta.test@gmail.com";
        String password = "testpwd1";
        int puerto = 587;
        String remitente = "scuesta.test@gmail.com";
        String destino1 =
                String asunto = "Prueba 3 de SMTPClient con GMAIL y SIN TLS";
        String mensaje = "Este mensaje no va con TLS.\n\nEste es el tercer mensaje que envio a mi cuenta de correo personal.\nA través de un programa en Java.\nUn Saludo!";
        String resp = "";

        System.out.println("Iniciando el programa........\n\n");
        /* ======================== DATOS IMPRESOS ======================== */
        System.out.print("Necesita negociación TLS (S, N)?: ");
        resp = r.dato("");
        System.out.println("Introduce servidor SMTP.........: " + server);
        System.out.println("Introduce usuario...............: " + username);
        System.out.println("Introduce contraseña............: " + password);
        System.out.println("Introduce puerto................: " + puerto);
        System.out.println("Introduce correo del remitente..: " + remitente);
        System.out.println("Introduce correo destinatario...: " + destino1);
        System.out.println("Introduce asunto................: " + asunto);
        System.out.println("Introduce mensaje...............: " + mensaje);

        System.out.println("\nSe introducirán un asunto y mensaje nuevos: ");
        asunto = "";
        mensaje = "";
        asunto = r.dato("Introduce asunto................: ");
        System.out.print("Introduce mensaje...............: ");
        do {
            String s = r.dato("");
            mensaje += s + "\n";
        } while (!server.equals("*"));

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
                    SimpleSMTPHeader cabecera = new SimpleSMTPHeader(remitente, destino1, asunto);

                    // el nombre de usuario y el email de origen coinciden
                    client.setSender(remitente);
                    client.addRecipient(destino1);
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

        } catch (IOException e) {
            System.err.println("Could not connect to server.");
            e.printStackTrace();
            System.exit(1);
        }
        try {
            client.disconnect();
        } catch (IOException f) {
            f.printStackTrace();
        }

        System.out.println("Fin de envío.");
        System.exit(0);
    }// main
}// ..ClienteSMTP3
