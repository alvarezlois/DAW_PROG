import java.util.ArrayList;

public class Cesta {
	private ArrayList <Producto> cesta;
	BaseDatos bd;

	Cesta(BaseDatos bd) {
		this.cesta=new ArrayList <Producto>();
		this.bd=bd;
	}

	void addProducto(int id_producto,int id_tipo) throws CestaException {
		try {
			Producto pr=bd.getProducto(id_producto,id_tipo);
			cesta.add(pr);
		} catch (DataException e) {
			throw new CestaException(e.getMessage());
		}
	}

	Producto findProducto(int id_producto) throws CestaException {
		for(Producto p: cesta) {
			if (p.getId()==id_producto) return p;
		}
		throw new CestaException("Producto no existente");	
	}

	void delProducto(int id_producto) throws CestaException {
		if (!cesta.remove(findProducto(id_producto))) throw new CestaException("Fallo eliminando producto");	
	}

	void clear() {
		cesta.clear();
	}
}
