import java.util.ArrayList;

public interface Database {
	public ArrayList <Pintura> getPinturas(Usuario user) throws Exception;
	public Pintura loadFiguras(Pintura p) throws Exception;
	public void savePintura(Usuario user,Pintura p) throws Exception;
	public Usuario readUser(String nick,String pass) throws Exception;
	public void saveUser(Usuario user) throws Exception;
	public void close() throws Exception;
}
