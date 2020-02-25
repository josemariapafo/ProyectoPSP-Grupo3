package bocateria.modelo.dao.ext;

import bocateria.exepcion.ExcepcionBocateria;
import bocateria.modelo.dao.DAO;
import bocateria.modelo.vo.ProductoVO;

import java.sql.SQLException;

public interface ProductoDAO extends DAO<ProductoVO> {
    ProductoVO obtenerProductoMedianteID(int id)throws SQLException;
    boolean modificarSinFoto(ProductoVO t) throws ExcepcionBocateria, SQLException;
    boolean stockUp (ProductoVO p) throws ExcepcionBocateria, SQLException;
    boolean stockDown (ProductoVO p) throws ExcepcionBocateria, SQLException;
}
