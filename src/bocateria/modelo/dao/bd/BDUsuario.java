package bocateria.modelo.dao.bd;

import bocateria.exepcion.ExcepcionBocateria;
import bocateria.modelo.dao.UsuarioDAO;
import bocateria.modelo.vo.UsuarioVO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BDUsuario implements UsuarioDAO {
    private Connection conn;


    private final String INSERT = "INSERT INTO USUARIO (USUARIO,NOMBRE,APELLIDOS,EMAIL,CONTRASEÑA,DIRECCION,LOCALIDAD,TELEFONO,ADMIN) VALUES (?,?,?,?,?,?,?,?,0)";
    private final String INSERTADMIN = "INSERT INTO USUARIO (USUARIO,NOMBRE,APELLIDOS,EMAIL,CONTRASEÑA,DIRECCION,LOCALIDAD,TELEFONO,ADMIN) VALUES (?,?,?,?,?,?,?,?,1)";
    private final String UPDATE = "UPDATE USUARIO SET NOMBRE = ?, APELLIDOS = ?, EMAIL = ?, CONTRASEÑA = ?, DIRECCION = ?, LOCALIDAD = ?, TELEFONO = ? WHERE USUARIO = ?";
    private final String DELETE = "DELETE FROM USUARIO WHERE USUARIO = ?";
    private final String GETALL = "SELECT USUARIO,NOMBRE,APELLIDOS,EMAIL,CONTRASEÑA,DIRECCION,LOCALIDAD,TELEFONO,ADMIN FROM USUARIO";
    private final String GETONE = "SELECT USUARIO,NOMBRE,APELLIDOS,EMAIL,CONTRASEÑA,DIRECCION,LOCALIDAD,TELEFONO,ADMIN FROM USUARIO WHERE USUARIO = ?";
    /*private final String SETMAIL = "INSERT INTO USU_MAIL (USUARIO, GMAIL, GPWD) VALUES (?,?,?)";*/
    private final String SETMAIL = "UPDATE USUARIO SET GMAIL_USER = ?, GMAIL_PWD = ? WHERE USUARIO = ?";

    BDUsuario(Connection conn) {
        this.conn = conn;
    }

    @Override
    public boolean alta(UsuarioVO u) throws ExcepcionBocateria, SQLException {
        PreparedStatement stmt = null;
        boolean efectuado = false;
        try {
            if (!checkIfThereAreUsers())
                stmt = conn.prepareStatement(INSERTADMIN);
            else
                stmt = conn.prepareStatement(INSERT);

            stmt.setString(1, u.getUsuario());
            stmt.setString(2, u.getNombre());
            stmt.setString(3, u.getApellidos());
            stmt.setString(4, u.getEmail());
            stmt.setString(5, u.getContrasena());
            stmt.setString(6, u.getDireccion());
            stmt.setString(7, u.getLocalidad());
            stmt.setString(8, u.getTelefono());
            if (stmt.executeUpdate() == 0)
                throw new ExcepcionBocateria("Puede que no se haya guardado el Usuario");
            else
                efectuado = true;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null)
                stmt.close();
        }
        return efectuado;
    }



    @Override
    public boolean modificar(UsuarioVO usuarioVO) throws ExcepcionBocateria {
        boolean efectuado;
        try (PreparedStatement stmt = conn.prepareStatement(UPDATE)) {
            stmt.setString(1, usuarioVO.getNombre());
            stmt.setString(2, usuarioVO.getApellidos());
            stmt.setString(3, usuarioVO.getEmail());
            stmt.setString(4, usuarioVO.getContrasena());
            stmt.setString(5, usuarioVO.getDireccion());
            stmt.setString(6, usuarioVO.getLocalidad());
            stmt.setString(7, usuarioVO.getTelefono());
            stmt.setString(8, usuarioVO.getUsuario());
            if (stmt.executeUpdate() == 0) {
                throw new ExcepcionBocateria("Puede que no se haya modificado el cliente " + usuarioVO.getUsuario());
            } else
                efectuado = true;
        } catch (SQLException | ExcepcionBocateria e) {
            throw new ExcepcionBocateria("Error SQL");
        }
        return efectuado;
    }

    @Override
    public boolean eliminar(UsuarioVO usuarioVO) throws ExcepcionBocateria {
        boolean efectuado;
        try (PreparedStatement stmt = conn.prepareStatement(DELETE)) {
            stmt.setString(1, usuarioVO.getUsuario());
            if (stmt.executeUpdate() == 0)
                throw new ExcepcionBocateria("Puede que no se haya borrado el Usuario");
            else
                efectuado = true;
        } catch (SQLException e) {
            throw new ExcepcionBocateria("SQL Error");
        }
        return efectuado;
    }

    @Override
    public List<UsuarioVO> obtenerTodos() throws ExcepcionBocateria {
        List<UsuarioVO> usuarios = new ArrayList<>();
        try (PreparedStatement stmt = conn.prepareStatement(GETALL); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                usuarios.add(convertir(rs));
            }
        } catch (SQLException e) {
            throw new ExcepcionBocateria("Error en SQL", e);
        }
        return usuarios;
    }

    @Override
    public UsuarioVO obtener(UsuarioVO u) throws ExcepcionBocateria, SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        UsuarioVO user;
        try {
            stmt = conn.prepareStatement(GETONE);
            stmt.setString(1, u.getUsuario());
            rs = stmt.executeQuery();
            if (rs.next()) {
                user = convertir(rs);
            } else {
                user = null;
            }
        } catch (SQLException e) {
            throw new ExcepcionBocateria("Error en SQL", e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return user;
    }


    @Override
    public UsuarioVO convertir(ResultSet rs) throws SQLException {
        String usu, nom, apl, email, pwd, dir, loc, tel;
        usu = rs.getString(1);
        nom = rs.getString(2);
        apl = rs.getString(3);
        email = rs.getString(4);
        pwd = rs.getString(5);
        dir = rs.getString(6);
        loc = rs.getString(7);
        tel = rs.getString(8);

        return new UsuarioVO(nom, apl, email, usu, pwd, dir, loc, tel);
    }

    // Comprueba si existe algún usuario en nuestra tabla
    private boolean checkIfThereAreUsers() throws SQLException {
        final String count = "SELECT USUARIO FROM USUARIO";
        Statement stmt;
        stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(count);
        return rs.next();
    }

    @Override
    public boolean compruebaAdmin(UsuarioVO usuario) throws ExcepcionBocateria, SQLException {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        int i = 0;
        try {
            stmt = conn.prepareStatement(GETONE);
            stmt.setString(1, usuario.getUsuario());
            rs = stmt.executeQuery();
            if (rs.next()) {
                i = rs.getInt(9);
            }
        } catch (SQLException e) {
            throw new ExcepcionBocateria("Error en SQL", e);
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        if (i == 1)
            return true;
        else
            return false;
    }

}
