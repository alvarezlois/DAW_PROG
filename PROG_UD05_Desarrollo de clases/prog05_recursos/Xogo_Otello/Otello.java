import java.util.Scanner;
import com.rodeira.xogos.mesa.*;

class Otello {
	private Tableiro t;
	private int turno;
   private String[] xog={"Xogador 1","Xogador 2"}   

	Otello() {
		init();
		t.show();
	}

   Otello(String x1,String x2) {
      xog[0]=x1;
      xog[1]=x2;
      init();
      t.show();
   }

	void init() {
		turno=0;
		t=new Tableiro(8,8);
		t.put(3,3,new Ficha(0));
		t.put(4,4,new Ficha(0));
		t.put(3,4,new Ficha(1));
		t.put(4,3,new Ficha(1));
	}

   private boolean reversiV(int f,int c,boolean r) {
      Ficha ficha=null;
      boolean ok=false;
      int f1=f-1;
      int alto;

      alto=t.getAlto();
      //Arriba
      //
      if (f>0) {
         ficha=t.get(f1,c);
         if (ficha!=null) {
            while((f1 >= 0)&&(ficha!=null)&&(!ficha.equals(turno))) {
               f1--;
               ficha=t.get(f1,c);
            }
            if ((f1>=0)&&(ficha!=null)&&(ficha.equals(turno))&&(f1!=(f-1))) {
               ok=true;
               if (r) {
                  for(int i=f1+1;i<f;i++) {
                     t.put(i,c,ficha);
                  }
                  t.put(f,c,ficha);
               }
            }
         }
      }
      // Abaixo
      //
      if (f<(alto-1)) {
         f1=f+1;
         ficha=t.get(f1,c);
         if (ficha!=null) {
            while((f1 < alto)&&(ficha!=null)&&(!ficha.equals(turno))) {
               f1++;
               ficha=t.get(f1,c);
            }
            if ((f1< alto)&&(ficha!=null)&&(ficha.equals(turno))&&(f1!=(f+1))) {
               ok=true;
               if (r) {
                  for(int i=f1-1;i>f;i--) {
                     t.put(i,c,ficha);
                  }
                  t.put(f,c,ficha);
               }
            }
         }
      }
      return ok;

   }

   private boolean reversiDD(int f,int c,boolean r) {
      Ficha ficha=null;
      boolean ok=false;
      int f1,c1;
      int alto,ancho;

      alto=t.getAlto();
      ancho=t.getAncho();
      //Arriba
      //
      if ((f>0)&&(c<(ancho-1))) {
         c1=c+1;
         f1=f-1;
         ficha=t.get(f1,c1);
         if (ficha!=null) {
            while((f1 >= 0)&&(c1 < ancho)&&(ficha!=null)&&(!ficha.equals(turno))) {
               f1--; c1++;
               ficha=t.get(f1,c1);
            }
            if ((f1>=0)&&(ficha!=null)&&(c1<ancho)&&(ficha.equals(turno))&&(f1!=(f-1))) {
               ok=true;
               if (r) {
                  c1--;
                  for(int i=f1+1;i<f;i++,c1--) {
                     t.put(i,c1,ficha);
                  }
                  t.put(f,c,ficha);
               }
            }
         }
      }
      // Abaixo
      //
      if ((f<(alto-1))&&(c>0)) {
         f1=f+1;
         c1=c-1;  
         ficha=t.get(f1,c1);
         if (ficha!=null) {
            while((f1 < alto)&&(c1>=0)&&(ficha!=null)&&(!ficha.equals(turno))) {
               f1++; c1--;
               ficha=t.get(f1,c1);
            }
            if ((f1< alto)&&(ficha!=null)&&(c1>=0)&&(ficha.equals(turno))&&(f1!=(f+1))) {
               ok=true;
               if (r) {
                  c1++;
                  for(int i=f1-1;i>f;i--,c1++) {
                     t.put(i,c1,ficha);
                  }
                  t.put(f,c,ficha);
               }
            }
         }
      }
      return ok;

   }

   private boolean reversiDI(int f,int c,boolean r) {
       Ficha ficha=null;
      boolean ok=false;
      int f1,c1;
      int alto,ancho;

      alto=t.getAlto();
      ancho=t.getAncho();
      //Arriba
      //
      if ((f>0)&&(c>0)) {
         c1=c-1;
         f1=f-1;
         ficha=t.get(f1,c1);
         if (ficha!=null) {
            while((f1 >= 0)&&(c1 >= 0)&&(ficha!=null)&&(!ficha.equals(turno))) {
               f1--; c1--;
               ficha=t.get(f1,c1);
            }
            if ((f1>=0)&&(ficha!=null)&&(c1 >= 0)&&(ficha.equals(turno))&&(f1!=(f-1))) {
               ok=true;
               if (r) {
                  c1++;
                  for(int i=f1+1;i<f;i++,c1++) {
                     t.put(i,c1,ficha);
                  }
                  t.put(f,c,ficha);
               }
            }
         }
      }
      // Abaixo
      //
      if ((f<(alto-1))&&(c<(ancho-1))) {
         f1=f+1;
         c1=c+1;  
         ficha=t.get(f1,c1);
         if (ficha!=null) {
            while((f1 < alto)&&(c1 < ancho)&&(ficha!=null)&&(!ficha.equals(turno))) {
               f1++; c1++;
               ficha=t.get(f1,c1);
            }
            if ((f1< alto)&&(ficha!=null)&&(c1>=0)&&(ficha.equals(turno))&&(f1!=(f+1))) {
               ok=true;
               if (r) {
                  c1--;
                  for(int i=f1-1;i>f;i--,c1--) {
                     t.put(i,c1,ficha);
                  }
                  t.put(f,c,ficha);
               }
            }
         }
      }
      return ok;
   }

