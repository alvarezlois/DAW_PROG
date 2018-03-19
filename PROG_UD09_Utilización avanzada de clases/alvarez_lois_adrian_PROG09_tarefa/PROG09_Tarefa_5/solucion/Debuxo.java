import java.util.Scanner;

class Debuxo {
	public static void main(String[] args) {
		char op=0;
		// Por suposto, tamén podo facer: Lienzo out=new Lienzo(10,30);
		Debuxable out=new Lienzo(40,80);
		Pintura oleo=new Pintura("Cadro Abstracto 2017");

		do {
			try {
				op=menu();
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
					case 'm':
						oleo.paint(out);
						out.show();
						break;
				}
			} catch(NumberFormatException e) {
				System.out.println("Punto erróneo");
			} catch(Exception e) {
				System.out.println("Figura incorrecta: "+e.getMessage());
			}
		}
		while(op!='s');
	}

	static char menu() {
			String op;
			Scanner in=new Scanner(System.in);
			System.out.println("Qué quieres hacer? (p)unto, (r)ectángulo, (t)riangulo, (m)mostrar o (s)alir");
			op=in.nextLine();
			if ((op!=null)&&(op.length()>0)) return op.charAt(0);
			return 0;
	}

	static Punto pidePunto() throws NumberFormatException {
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
