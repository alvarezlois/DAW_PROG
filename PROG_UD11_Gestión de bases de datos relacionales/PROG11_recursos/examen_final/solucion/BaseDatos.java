import java.sql.SQLException;

public interface BaseDatos {
	Producto getProducto(int idProducto, int idTipo) throws DataException, SQLException; 
	Producto updateProducto(Producto p) throws DataException,SQLException;
	Factura procesaCesta(Cesta c) throws DataException;
}
