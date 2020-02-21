package bocateria.modelo.dao.ext;

import bocateria.modelo.dao.DAO;
import bocateria.modelo.vo.ProductoVO;

import java.sql.SQLException;

public interface ProductoDAO extends DAO<ProductoVO> {
    ProductoVO obtenerProductoMedianteID(int id)throws SQLException;
}
