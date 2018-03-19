class Punto {
   int x,y;

	Punto(int y,int x) {
		this.x=x;
		this.y=y;
	}

	void paint(Lienzo l) {
		l.putPixel(y,x);
	}
}
