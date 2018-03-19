class Lienzo implements Debuxable {
	int ancho,alto;
	int[][] out;

	Lienzo(int alto,int ancho) {
		this.ancho=ancho;
		this.alto=alto;
		out=new int[alto][ancho];
	}

	public void show() {
		for(int f=0;f<alto;f++) {
			for(int c=0;c<ancho;c++) {
				if (out[f][c]==0) System.out.print(" ");
				else	System.out.print(""+(char)out[f][c]);
			}
			System.out.println();
		}
	}

	public void putPixel(int y,int x) {
		try {
			out[y][x]='*';
		} catch (Exception e) {
			// Ignoramos o pintado fora do lienzo
		}
	}

	public void writeText(int y,int x,String s) {
		try {
			for(int i=0;i<s.length();i++) out[y][x+i]=s.charAt(i);
		} catch (Exception e) {
			// Ignoramos o pintado fora do lienzo
		}
	}

	public int getWidth() {
		return out[0].length;
	}

	public int getHeight() {
		return out.length;
	}
}
