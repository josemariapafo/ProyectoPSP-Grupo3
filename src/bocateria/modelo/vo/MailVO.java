package bocateria.modelo.vo;

public class MailVO {
    private String server, gUser, gPwd, remitente, destinatario, asunto, mensaje;
    private int puerto;

    public MailVO(PedidoVO p) {
        String nickUsuario = p.getUsuario().getUsuario(), emailDestinatario = p.getUsuario().getEmail();

        this.server = "stmp.gmail.com";
        this.gUser = "scuesta.test@gmail.com";
        this.gPwd = "testpwd1";
        this.puerto = 587;
        this.remitente = "scuesta.test@gmail.com";
        this.destinatario = emailDestinatario;
        this.asunto = "Pedido con ID: " + p.getPedidoId() + " de " + nickUsuario;
        String msj = "";
        msj += nickUsuario + " ha realizado el siguiente pedido:\n\n";
        msj += "A fecha de : " + p.getDate() + "\n";
        for (ProductoVO pro : p.getListaProductos()) {
            msj += " - " + pro.getNombre() + " - Precio ud: " + pro.getPrecio() + " - Uds: " + pro.getCantidad() + " - Total: " + (pro.getPrecio() * pro.getCantidad()) + " €\n";
        }
        msj += "\n\nEso hace un total de: "+p.getTotal()+" €\n\n";
        msj += "Gracias por confiar en JOSEB!\nUn saludo.";
        this.mensaje = msj;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getgUser() {
        return gUser;
    }

    public void setgUser(String gUser) {
        this.gUser = gUser;
    }

    public String getgPwd() {
        return gPwd;
    }

    public void setgPwd(String gPwd) {
        this.gPwd = gPwd;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public int getPuerto() {
        return puerto;
    }

    public void setPuerto(int puerto) {
        this.puerto = puerto;
    }
}
