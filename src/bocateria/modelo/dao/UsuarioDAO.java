package bocateria.modelo.dao;

import bocateria.exepcion.ExcepcionBocateria;
import bocateria.modelo.vo.UsuarioVO;

import java.sql.SQLException;

public interface UsuarioDAO extends DAO<UsuarioVO>{
    boolean compruebaAdmin(UsuarioVO usuario) throws ExcepcionBocateria, SQLException;
}
