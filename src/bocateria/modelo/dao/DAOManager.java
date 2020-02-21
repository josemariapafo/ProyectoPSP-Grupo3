package bocateria.modelo.dao;

import bocateria.modelo.dao.ext.PedidoDAO;
import bocateria.modelo.dao.ext.ProductoDAO;
import bocateria.modelo.dao.ext.UsuarioDAO;

public interface DAOManager {
    public UsuarioDAO getUsuarioDAO();
    public ProductoDAO getProductoDAO();
    public PedidoDAO getPedidoDAO();
}
