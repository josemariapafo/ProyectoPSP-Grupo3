package bocateria.modelo.dao.impl;

import bocateria.Main;
import bocateria.exepcion.ExcepcionBocateria;
import bocateria.modelo.dao.ext.ProductoDAO;
import bocateria.modelo.vo.ProductoVO;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BDProducto implements ProductoDAO {

    private final String INSERT = "INSERT INTO PRODUCTO(NOMBRE,DESCRIPCION,FOTO,PRECIO,STOCK) VALUES (?,?,?,?,?)";
    private final String UPDATE = "UPDATE PRODUCTO SET NOMBRE = ?, DESCRIPCION = ?, FOTO = ?, PRECIO = ?, STOCK = ? WHERE CODIGO = ?";
    private final String UPDATE_NO_IMG = "UPDATE PRODUCTO SET NOMBRE = ?, DESCRIPCION = ?, PRECIO = ?, STOCK = ? WHERE CODIGO = ?";
    private final String STOCKUP = "UPDATE PRODUCTO SET STOCK = (STOCK + ?) WHERE CODIGO = ?";
    private final String STOCKDOWN = "UPDATE PRODUCTO SET STOCK = (STOCK - ?) WHERE CODIGO = ?";
    private final String DELETE = "DELETE FROM PRODUCTO WHERE CODIGO = ?";
    private final String GETALL = "SELECT CODIGO,NOMBRE,DESCRIPCION,FOTO,PRECIO,STOCK FROM PRODUCTO";
    private final String GETONE = "SELECT CODIGO,NOMBRE,DESCRIPCION,FOTO,PRECIO,STOCK FROM PRODUCTO WHERE CODIGO = ?";

    private String filePath;

    private Connection conn;

    BDProducto(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean alta(ProductoVO productoVO) throws SQLException {
        PreparedStatement stmt = null;
        boolean efectuado = false;
        try {
            stmt = conn.prepareStatement(INSERT);
            stmt.setString(1, productoVO.getNombre());
            stmt.setString(2, productoVO.getDescripcion());
            stmt.setBlob(3, productoVO.getInputStream());
            stmt.setDouble(4, productoVO.getPrecio());
            stmt.setInt(5,productoVO.getStock());
            if (stmt.executeUpdate() == 0)
                throw new ExcepcionBocateria("Puede que no se haya guardado el bocata");
            else
                efectuado = true;
        } catch (SQLException | ExcepcionBocateria | FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        System.out.println(efectuado);
        return efectuado;
    }

    @Override
    public boolean modificar(ProductoVO productoVO) throws ExcepcionBocateria {
        try (PreparedStatement stmt = conn.prepareStatement(UPDATE)) {
            stmt.setString(1, productoVO.getNombre());
            stmt.setString(2, productoVO.getDescripcion());
            stmt.setBlob(3, productoVO.getInputStream());
            stmt.setDouble(4, productoVO.getPrecio());
            stmt.setInt(5, productoVO.getCodigo());
            if (stmt.executeUpdate() == 0) {
                throw new ExcepcionBocateria("Puede que no se haya modificado el cliente");
            }
        } catch (SQLException | ExcepcionBocateria | FileNotFoundException e) {
            throw new ExcepcionBocateria("Error en sql", e);
        }
        return false;
    }

    @Override
    public boolean eliminar(ProductoVO productoVO) throws ExcepcionBocateria{
        try (PreparedStatement stmt = conn.prepareStatement(DELETE)) {
            stmt.setInt(1, productoVO.getCodigo());
            if (stmt.executeUpdate() == 0)
                throw new ExcepcionBocateria("Puede que no se haya borrado el cliente.");

        } catch (SQLException e) {
            throw new ExcepcionBocateria("Error en sql", e);
        }
        return false;
    }

    @Override
    public List<ProductoVO> obtenerTodos() throws ExcepcionBocateria {
        List<ProductoVO> productos = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(GETALL); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                productos.add(convertir(rs));
            }
        } catch (SQLException e) {
            throw new ExcepcionBocateria("Error en SQL");
        }
        return productos;
    }

    @Override
    public ProductoVO obtener(ProductoVO productoVO) throws ExcepcionBocateria, SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ProductoVO p;
        try {
            stmt = conn.prepareStatement(GETONE);
            stmt.setInt(1, productoVO.getCodigo());
            rs = stmt.executeQuery();
            if (rs.next())
                p = convertir(rs);
            else {
                p = null;
            }
        } catch (SQLException e) {
            throw new ExcepcionBocateria("Error SQL");
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return p;
    }

    @Override
    public ProductoVO convertir(ResultSet rs) throws SQLException {
        String nombre = rs.getString(2);
        String descripcion = rs.getString(3);
        byte[] f = rs.getBytes(4);
        Image foto = convertToJavaFXImage(f,100,100);
        Double precio = rs.getDouble(5);
        int stock = rs.getInt(6);
        ProductoVO p = new ProductoVO(nombre, descripcion, foto, precio,stock);

        p.setCodigo(rs.getInt(1));
        return p;
    }

    private static Image convertToJavaFXImage(byte[] raw, final int width, final int height) {
        WritableImage image = new WritableImage(width, height);
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(raw);
            BufferedImage read = ImageIO.read(bis);
            image = SwingFXUtils.toFXImage(read, null);
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        return image;
    }

    @Override
    public ProductoVO obtenerProductoMedianteID(int id) throws SQLException{
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ProductoVO p=null;
        try {
            stmt = conn.prepareStatement(GETONE);
            stmt.setInt(1,id);
            rs = stmt.executeQuery();
            if (rs.next())
                p = convertir(rs);
            else {
                p = null;
            }
        } catch (SQLException e) {
            try {
                throw new ExcepcionBocateria("Error SQL");
            } catch (ExcepcionBocateria excepcionBocateria) {
                excepcionBocateria.printStackTrace();
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return p;
    }

    @Override
    public boolean modificarSinFoto(ProductoVO productoVO) throws ExcepcionBocateria {
        try (PreparedStatement stmt = conn.prepareStatement(UPDATE_NO_IMG)) {
            stmt.setString(1, productoVO.getNombre());
            stmt.setString(2, productoVO.getDescripcion());
            stmt.setDouble(3, productoVO.getPrecio());
            stmt.setInt(4,productoVO.getStock());
            stmt.setInt(5, productoVO.getCodigo());
            if (stmt.executeUpdate() == 0) {
                throw new ExcepcionBocateria("Puede que no se haya modificado el producto");
            }
        } catch (SQLException | ExcepcionBocateria e) {
            throw new ExcepcionBocateria("Error en sql", e);
        }
        return false;
    }

    @Override
    public boolean stockUp(ProductoVO p) throws ExcepcionBocateria, SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(STOCKUP)) {
            stmt.setInt(1,p.getCantidad());
            stmt.setInt(2,p.getCodigo());
            if (stmt.executeUpdate() == 0) {
                throw new ExcepcionBocateria("Puede que no se haya modificado el producto");
            }
        } catch (SQLException | ExcepcionBocateria e) {
            throw new ExcepcionBocateria("Error en sql", e);
        }
        return false;
    }

    @Override
    public boolean stockDown(ProductoVO p) throws ExcepcionBocateria, SQLException {
        try (PreparedStatement stmt = conn.prepareStatement(STOCKDOWN)) {
            stmt.setInt(1,p.getCantidad());
            stmt.setInt(2,p.getCodigo());
            if (stmt.executeUpdate() == 0) {
                throw new ExcepcionBocateria("Puede que no se haya modificado el producto");
            }
        } catch (SQLException | ExcepcionBocateria e) {
            throw new ExcepcionBocateria("Error en sql", e);
        }
        return false;
    }
}
