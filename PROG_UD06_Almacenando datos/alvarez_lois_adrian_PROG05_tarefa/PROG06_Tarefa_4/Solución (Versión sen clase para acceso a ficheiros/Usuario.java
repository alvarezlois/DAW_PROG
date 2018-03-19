import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Usuario {
	public static final String FILENAME="clientes.dat";

	private String dni;
	private String nome;
	private String apelidos;
	private String direccion;
	private String telefono;
	private String email;
	private String nick;
	private String password;

	/** Constructor
			Recupera de usuarios.dat o usuario co nick indicado, lanzando unha ClienteException si non se atopa.
	*/
	public Usuario (String nick) throws UsuarioException,IOException {
		DataInputStream dos=null;
		try {
			// Buscamos un cliente con ese DNI
			dos=new DataInputStream(new FileInputStream(new File(FILENAME)));
			while(true) {
				read(dos); // Leemos Cliente
				if (nick.equals(this.nick)) break; // ATOPADO
			}
		} catch(EOFException e) {
			throw new UsuarioException("Usuario inexistente");
		} catch(FileNotFoundException e) {
			throw new UsuarioException("Sin Usuarios");
		} finally {
			if (dos!=null) dos.close();
		}
	}

	/** Constructor
			Crea o obxecto Usuario, e o da de alta no ficheiro clientes.dat si o nick xa existe, lanza unha excepcion
	*/
	public Usuario(String dni,String nome,String apelidos,String direccion,String telefono,String email,String nick,String pass) throws Exception {
		this.dni=dni;
		this.nome=nome;
		this.apelidos=apelidos;
		this.direccion=direccion;
		this.telefono=telefono;
		this.email=email;
		this.nick=nick;
		this.password=pass;
		save(); // Gardamos o cliente no ficheiro
	};

	/** save
		Almacena a información do obxecto Usuario en clientes.dat. Si o dni xa existe, lanza unha ClienteException
	*/
	public void save() throws Exception {
		Usuario c=null;
		try {
			c=new Usuario(nick); // Buscamos o Usuario con ese nick
		} catch (UsuarioException e) {
			// Non o Atopamos, se da de alta.
			write();
		} 
	}

	/** getDni
			Devolve o DNI do Usuario
	*/
	public String getDni() {
		return dni;
	}

	/** getNome
			Devolve o Nome do Usuario
	*/
	public String getNome() {
		return nome;
	}

	/** getApelidos
			Devolve os Apelidos do Usuario
	*/
	public String getApelidos() {
		return apelidos;
	}

	/** getDireccion
			Devolve a Dirección do Usuario
	*/
	public String getDireccion() {
		return direccion;
	}

	/** getTelefono
			Devolve os Apelidos do Usuario
	*/
	public String getTelefono() {
		return telefono;
	}

	/** getEmail
			Devolve os Apelidos do Usuario
	*/
	public String getEmail() {
		return email;
	}

	/** login
			Método estático que permite recuperar un Usuario a partir do nick e da pass
	*/
	public static Usuario login(String nick,String pass) throws UsuarioException,Exception {
		Usuario user=null;
		user=new Usuario(nick);
		if (!pass.equals(user.password)) throw new UsuarioException("Login Erróneo");
		return user;
	}

	/** write
			(Utilizo un DataOutputStream en lugar de ObjectOutputStream, para engadir Usuarios sen preocuparme das cabeceiras)
			Escribe no final do ficheiro os datos do Cliente
	*/
	private void write() throws IOException {
		DataOutputStream dos=null;
		try {
			dos=new DataOutputStream(new FileOutputStream(new File(FILENAME),true));
			dos.writeUTF(dni);
			dos.writeUTF(nome);
			dos.writeUTF(apelidos);
			dos.writeUTF(direccion);
			dos.writeUTF(telefono);
			dos.writeUTF(email);
			dos.writeUTF(nick);
			dos.writeUTF(password);
		} finally {
			if (dos!=null) dos.close();
		}
	}

	/** read
			Lee dun DataInputStream aberto os datos do Usuario.
	*/
	private void read(DataInputStream dis) throws IOException {
		dni=dis.readUTF();
		nome=dis.readUTF();
		apelidos=dis.readUTF();
		direccion=dis.readUTF();
		telefono=dis.readUTF();
		email=dis.readUTF();
		nick=dis.readUTF();
		password=dis.readUTF();
	}

	/** toString
			Devolve a representación String dun Usuario
	*/
	public String toString() {
		return "("+dni+") "+nome+" "+apelidos;
	}
}
