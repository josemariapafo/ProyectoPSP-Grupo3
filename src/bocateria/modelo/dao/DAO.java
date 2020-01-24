package bocateria.modelo.dao;

import bocateria.exepcion.ExcepcionBocateria;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    public boolean alta(T t)throws ExcepcionBocateria;
    public boolean modificar(T t)throws ExcepcionBocateria;
    public boolean eliminar(T t)throws ExcepcionBocateria;
    public List<T> obtenerTodos()throws ExcepcionBocateria;
    public T obtener(int id)throws ExcepcionBocateria;
    public T convertir(ResultSet rs) throws SQLException, ExcepcionBocateria;
}