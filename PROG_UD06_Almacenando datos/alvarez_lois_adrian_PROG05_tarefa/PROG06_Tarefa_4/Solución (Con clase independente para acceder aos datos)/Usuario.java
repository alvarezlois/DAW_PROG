import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Usuario {
	public static final String FILENAME="usuarios.dat";

	private String dni;
	private String nome;
	private String apelidos;
	private String direccion;
	private String telefono;
	private String email;
	private String nick;
	private String password;

	public Usuario() {
	}

	/** Constructor
			Recupera de usuarios.dat o usuario co nick indicado, lanzando unha ClienteException si non se atopa.
	*/
	public Usuario (String nick) throws Exception {
		UserDatabase db=new UserDatabase();;
		try {
			// Buscamos un cliente con ese DNI
			db.search(this,nick);
		} finally {
			db.close();
		}
	}

	/** Constructor
			Crea o obxecto Usuario, e o da de alta no ficheiro clientes.dat si o nick xa existe, lanza unha excepcion
	*/
	public Usuario(String dni,String nome,String apelidos,String direccion,String telefono,String email,String nick,String pass) throws Exception {
		UserDatabase db=new UserDatabase();
		try {
			this.dni=dni;
			this.nome=nome;
			this.apelidos=apelidos;
			this.direccion=direccion;
			this.telefono=telefono;
			this.email=email;
			this.nick=nick;
			this.password=pass;
			db.save(this); // Gardamos o cliente no ficheiro
		} finally {
			db.close();
		}
	};

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

	/** getNick
	*/
	public String getNick() {
		return nick;
	}

	/** getPassword
	*/
	public String getPassword() {
		return password;
	}

	/** setDni
	*/
	public void setDni(String dni) {
		this.dni=dni;
	}

	/** setNome
	*/
	public void setNome(String nome) {
		this.nome=nome;
	}

	/** setApelidos
	*/
	public void setApelidos(String apelidos) {
		this.apelidos=apelidos;
	}

	/** setDireccion
	*/
	public void setDireccion(String direccion) {
		this.direccion=direccion;
	}

	/** setTelefono
	*/
	public void setTelefono(String telefono) {
		this.telefono=telefono;
	}

	/** setEmail
	*/
	public void setEmail(String email) {
		this.email=email;
	}

	/** setNick
	*/
	public void setNick(String nick) {
		this.nick=nick;
	}

	/** setPassword
	*/
	public void setPassword(String password) {
		this.password=password;
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

	
	/** toString
			Devolve a representación String dun Usuario
	*/
	public String toString() {
		return "("+dni+") "+nome+" "+apelidos;
	}
}
