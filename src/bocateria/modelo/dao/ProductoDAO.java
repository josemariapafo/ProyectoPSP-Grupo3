package bocateria.modelo.dao;

import bocateria.modelo.vo.ProductoVO;

import java.sql.SQLException;

public interface ProductoDAO extends DAO<ProductoVO>{
    ProductoVO obtenerProductoMedianteID(int id)throws SQLException;
}
