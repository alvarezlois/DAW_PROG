package daw.iesrodeira.xeometria;

public class Rectangulo {
	private int ancho=0;
	private int alto=0;

	public Rectangulo(int ancho,int alto) {
		this.ancho=ancho;
		this.alto=alto;
	}

	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void show() {
		System.out.println("Son un bonito rectangulo de "+ancho+"x"+alto);
	}


	public static void main(String[] args) {
		System.out.println("Este non é o programa principal...");
	}
	
}
