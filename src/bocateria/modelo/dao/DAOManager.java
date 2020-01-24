package bocateria.modelo.dao;

public interface DAOManager {
    public UsuarioDAO getUsuarioDAO();
    public ProductoDAO getProductoDAO();
    public PedidoDAO getPedidoDAO();
}
