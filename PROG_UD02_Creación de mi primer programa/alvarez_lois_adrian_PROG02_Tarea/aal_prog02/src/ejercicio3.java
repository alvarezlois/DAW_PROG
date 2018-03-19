
public class ejercicio3 { 
public static void main(String[ ] args) {
        // declaración e inicialización de variables
	boolean casado = true; 
	final int MAXIMO = 999999;
	byte diasem = 1;
	char sexo ='M';
	short diaanual = 300;
	long miliseg = 1298332800000L;
	double totalfactura = 10350.677734;
	long poblacion = 6775235741L;
	
        // Presentación das variables
	System.out.println("---- EJERCICIO DE VARIABLES Y TIPOS DE DATOS ----");
	System.out.println("\tEl valor de la variable casado es " + casado);
	System.out.println("\tEl valor de la variable MAXIMO es " + MAXIMO);
	System.out.println("\tEl valor de la variable diasem es " + diasem);
	System.out.println("\tEl valor de la variable diaanual es " + diaanual);
	System.out.println("\tEl valor de la variable miliseg es " + miliseg);
	System.out.println("\tEl valor de la variable totalfactura es " + (float)totalfactura);
	System.out.println("\tEl valor de la variable poblacion es " + poblacion);
	System.out.println("\tEl valor de la variable sexo es " + sexo);
	
	System.out.printf("\n---- EJERCICIO DE VARIABLES Y TIPOS DE DATOS ----");
	System.out.printf("\n\tEl valor de la variable casado es %b" , casado);
	System.out.printf("\n\tEl valor de la variable MAXIMO es %d" , MAXIMO);
	System.out.printf("\n\tEl valor de la variable diasem es %d" , diasem);
	System.out.printf("\n\tEl valor de la variable diaanual es %d" , diaanual);
	System.out.printf("\n\tEl valor de la variable miliseg es %d" , miliseg);
	System.out.printf("\n\tEl valor de la variable totalfactura es %.6f" , totalfactura);
	System.out.printf("\n\tEl valor de la variable totalfactura en notacion cient�fica es %e" , totalfactura);
	System.out.printf("\n\tEl valor de la variable poblacion es %d" , poblacion);
	System.out.printf("\n\tEl valor de la variable sexo es %s" , sexo);
	
} 
} 