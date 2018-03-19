package unit06b;

import java.net.*; 
import java.io.*;
import java.util.ArrayList;

/**
	Clase Service
	
	Atende a un cliente nun fío de execución
*/
class Service implements Runnable {
	private Socket client;
	private ClientFile cf=null;
	
	/**
		CONSTRUCTOR - Almacena o socket do cliente (a ip, o porto, os fluxos de datos..) e inicia un thread para atendelo
		@param client - Información do socket conectado co cliente
		@param cf - Obxecto ClientFile para acceder aos datos común para todos os fíos.
	*/	
	Service(ClientFile cf,Socket client) {
		this.client=client;
		this.cf=cf;
		new Thread(this).start();
	}

	/**
		Método run do fío. O código expresado aquí se executa de xeito simultáneo ao resto de fíos de execución
	*/
	public void run() {
		Cliente cl;
		String nif;
		Command command=null;
		try {
			ObjectInputStream rdr=new ObjectInputStream(client.getInputStream()); 
			ObjectOutputStream wrt=new ObjectOutputStream(client.getOutputStream()); 
			
			do {
				try {
					String strcmd=(String) rdr.readObject(); 			// Leemos o nome do comando eo transformamos ao enum correspondente
					command=Command.valueOf(strcmd);
					switch(command) {
						case ADDCLIENT:		cl=(Cliente) rdr.readObject();	// Leemos o cliente a engadir
											synchronized (cf) {				// Impedimos que dous ClienteBancarioNet distintos den de alta o mesmo Cliente
												cf.addClient(cl);
											}
											wrt.writeObject("Cliente Engadido Correctamente");
											break;

						case LISTCLIENTS:	ArrayList <Cliente> lc=cf.getList();	// Enviamos a lista de clientes
											wrt.writeObject(lc);
											wrt.flush();
											wrt.reset(); 							// Sempre envio o mesmo obxecto ArrayList, temos que limpar a cache...
											break;

						case SEARCHCLIENT:	nif=(String) rdr.readObject();
											cl=(Cliente) cf.getClient(nif);
											if (cl==null) throw new ClientNotExistsException(nif);
											wrt.writeObject(cl);
											break;
	
						case DELCLIENT:		nif=(String) rdr.readObject();
											synchronized(cf) {						// Impedimos que dous ClienteBancarioNet borren Clientes á vez
												cf.deleteClient(nif);
											}
											wrt.writeObject("Cliente Eliminado");
											break;
	
						case DELFILE:		cf.clearClients();
											wrt.writeObject("Base de datos borrada");
											break;
					}
				} catch (ClientExistsException e) {
					wrt.writeObject("O cliente xa existe");
				} catch (ClientNotExistsException e) {
					wrt.writeObject("O cliente "+e.getMessage()+" non existe");
				} 
			} while(command!=Command.QUIT);
			System.out.println(client+" pechou a súa conexión");
			client.close();
		} catch(Exception e) {
			try {
				client.close();
			} catch (Exception ce) {
				System.out.println("Erro pechando socket "+ce.getMessage());
			}
			System.out.println("Erro de Cliente "+e.getMessage());
		}
	}
}
