import java.util.Scanner;
import java.util.ArrayList;

class Debuxo {
	Database db;
	Debuxable canvas;
	Pintura oleo=null;
	Usuario usuario=null;

	public static void main(String[] args) {
		char op=0;
		Debuxable out=new Lienzo(40,80);
		try {
			Database db=MySQLDB.getInstance();
			Debuxo app=new Debuxo(db,out);

			try {
				db.close();
			} catch(Exception e) {
				System.out.println("Error "+e.getMessage()+" pechando database");
			}
		} catch (Exception e) {
			System.out.println("Error "+e.getMessage()+" abrindo database");
		}
	}

	public Debuxo(Database db,Debuxable l) {
		this.db=db;
		this.canvas=l;
		run();
	}

	private void run() {	
		char op=0;

		do {
			try {
				op=menu_login();
				if ((op=='e')&&(usuario==null))	login();
				else if (op=='r')	register();
				else if (op=='e') {
					menu_pintura();
				}
			} catch (Exception e) {
				System.out.println("ERROR: "+e.getMessage());
			}
		} while(op!='s');
	}

	private void register() throws Exception {
		Scanner in=new Scanner(System.in);

		System.out.println("Registrando...");
		System.out.println("Nick: ");
		String nick=in.nextLine();
		System.out.println("Pass:");
		String pass=in.nextLine();	
		System.out.println("Nombre:");
		String nome=in.nextLine();
		System.out.println("Apellidos:");
		String apelidos=in.nextLine();
		db.saveUser(new Usuario(nick,nome,apelidos,pass));
		System.out.println("Usuario guardado correctamente");
	}


	private void login() throws Exception {
		Scanner in=new Scanner(System.in);

		System.out.println("Entrada...");
		System.out.println("Nick: ");
		String nick=in.nextLine();
		System.out.println("Pass:");
		String pass=in.nextLine();		
		this.usuario=db.readUser(nick,pass);
		System.out.println("Bienvenido "+this.usuario);	
		menu_pintura();
	}

	private void dibuja() {
		char op=0;
				
		do {
				try {
					op=main_menu();
					switch(op) {
						case 'p': 
							oleo.add(pidePunto());
							break;
						case 'r':
							oleo.add(new Rectangulo(pidePunto(),pidePunto()));
							break;
						case 't':
							oleo.add(new Triangulo(pidePunto(),pidePunto(),pidePunto()));
							break;
						case 'g':
							try {
								String n=oleo.getName();
								System.out.println("Gardando "+n);
								if (n.equals("Untitled")) {
									Scanner in=new Scanner(System.in);
									System.out.println("Qué nombre quieres ponerle a la pintura?:");
									oleo.setName(in.nextLine());
								}
								db.savePintura(usuario,oleo);
								System.out.println("Pintura gardada");
							} catch(Exception e) {
								System.out.println("Error gardando pintura: "+e.getMessage());
							}
							break;
						case 'm':
							canvas.clear();
							oleo.paint(canvas);
							canvas.show();
							break;
					}
				} catch(NumberFormatException e) {
					System.out.println("Punto erróneo");
				} catch(Exception e) {
					System.out.println("Figura incorrecta: "+e.getMessage());
				}
		}
		while(op!='s');
		oleo=null;
	}

	private void menu_pintura() throws Exception {
		int idx,np;
		String op;

		if (this.usuario==null) throw new Exception("Debes iniciar sesión");

		ArrayList <Pintura> lista=db.getPinturas(this.usuario);
		Scanner in=new Scanner(System.in);

		op="n";
		if (lista.size()>0) {
			System.out.println("Elige una pintura de las siguientes, o empieza una nueva (n)");
			idx=1;
			for(Pintura p: lista) {
				System.out.println(idx+") "+p);
				idx++;
			}
			op=in.nextLine();
		}
		if (op.equals("n")) {
			oleo=new Pintura("Untitled");
		}
		else {
			try {
				np=Integer.parseInt(op)-1;
				oleo=db.loadFiguras(lista.get(np));
				System.out.println("Editando la Pintura "+oleo);
			} catch(Exception e) {
				throw new Exception("La pintura no existe o no se pudo cargar: "+e.getMessage());
			}
		}
		dibuja();
		usuario=null;
	}


	private char menu_login() {
			String op;
			Scanner in=new Scanner(System.in);
			System.out.println("Qué quieres hacer? (r)egistrarte, (e)entrar o (s)alir");
			op=in.nextLine();
			if ((op!=null)&&(op.length()>0)) return op.charAt(0);
			return 0;
	}


	private char main_menu() {
			String op;
			Scanner in=new Scanner(System.in);
			System.out.println("Qué quieres hacer? (p)unto, (r)ectángulo, (t)riangulo, (m)mostrar, (g) guardar o (s)alir");
			op=in.nextLine();
			if ((op!=null)&&(op.length()>0)) return op.charAt(0);
			return 0;
	}

	private Punto pidePunto() throws NumberFormatException {
		Scanner in=new Scanner(System.in);
		String str;
		int y,x;

		System.out.print("Fila: ");
		y=Integer.parseInt(in.nextLine());
		System.out.print("Columna: ");
		x=Integer.parseInt(in.nextLine());
		return new Punto(y,x);
	}
}
