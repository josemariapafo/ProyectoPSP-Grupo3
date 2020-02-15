package bocateria.modelo.dao.bd;

import bocateria.exepcion.ExcepcionBocateria;
import bocateria.modelo.dao.PedidoDAO;
import bocateria.modelo.vo.PedidoVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BDPedido implements PedidoDAO {

    private Connection conexion;

    private final String INSERT = "INSERT INTO PEDIDO(PEDIDOID,TOTAL,FECHA) VALUES (?,?,SYSDATE)";
    private final String INSERT_PEDPROD = "INSERT INTO pedido_producto (idPedido, idProducto,cantidad) VALUES (?,?,?)";
    private final String INSERT_USUPED = "INSERT INTO USUARIO_PEDIDO (IDUSUARIO,IDPEDIDO) VALUES (?,?)";
    private final String UPDATE = "UPDATE PEDIDO SET TOTAL = ?, FECHA = SYSDATE WHERE PEDIDOID = ?";
    private final String DELETE = "DELETE FROM PEDIDO WHERE PEDIDOID = ?";
    private final String GETALL = "SELECT PEDIDOID,TOTAL,FECHA FROM PEDIDO";
    private final String GETONE = "SELECT CODIGO,NOMBRE,DESCRIPCION,FOTO,PRECIO,STOCK FROM PRODUCTO WHERE PEDIDOID = ?";

    BDPedido(Connection conexion){
        this.conexion = conexion;
    }

    @Override
    public boolean alta(PedidoVO pedidoVO) throws ExcepcionBocateria {
        String query2 = "insert into pedido(total,fecha) values("
                + pedidoVO.getTotal() + ","+pedidoVO.getDate()+")";
        System.out.printf("Precio total del pedido desde el alta: "+pedidoVO.getTotal());

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
        ArrayList<PedidoVO> pedidos = new ArrayList<>();
        String query1 = "select usuario,pedidoId,precio,cantidad from pedido";

        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(query1);
            while (rs.next()) {
                int pedidoId = (rs.getInt("pedidoiId"));
                Double total= (rs.getDouble("total"));
                long fecha = rs.getBigDecimal("fecha").longValue();

                PedidoVO pedido = new PedidoVO(pedidoId,total,fecha);
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
        String query1 = "select pedidoId,total,fecha from pedido";
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(query1);
            while (rs.next()) {
                pedido.setPedidoId(rs.getInt("pedidoiId"));
                pedido.setTotal(rs.getDouble("total"));
                pedido.setDate(rs.getBigDecimal("fecha").longValue());
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

    @Override
    public boolean insertarPedidoProducto(int idPedido,int idProducto,int cantidad) throws ExcepcionBocateria{
        PreparedStatement stmt = null;
        boolean efectuado = false;
        try {
                stmt = conexion.prepareStatement(INSERT_PEDPROD);

            stmt.setInt(1, idPedido);
            stmt.setInt(2, idProducto);
            stmt.setInt(3, cantidad);

            System.out.printf("Buenas gente");
            System.out.printf("idPedido: "+idPedido+" idProducto: "+idProducto+" cantidad: "+cantidad);

            if (stmt.executeUpdate() == 0) {
                System.out.printf("Estoy to doramion");
                throw new ExcepcionBocateria("Insert Pedido Producto no realizado");
            }
            else {
                efectuado = true;
            }
        } catch (SQLException | ExcepcionBocateria e) {
            e.printStackTrace();
        }
        return efectuado;
    }

    @Override
    public boolean insertarUsuarioPedido(String idUsuario,int idPedido) throws ExcepcionBocateria{
        PreparedStatement stmt = null;
        boolean efectuado = false;
        try {
            stmt = conexion.prepareStatement(INSERT_USUPED);

            stmt.setString(1, idUsuario);
            stmt.setInt(2, idPedido);

            if (stmt.executeUpdate() == 0)
                throw new ExcepcionBocateria("Insert Usuario Pedido no realizado");
            else
                efectuado = true;
        } catch (SQLException | ExcepcionBocateria e) {
            e.printStackTrace();
        }
        return efectuado;

    }

    @Override
    public int obtenerUltimaIDPedido() throws ExcepcionBocateria {
        int ultimaID = 0;
        String query1 = "select pedidoId,total,fecha from pedido";
        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(query1);
            System.out.printf("Hola");
            while (rs.next()) {
                ultimaID = rs.getInt("pedidoId");
            }
            return ultimaID;

        } catch (SQLException e1){
            throw new ExcepcionBocateria("Error al obtener el Pedido");
        }
    }
}
