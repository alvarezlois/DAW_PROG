package hotel;

import hotel.errors.ClienteException;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Cliente {
	public static final String FILENAME="hotel/datos/clientes.dat";

	private String dni;
	private String nome;
	private String apelidos;
	private String direccion;
	private String telefono;
	private String email;

	/** Constructor
			Recupera de clientes.dat o cliente co dni indicado, lanzando unha ClienteException si non se atopa.
	*/
	public Cliente(String dni) throws ClienteException,IOException {
		DataInputStream dos=null;
		try {
			// Buscamos un cliente con ese DNI
			dos=new DataInputStream(new FileInputStream(new File(FILENAME)));
			while(true) {
				read(dos); // Leemos Cliente
				if (dni.equals(this.dni)) break; // ATOPADO
			}
		} catch(EOFException e) {
			throw new ClienteException("Non se atopa o Cliente",ClienteException.NOTFOUND);
		} catch(FileNotFoundException e) {
			throw new ClienteException("Sin Clientes",ClienteException.NOTFOUND);
		} finally {
			if (dos!=null) dos.close();
		}
	}

	/** Constructor
			Crea o obxecto Cliente, e o da de alta no ficheiro clientes.dat si o dni xa existe, lanza unha ClienteException.
	*/
	public Cliente(String dni,String nome,String apelidos,String direccion,String telefono,String email) throws ClienteException, Exception {
		this.dni=dni;
		this.nome=nome;
		this.apelidos=apelidos;
		this.direccion=direccion;
		this.telefono=telefono;
		this.email=email;
		save(); // Gardamos o cliente no ficheiro
	};

	/** save
		Almacena a información do obxecto Cliente en clientes.dat. Si o dni xa existe, lanza unha ClienteException
	*/
	public void save() throws ClienteException, Exception {
		Cliente c=null;
		try {
			c=new Cliente(dni); // Buscamos o Cliente con ese DNI
		} catch (ClienteException e) {
			if (e.getCode()==ClienteException.NOTFOUND) { // Non o Atopamos
				write();
			} else throw e;
		} catch (Exception e) {
			throw new ClienteException("ERRO gardando cliente ("+e.getMessage()+")");
		}
	}

	/** getDni
			Devolve o DNI do Cliente
	*/
	public String getDni() {
		return dni;
	}

	/** getNome
			Devolve o Nome do Cliente
	*/
	public String getNome() {
		return nome;
	}

	/** getApelidos
			Devolve os Apelidos do Cliente
	*/
	public String getApelidos() {
		return apelidos;
	}

	/** write
			(Utilizo un DataOutputStream, para engadir Clientes sen preocuparme das cabeceiras)
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
		} finally {
			if (dos!=null) dos.close();
		}
	}

	/** read
			Lee dun DataInputStream aberto os datos do Cliente.
	*/
	private void read(DataInputStream dis) throws IOException {
		dni=dis.readUTF();
		nome=dis.readUTF();
		apelidos=dis.readUTF();
		direccion=dis.readUTF();
		telefono=dis.readUTF();
		email=dis.readUTF();
	}

	/** toString
			Devolve a representación String dun Cliente
	*/
	public String toString() {
		return "("+dni+") "+nome+" "+apelidos;
	}
}
