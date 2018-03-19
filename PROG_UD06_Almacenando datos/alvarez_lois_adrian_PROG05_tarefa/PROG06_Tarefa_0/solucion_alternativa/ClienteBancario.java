package unit06b;

import java.util.Scanner;
import java.util.ArrayList;

public class ClienteBancario { 
	Scanner kb=null;
	ClientFile f;

	/**
		Borra a pantalla (consola)
	*/
	public final static void clearConsole()	{
		System.out.print("\u001b[2J");
		System.out.flush();
	}

	/**
		Espera a pulsar enter
	*/
	private void pulsaEnter() {
		System.out.println();
		System.out.println("Pulsa Enter para continuar...");
		kb.nextLine();
	}	

	/**
		Visualiza o menú de opcións devolvendo a elexida
	*/
	private String menu() {
		ClienteBancario.clearConsole();
		System.out.println("Menú de Opcións");
		System.out.println("(A)ñadir cliente.");
		System.out.println("(L)istar clientes.");
		System.out.println("(B)uscar cliente por NIF");
		System.out.println("Bo(r)rar cliente");
		System.out.println("(E)liminar todos os datos");
		System.out.println("(S)alir");
		return kb.nextLine().toUpperCase();
	}

	/**
		Pide os datos dun novo cliente e os garda
	*/
	private void novoCliente() throws Exception {
		System.out.print("DNI: ");
		String dni=kb.nextLine();
		Cliente.checkDNI(dni);
		System.out.print("Nome: ");
		String nome=kb.nextLine();
		System.out.print("Telefono: ");
		String tel=kb.nextLine();
		System.out.print("Dirección: ");
		String dir=kb.nextLine();
		System.out.print("Importe de la Deuda: ");
		String strdeuda=kb.nextLine();
		double deuda=Double.parseDouble(strdeuda);
		Cliente cl=new Cliente(dni,nome,tel,dir,deuda);

		try {
			f.addClient(cl);
		} catch (ClientExistsException e) {
			System.out.println("O cliente xa existe: ");
			System.out.println(e.getMessage());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		System.out.println();
		System.out.println("Cliente Engadido correctamente");
		pulsaEnter();
	}

	/**
		Lista os clientes e espera a que se pulse enter cada un de eles
	*/
	private void listaClientes() throws Exception {
		ArrayList <Cliente> result=f.getList();
		int n=result.size();
		if (n>0) {
			System.out.println("Listando "+n+" Clientes...");
			for (Cliente c: result) {
				System.out.println(c);
				pulsaEnter();
			}
		} else System.out.println("\nNon existen clientes na Base de Datos\n");
	}

	/**
		Busca o cliente co nif indicado
		@param nif - NIF a buscar
	*/
	private void buscaCliente(String nif) throws ClientNotExistsException {
		Cliente cl=(Cliente) f.getClient(nif);
		if (cl==null) throw new ClientNotExistsException(nif);
		System.out.println();
		System.out.println(cl);
		pulsaEnter();
	}

	/**
		Elimina o cliente co nif indicado
		@param nif - NIF do cliente a eliminar
	*/
	private void borraCliente(String nif) throws Exception {
		f.deleteClient(nif);

		System.out.println();
		System.out.println("Cliente Eliminado");
		pulsaEnter();
	}


	/**
		Recolle a opción elexida e se encarga de que se realice a acción axeitada
	*/
	private void servicio() {
		String nif;
		char option;

		kb=new Scanner(System.in);
		do {
			option=menu().charAt(0);
			ClienteBancario.clearConsole();
			try {			
				switch(option) {
					case 'A': 	novoCliente();
								break;
					case 'L':	listaClientes();
								break;
					case 'B':	System.out.print("NIF do Cliente a buscar: ");
								nif=kb.nextLine();
								Cliente.checkDNI(nif);
								buscaCliente(nif);
								break;
					case 'R':	System.out.print("NIF do Cliente a eliminar: ");
								nif=kb.nextLine();
								Cliente.checkDNI(nif);
								borraCliente(nif);
								break;
					case 'E':	f.clearClients();
								break;
					case 'S':	break;
				}
			} catch (DNIException e) {
				System.out.println("O DNI non é correcto");
				pulsaEnter();
			} catch (NumberFormatException e) {
				System.out.println("O Importe introducido non é correcto");
				pulsaEnter();
			} catch (ClientNotExistsException e) {
				System.out.println("O cliente "+e.getMessage()+" non existe");
				pulsaEnter();
			} catch (ClientExistsException e) {
				System.out.println("O cliente xa existe, os seus datos son: ");
				System.out.println(e);
				pulsaEnter();
			} catch (Exception e) {
				System.out.println("ERROR!!! "+e.getMessage());
				pulsaEnter();
			}
		} while(option!='S');
	}
 
	/**
		CONSTRUCTOR
	*/
	ClienteBancario() throws Exception { 
		f=new ClientFile();
		servicio();
	} 
	
	/**
		Programa Principal
	*/
	public static void main(String[] args) { 
		try {
			new ClienteBancario();
		} catch (Exception e) {
			System.out.println("Error "+e.getMessage());
		}
	} 
} 
