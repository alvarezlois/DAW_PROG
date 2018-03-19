class Punto extends Figura {
   int x,y;

	Punto(int y,int x) {
		this.x=x;
		this.y=y;
		this.type=Types.PUNTO;
	}

	void paint(Debuxable d) {
		d.putPixel(y,x);
	}

	int getX() {
		return x;
	}

	int getY() {
		return y;
	}
		
}
