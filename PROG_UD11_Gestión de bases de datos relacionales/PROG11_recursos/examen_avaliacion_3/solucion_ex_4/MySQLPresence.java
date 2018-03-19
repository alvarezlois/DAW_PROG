package ControlPresencial;

import java.sql.*;
import java.util.ArrayList;

public class MySQLPresence implements KeepPresence {
	private static  MySQLPresence db=null;
	private Connection c=null;
	private String[] status={"Entrada","Saida"};

	public static KeepPresence getInstance() throws Exception {
		if (MySQLPresence.db==null) MySQLPresence.db=new MySQLPresence();
		return MySQLPresence.db;
	}

	private MySQLPresence() throws Exception {
	   c=DriverManager.getConnection("jdbc:mysql://vj.iesrodeira.com:3306/PresenceDatabase","test","test");
	}


	public void registerPresence(Persoal p,long time, int type) throws Exception {
		PreparedStatement pst;

		pst=c.prepareStatement("INSERT INTO Presencia (ID,Entrada,Saida,Bono) VALUES(?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
      pst.setString(1,p.getID());
  		pst.setInt(4,p.getBonus());
		switch(type) {
			case 0:  pst.setLong(2,time);
						pst.setLong(3,0);
						break;
			case 1:  pst.setLong(3,time);
						pst.setLong(2,0);
						break;
		}
      pst.executeUpdate();
		pst.close();
	}

	public Persoal getPersonal(String id) throws Exception {
		PreparedStatement pst;
		Persoal p=null;

		String sql="SELECT * FROM Persoal WHERE ID=?";
		pst=c.prepareStatement(sql);
		pst.setString(1,id);
		ResultSet rs=pst.executeQuery();
      if (rs.next()) {
			switch(rs.getInt("Tipo")) {
				case 0: p=new Traballador(rs.getString("ID"),rs.getString("Nome"),rs.getString("Telefono"),rs.getString("E-Mail")); break; 
				case 1: p=new Xefe(rs.getString("ID"),rs.getString("Nome"),rs.getString("Telefono"),rs.getString("E-Mail")); break;
				case 2: p=new Accionista(rs.getString("ID"),rs.getString("Nome"),rs.getString("Telefono"),rs.getString("E-Mail")); break;
			}
		} else throw new Exception("Not Authorized!!");
		rs.close();
		pst.close();
		return p;
	}

	public void close() throws Exception {
		c.close();
		MySQLPresence.db=null;
		c=null;
	}
}
	
