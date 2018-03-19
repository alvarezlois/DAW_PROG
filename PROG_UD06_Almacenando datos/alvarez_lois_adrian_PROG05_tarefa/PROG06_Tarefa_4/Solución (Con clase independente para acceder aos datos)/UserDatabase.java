import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class UserDatabase {
   public static final String FILENAME="usuarios.dat";
	DataInputStream dis=null;
	DataOutputStream dos=null;

	/** close
			Pecha os ficheiros abertos
	*/
	public void close() throws Exception {
		if (dos!=null) dos.close();
		if (dis!=null) dis.close();
		dos=null;
		dis=null;
	}

	/** write
			Escribe un usuario ao final do ficheiro
	*/
	public void write(Usuario user) throws IOException {
		if (dos==null) dos=new DataOutputStream(new FileOutputStream(new File(FILENAME),true));
		dos.writeUTF(user.getDni());
		dos.writeUTF(user.getNome());
		dos.writeUTF(user.getApelidos());
		dos.writeUTF(user.getDireccion());
		dos.writeUTF(user.getTelefono());
		dos.writeUTF(user.getEmail());
		dos.writeUTF(user.getNick());
		dos.writeUTF(user.getPassword());
	}

	/** read
			Lee un usuario do ficheiro.
	*/
	public void read(Usuario user) throws IOException {
		if (dis==null) dis=new DataInputStream(new FileInputStream(new File(FILENAME)));
		user.setDni(dis.readUTF());
		user.setNome(dis.readUTF());
		user.setApelidos(dis.readUTF());
		user.setDireccion(dis.readUTF());
		user.setTelefono(dis.readUTF());
		user.setEmail(dis.readUTF());
		user.setNick(dis.readUTF());
		user.setPassword(dis.readUTF());
	}

	/** search
			Busca un usuario no ficheiro
	*/
	public void search(Usuario user,String nick) throws Exception {
		close(); // Por si acaso
		try {
			while(true) {
				read(user); // Leemos Cliente
				if (nick.equals(user.getNick())) return; // ATOPADO
			}
		} catch(FileNotFoundException|EOFException e) {
			throw new UsuarioException("Usuario non atopoado");
		}
	}

	/** save
		Si o nick non existe, escribe o usuario ao final do ficheiro
	*/
	public void save(Usuario user) throws Exception {
		Usuario old=new Usuario();
		try {
			search(old,user.getNick());
			throw new UsuarioException("Xa existe un usuario con ese nick");
		} catch(UsuarioException e) {
			close();
			write(user);
		} 	
	}
}
