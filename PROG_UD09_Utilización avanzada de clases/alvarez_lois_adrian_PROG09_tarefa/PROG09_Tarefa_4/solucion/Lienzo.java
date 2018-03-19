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

	public void linea(int y,int x, int y1, int x1) {
		if (x==x1) {
			if (y1<y) {	int k=y1;	y1=y; y=k;	}
			for(int i=y;i<=y1;i++) putPixel(i,x);
		}
		else if (y==y1) {
			if (x1<x) {	int k=x1;	x1=y; x=k;	}
			for(int i=x;i<=x1;i++) putPixel(y,i);
		}
		else Bresenham(y,x,y1,x1);
	}

	/** Algoritmo de Bresenham: 
			Fusilado de https://es.wikibooks.org/wiki/Implementaci%C3%B3n_de_algoritmos/Geometr%C3%ADa/Algoritmo_de_Bresenham
					 
			La adaptación es mínima. La visualización en modo texto muy mala por falta de resolución.		
	*/
	private void Bresenham(int y0, int x0, int y1, int x1) { 
  		int x, y, dx, dy, p, incE, incNE, stepx, stepy;
		dx = (x1 - x0);
  		dy = (y1 - y0);

		 /* determinar que punto usar para empezar, cual para terminar */
  		if (dy < 0) { 
    		dy = -dy; 
    		stepy = -1; 
  		} else {
    		stepy = 1;
  		}

		if (dx < 0) {  
    		dx = -dx;  
    		stepx = -1; 
  		} else {
    		stepx = 1;
  		}

		x = x0;
  		y = y0;
		putPixel(y0,x0);

		/* se cicla hasta llegar al extremo de la línea */
  		if(dx>dy) {
    		p = 2*dy - dx;
    		incE = 2*dy;
    		incNE = 2*(dy-dx);
    		while (x != x1) {
      		x = x + stepx;
      		if (p < 0){
        			p = p + incE;
      		} else {
        			y = y + stepy;
        			p = p + incNE;
      		}
      		putPixel(y,x);
    		}
  		} else {
    		p = 2*dx - dy;
    		incE = 2*dx;
    		incNE = 2*(dx-dy);
    		while (y != y1) {
      		y = y + stepy;
      		if (p < 0) {
        			p = p + incE;
      		} else {
        			x = x + stepx;
        			p = p + incNE;
      		}
      		putPixel(y,x);
    		}
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
