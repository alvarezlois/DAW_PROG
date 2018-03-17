package daw.iesrodeira.xeometria;

public class Pantalla {
	char[][] screen;

	public Pantalla(int szx,int szy) {
		screen=new char[szy][szx];
		clear();
	}
	

	public void clear() {
		int f;
		int c;

		f=0;
		while(f<screen.length) {
			c=0;
			while(c<screen[0].length) {
				screen[f][c]=' ';
			}
			f=f+1;
		}
	}

	public void lineaH(int dx,int dy,int ancho) {
		int c=0;
		while(c<ancho) {
			screen[dy][dx+c]='*';
			c=c+1;
		}
	}

	public void lineaV(int dx,int dy,int alto) {
		int f=0;
		while(f<alto) {
			screen[dy+f][dx]='*';
			f=f+1;
		}
	}

	public void show() {
		int f;
		int c;

		f=0;
		while(f<screen.length) {
			c=0;
			while(c<screen[0].length) {
				System.out.print(screen[f][c]);
			}
			System.out.println();
			f=f+1;
		}
	}

	public setPoint(int x,int y,char value) {
		screen[y][x]=value;
	}
}
