package com.rodeira.xogos.mesa;

public class Ficha {
	private int cor;

	public Ficha(int cor) {
		this.cor=cor;
	}

   public static String fichaTurno(int cor) {
      switch(cor) {
         case 0: return "x";
         case 1: return "o";
      }
      return " ";
   }

   public void show() {
      System.out.print(fichaTurno(cor));
   }

   public int getCor() {
      return cor;
   }

   public boolean equals(Ficha f) {
      return (cor==f.getCor());
   }

   public boolean equals(int c) {
      return (cor==c);
   }
}
