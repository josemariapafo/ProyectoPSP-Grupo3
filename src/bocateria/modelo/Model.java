package bocateria.modelo;

import bocateria.controlador.SendMailController;
import bocateria.exepcion.ExcepcionBocateria;
import bocateria.modelo.dao.impl.BDManager;
import bocateria.modelo.vo.*;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.spec.InvalidKeySpecException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Model {

    private BDManager bdManager;

    public Model() {
        bdManager = new BDManager();
    }

    public BDManager getBdManager() {
        return bdManager;
    }

    //METODOS USUARIO
    public boolean altaUsuario(UsuarioVO usuarioVO) throws ExcepcionBocateria, SQLException {
        return bdManager.getUsuarioDAO().alta(usuarioVO);
    }

    public boolean eliminarUsuario(UsuarioVO usuarioVO) throws ExcepcionBocateria, SQLException {
        return bdManager.getUsuarioDAO().eliminar(usuarioVO);
    }

    public List<UsuarioVO> obtenerTodosUsuarios() throws ExcepcionBocateria, SQLException {
        return bdManager.getUsuarioDAO().obtenerTodos();
    }

    public UsuarioVO obtenerUsuario(UsuarioVO usuarioVO) throws ExcepcionBocateria, SQLException {
        return bdManager.getUsuarioDAO().obtener(usuarioVO);
    }

    public boolean compruebaAdmin(UsuarioVO usuario) throws ExcepcionBocateria, SQLException {
        return bdManager.getUsuarioDAO().compruebaAdmin(usuario);
    }

    public UsuarioVO usuarioLogueado(UsuarioVO usuarioVO) throws ExcepcionBocateria, SQLException {
        String pwd;
        pwd = usuarioVO.getContrasena();
        UsuarioVO usuBD = bdManager.getUsuarioDAO().obtener(usuarioVO);
        if (usuBD != null) {
            if (checkPwd(pwd, usuBD)) {
                return bdManager.getUsuarioDAO().obtener(usuarioVO);
            }
        }
        return null;
    }

    private boolean checkPwd(String pwd, UsuarioVO u) {
        return pwd.equals(u.getContrasena());
    }
    //METODOS DE LOS PEDIDOS

    /*public boolean altaPedido(PedidoVO pedidoVO){
        try {
            return true;
            bdManager.getPedidoDAO().alta(pedidoVO);
            return true;
        } catch (ExcepcionBocateria excepcionBocateria) {
            System.out.println("Error en el alta del pedido");
            excepcionBocateria.printStackTrace();
            return false;
        }
    }*/
    public boolean eliminarPedido(PedidoVO pedidoVO) {
        try {
            bdManager.getPedidoDAO().eliminar(pedidoVO);
            return true;
        } catch (ExcepcionBocateria | SQLException excepcionBocateria) {
            System.out.println("Error al eliminar el Pedido");
            excepcionBocateria.printStackTrace();
            return false;
        }
    }

    public List<PedidoVO> obtenerTodosPedidos() {
        try {
            return bdManager.getPedidoDAO().obtenerTodos();
        } catch (ExcepcionBocateria | SQLException excepcionBocateria) {
            System.out.println("Error al obtener todos los pedidos");
            excepcionBocateria.printStackTrace();
        }
        return null;
    }

    public boolean insertarPedido(PedidoVO pedidoVO) throws ExcepcionBocateria, SQLException {
        return bdManager.getPedidoDAO().alta(pedidoVO);
    }

    public boolean insertarPedidoProducto(int idPedido, int idProducto, int cantidad) throws ExcepcionBocateria {
        return bdManager.getPedidoDAO().insertarPedidoProducto(idPedido, idProducto, cantidad);
    }

    public int obtenerUltimaIdPedido() throws ExcepcionBocateria {
        return bdManager.getPedidoDAO().obtenerUltimaIDPedido();
    }

    public boolean insertarUsuarioPedido(String idUsuario, int idPedido) throws ExcepcionBocateria {
        return bdManager.getPedidoDAO().insertarUsuarioPedido(idUsuario, idPedido);
    }

    //METODOS  DE PRODUCTOS
    public boolean altaProducto(ProductoVO productoVO) {
        try {
            bdManager.getProductoDAO().alta(productoVO);
            return true;
        } catch (ExcepcionBocateria | SQLException excepcionBocateria) {
            System.out.println("Error en el alta del Producto");
            excepcionBocateria.printStackTrace();
            return false;
        }
    }

    public void sendMail(MailVO correo) throws UnrecoverableKeyException, NoSuchAlgorithmException, IOException, KeyStoreException, InvalidKeyException, InvalidKeySpecException {
        SendMailController sender = new SendMailController(correo);
        sender.sendMail();
    }

    public boolean eliminarProducto(ProductoVO productoVO) {
        try {
            bdManager.getProductoDAO().eliminar(productoVO);
            return true;
        } catch (ExcepcionBocateria | SQLException excepcionBocateria) {
            System.out.println("Error en la eliminación del producto");
            excepcionBocateria.printStackTrace();
            return false;
        }
    }

    public List<ProductoVO> obtenerTodosProductos() {
        try {
            return bdManager.getProductoDAO().obtenerTodos();
        } catch (ExcepcionBocateria | SQLException excepcionBocateria) {
            System.out.println("Error al obtener todos los productos");
            excepcionBocateria.printStackTrace();
        }
        return null;
    }

    public List<PedidoVO> obtenerPedidosHoy() throws ExcepcionBocateria, SQLException {
        List<PedidoVO> pedidosFechaHoy = bdManager.getPedidoDAO().obtenerTodosPedidosHoy();
        for (PedidoVO pedido : pedidosFechaHoy) {
            System.out.println(pedido);
        }
        List<PedidoProductoVO> productosPorPedido;
        ProductoVO producto;
        for (PedidoVO pedido : pedidosFechaHoy) {
            productosPorPedido = bdManager.getPedidoDAO().obtenerPedidoProductoList(pedido);
            List<ProductoVO> listaProductos = new ArrayList<>();
            for (PedidoProductoVO pp : productosPorPedido) {
                producto = bdManager.getProductoDAO().obtenerProductoMedianteID(pp.getIdProducto());
                producto.setCantidad(pp.getCantidad());
                listaProductos.add(producto);
            }
            pedido.setUsuario(bdManager.getUsuarioDAO().obtenerPorId(bdManager.getPedidoDAO().obtenerUsuarioPedido(pedido).getIdUsuario()));
            pedido.setListaProductos(listaProductos);
        }
        return pedidosFechaHoy;
    }

    public boolean actualizarProductoSinFoto(ProductoVO productoVO) throws ExcepcionBocateria, SQLException {
        return bdManager.getProductoDAO().modificarSinFoto(productoVO);
    }

    public boolean stockDown(ProductoVO p) throws ExcepcionBocateria, SQLException {
        return bdManager.getProductoDAO().stockDown(p);
    }
}
