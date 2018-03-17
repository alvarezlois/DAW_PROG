import		daw.iesrodeira.xeometria.Pantalla;
import		daw.iesrodeira.xeometria.Rectangulo;

public class App {
	public static void main(String[] args) {
		Rectangulo a;
		Pantalla p;


		a=new Rectangulo(30,10);
		p=new Pantalla(80,25);
		a.setPantalla(p);

		System.out.println("O Ancho é "+a.getAncho()+" e o alto é "+a.getAlto());
		a.show(4,4);
	}
}
