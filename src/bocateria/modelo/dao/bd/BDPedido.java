package bocateria.modelo.dao.bd;

import bocateria.exepcion.ExcepcionBocateria;
import bocateria.modelo.dao.PedidoDAO;
import bocateria.modelo.vo.PedidoVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BDPedido implements PedidoDAO {

    Connection conexion;

    public BDPedido(Connection conexion){
        this.conexion = conexion;
    }

    @Override
    public boolean alta(PedidoVO pedidoVO) throws ExcepcionBocateria {
        String query2 = "insert into pedido(usuario,pedidoId,precio,cantidad) values('"
                + pedidoVO.getUsuario() + "'," + pedidoVO.getPedidoId() + "," + pedidoVO.getPrecio() + ","
                + pedidoVO.getCantidad() +")";
        Statement stmt2;
        try {
            stmt2 = conexion.createStatement();
            stmt2.executeUpdate(query2);
            return true;
        } catch (SQLException e) {
            throw new ExcepcionBocateria("Error al introducir un Pedido");
        }

    }

    @Override
    public boolean modificar(PedidoVO pedidoVO) throws ExcepcionBocateria{
        return false;
    }

    @Override
    public boolean eliminar(PedidoVO pedidoVO) throws ExcepcionBocateria{
        String query = "delete from pedido where pedidoId=" + pedidoVO.getPedidoId();

        try {
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            throw new ExcepcionBocateria("Error al eliminar un Pedido");
        }
    }

    @Override
    public List<PedidoVO> obtenerTodos() throws ExcepcionBocateria{
        ArrayList<PedidoVO> pedidos = new ArrayList<PedidoVO>();
        String query1 = "select usuario,pedidoId,precio,cantidad from pedido";

        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(query1);
            while (rs.next()) {
                String usuario = (rs.getString("usuario"));
                int pedidoId = (rs.getInt("pedidoiId"));
                Double precio= (rs.getDouble("precio"));
                int cantidad = (rs.getInt("cantidad"));
                PedidoVO pedido = new PedidoVO(usuario,pedidoId,precio,cantidad);
                pedidos.add(pedido);
                return pedidos;
            }
        } catch (SQLException e1){
            throw new ExcepcionBocateria("Error al obtener todos los Pedidos");
        }
        return pedidos;
    }

    @Override
    public PedidoVO obtener(PedidoVO pedidoVO) throws ExcepcionBocateria{
        PedidoVO pedido = new PedidoVO();
        String query1 = "select usuario,pedidoId,precio,cantidad from pedido";
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(query1);
            while (rs.next()) {
                pedido.setUsuario(rs.getString("usuario"));
                pedido.setPedidoId(rs.getInt("pedidoiId"));
                pedido.setPrecio(rs.getDouble("precio"));
                pedido.setCantidad(rs.getInt("cantidad"));
                return pedido;
            }
        } catch (SQLException e1){
            throw new ExcepcionBocateria("Error al obtener el Pedido");
        }

        return pedido;
    }

    @Override
    public PedidoVO convertir(ResultSet rs) throws ExcepcionBocateria{
        return null;
    }
}
