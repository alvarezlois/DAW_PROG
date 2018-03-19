class Lienzo {
	int ancho,alto;
	int[][] out;

	Lienzo(int alto,int ancho) {
		this.ancho=ancho;
		this.alto=alto;
		out=new int[alto][ancho];
	}

	void show() {
		for(int f=0;f<alto;f++) {
			for(int c=0;c<ancho;c++) {
				if (out[f][c]!=0) System.out.print("*");
				else 					System.out.print(".");
			}
			System.out.println();
		}
	}

	void putPixel(int y,int x) {
		try {
			out[y][x]=1;
		} catch (Exception e) {
			// Ignoramos o pintado fora do lienzo
		}
	}
}
