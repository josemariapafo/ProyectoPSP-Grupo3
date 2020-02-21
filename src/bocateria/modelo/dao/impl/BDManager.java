package bocateria.modelo.dao.impl;

import bocateria.exepcion.Alertas;
import bocateria.modelo.dao.DAOManager;
import bocateria.modelo.dao.ext.PedidoDAO;
import bocateria.modelo.dao.ext.ProductoDAO;
import bocateria.modelo.dao.ext.UsuarioDAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDManager implements DAOManager {

    private Alertas alerta = new Alertas();
    private Connection conn = null;
    private UsuarioDAO usuario = null;
    private PedidoDAO pedido = null;
    private ProductoDAO producto = null;

    public BDManager() {
        loadDriver();
    }

    public void loadDriver() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/bocateria?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=CET", "root", "");
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException | SQLException e) {
            // TODO Auto-generated catch block
            alerta.error(e.getMessage());
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        return conn;
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
