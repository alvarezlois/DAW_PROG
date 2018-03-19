import hotel.*;
import hotel.errors.*;
import hotel.utils.*;

import java.util.Scanner;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

class Hotel {
	private static enum MainOps {RESERVA,CONSULTA,ENTRADA,SAIDA,LIBRES,OCUPADAS,FIN,ERROR};
	private static Scanner kb=new Scanner(System.in);

	/**
		Borra a pantalla (consola)
	*/
	private final static void clearConsole()	{
		System.out.print("\033[H\033[2J");  
		System.out.flush();
	}

	/**
		Espera a pulsar enter
	*/
	private final static void pulsaEnter() {
		System.out.println();
		System.out.println("\nPulsa Enter para continuar...");
		kb.nextLine();
	}

	/**
			Visualiza un ArrayList en dúas columnas
	*/
	public static void listado(ArrayList lh) {
		int idx=0;
		for (Object h: lh) {
			System.out.print(h+"\t");
			idx++;
			if ((idx%2)==0) {
				System.out.println();
				idx=0;
			}
		}
	}

	/** Visualiza o Menú e Pide as Opcións
	*/
	private final static MainOps mainmenu() {
		MainOps op;

		try {
			clearConsole();
			System.out.println("\tXestión de Reservas");
			System.out.println("\t-------------------\n");
			System.out.println("\t1.- Reservar");
			System.out.println("\t2.- Consulta Reserva");
			System.out.println("\t3.- Entrada en Habitación");
			System.out.println("\t4.- Saída do Hotel");
			System.out.println("\t5.- Listado Habitacións Libres");
			System.out.println("\t6.- Listado de Ocupantes");
			System.out.println("\t7.- Saír\n\n");
			System.out.print("\tOpcion: ");
			int d=Integer.parseInt(kb.nextLine());
			return MainOps.values()[d-1];
		} catch(Exception e) {
			return MainOps.ERROR;
		}
	}
	
	/**
			Devolve a habitación da lista co número indicado
	*/
	private static Habitacion compruebaHabitacion(int nh, ArrayList <Habitacion> lh) throws Exception {
		for (Habitacion h: lh) {
			if (h.getNumero() == nh) return h;
		}
		throw new Exception("Non se atopa a habitación "+nh);
	}

	/** Crea unha reserva
	*/
	private static void do_reserva() {
		ArrayList <Habitacion> libres;
		Date entrada;
		Date saida;
		int nh;
		String dni;
		String nome;
		String apelidos;
		String direccion;
		String telefono;
		String email;
		Cliente cl;
		Habitacion h;
	
		clearConsole();
		try {
			System.out.println("Data de Entrada (dia/mes/ano): ");
			entrada=new DateString(kb.nextLine()).getDate();		// Convertimos o String a un obxecto Date
			if (DateString.compareTo(new Date(),entrada) > 0) throw new Exception("A data de entrada non pode ser anterior ao día de hoxe");

			System.out.println("Data de Salida (dia/mes/ano): ");
			saida=new DateString(kb.nextLine()).getDate();
			if (DateString.compareTo(entrada,saida) >= 0) throw new Exception("A data de saída non pode ser anterior á da entrada");

			libres=Habitacion.listaLibres(entrada,saida);	// Collemos a lista de habitacións dispoñibles entre esas dúas datas
			if (libres.size()==0) throw new Exception("Non se atopan habitacións dispoñibles");
			System.out.println("As Habitacións dispoñibles son as seguintes: ");
			listado(libres);

			System.out.println("Número de Habitación: ");
			nh=Integer.parseInt(kb.nextLine());
			h=compruebaHabitacion(nh,libres); // Comprobamos que a habitación está libre
			System.out.println("DNI do Cliente: ");
			dni=kb.nextLine();
			// Buscamos o cliente na lista de clientes, si non está o damos de alta
			try {
				cl=new Cliente(dni);
				System.out.println("Reserva para o Cliente: "+cl);
			} catch(ClienteException e) {
				System.out.println("Novo Cliente, introduce os datos de cliente:\n ");
				System.out.println("Nome: "); nome=kb.nextLine();
				System.out.println("Apelidos: "); apelidos=kb.nextLine();
				System.out.println("Dirección: "); direccion=kb.nextLine();
				System.out.println("Teléfono: "); telefono=kb.nextLine();
				System.out.println("e-mail: "); email=kb.nextLine();
				cl=new Cliente(dni,nome,apelidos,direccion,telefono,email);
				System.out.println("O Cliente "+cl+" foi dado de alta");
			}
			Reserva r=new Reserva(cl,h,entrada,saida); // Creamos a Reserva
			System.out.println("Reserva Realizada: \n"+r);
		} catch (Exception e) {
			System.out.println("ERRO: "+e.getMessage());
		}	
		pulsaEnter();
	}

