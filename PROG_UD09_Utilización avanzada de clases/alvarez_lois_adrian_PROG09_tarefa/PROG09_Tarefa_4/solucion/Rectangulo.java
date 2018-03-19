class Rectangulo extends Figura {
	Punto a;
	Punto b;

	Rectangulo(Punto a,Punto b) {
		this.type=Types.RECTANGULO;
		this.a=a;
		this.b=b;
	}

	void paint(Debuxable d) {
		d.linea(a.getY(),a.getX(),b.getY(),a.getX());
		d.linea(a.getY(),a.getX(),a.getY(),b.getX());
		d.linea(b.getY(),a.getX(),b.getY(),b.getX());
		d.linea(b.getY(),b.getX(),a.getY(),b.getX());
	}
}
