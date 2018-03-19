package unit06b;

import java.net.*; 
import java.io.*; 

public class ServidorBancarioNet { 
	private static final int PORT=17800;	// A conexión é no porto 17800
	ServerSocket socket;
	ClientFile cf=null;		// Común a todos os fíos

	/**
		Constructor, crea o socket e o obxecto ClientFile para traballar cos datos
	*/
	public ServidorBancarioNet() { 
		try { 
			socket=new ServerSocket(PORT); 
			cf=new ClientFile();
		} catch(Exception e) { 
			System.out.println("Error Iniciando Servidor Bancario "+e.getMessage()); 
			System.exit(0); 
		}
	}
 
	/**
			Servidor
			Para cada conexión crea un novo fío de execución (thread) que se encargará de ler os parámetros e realizar as accións correspondentes
	*/
	public void servicio() { 
		Socket client=null; 
		String command; 

		try { 
			while(true) { 
				try { 
					client=socket.accept(); 					// Novo cliente!!
					System.out.println("Atendendo novo cliente "+client);
					new Service(cf,client);
				} catch(IOException e) { 
					System.out.println("Cliente Rexeitado"); 
				} 
			} 
		} catch (Exception e) {
			System.out.println("Erro atendendo ao cliente "+e.getMessage());
		} finally { 	// Sempre cando rematemos debemos pechar o socket...
			System.out.println("Saindo do Server"); 
			try { 
				socket.close(); 
			} catch(IOException e) { 
				System.out.println("Erro Pechando server"); 
			} 
		} 
	} 

	
	/**
		Inicio - Lanza o servidor
	*/
	public static void main(String[] args) { 
		ServidorBancarioNet bs=new ServidorBancarioNet(); 
		bs.servicio();
	} 
} 