	/**
		Consulta de Reservas 
	*/
	public static void do_consulta() {
		ArrayList <Reserva> l;
		Reserva r;
		String str;
		
		try {
			clearConsole();
			System.out.println("Como desexas consultar?: a) Por Cliente ou b) Por Nº de Reserva");
			str=kb.nextLine().toUpperCase();
			if (str.charAt(0)=='A') {
				System.out.println("DNI do Cliente?: ");
				str=kb.nextLine();
				l=Reserva.listaReservas(str); // Obtemos a lista de reservas do cliente
				listado(l);
			} else if (str.charAt(0)=='B') {
				System.out.println("Nº de Reserva?: ");
				str=kb.nextLine();
				r=new Reserva(Integer.parseInt(str));	// Consultamos a reserva con ese número
				System.out.println("Datos da Reserva: \n"+r);
				if (r.getEntrada()==null) {  // Si non entrou xa no hotel, podemos cancelar a reserva
					System.out.println("\nDesexas cancelar esta reserva? (s/n)");
					str=kb.nextLine().toUpperCase();
					if (str.charAt(0)=='S') r.cancel(); // Cancelamos a reserva
				}
			}
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		pulsaEnter();
	}

	/**
		Efectúa a entrada dun cliente na súa reserva
	*/
	public static void do_entrada() {
		String str;
		Reserva r;

		try {
			clearConsole();
			System.out.println("Nº de Reserva?: ");
			str=kb.nextLine();
			r=new Reserva(Integer.parseInt(str));	// Consultamos a reserva
			r.entrada();	// Efectuamos a entrada
			System.out.println("Entrada efectuada: \n"+r);
		} catch(Exception e) {
			System.out.println("ERROR consultando/formalizando reserva: "+e.getMessage());
		}
		pulsaEnter();
	}

	/**
		Efectúa a saída dun cliente do hotel
	*/
	public static void do_saida() {
		String str;
		Reserva r;

		try {
			clearConsole();
			System.out.println("Nº de Reserva?: ");
			str=kb.nextLine();
			r=new Reserva(Integer.parseInt(str));	// Consultamos a reserva
			r.saida();	// Efectuamos a saída
			System.out.println("Saida efectuada: \n"+r);
		} catch(Exception e) {
			System.out.println("ERROR consultando/finalizando reserva: "+e.getMessage());
		}
		pulsaEnter();
	}

	/**
		Obten a lista das habitacións libres
	*/
	public static void lista_libres() {
		ArrayList <Habitacion> libres;
		DateString entrada,saida;

		clearConsole();
		try {
			// Pedimos as datas de consulta
			System.out.println("Data de Entrada (dia/mes/ano): "); 
			entrada=new DateString(kb.nextLine());

			System.out.println("Data de Salida (dia/mes/ano): ");
			saida=new DateString(kb.nextLine());
			if (entrada.compareTo(saida) >= 0) throw new Exception("A data de saída non pode ser anterior á da entrada");

			// Obtemos a lista de habitacións libres entre esas dúas datas
			libres=Habitacion.listaLibres(entrada.getDate(),saida.getDate());
			if (libres.size() > 0) {
				System.out.println("As Habitacións dispoñibles son as seguintes: \n");
				listado(libres);  // Listamos
			} else System.out.println("Non quedan habitacións dispoñibles en esas datas");
		} catch (Exception e) {
			System.out.println("Error obtendo habitacións libres ("+e.getMessage()+")");
		}
		pulsaEnter();
	}

	/**
		Visualiza a lista de ocupacións neste momento
	*/
	public static void lista_ocupacion() {
		ArrayList <Reserva> ocupadas;
		clearConsole();
		try {
			ocupadas=Reserva.listaOcupantes(); // Obtemos a lista de reservas ocupadas, e listamos
			if (ocupadas.size() > 0) {
				System.out.println("Lista de Ocupacións: \n");
				listado(ocupadas);
			} else {
				System.out.println("O Hotel non hospeda ningún cliente neste momento");
			}
		} catch (Exception e) {
			System.out.println("Error obtendo habitacións libres ("+e.getMessage()+")");
		}
		pulsaEnter();
	}

	/**
			PROGRAMA PRINCIPAL
	*/
	public static void main(String[] args) {
		MainOps op=MainOps.FIN;
		do {
			op=mainmenu();
			switch(op) {
				case RESERVA:	do_reserva();
									break;
				case CONSULTA:	do_consulta();
									break;
				case ENTRADA:	do_entrada();
									break;
				case SAIDA:		do_saida();
									break;
				case LIBRES:	lista_libres();
									break;
				case OCUPADAS:	lista_ocupacion();
									break;
			}
		} while(op!=MainOps.FIN);
	}
}
