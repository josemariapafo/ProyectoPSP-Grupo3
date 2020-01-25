package bocateria.modelo.dao.bd;

import bocateria.exepcion.ExcepcionBocateria;
import bocateria.modelo.dao.UsuarioDAO;
import bocateria.modelo.vo.ProductoVO;
import bocateria.modelo.vo.UsuarioVO;

import java.sql.*;
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
                UsuarioVO persona = new UsuarioVO(usuario, nombre, apellidos, email, contraseña, direccion, localidad, telefono);
                persons.add(persona);
                return persons;
            }
        } catch (SQLException e1) {
            throw new ExcepcionBocateria("Error al obtener todos los usuarios");
        }
        return persons;
    }

    @Override
    public UsuarioVO obtener(UsuarioVO usuarioVO) throws ExcepcionBocateria {
        UsuarioVO usuario = new UsuarioVO();
        String query1 = "select usuario,nombre,apellidos,email,contraseña,direccion,localidad,telefono from usuario where usuario = ? and contraseña = ?";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ProductoVO p = null;
        try {
            stmt = conexion.prepareStatement(query1);
            System.out.println("1");

            stmt.setString(1, usuarioVO.getUsuario());
            stmt.setString(2, usuarioVO.getContraseña());
            System.out.println("2");
            rs = stmt.executeQuery();
            System.out.println("3");

            if (rs.next()) {
                usuario.setUsuario(rs.getString("usuario"));
                System.out.println("4");
                usuario.setNombre(rs.getString("nombre"));
                usuario.setApellidos(rs.getString("apellidos"));
                usuario.setEmail(rs.getString("email"));
                usuario.setContraseña(rs.getString("contraseña"));
                usuario.setDireccion(rs.getString("direccion"));
                usuario.setLocalidad(rs.getString("localidad"));
                usuario.setTelefono(rs.getString("telefono"));
            }else{
                System.out.println("No se encontró nada");
                System.out.println(usuario.toString());

            }
            return usuario;
        } catch (SQLException e1) {
            System.out.println("Error al encontrar el usuario");
            return null;
        }

    }


    @Override
    public UsuarioVO convertir(ResultSet rs) {

        return null;
    }
}
