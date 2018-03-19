import java.util.ArrayList;
import java.util.HashMap;

public class MySQLDB implements Database {
	private static MySQLDB db=null;

	private HashMap <Integer,ArrayList<Pintura>> list;
	private ArrayList <Usuario> users;
	private static int userIDs=1;

	public static MySQLDB getInstance() {
		if (MySQLDB.db==null) MySQLDB.db=new MySQLDB();
		return MySQLDB.db;
	}

	private MySQLDB() {
		list=new HashMap<Integer,ArrayList<Pintura>>();
		users=new ArrayList <Usuario>();
	}

	public ArrayList <Pintura> getPinturas(Usuario user) throws Exception {
		return list.get(user.getID());
	}

	public Pintura loadFiguras(Pintura p) throws Exception {
		return p;
	}

	public void savePintura(Usuario user,Pintura p) throws Exception {
			list.get(user.getID()).add(p);		
	}

	public Usuario readUser(String nick,String pass) throws Exception {
		for(Usuario u: users) 
			if (u.getNick().equals(nick) && u.getPass().equals(pass)) return u;
		throw new Exception("O usuario non existe");
	}

	public void saveUser(Usuario user) throws Exception {
		user.setID(userIDs); userIDs++;
		users.add(user);
		list.put(user.getID(),new ArrayList<Pintura>());
	}

	public void close() throws Exception {
	}
}
	
