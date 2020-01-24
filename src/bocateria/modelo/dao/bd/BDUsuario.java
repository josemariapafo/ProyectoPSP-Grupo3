package bocateria.modelo.dao.bd;

import bocateria.exepcion.ExcepcionBocateria;
import bocateria.modelo.dao.UsuarioDAO;
import bocateria.modelo.vo.UsuarioVO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BDUsuario implements UsuarioDAO {
    private Connection conexion;

    public BDUsuario(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public boolean alta(UsuarioVO usuarioVO) throws ExcepcionBocateria {
        String query2 = "insert into usuario(usuario,nombre,apellidos,email,contraseña,direccion,localidad) values('"
                + usuarioVO.getUsuario() + "','" + usuarioVO.getNombre() + "','" + usuarioVO.getApellidos() + "','"
                + usuarioVO.getEmail() + "','" + usuarioVO.getContraseña() + "','" + usuarioVO.getDireccion() + "','" + usuarioVO.getLocalidad() + "')";
        Statement stmt2;
        try {
            stmt2 = conexion.createStatement();
            stmt2.executeUpdate(query2);
            return true;
        } catch (SQLException e) {
            throw new ExcepcionBocateria("Error al introducir una Persona");
        }

    }

    @Override
    public boolean modificar(UsuarioVO usuarioVO) {

        return false;
    }

    @Override
    public boolean eliminar(UsuarioVO usuarioVO) throws ExcepcionBocateria {
        String query = "delete from usuario where usuario='" + usuarioVO.getUsuario() + "'";

        try {
            Statement stmt = conexion.createStatement();
            stmt.executeUpdate(query);
            return true;
        } catch (SQLException e) {
            throw new ExcepcionBocateria("Error al eliminar una persona");
        }

    }

    @Override
    public List<UsuarioVO> obtenerTodos() throws ExcepcionBocateria {
        ArrayList<UsuarioVO> persons = new ArrayList<UsuarioVO>();
        String query1 = "select usuario,nombre,apellidos,email,contraseña,direccion,localidad,telefono from usuario";

        try {
            Statement stmt = conexion.createStatement();
            ResultSet rs = stmt.executeQuery(query1);
            while (rs.next()) {
                String usuario = (rs.getString("usuario"));
                String nombre = (rs.getString("nombre"));
                String apellidos = (rs.getString("apellidos"));
                String email = (rs.getString("email"));
                String contraseña = (rs.getString("contraseña"));
                String direccion = (rs.getString("direccion"));
                String localidad = (rs.getString("localidad"));
                String telefono = rs.getString("telefono");
                UsuarioVO persona = new UsuarioVO(usuario, nombre, apellidos, email, contraseña, direccion, localidad,telefono);
                persons.add(persona);
                return persons;
            }
        } catch (SQLException e1) {
            throw new ExcepcionBocateria("Error al obtener todos los usuarios");
        }
        return persons;
    }

    @Override
    public UsuarioVO obtener(int id) {

        return null;
    }

    @Override
    public UsuarioVO convertir(ResultSet rs) {

        return null;
    }
}
