package bocateria.modelo.dao.bd;

import bocateria.exepcion.ExcepcionBocateria;
import bocateria.modelo.dao.ProductoDAO;
import bocateria.modelo.vo.ProductoVO;

import java.io.FileNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BDProducto implements ProductoDAO {

    final String INSERT = "INSERT INTO PRODUCTO(NOMBRE,DESCRIPCION,FOTO,PRECIO,STOCK) VALUES (?,?,?,?,0)";
    final String UPDATE = "UPDATE PRODUCTO SET NOMBRE = ?, DESCRIPCION = ?, FOTO = ?, PRECIO = ?, STOCK = ? WHERE CODIGO = ?";
    final String STOCKUP = "UPDATE PRODUCTO SET STOCK = (STOCK + ?) WHERE CODIGO = ?";
    final String STOCKDOWN = "UPDATE PRODUCTO SET STOCK = (STOCK - ?) WHERE CODIGO = ?";
    final String DELETE = "DELETE FROM PRODUCTO WHERE CODIGO = ?";
    final String GETALL = "SELECT CODIGO,NOMBRE,DESCRIPCION,FOTO,PRECIO,STOCK FROM PRODUCTO";
    final String GETONE = "SELECT CODIGO,NOMBRE,DESCRIPCION,FOTO,PRECIO,STOCK FROM PRODUCTO WHERE CODIGO = ?";

    private String filePath;

    private Connection conn;

    public BDProducto(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean alta(ProductoVO productoVO) throws ExcepcionBocateria {
        PreparedStatement stmt = null;
        boolean efectuado = false;
        try {
            stmt = conn.prepareStatement(INSERT);
            stmt.setString(1, productoVO.getNombre());
            stmt.setString(2, productoVO.getDescripcion());
            stmt.setBlob(3, productoVO.getInputStream());
            stmt.setDouble(4, productoVO.getPrecio());
            if (stmt.executeUpdate() == 0)
                throw new ExcepcionBocateria("Puede que no se haya guardado el bocata");
            else
                efectuado = true;
        } catch (SQLException | ExcepcionBocateria | FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    throw new ExcepcionBocateria("Error");
                }
            }
        }
        System.out.println(efectuado);
        return efectuado;
    }

    @Override
    public boolean modificar(ProductoVO productoVO) throws ExcepcionBocateria {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, productoVO.getNombre());
            stmt.setString(2, productoVO.getDescripcion());
            stmt.setBlob(3, productoVO.getFoto());
            stmt.setDouble(4, productoVO.getPrecio());
            stmt.setInt(5, productoVO.getCodigo());
            if (stmt.executeUpdate() == 0) {
                throw new ExcepcionBocateria("Puede que no se haya modificado el cliente");
            }
        } catch (SQLException | ExcepcionBocateria e) {
            throw new ExcepcionBocateria("Error en sql", e);
        } finally {
            if (stmt != null)
                try {
                    stmt.close();
                } catch (SQLException e) {
                    throw new ExcepcionBocateria("Error en sql", e);
                }
        }
        return false;
    }

    @Override
    public boolean eliminar(ProductoVO productoVO) throws ExcepcionBocateria {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(DELETE);
            stmt.setInt(1, productoVO.getCodigo());
            if (stmt.executeUpdate() == 0)
                throw new ExcepcionBocateria("Puede que no se haya borrado el cliente.");

        } catch (SQLException e) {
            throw new ExcepcionBocateria("Error en sql", e);
        } finally {
            if (stmt != null)
                try {
                    stmt.close();
                } catch (SQLException e) {
                    throw new ExcepcionBocateria("Error en sql", e);
                }
        }
        return false;
    }

    @Override
    public List<ProductoVO> obtenerTodos() throws ExcepcionBocateria {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<ProductoVO> productos = new ArrayList<ProductoVO>();
        try {
            stmt = conn.prepareStatement(GETALL);
            rs = stmt.executeQuery();
            while (rs.next()) {
                productos.add(convertir(rs));
            }
        } catch (SQLException e) {
            throw new ExcepcionBocateria("Error en SQL");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new ExcepcionBocateria("Error en SQL", e);
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    throw new ExcepcionBocateria("Error en SQL", e);
                }
            }

        }

        return null;
    }

    @Override
    public ProductoVO obtener(ProductoVO productoVO) throws ExcepcionBocateria {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ProductoVO p = null;
        try {
            stmt = conn.prepareStatement(GETONE);
            stmt.setInt(1, productoVO.getCodigo());
            rs = stmt.executeQuery();
            if (rs.next())
                p = convertir(rs);
            else
                throw new ExcepcionBocateria("No se ha encontrado el registro");
        } catch (SQLException e) {
            throw new ExcepcionBocateria("Error SQL");
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    throw new ExcepcionBocateria("Error en SQL", e);
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    throw new ExcepcionBocateria("Error en SQL", e);
                }
            }
        }
        return p;
    }

    @Override
    public ProductoVO convertir(ResultSet rs) throws SQLException {
        String nombre = rs.getString(2);
        String descripcion = rs.getString(3);
        Blob foto = rs.getBlob(4);
        Double precio = rs.getDouble(5);
        ProductoVO p = new ProductoVO(nombre, descripcion, foto, precio);
        p.setCodigo(rs.getInt(1));
        return p;
    }
}
