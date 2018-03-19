class Triangulo extends Figura {
	Punto a;
	Punto b;
	Punto c;

	Triangulo(Punto a,Punto b,Punto c) {
		this.type=Types.TRIANGULO;
		this.a=a;
		this.b=b;
		this.c=c;
	}

	void paint(Debuxable d) {
		d.linea(a.getY(),a.getX(),b.getY(),b.getX());
		d.linea(b.getY(),b.getX(),c.getY(),c.getX());
		d.linea(c.getY(),c.getX(),a.getY(),a.getX());
	}
}