   private boolean reversiH(int f, int c, boolean r) {
      Ficha ficha=null;
      boolean ok=false;
      int c1=c-1;
      int ancho;

      ancho=t.getAncho();
      //Esquerda
      //
      if (c>0) {
         ficha=t.get(f,c1);
         if (ficha!=null) {
            while((c1 >= 0)&&(ficha!=null)&&(!ficha.equals(turno))) {
               c1--;
               ficha=t.get(f,c1);
            }
            if ((c1>=0)&&(ficha!=null)&&(ficha.equals(turno))&&(c1!=(c-1))) {
               ok=true;
               if (r) {
                  for(int i=c1+1;i<c;i++) {
                     t.put(f,i,ficha);
                  }
                  t.put(f,c,ficha);
               }
            }
         }
      }
      // Dereita
      //
      if (c<(ancho-1)) {
         c1=c+1;
         ficha=t.get(f,c1);
         if (ficha!=null) {
            while((c1 < ancho)&&(ficha!=null)&&(!ficha.equals(turno))) {
               c1++;
               ficha=t.get(f,c1);
            }
            if ((c1< ancho)&&(ficha!=null)&&(ficha.equals(turno))&&(c1!=(c+1))) {
               ok=true;
               if (r) {
                  for(int i=c1-1;i>c;i--) {
                     t.put(f,i,ficha);
                  }
                  t.put(f,c,ficha);
               }
            }
         }
      }
      return ok;
   }

   private boolean reversi(int f,int c,boolean revira) {
      boolean ok=false;
      
      /*
      ok = ok || reversiH(f,c,true);
      ok = reversiV(f,c,true) || ok;
      ok = reversiDD(f,c,true) || ok;
      ok = reversiDI(f,c,true)) || ok;
      return ok;*/
      if ((f<0)||(f >= t.getAlto())) return false;
      if ((c<0)||(c >= t.getAncho())) return false;
      if (t.get(f,c)!=null) return false;
      if (reversiH(f,c,revira)) ok=true;
      if (reversiV(f,c,revira)) ok=true;
      if (reversiDD(f,c,revira)) ok=true;
      if (reversiDI(f,c,revira)) ok=true;
      return ok;
   }

   private void showWinner() {
      System.out.println("Non sei... oh!! qué mais dará...");
   }

   private boolean endGame() {
      boolean pode=false;
      int alto,ancho;
      
      alto=t.getAlto();
      ancho=t.getAncho();
      for(int f=0;(f<alto);f++) {
         for(int c=0;(c<ancho); c++) {
            if (reversi(f,c,false)) return false;
            else {
               turno=1-turno;
               pode=pode||reversi(f,c,false);
               turno=1-turno;
            }
         }
      }
      turno=1-turno;
      return (!pode);
   }


   private void faiXogada() {
      int alto=t.getAlto();
      int ancho=t.getAncho();

      for(int f=0;(f<alto);f++) {
         for(int c=0;(c<ancho); c++) {
            if (reversi(f,c,true)) return;
         }
      }
   }

   private showWinner() {
      int f1=0;   
      int f2=0;

      int ancho=t.getAncho();
      int alto=t.getAlto();
      for(f=0;f<alto;f++)
         for(c=0;c<ancho;c++) {
            Ficha f=t.get(f,c);
            if (f!=null) {
               if (f.equals(0))  f1++;
               else              f2++;
            }
         }
      if (f1>f2) System.out.println("Noraboa, "+xog[0]);
      else if (f2>f1) System.out.println("Noraboa, "+xog[1]);
      else  System.out.println("Foi un honroso empate!!");
      System.out.println("O resultado foi de "+f1+" a "+f2);
   }

   public void run() {
      int f; int c; 
      boolean ok;
      Scanner scn=new Scanner(System.in);
      while (!endGame()) {
         try {
            System.out.println("Turno do "+xog[turno]+" ("+Ficha.fichaTurno(turno)+")");
            if (xog[turno].equals("Computer")) {
               faiXogada();
               ok=true;
            } else  {
               System.out.print("Fila?:");
               f=Integer.parseInt(scn.nextLine());
               System.out.print("Columna?:");
               c=Integer.parseInt(scn.nextLine());
               ok=reversi(f,c,true);
            }
            if (!ok) System.out.println("Movimento Erróneo, move de novo");
            else {
                System.out.println(xog[turno]+" xogou en ("+f+","+c+")");
                turno=1-turno;
            }
         } catch (NumberFormatException e) {
            System.out.println("Debes escribir un número entre 0 e 7, move de novo");
         }
         catch(Exception e) {
            System.out.println("Erro na entrada, move de novo");
         }
         t.show();
      }
      showWinner();

   }

	public static void main(String[] args) {
      Otello game;
      if (args.length==2) {
         game=new Otello(args[0],args[1]);
      } else Otello game=new Otello();
      game.run();
	}
}
