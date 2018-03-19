
public class Dimension {
	private float ancho;		// Anchura do paquete en cm
	private float alto;			// Altura do paquete en cm
	private float profundo;	// Profundidade do paquete en cm
	private double volume;		// Volume en ccc
	private int categoria;		// Categoria do paquete

	/**
		Constructor
			- Calcula a categoría que corresponde ás dimensións indicadas
	*/
	public Dimension(float ancho,float alto,float profundo) throws Exception {
		if ((ancho<0)||(alto<0)||(profundo<0)) throw new Exception("Error nas dimensións, deben ser positivas");
		this.ancho=ancho;
		this.alto=alto;
		this.profundo=profundo;
		volume=ancho*alto*profundo;
		if (volume > 1000000.0) categoria=4;
		else if (volume > 125000.0) categoria=3;
		else if (volume > 15625.0) categoria=2;
		else	categoria=1;
	}

	/**
		Devolve a categoría
	*/
	public int getCategoria() {
		return categoria;
	}

	/**
		Devolve o sobrecusto que implicaría un paquete deste tamano
	*/
	public double getSobrecusto() {
		double custo=0;
		switch(categoria) {
			case 2: custo=5.0; 	break;
			case 3: custo=15.0; break;
			case 4: custo=15.0+((volume-1000000.0)*0.08)/100.0; break;
		}
		return custo;
	}
}
