package unit06b;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.ArrayList;

/**
	Erro de cliente xa existente
*/
class ClientExistsException extends Exception {
	ClientExistsException(String info) {
		super(info);
	}
}

/**
	Erro de cliente non existente
*/
class ClientNotExistsException extends Exception {
	ClientNotExistsException(String info) {
		super(info);
	}
}


/** 
	ClientFile
	
	Xestiona as distintas operacións sobre o ficheiro de clientes.
*/
class ClientFile {
	private final static String PATH="clientes.dat";
	private File f;
	private FileInputStream fi;
	private FileOutputStream fo;
	private ObjectInputStream ois=null;
	private ObjectOutputStream oos=null;

	private ArrayList <Cliente> list=null;		// Almacena a lista de clientes en memoria

	/**
		Abre o stream de entrada para ler os clientes
	*/
	private void openInputStream() throws Exception {
		fi=new FileInputStream(f);
		ois=new ObjectInputStream(fi);
	}

	/**
		Abre o stream de saída para escribir a lista de clientes no arquivo
	*/
	private void openOutputStream() throws Exception {
		fo=new FileOutputStream(f);
		oos=new ObjectOutputStream(fo);	
	}

	/**
		Constructor, si o ficheiro non existe, o crea
	*/
	public ClientFile() throws Exception {
		list=new ArrayList <Cliente>();
		f=new File(ClientFile.PATH);
		if (!f.exists()) {	
			// O ficheiro non existe, o creamos.
			openOutputStream();
			close();
		}
		// Cargamos en memoria a lista de clientes
		loadList();
	}

	/**
		Carga a lista de clientes en memoria e a devolve
	*/
	public ArrayList <Cliente> loadList() throws IOException,ClassNotFoundException {
		list.clear();
		try {
			openInputStream();
			while(true) {
				Cliente cl=(Cliente) ois.readObject();
				list.add(cl);
			}
		} catch (EOFException e) {
			return list;	
		} catch (Exception e) {
			return list;
		} finally {
			close();
		}
	}

	/**
		Garda no disco a lista de clientes que temos en memoria
	*/
	public void saveList() throws Exception {
		try {
			f.delete();
			openOutputStream();
		
			for(Cliente cl: list) {
				oos.writeObject(cl);
			}
			oos.flush();
		} catch (Exception e) {
			throw new Exception("ERROR FATAL GARDANDO DATOS. Pode peligrar a integridade da información");
		}
	}

	/**
		Devolve o cliente co nif indicado, ou null si non existe
		@param nif - Nif do cliente a  buscar
	*/
	public Cliente getClient(String nif) {
		for(Cliente cl: list) {
			if (nif.equals(cl.getNif())) return cl;
		}
		return null;
	}

	/**
		Engade un novo cliente a lista e ao ficheiro, si o cliente xa existe, lanza un erro ClientExistsException
		@param cl - Cliente a engadir
	*/
	public void addClient(Cliente cl) throws ClientExistsException,Exception {
		if (getClient(cl.getNif())==null) {
			list.add(cl);
			saveList();
		} else throw new ClientExistsException(cl.toString());
	
	}

	/**
		elimina da lista e do ficheiro o cliente indicado por nif, si non existe xenera o erro ClientNotExistsException
		@param nif - Nif do cliente a eliminar
	*/
	public void deleteClient(String nif) throws ClientNotExistsException,Exception {
		Cliente cl=getClient(nif);
		if ((cl!=null) && (list.remove(cl)))	saveList();
		else throw new ClientNotExistsException(nif);
	}

	public void clearClients() throws Exception {
		list.clear();
		f.delete();
	}

	/**
		Devolve a lista de clientes almacenada na memoria
	*/
	public ArrayList <Cliente> getList() {
		return list;
	}

	/**
		Pecha os fluxos de datos abertos.
	*/
	void close() throws IOException {
		if (ois!=null) {
			ois.close();
			ois=null;
		}
		if (oos!=null) {
			oos.close();
			oos=null;
		}
	}

	/**
		Test unitario - Comproba o funcionamento da clase
	*/
	public static void main(String[] args) {
		try {
			ClientFile cf=new ClientFile(); 
			Cliente cl=new Cliente("36073289C","test","test","test",0.0d);
			cf.addClient(cl);
			System.out.println("O cliente con nif 12121212N "+((cf.getClient("12121212N")!=null)?"Existe":"Non Existe"));
			System.out.println("O cliente con nif 36073289C "+((cf.getClient("36073289C")!=null)?"Existe":"Non Existe"));
			cf.addClient(new Cliente("87809208S","otro","otro","otro",0.0d));
			System.out.println("Lista de Clientes: ");
			for (Cliente c: cf.getList()) {
				System.out.println(c);
			}
		} catch (ClientExistsException e) {
			System.out.println("Xa existe o cliente:\n"+e.getMessage());
		} catch (ClientNotExistsException e) {
			System.out.println("O cliente "+e.getMessage()+" non existe");
		} 
		catch (Exception e) {
			System.out.println("Error "+e.getMessage());
		}
	}
}
