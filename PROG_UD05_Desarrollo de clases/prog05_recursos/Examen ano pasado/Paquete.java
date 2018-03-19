import java.util.Calendar;

public class Paquete {
	private Dimension d;		// Dimensions do paquete
	private float peso;		// Peso do paquete en Kg
	private long codigo;		// Código do paquete
	private char categoria;	// Categoría según o peso
	private static int numpaquete=0;	// Leva conta do número de paquete creado

	/**
			Constructor
	*/
	public Paquete(float ancho,float alto,float profundo,float peso) throws Exception {
		d=new Dimension(ancho,alto,profundo);
		this.peso=peso;
		codigo=calculaCodigo();
		categoria=calculaCategoria();
	}

	/**
		Devolve a categoría que corresponde por peso
	*/
	public char getCategoria() {
		return categoria;
	}

	/**
		Devolve o sobrecusto debido ao volume e peso do paquete
	*/
	public double getSobrecusto() {
		double custo=0.0;
		// Cun switch será mais cómodo xestionar novas categorías	
		switch(categoria) {
			case 'B': custo=10.0;	break;
			case 'C': custo=30.0;  break;
		}
		return (custo+d.getSobrecusto());
	}

	/**
		Devolve o obxecto Dimension do paquete
	*/
	public Dimension getVolume() {
		return d;
	}

	/**
		Devolve o código do paquete
	*/
	public long getCodigo() {
		return codigo;
	}

	/**
			Este método privado calcula o código do paquete.
			Lanza unha excepción si xa se enviaron 9999 paquetes.... obviamente este límite é un posible fallo de deseño do
			código dos paquetes
	*/
	private long calculaCodigo() throws Exception {
		numpaquete++;														// Comezamos co paquete número 1
		if (numpaquete>=10000) throw new Exception("Hoxe non se admiten máis paquetes");	// Si agotamos o número de paquetes, lanzamos unha excepcion ;) Erro de deseño.
		Calendar data=Calendar.getInstance();
		int ano=data.get(Calendar.YEAR);								// Ano actual
		int mes=data.get(Calendar.MONTH);							// Mes actual
		int dia=data.get(Calendar.DAY_OF_MONTH);					// Dia actual
		return (((ano*100L+mes)*100L)+dia)*10000L+numpaquete;
	}

	/**
		Este método privado calcula a categoría á que pertence un paquete segun o seu peso
	*/
	private char calculaCategoria() {
		if (peso > 20.0) return 'C';
		else if (peso > 5.0) return 'B';
		return 'A';
	}
}
