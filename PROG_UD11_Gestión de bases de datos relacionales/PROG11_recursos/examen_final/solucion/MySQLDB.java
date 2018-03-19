
import java.sql.*;
import java.util.ArrayList;

class MySQLDB implements BaseDatos {
	private static  MySQLDB db=null;
	private Connection c=null;

	public static MySQLDB getInstance() throws DataException {
		try {
			if (MySQLDB.db==null) MySQLDB.db=new MySQLDB();
			return MySQLDB.db;
		} catch(Exception e) {
			throw new DataException(e.getMessage());
		}
	}

	private MySQLDB() throws Exception {
	   	c=DriverManager.getConnection("jdbc:mysql://vj.iesrodeira.com:3306/MyStore","test","test");
	}

	public Producto getProducto(int idProducto, int idTipo) throws DataException, SQLException {
		Producto result=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		String producto;

		switch(idTipo) {
			case 2: producto="Impresoras"; break;
			case 1: producto="Componentes"; break;
			case 0: producto="Ordenadores"; break;
			default: throw new DataException("Producto descoñecido");
		}
				
		try {
			pst=c.prepareStatement("SELECT * FROM Productos,"+producto+" WHERE Productos.id_producto="+producto+".id_producto AND Productos.id_producto=?");
			pst.setInt(1,idProducto);
			rs=pst.executeQuery();
			if (!rs.next()) throw new DataException("Producto non atopado");
			switch(idTipo) {
				case 2: result=getImpresora(rs); break;
				case 1: result=getComponente(rs); break;
				case 0: result=getOrdenador(rs); break;
			}
		} catch(Exception e) {
			throw new DataException(e.getMessage());
		} finally {
			if (rs!=null) rs.close();
			if (pst!=null) pst.close();
		}
		return result;
	}

	public Producto updateProducto(Producto p) throws DataException, SQLException {
			PreparedStatement pst=null;
	
			try {
				c.setAutoCommit(false);		
				pst=c.prepareStatement("UPDATE Productos SET nome_producto=?,precio_producto=?,foto_producto=?,existencias=?,tipo=? WHERE id_producto=?");
				pst.setString(1,p.getNome());
				pst.setFloat(2,p.getPrecio());
				pst.setString(3,p.getFoto());
				pst.setInt(4,p.getExistencias());
				pst.setInt(5,p.getType());
				pst.setInt(6,p.getId());
				pst.executeUpdate();
				switch(p.getType()) {
					case 2: updateImpresora(p); break;
					case 1: updateComponente(p); break;
					case 0: updateOrdenador(p); break;
				}
				c.commit();
			} catch(Exception e) {
				c.rollback();
				throw new DataException(e.getMessage());
			} finally {
				if (pst!=null) pst.close();
				c.setAutoCommit(true);
			}
			return p;
	}


	public Factura procesaCesta(Cesta c) throws DataException {
		throw new DataException("Opcion no implementada");
	};

	private Producto getImpresora(ResultSet rs) throws SQLException {
		Impresora p=new Impresora(rs.getInt("id_producto"),rs.getString("nombre"),rs.getFloat("precio"),rs.getString("foto"),rs.getInt("existencias"),rs.getString("tipo"),rs.getInt("paginas_minuto"),rs.getBoolean("color_sn"),rs.getBoolean("doblecara_sn"),rs.getBoolean("fax_sn"),rs.getBoolean("red_sn"));
		return p;
	}

	private Producto getComponente(ResultSet rs) throws SQLException  {
		Componente c=new Componente(rs.getInt("id_producto"),rs.getString("nombre"),rs.getFloat("precio"),rs.getString("foto"),rs.getInt("existencias"),rs.getString("descripcion"));
		return c;
	}

	private Producto getOrdenador(ResultSet rs) throws SQLException  {
		Ordenador o=new Ordenador(rs.getInt("id_producto"),rs.getString("nombre"),rs.getFloat("precio"),rs.getString("foto"),rs.getInt("existencias"),rs.getString("CPU"),rs.getInt("RAM"),rs.getInt("HDD"));
		return o;
	}

	private void updateOrdenador(Producto p) throws SQLException {
		PreparedStatement pst=null;
		Ordenador o=(Ordenador) p;
		try {
				pst=c.prepareStatement("UPDATE Ordenadores SET CPU=?,RAM=?,HDD=? WHERE id_producto=?");
				pst.setString(1,o.getCPU());
				pst.setInt(2,o.getRAM());
				pst.setInt(3,o.getHDD());
				pst.setInt(4,o.getId());
				pst.executeUpdate();
			} finally {
				if (pst!=null) pst.close();
			}
	}

	private void updateComponente(Producto p) throws SQLException {
		PreparedStatement pst=null;
		Componente componente=(Componente) p;
		try {
				pst=c.prepareStatement("UPDATE Componentes SET descripcion=? WHERE id_producto=?");
				pst.setString(1,componente.getDescripcion());
				pst.setInt(2,componente.getId());
				pst.executeUpdate();
			} finally {
				if (pst!=null) pst.close();
			}
	}

	private void updateImpresora(Producto p) throws SQLException {
		PreparedStatement pst=null;
		Impresora pr=(Impresora) p;

		try {
				pst=c.prepareStatement("UPDATE Impresoras SET tipo=?,paginas_minuto=?,color_sn=?,doblecara_sn=?,fax_sn=?,red_sn=? WHERE id_producto=?");
				pst.setString(1,pr.getTipo());
				pst.setInt(2,pr.getPpm());
				pst.setBoolean(3,pr.isColor());
				pst.setBoolean(4,pr.isDobleCara());
				pst.setBoolean(5,pr.isFax());
				pst.setBoolean(6,pr.isNetwork());
				pst.setInt(7,pr.getId());
				pst.executeUpdate();
			} finally {
				if (pst!=null) pst.close();
			}
	}


}
