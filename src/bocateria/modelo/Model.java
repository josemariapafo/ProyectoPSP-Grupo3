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
    public void altaUsuario(UsuarioVO usuarioVO){
        try {
            bdManager.getUsuarioDAO().alta(usuarioVO);
        } catch (ExcepcionBocateria excepcionBocateria) {
            System.out.println("Error enel alta del usuario");
            excepcionBocateria.printStackTrace();
        }
    }
    public void eliminarUsuario(UsuarioVO usuarioVO){
        try {
            bdManager.getUsuarioDAO().eliminar(usuarioVO);
        } catch (ExcepcionBocateria excepcionBocateria) {
            System.out.println("Error en la eliminacion");
            excepcionBocateria.printStackTrace();
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
    public void altaPedido(PedidoVO pedidoVO){
        try {
            bdManager.getPedidoDAO().alta(pedidoVO);
        } catch (ExcepcionBocateria excepcionBocateria) {
            System.out.println("Error en el alta del pedido");
            excepcionBocateria.printStackTrace();
        }
    }
    public void eliminarPedido(PedidoVO pedidoVO) {
        try {
            bdManager.getPedidoDAO().eliminar(pedidoVO);
        } catch (ExcepcionBocateria excepcionBocateria) {
            System.out.println("Error al eliminar el Pedido");
            excepcionBocateria.printStackTrace();
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
    public void altaProducto(ProductoVO productoVO){
        try {
            bdManager.getProductoDAO().alta(productoVO);
        } catch (ExcepcionBocateria excepcionBocateria) {
            System.out.println("Error en el alta del Producto");
            excepcionBocateria.printStackTrace();
        }
    }
    public void eliminarProducto(ProductoVO productoVO){
        try {
            bdManager.getProductoDAO().eliminar(productoVO);
        } catch (ExcepcionBocateria excepcionBocateria) {
            System.out.println("Error en la eliminación del producto");
            excepcionBocateria.printStackTrace();
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
