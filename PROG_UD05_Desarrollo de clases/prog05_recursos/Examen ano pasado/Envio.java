import java.util.Scanner;

public class Envio {
	private final static int CUSTOBASE=50;
	private Paquete paquete;			// Paquete a enviar
	private Localidade localidade;	// Localidade de destiño
	private String direccion;			// Dirección de destiño (rúa)
	private String telefono;			// Teléfono de contacto
	private String codigo;				// Codigo do envio
	private boolean urxente;			// Indica si o envío é urxente
	private double custo;				// Calcularemos o custo ao construir o obxecto

	/**
		Constructor
	*/
	public Envio(Paquete paquete,int codpostal,String direccion,String telefono,boolean urxente) throws LocalidadeException {
		this.paquete=paquete;
		this.localidade=new Localidade(codpostal);
		this.direccion=direccion;
		this.telefono=telefono;
		this.urxente=urxente;
		codigo=calculaCodigo();
		custo=calculaCusto();
	}

	/**
		Devolve o código de envío
	*/
	public String getCodigo() {
		return codigo;
	}

	/**
		Devolve o custo do envío
	*/
	public double getCusto() {
		return custo;
	}

	/**
		Representación do envío en formato String. Facilita a visualización dos envíos
	*/
	public String toString() {
		String str=	"Codigo de envío: "+getCodigo()+"\n"+
						"Dirección: "+direccion+", "+localidade.getCodigoPostal()+" "+localidade.getPoboacion()+" ("+localidade.getProvincia()+")\n"+
						"Teléfono de Contacto: "+telefono+"\n"+
						"Importe a Pagar: "+getCusto()+"\n";

		if (urxente) str+="ENVÍO URXENTE";
		else			 str+="Envío ordinario";
		return str;
	}

	/**
			Este método privado calcula o código de envío
	*/
	private String calculaCodigo() {
		String str;

		// Existen métodos alternativos para a conversión, como String.valueOf...
		// A simple concatenacion tamén convertiría, xa que se chamarían automáticamente os métodos toString das clases
		// O fago de un en un para unha maior comprensión
		String catvolume=Integer.toString(paquete.getVolume().getCategoria());
		String catpeso=Character.toString(paquete.getCategoria());
		String zona=Integer.toString(localidade.getZone());
		String codpostal=Integer.toString(localidade.getCodigoPostal());
		String codpaquete=Long.toString(paquete.getCodigo());
		if (urxente) str="U"+catvolume+catpeso+zona+codpostal+codpaquete;
		else 			 str="N"+catvolume+catpeso+zona+codpostal+codpaquete;;
		return str;
	}
	
	/**
		Este método privado calcula o custo de envío
	*/
	private double calculaCusto() {
		int c=0;
		if (urxente) c=10;
		return CUSTOBASE+paquete.getSobrecusto()+localidade.getSobrecusto()+c;
	}


/**
		########################## Aplicación de Proba ############################################
*/
	public static void main(String args[]) {
		try {
				Scanner input=new Scanner(System.in);

				// Creamos o paquete
				System.out.println("Alto do Paquete: ");
				float alto=input.nextFloat();
				System.out.println("Ancho do Paquete: ");
				float ancho=input.nextFloat();
				System.out.println("Profundidade do Paquete: ");
				float prof=input.nextFloat();
				System.out.println("Peso do Paquete: ");
				float peso=input.nextFloat();
				Paquete p=new Paquete(alto,ancho,prof,peso);

				// Creamos o envío
				System.out.println("Codigo Postal: ");
				int codpos=input.nextInt();  
				input.nextLine(); // Eliminamos o salto de liña do buffer...
				System.out.println("Dirección: ");
				String direccion=input.nextLine();
				System.out.println("Teléfono de Contacto: ");
				String telefono=input.nextLine();			
				System.out.println("(N)ormal ou (U)rxente?");
				char n=Character.toUpperCase(input.nextLine().charAt(0));
				if ((n!='U')&&(n!='N')) throw new Exception("Debes poñer N para envío normal e U para envío Urxente");
				Envio e=new Envio(p,codpos,direccion,telefono,(n=='U'));
				System.out.println("Envío creado correctamente. Os datos de envío son os seguintes:\n"+e);
		} catch (Exception e) {
			System.out.println("Error "+e.getMessage());
		}
	}
}
