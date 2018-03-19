import java.util.Scanner;

enum Meses { 
   Xaneiro, Febreiro, Marzo, Abril, Maio, Xuño, Xullo, Agosto, Setembro, Outubro, Novembro, Decembro 
}


class Exercicios {
   
   static int factorial (int n) {
      int f=1;
      while (n > 1) {
         f=f*n;
         n--;
      }
      return f;
   }

   static void tabla(int n) {
      System.out.println("Taboa do "+n);
      for(int i=1; i<=10 ; i++) {
         System.out.println(n+"*"+i+"="+(n*i));
      }
   }

   static void tabla() {
      System.out.println("Taboas de Multiplicar");
      for(int i=1; i<=10; i++) tabla(i);
   }

   static float numero_e(int precision) {
      float e=0;
      for(int i=0;i<precision;i++) {
         e=e+1/factorial(i);
      }
      return e;
   }

   static boolean ePrimo(int n) {
      for (int i=2; i<n ; i++) {
         if (n % i == 0) return false;
      }
      return true;
   }

   static int voltea(int n) {
      int nv=0;
      int d;
      while (n > 0) {
         d=n%10;
         nv=(nv*10)+d;
         n=n/10;
      }
      return nv;
   }

   String toRoman(int n) {
      String roman="";
      int r;

      r=n/1000; n=n%1000;
      for(int i=0;i<r;i++) roman=roman+"M";
      r=n/900; n=n%900;
      if (r>0) roman=roman+"CM";
      r=n/500; n=n%500;
      if (r>0) roman=roman+"D";
      r=n/400; n=n%400;
      if (r>0) roman=roman+"CD";
      r=n/100; n=n%100;
      for(int i=0;i<r;i++) roman=roman+"C";
      r=n/90; n=n%90;
      if (r>0) roman=roman+"XC";
      r=n/50; n=n%50;
      if (r>0) roman=roman+"L";
      r=n/40; n=n%40;
      if (r>0) roman=roman+"XL";
      r=n/10; n=n%10;
      for(int i=0;i<r;i++) roman=roman+"X";
      r=n/5; n=n%5;
      if (r>0) roman=roman+"V";
      r=n/4; n=n%4;
      if (r>0) roman=roman+"IV";
      for(int i=0;i<n;i++) roman=roman+"I";
      return roman;
   }

   static boolean eCapicua(int n) {
      int v=voltea(n);
      if (n==v) return true;
      return false;
   }

   static int max(int a, int b) {
      //return ( a > b) ? a:b;
      if (a > b) return a;
      return b;
   }

   public static boolean ePar(int n) {
      return ((n%2)==0);
   }

   public static int diasMes(Meses mes, int ano) {
      int dias;

      switch(mes) {
         case Xaneiro:    case Marzo:
         case Maio:       case Xullo:
         case Agosto:     case Outubro:
         case Decembro:   
            dias=31; 
            break;
         case Febreiro: 
            if (eBisiesto(ano)) dias=29;
            else                dias=28;
            break;
         default: 
            dias=30;
      }
      return dias;
   }

   static boolean eBisiesto(int ano) {
      if (((ano%4==0) && (ano%100 !=0 ))||(ano%400==0)) return true;
      return false;
   }

   static void visualizaFactores(int num) {
      int f=2;

      while(num > 1) {
         if (num % f != 0) f=f+1;
         else {
            System.out.print(f+" ");
            num=num/f;
         }
      }
      System.out.println("1");
   }

   public static char getCategoria(int n) {
      if (n >= 51) return 'C';
      else  if (n <= 25) return 'A';
      return 'B';
   }

	public static void main(String[] args) {
		Scanner scn=new Scanner(System.in);
		System.out.println("Introduce un Número: ");
		int n=Integer.parseInt(scn.nextLine());
		if (Exercicios.ePar(n))
			 System.out.println(n+" é Par");		
      else
          System.out.println(n+" NON é Par");
      
      System.out.print("Mes? ");
      String mes=scn.nextLine();
      System.out.print("Ano? ");
      int ano=Integer.parseInt(scn.nextLine());
      System.out.println("O mes de "+mes+" de "+ano+" ten "+Exercicios.diasMes(Meses.valueOf(mes),ano));
		System.out.print("Numero a descompoñer?");
      n=Integer.parseInt(scn.nextLine());
      visualizaFactores(n);
      System.out.println("Nome? ");
      String nome=scn.nextLine();
      System.out.println("Edade?");
      n=Integer.parseInt(scn.nextLine());
      System.out.println(nome+" terá "+(n+10)+" Anos dentro de 10 anos, e pertence á categoria "+getCategoria(n));
	}
}
