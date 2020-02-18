package bocateria.modelo.vo;

public class MailVO {
    private String  destinatario, asunto, mensaje;

    public MailVO(PedidoVO p) {
        String nickUsuario = p.getUsuario().getUsuario(), emailDestinatario = p.getUsuario().getEmail();

        this.destinatario = emailDestinatario;
        this.asunto = "Pedido con ID: " + p.getPedidoId() + " de " + nickUsuario;
        String msj = "";
        msj += nickUsuario + " ha realizado el siguiente pedido:\n\n";
        msj += "A fecha de : " + p.getDate() + "\n";
        for (ProductoVO pro : p.getListaProductos()) {
            msj += " - " + pro.getNombre() + " - Precio ud: " + pro.getPrecio() + " - Uds: " + pro.getCantidad() + " - Total: " + (pro.getPrecio() * pro.getCantidad()) + " Euros\n";
        }
        msj += "\n\nEso hace un total de: "+p.getTotal()+" Euros\n\n";
        msj += "Gracias por confiar en JOSEB!\nSu pedido estará en listo aproximadamente en 20 minutos!";
        this.mensaje = msj;
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

}
