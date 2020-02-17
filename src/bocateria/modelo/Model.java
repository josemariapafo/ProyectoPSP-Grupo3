package bocateria.modelo;

import bocateria.exepcion.ExcepcionBocateria;
import bocateria.modelo.dao.bd.BDManager;
import bocateria.modelo.vo.PedidoVO;
import bocateria.modelo.vo.ProductoVO;
import bocateria.modelo.vo.UsuarioVO;

import java.sql.SQLException;
import java.util.List;

public class Model {

    private BDManager bdManager;

    public Model() {
        try {
            bdManager = new BDManager();
        } catch (SQLException e) {
            System.out.println("Error al cargar bdManager desde el modelo");
            e.printStackTrace();
        }
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
        String usu, pwd;
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
}
