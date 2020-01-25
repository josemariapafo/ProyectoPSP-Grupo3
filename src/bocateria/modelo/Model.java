package bocateria.modelo;

import bocateria.exepcion.ExcepcionBocateria;
import bocateria.modelo.dao.bd.BDManager;
import bocateria.modelo.vo.PedidoVO;
import bocateria.modelo.vo.ProductoVO;
import bocateria.modelo.vo.UsuarioVO;

import java.util.List;

public class Model {

    private BDManager bdManager;

    //METODOS USUARIO
    public boolean altaUsuario(UsuarioVO usuarioVO){
        try {
            bdManager.getUsuarioDAO().alta(usuarioVO);
            return true;
        } catch (ExcepcionBocateria excepcionBocateria) {
            System.out.println("Error enel alta del usuario");
            excepcionBocateria.printStackTrace();
            return false;
        }
    }
    public boolean eliminarUsuario(UsuarioVO usuarioVO){
        try {
            bdManager.getUsuarioDAO().eliminar(usuarioVO);
            return true;
        } catch (ExcepcionBocateria excepcionBocateria) {
            System.out.println("Error en la eliminacion");
            excepcionBocateria.printStackTrace();
            return false;
        }

    }
    public List<UsuarioVO> obtenerTodosUsuarios(){
        try {
            return bdManager.getUsuarioDAO().obtenerTodos();
        } catch (ExcepcionBocateria excepcionBocateria) {
            System.out.println("Error en la optención de todos los usuarios");
            excepcionBocateria.printStackTrace();
        }
        return null;
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
        } catch (ExcepcionBocateria excepcionBocateria) {
            System.out.println("Error al eliminar el Pedido");
            excepcionBocateria.printStackTrace();
            return false;
        }
    }
    public List<PedidoVO> obtenerTodosPedidos(){
        try {
            return bdManager.getPedidoDAO().obtenerTodos();
        } catch (ExcepcionBocateria excepcionBocateria) {
            System.out.println("Error al obtener todos los pedidos");
            excepcionBocateria.printStackTrace();
        }
        return null;
    }
    //METODOS  DE PRODUCTOS
    public boolean altaProducto(ProductoVO productoVO){
        try {
            bdManager.getProductoDAO().alta(productoVO);
            return true;
        } catch (ExcepcionBocateria excepcionBocateria) {
            System.out.println("Error en el alta del Producto");
            excepcionBocateria.printStackTrace();
            return false;
        }
    }
    public boolean eliminarProducto(ProductoVO productoVO){
        try {
            bdManager.getProductoDAO().eliminar(productoVO);
            return true;
        } catch (ExcepcionBocateria excepcionBocateria) {
            System.out.println("Error en la eliminación del producto");
            excepcionBocateria.printStackTrace();
            return false;
        }
    }
    public List<ProductoVO> obtenerTodosProductos(){
        try {
            return bdManager.getProductoDAO().obtenerTodos();
        } catch (ExcepcionBocateria excepcionBocateria) {
            System.out.println("Error al obtener todos los productos");
            excepcionBocateria.printStackTrace();
        }
        return null;
    }
}
