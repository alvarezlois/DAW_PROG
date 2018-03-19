package com.rodeira.xogos.mesa;

public class Tableiro {
	private int ancho;
	private int alto;
	private Ficha[][] t;

	public Tableiro(int alto,int ancho) {
		this.ancho=ancho;
		this.alto=alto;
		t=new Ficha[alto][ancho];
		clear();
	}

	public void clear() {
		for(int f=0;f<alto;f++) {
			for(int c=0;c<ancho;c++) {
				t[f][c]=null;
			}
		}
	}

   public int getAncho() {
      return ancho;
   }

   public int getAlto() {
      return alto;
   }

	public void show() {
      System.out.print(" ");
      for(int c=0;c<ancho;c++) System.out.print(c);
      System.out.println();
		for(int f=0;f<alto;f++) {
         System.out.print(f);
			for(int c=0;c<ancho;c++) {
				if (t[f][c]!=null) t[f][c].show();
            else               System.out.print(" ");
			}
         System.out.println();
		}
	}

	public void put(int y,int x,Ficha f) {
		t[y][x]=f;
	}
	
	public Ficha get(int y,int x) {
		return t[y][x];
	}
}
