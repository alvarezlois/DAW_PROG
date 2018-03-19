
import java.util.Scanner;


class Principal {
	private static enum MainOps {REXISTRO,LOGIN,FIN,ERROR};
	private static Scanner kb=new Scanner(System.in);

	/**
		Borra a pantalla (consola)
	*/
	private final static void clearConsole()	{
		System.out.print("\033[H\033[2J");  // Borra a consola de GNOME (Linux). Busca en Internet como facelo de xeito xeral.
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


	/** Visualiza o Menú e Pide as Opcións
	*/
	private static MainOps mainmenu() {
		MainOps op;

		try {
			clearConsole();
			System.out.println("\tOpcións da Aplicación");
			System.out.println("\t-------------------\n");
			System.out.println("\t1.- Rexistrarse");
			System.out.println("\t2.- Login");
			System.out.println("\t3.- Saír\n\n");
			System.out.print("\tOpcion: ");
			int d=Integer.parseInt(kb.nextLine());
			return MainOps.values()[d-1];
		} catch(Exception e) {
			return MainOps.ERROR;
		}
	}

	/** Rexistro: Devolve null si o rexistro non se realiza
	*/
	public static Usuario do_rexistro() {
		Usuario user=null;
		String dni;
		String nome;
		String apelidos;
		String direccion;
		String telefono;
		String email;
		String nick;
		String pass;

		clearConsole();
		try {
			System.out.println("\tREXISTRO DE USUARIO");
			System.out.println("\t-------------------");
			System.out.println("\t\tIntroduce * ao principio de calqueira entrada para cancelar");		
			System.out.println();
			System.out.print("\tDNI: ");
			dni=kb.nextLine();
			if (dni.charAt(0)=='*') throw new Exception("Rexistro Cancelado");

			System.out.print("\tNome: ");
			nome=kb.nextLine();
			if (nome.charAt(0)=='*') throw new Exception("Rexistro Cancelado");
		
			System.out.print("\tApelidos: ");
			apelidos=kb.nextLine();
			if (apelidos.charAt(0)=='*') throw new Exception("Rexistro Cancelado");

			System.out.print("\tDirección: ");
			direccion=kb.nextLine();
			if (direccion.charAt(0)=='*') throw new Exception("Rexistro Cancelado");

			System.out.print("\tTeléfono: ");
			telefono=kb.nextLine();
			if (telefono.charAt(0)=='*') throw new Exception("Rexistro Cancelado");

			System.out.print("\tE-Mail: ");
			email=kb.nextLine();
			if (email.charAt(0)=='*') throw new Exception("Rexistro Cancelado");

			System.out.print("\tNick: ");
			nick=kb.nextLine();
			if (nick.charAt(0)=='*') throw new Exception("Rexistro Cancelado");

			System.out.print("\tPassword: ");
			pass=kb.nextLine();
			if (pass.charAt(0)=='*') throw new Exception("Rexistro Cancelado");

			user=new Usuario(dni,nome,apelidos,direccion,telefono,email,nick,pass);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}		
		pulsaEnter();
		return user;
	}

	/** do_login
			Recupera os datos dun usuario con nick e pass. Devolve null si non existe ou se da un erro.
	*/
	public static Usuario do_login() {
		Usuario user=null;
		String nick;
		String pass;
		
		clearConsole();
		try {
			System.out.println("\tLOGIN DE USUARIO");
			System.out.println("\t----------------\n");
			System.out.print("\tNick: ");
			nick=kb.nextLine();
			System.out.print("\tPass: ");
			pass=kb.nextLine();
			user=Usuario.login(nick,pass);
		} catch(Exception e) {}
		return user;
	} 

	/**
		Inicio da aplicación despois de facer Login
	*/
	public static void start(Usuario user) {
		clearConsole();
		System.out.println("Benvido a aplicación "+user);
		System.out.println("\nComencemos o traballo....");
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
				case REXISTRO:	do_rexistro();
									break;
				case LOGIN:		Usuario user=do_login();
									if (user!=null) start(user);
									else {
										System.out.println("\nLogin Erróneo\n");
										pulsaEnter();
									}
									break;
				case ERROR:		System.out.println("ERRO na entrada");
			}
		} while(op!=MainOps.FIN);
	}
}
