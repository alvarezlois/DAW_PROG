import java.sql.*;
import java.util.ArrayList;

public class MySQLDB implements Database {
	private static  MySQLDB db=null;
	private Connection c=null;

	public static Database getInstance() throws Exception {
		if (MySQLDB.db==null) MySQLDB.db=new MySQLDB();
		return MySQLDB.db;
	}

	private MySQLDB() throws Exception {
	   c=DriverManager.getConnection("jdbc:mysql://vj.iesrodeira.com:3306/Figuras","test","test");
	}

	public ArrayList <Pintura> getPinturas(Usuario user) throws Exception {
		ArrayList <Pintura> paints=new ArrayList <Pintura>();
		PreparedStatement pst;

		String sql="SELECT * FROM Pinturas WHERE idusuario=?";
		pst=c.prepareStatement(sql);
		pst.setInt(1,user.getID());
		ResultSet rs=pst.executeQuery();
      while (rs.next()) {
			Pintura p=new Pintura(rs.getInt("id"),rs.getString("nome"));
			paints.add(p);
		}
		rs.close();
		pst.close();
		return paints;
	}

	public Pintura loadFiguras(Pintura p) throws Exception {
		ArrayList <Figura> lf=new ArrayList <Figura> ();
		ArrayList <Punto> lp=null;

		Figura f=null;
		PreparedStatement pst;
		// Leemos as figuras cos puntos no orden correcto
		String sql="SELECT * FROM Figuras,Puntos WHERE Puntos.idfigura=Figuras.id AND Figuras.idpintura=? ORDER BY idfigura,`order`";
		pst=c.prepareStatement(sql);
		pst.setInt(1,p.getID());
		ResultSet rs=pst.executeQuery();
		int idf=0;
      while (rs.next()) {
			int id=rs.getInt("id");
			if (id!=idf) {				// Nova Figura
				if (f!=null) {			// Si temos figura anterior, a gardamos
					lf.add(f); f=null;
				}
				lp=new ArrayList <Punto>();	// Inicializamos os puntos da nova figura
				idf=id;
			}
			Punto np=new Punto(rs.getInt("y"),rs.getInt("x"));
			lp.add(np);	// Gardamos Punto.
			switch(Types.values()[rs.getInt("tipo")]) {
				case PUNTO: 		f=lp.get(0); f.setID(id); break;
				case RECTANGULO: 
					// Si levamos 2 puntos, construimos o rectángulo..
					if (lp.size()==2) {
						f=new Rectangulo(lp.get(0),lp.get(1));	// Vertice superior esquerdo e inferior dereito
						f.setID(id);
					}
					break;
				case TRIANGULO:
					if (lp.size()==3) {
						f=new Triangulo(lp.get(0),lp.get(1),lp.get(2));
						f.setID(id);
					}
					break;
			}
		}
		if (f!=null) lf.add(f);
		p.setFiguras(lf);
		rs.close();
		pst.close();
		return p;
	}

	public int saveFigura(int idpintura,Figura f) throws Exception {
		PreparedStatement pst;
		ResultSet rs;
		int idx;
		int fid=0; 		// So poño os ID si a transacción se completa

		if (f.getID()==0) {
			ArrayList <Punto> lp=new ArrayList <Punto>();
			switch(f.type) {
				case PUNTO: 		lp.add((Punto)f); break;
				case RECTANGULO:	lp.add(((Poligono)f).getVertice(0)); lp.add(((Poligono)f).getVertice(2)); break;
				case TRIANGULO:	lp.add(((Poligono)f).getVertice(0)); lp.add(((Poligono)f).getVertice(1)); lp.add(((Poligono)f).getVertice(2)); break;
			}
			pst=c.prepareStatement("INSERT INTO Figuras (idpintura,tipo) VALUES (?,?)",Statement.RETURN_GENERATED_KEYS);
         pst.setInt(1,idpintura);
         pst.setInt(2,f.type.ordinal());
         pst.executeUpdate();
         rs=pst.getGeneratedKeys();
         if (rs.next()){
            fid=rs.getInt(1);
         }
			rs.close();
			pst.close();
			idx=0;
			pst=c.prepareStatement("INSERT INTO Puntos (`idfigura`,`order`,`y`,`x`) VALUES (?,?,?,?)");
			for(Punto p: lp) {
				pst.setInt(1,fid);
				pst.setInt(2,idx);
				pst.setInt(3,p.getY());
				pst.setInt(4,p.getX());
	         pst.executeUpdate();
				idx++;
			}
			pst.close();
		}
		return fid;
	}

	public void savePintura(Usuario user,Pintura p) throws Exception {
		PreparedStatement pst;
		ResultSet rs;
		ArrayList <Figura> lf=p.getFiguras();
		int idpintura=p.getID();
		c.setAutoCommit(false);		// Se garda todo en unha transacción: todo ou nada
		try {
			if (idpintura==0) {		// A pintura non estaba. A aplicación non permite modificación de Pinturas, nin borrado/modificación das figuras, so engadir novas
				pst=c.prepareStatement("INSERT INTO Pinturas (idusuario,nome) VALUES(?,?)",Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1,user.getID());
            pst.setString(2,p.getName());
            pst.executeUpdate();
            rs=pst.getGeneratedKeys();
            if (rs.next()){
                idpintura=rs.getInt(1);
            }
				rs.close();
				pst.close();
			}
			for(Figura f: lf) {		// Gardamos as Figuras da pintura
				f.setID(saveFigura(idpintura,f));
			}
			c.commit(); // TODO OK
		} catch(Exception e) {
			c.rollback(); // FALLO, Non facemos nada
			throw new Exception(e.getMessage());
		}
	}

	public Usuario readUser(String nick,String pass) throws Exception {
		PreparedStatement pst;
		// Leemos as figuras cos puntos no orden correcto
		pst=c.prepareStatement("SELECT * FROM Usuarios WHERE user=? AND password=?");
		pst.setString(1,nick);
		pst.setString(2,pass);
		ResultSet rs=pst.executeQuery();
      if (rs.next()) {
			Usuario u=new Usuario(rs.getString("user"),rs.getString("password"),rs.getString("nombre"),rs.getString("Apellidos"));
			u.setID(rs.getInt("id"));
			return u;
		} 
		throw new Exception("O usuario non existe");	
	}

	public void saveUser(Usuario user) throws Exception {
		// O usuario sempre é novo... non se contempla a súa modificación
		PreparedStatement pst;
		ResultSet rs;

		// Nick e Password deben ser indices únicos, ou debemos verificar que o ID valga 0
		pst=c.prepareStatement("INSERT INTO Usuarios (user,password,nombre,Apellidos) VALUES (?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
      pst.setString(1,user.getNick());
      pst.setString(2,user.getPass());
      pst.setString(3,user.getNome());
      pst.setString(4,user.getApelidos());
      pst.executeUpdate();
      rs=pst.getGeneratedKeys();
      if (rs.next()){
			user.setID(rs.getInt(1));
      }
		rs.close();
		pst.close();
	}

	public void close() throws Exception {
		c.close();
		MySQLDB.db=null;
		c=null;
	}
}
	
