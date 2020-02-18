package bocateria.modelo.dao;

import bocateria.exepcion.ExcepcionBocateria;
import bocateria.modelo.vo.PedidoProductoVO;
import bocateria.modelo.vo.PedidoVO;
import bocateria.modelo.vo.UsuarioPedidoVO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface PedidoDAO extends DAO<PedidoVO>{
    boolean insertarPedidoProducto(int idPedido,int idProducto,int cantidad) throws ExcepcionBocateria;
    boolean insertarUsuarioPedido(String idUsuario,int idPedido) throws ExcepcionBocateria;
    int obtenerUltimaIDPedido() throws ExcepcionBocateria;
    UsuarioPedidoVO obtenerUsuarioPedido(PedidoVO p) throws SQLException, ExcepcionBocateria;
    List<PedidoProductoVO> obtenerPedidoProductoList(PedidoVO p) throws ExcepcionBocateria, SQLException;
    UsuarioPedidoVO convertirUsuPed(ResultSet rs) throws SQLException;
    PedidoProductoVO convertirPedProd(ResultSet rs) throws SQLException;
}
