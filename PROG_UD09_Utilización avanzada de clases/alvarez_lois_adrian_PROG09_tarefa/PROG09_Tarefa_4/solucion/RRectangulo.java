class RRectangulo extends Rectangulo {

	RRectangulo(Punto a,Punto b) {
		super(a,b);
	}

	void paint(Debuxable d) {
		int y=a.getY(); int y1=b.getY();
		if (y>y1) {
			y=b.getY(); 
			y1=a.getY();
		}
		for(int i=y;i<=y1;i++) 
			d.linea(i,a.getX(),i,b.getX());
	}
}
