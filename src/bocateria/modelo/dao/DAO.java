package bocateria.modelo.dao;

import bocateria.exepcion.ExcepcionBocateria;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    boolean alta(T t) throws ExcepcionBocateria, SQLException;
    boolean modificar(T t) throws ExcepcionBocateria, SQLException;
    boolean eliminar(T t) throws ExcepcionBocateria, SQLException;
    List<T> obtenerTodos() throws ExcepcionBocateria, SQLException;
    T obtener(T t) throws ExcepcionBocateria, SQLException;
    T convertir(ResultSet rs) throws SQLException, ExcepcionBocateria;
}