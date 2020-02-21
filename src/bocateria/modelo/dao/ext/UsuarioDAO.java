package bocateria.modelo.dao.ext;

import bocateria.exepcion.ExcepcionBocateria;
import bocateria.modelo.dao.DAO;
import bocateria.modelo.vo.UsuarioVO;

import java.sql.SQLException;

public interface UsuarioDAO extends DAO<UsuarioVO> {
    boolean compruebaAdmin(UsuarioVO usuario) throws ExcepcionBocateria, SQLException;
}
