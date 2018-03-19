class Punto {
   int x,y;

	Punto(int y,int x) {
		this.x=x;
		this.y=y;
	}

	void paint(Debuxable d) {
		d.putPixel(y,x);
	}
}
