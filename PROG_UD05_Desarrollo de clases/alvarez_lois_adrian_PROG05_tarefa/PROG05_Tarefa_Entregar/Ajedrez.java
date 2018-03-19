import java.util.Scanner;

class Ajedrez extends Juguete {
	protected String modelo;

	public Ajedrez(String modelo,String codigo) throws JugueteException {
		super("JMES12"+codigo,"Ajedrez");
		this.modelo=modelo;
	}


// EXERCICIO
//
	public static void main(String[] args) {
		boolean ok;
		Juguete j;
		Ajedrez a;
		try  {
			a=new Ajedrez("Karpov","1765");
			a.setExistencias(1);
			a.setExistencias(a.getExistencias()+5);
		
			do {
				try {
					ok=true;
					Scanner teclado=new Scanner(System.in);
					System.out.println("Seccion: ");
					String seccion=teclado.nextLine();
					System.out.println("Edad: ");
					String edad=teclado.nextLine();
					System.out.println("Codigo: ");
					String codigo=teclado.nextLine();
					System.out.println("Nombre: ");
					String nombre=teclado.nextLine();
					j=new Juguete(seccion+edad+codigo,nombre);
				} catch(Exception e) {
					System.out.println("ERROR: "+e.getMessage());
					ok=false;
				}		
			} while(!ok);
			System.out.println("Creado "+Juguete.totaljuguetes+" Objeto Juguete");

		} catch (JugueteException e) {
			System.out.println("ERROR "+e.getMessage());
		}
	}
}
