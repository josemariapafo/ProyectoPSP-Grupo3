package bocateria.modelo.dao;

import bocateria.exepcion.ExcepcionBocateria;
import bocateria.modelo.vo.PedidoVO;

public interface PedidoDAO extends DAO<PedidoVO>{
    boolean insertarPedidoProducto(int idPedido,int idProducto,int cantidad) throws ExcepcionBocateria;
    boolean insertarUsuarioPedido(String idUsuario,int idPedido) throws ExcepcionBocateria;
}
