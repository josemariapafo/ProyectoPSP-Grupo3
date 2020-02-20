package bocateria.modelo;

import bocateria.controlador.SendMailController;
import bocateria.exepcion.ExcepcionBocateria;
import bocateria.modelo.dao.bd.BDManager;
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

    public List<PedidoVO> obtenerPedidosHoy(){
        List<PedidoVO> pedidosFechaHoy = bdManager.getPedidoDAO().obtenerTodosPedidosHoy();
        for(int i = 0; i<pedidosFechaHoy.size(); i++){
            System.out.println(pedidosFechaHoy.get(i));
        }

        System.out.println("Obtenemos por cada pedido su array de productos");
        List<List<PedidoProductoVO>> pedidoProductoVO = new ArrayList<>();
        for(int i = 0; i<pedidosFechaHoy.size(); i++){
            try {
                pedidoProductoVO.add(bdManager.getPedidoDAO().obtenerPedidoProductoList(pedidosFechaHoy.get(i)));
            } catch (ExcepcionBocateria excepcionBocateria) {
                excepcionBocateria.printStackTrace();
                System.out.println("Error al obtener de cada pedido sus productos");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        //Añadir lista de Productos al pedido y añadir el nombre del usuario
        for(int i = 0; i<pedidosFechaHoy.size(); i++){
            List<ProductoVO> productoVOS = new ArrayList<>();
            UsuarioVO usuario = new UsuarioVO();
            try {
                usuario.setUsuario(bdManager.getPedidoDAO().obtenerUsuarioDelPedido(pedidosFechaHoy.get(i).getPedidoId()));
                pedidosFechaHoy.get(i).setUsuario(usuario);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Error al guardar el usuario");
            }

            for(int j = 0; j<pedidoProductoVO.get(i).size();j++){
                try {
                    productoVOS.add(bdManager.getProductoDAO().obtenerProductoMedianteID(pedidoProductoVO.get(i).get(j).getIdProducto()));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            pedidosFechaHoy.get(i).setListaProductos(productoVOS);
        }

        //MOSTRAR POR CADA PEDIDO TODO SUS ATRIBUTOS
        System.out.println("MOSTRAR POR CADA PEDIDO TODO SUS ATRIBUTOS");
        for(int i = 0; i<pedidosFechaHoy.size(); i++){
            System.out.println(pedidosFechaHoy.get(i).getPedidoId()+" "+pedidosFechaHoy.get(i).getDate()+" "+pedidosFechaHoy.get(i).getTotal()+" "+pedidosFechaHoy.get(i).getUsuario().getUsuario());
            for(int j= 0; j<pedidosFechaHoy.get(i).getListaProductos().size(); j++){
                System.out.println("   "+pedidosFechaHoy.get(i).getListaProductos().get(j));
            }
        }
        //mainApp.setListaComanda(pedidosFechaHoy);
        return pedidosFechaHoy;
    }
}
