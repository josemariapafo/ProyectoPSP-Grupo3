package bocateria.modelo.vo;

public class UsuarioPedidoVO {
    private String idUsuario;
    private int idPedido;

    public UsuarioPedidoVO(String idUsuario, int idPedido) {
        this.idUsuario = idUsuario;
        this.idPedido = idPedido;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }
}
