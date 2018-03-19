import java.util.Scanner;

class Debuxo {
	public static void main(String[] args) {
		Punto p;
		// Por suposto, tamén podo facer: Lienzo out=new Lienzo(10,30);
		Debuxable out=new Lienzo(10,30);

				
		p=pidePunto();
		if (p!=null) {
			p.paint(out);
			out.show();
		} else System.out.println("Coordenadas Erróneas");
	}

	static Punto pidePunto() {
		Scanner in=new Scanner(System.in);
		String str;
		int y,x;

		try {
			System.out.print("Fila: ");
			y=Integer.parseInt(in.nextLine());
			System.out.print("Columna: ");
			x=Integer.parseInt(in.nextLine());
			return new Punto(y,x);
		} catch(NumberFormatException e) {
			return null;
		}
	}
}
