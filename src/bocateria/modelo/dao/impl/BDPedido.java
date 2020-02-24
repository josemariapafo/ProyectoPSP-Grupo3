package bocateria.modelo.dao.impl;

import bocateria.exepcion.ExcepcionBocateria;
import bocateria.modelo.dao.ext.PedidoDAO;
import bocateria.modelo.vo.PedidoProductoVO;
import bocateria.modelo.vo.PedidoVO;
import bocateria.modelo.vo.ProductoVO;
import bocateria.modelo.vo.UsuarioPedidoVO;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BDPedido implements PedidoDAO {

    private Connection conn;

    private final String INSERT = "INSERT INTO PEDIDO(TOTAL,FECHA) VALUES (?, SYSDATE())";
    private final String INSERT_PEDPROD = "INSERT INTO pedido_producto (idPedido, idProducto,cantidad) VALUES (?,?,?)";
    private final String INSERT_USUPED = "INSERT INTO USUARIO_PEDIDO (IDUSUARIO,IDPEDIDO) VALUES (?,?)";
    private final String UPDATE = "UPDATE PEDIDO SET TOTAL = ?, FECHA = SYSDATE WHERE PEDIDOID = ?";
    private final String DELETE = "DELETE FROM PEDIDO WHERE PEDIDOID = ?";
    private final String GETALL = "SELECT PEDIDOID, TOTAL, FECHA FROM PEDIDO";
    private final String GETONE = "SELECT PEDIDOID, TOTAL, FECHA FROM PEDIDO WHERE PEDIDOID = ?";

    private final String GETALL_PEDPROD = "SELECT IDPEDIDO,IDPRODUCTO,CANTIDAD FROM PEDIDO_PRODUCTO";
    private final String GETONE_PEDPROD = "SELECT IDPEDIDO,IDPRODUCTO,CANTIDAD FROM PEDIDO_PRODUCTO WHERE IDPEDIDO = ?";
    private final String GETALL_USUPED = "SELECT IDUSUARIO,IDPEDIDO FROM USUARIO_PEDIDO";
    private final String GETONE_USUPED_USUARIO = "SELECT IDUSUARIO,IDPEDIDO FROM USUARIO_PEDIDO WHERE IDUSUARIO = ?";
    private final String GETONE_USUPED_PEDIDO = "SELECT IDUSUARIO,IDPEDIDO FROM USUARIO_PEDIDO WHERE IDPEDIDO = ?";

    BDPedido(Connection conn) {
        this.conn = conn;
    }

//    @Override
//    public boolean alta(PedidoVO pedidoVO) throws ExcepcionBocateria {
//        String query2 = "insert into pedido(total,fecha) values("
//                + pedidoVO.getTotal() + ","+pedidoVO.getDate()+")";
//        System.out.printf("Precio total del pedido desde el alta: "+pedidoVO.getTotal());
//
//        Statement stmt2;
//        try {
//            stmt2 = conn.createStatement();
//            stmt2.executeUpdate(query2);
//            return true;
//        } catch (SQLException e) {
//            throw new ExcepcionBocateria("Error al introducir un Pedido");
//        }
//    }
    public BDPedido(){

    }

    @Override
    public boolean alta(PedidoVO pedidoVO) throws ExcepcionBocateria, SQLException {
        PreparedStatement stmt = null;
        boolean efectuado = false;
        try {
            stmt = conn.prepareStatement(INSERT);
            stmt.setDouble(1, pedidoVO.getTotal());
            if (stmt.executeUpdate() == 0)
                throw new ExcepcionBocateria("Puede que no se haya guardado el Pedido en la tabla Pedido");
            else
                efectuado = true;
        } catch (SQLException | ExcepcionBocateria e) {
            e.printStackTrace();
        } finally {
            if (stmt != null)
                stmt.close();
        }
        return efectuado;
    }

    @Override
    public boolean modificar(PedidoVO pedidoVO) throws ExcepcionBocateria {
        return false;
    }

    @Override
    public boolean eliminar(PedidoVO pedidoVO) throws ExcepcionBocateria {
        try (PreparedStatement stmt = conn.prepareStatement(DELETE)) {
            stmt.setInt(1, pedidoVO.getPedidoId());
            if (stmt.executeUpdate() == 0)
                throw new ExcepcionBocateria("Puede que no se haya borrado el pedido!");
            else
                return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<PedidoVO> obtenerTodos() throws ExcepcionBocateria {
        List<PedidoVO> pedidos = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(GETALL); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                pedidos.add(convertir(rs));
            }
        } catch (SQLException e) {
            throw new ExcepcionBocateria("Error en SQL");
        }
        return pedidos;
    }

    @Override
    public PedidoVO obtener(PedidoVO pedidoVO) throws ExcepcionBocateria, SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PedidoVO pedido;
        try {
            stmt = conn.prepareStatement(GETONE);
            stmt.setInt(1, pedidoVO.getPedidoId());
            rs = stmt.executeQuery();
            if (rs.next())
                pedido = convertir(rs);
            else
                pedido = null;
        } catch (SQLException e) {
            throw new ExcepcionBocateria("ERROR EN SQL");
        } finally {

            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return pedido;
    }

    @Override
    public UsuarioPedidoVO obtenerUsuarioPedido(PedidoVO p) throws SQLException, ExcepcionBocateria {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        UsuarioPedidoVO usuped;
        try {
            stmt = conn.prepareStatement(GETONE_USUPED_PEDIDO);
            stmt.setInt(1, p.getPedidoId());
            rs = stmt.executeQuery();
            if (rs.next())
                usuped = convertirUsuPed(rs);
            else
                usuped = null;
        } catch (SQLException e) {
            throw new ExcepcionBocateria("ERROR EN SQL");
        } finally {

            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return usuped;
    }

    //METODO ENCARGADO DE RECOGER AQUELLO PEDIDOS DE LA FECHA DE HOY
    @Override
    public List<PedidoVO> obtenerTodosPedidosHoy() {
        List<PedidoVO> pedidos = new ArrayList<PedidoVO>();

        java.util.Date date = new java.util.Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = dateFormat.format(date);
        System.out.println(strDate);
        String query1 = "SELECT PEDIDOID,TOTAL,FECHA FROM PEDIDO where fecha='"+strDate+"'";
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query1);

            while (rs.next()) {
                PedidoVO pedidoVO = new PedidoVO();
                pedidoVO.setPedidoId((rs.getInt("pedidoId")));
                pedidoVO.setTotal(rs.getDouble("total"));
                pedidoVO.setDate(rs.getDate("fecha"));
                pedidos.add(pedidoVO);
            }
        } catch (SQLException e1) {
            try {
                throw new ExcepcionBocateria("Error al buscar coincidencias");
            } catch (ExcepcionBocateria excepcionBocateria) {
                excepcionBocateria.printStackTrace();
            }
        }
        return pedidos;
    }

    @Override
    public List<PedidoProductoVO> obtenerPedidoProductoList(PedidoVO p) throws ExcepcionBocateria, SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<PedidoProductoVO> pedprod = new ArrayList<>();
        try {
            stmt = conn.prepareStatement(GETONE_PEDPROD);
            stmt.setInt(1, p.getPedidoId());
            rs = stmt.executeQuery();
            while (rs.next())
                pedprod.add(convertirPedProd(rs));

        } catch (SQLException e) {
            throw new ExcepcionBocateria("ERROR EN SQL");
        } finally {

            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return pedprod;
    }

    @Override
    public PedidoVO convertir(ResultSet rs) throws SQLException {
        int pedidoId = rs.getInt(1);
        double total = rs.getDouble(2);
        Date fecha = rs.getDate(3);

        PedidoVO p = new PedidoVO();
        p.setPedidoId(pedidoId);
        p.setTotal(total);
        p.setDate(fecha);
        return p;
    }
    @Override
    public UsuarioPedidoVO convertirUsuPed(ResultSet rs) throws SQLException {
        String idUsuario = rs.getString(1);
        int idPedido = rs.getInt(2);
        return new UsuarioPedidoVO(idUsuario,idPedido);
    }
    @Override
    public PedidoProductoVO convertirPedProd(ResultSet rs) throws SQLException {
        int idPedido, idProducto, cantidad;
        idPedido = rs.getInt(1);
        idProducto = rs.getInt(2);
        cantidad = rs.getInt(3);
        return new PedidoProductoVO(idPedido,idProducto,cantidad);
    }

    @Override
    public boolean insertarPedidoProducto(int idPedido, int idProducto, int cantidad) throws ExcepcionBocateria {
        PreparedStatement stmt = null;
        boolean efectuado = false;
        try {
            stmt = conn.prepareStatement(INSERT_PEDPROD);

            stmt.setInt(1, idPedido);
            stmt.setInt(2, idProducto);
            stmt.setInt(3, cantidad);

            if (stmt.executeUpdate() == 0)
                throw new ExcepcionBocateria("Insert Pedido Producto no realizado");
            else
                efectuado = true;
        } catch (SQLException | ExcepcionBocateria e) {
            e.printStackTrace();
        }
        return efectuado;
    }

    @Override
    public boolean insertarUsuarioPedido(String idUsuario, int idPedido) throws ExcepcionBocateria {
        PreparedStatement stmt = null;
        boolean efectuado = false;
        try {
            stmt = conn.prepareStatement(INSERT_USUPED);

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
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query1);
            System.out.printf("Hola");
            while (rs.next()) {
                ultimaID = rs.getInt("pedidoId");
            }
            return ultimaID;

        } catch (SQLException e1) {
            throw new ExcepcionBocateria("Error al obtener el Pedido");
        }
    }
}
