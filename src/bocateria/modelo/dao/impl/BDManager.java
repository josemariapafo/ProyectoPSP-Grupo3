package bocateria.modelo.dao.impl;

import bocateria.modelo.dao.DAOManager;
import bocateria.modelo.dao.ext.PedidoDAO;
import bocateria.modelo.dao.ext.ProductoDAO;
import bocateria.modelo.dao.ext.UsuarioDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDManager implements DAOManager {

    private Connection conn;
    private UsuarioDAO usuario;
    private PedidoDAO pedido;
    private ProductoDAO producto;

    public BDManager() throws SQLException {
        loadDriver();
        conectar();
    }

    public void loadDriver() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void conectar() throws SQLException {
        conn = DriverManager.getConnection("jdbc:mysql://localhost/bocateria?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=CET", "root", "");
    }

    @Override
    public UsuarioDAO getUsuarioDAO() {
        if (usuario == null) {
            usuario = new BDUsuario(conn);
        }
        return usuario;
    }

    @Override
    public ProductoDAO getProductoDAO() {
        if (producto == null) {
            producto = new BDProducto(conn);
        }
        return producto;
    }

    @Override
    public PedidoDAO getPedidoDAO() {

        if (pedido == null) {
            pedido = new BDPedido(conn);
        }
        return pedido;
    }
}
