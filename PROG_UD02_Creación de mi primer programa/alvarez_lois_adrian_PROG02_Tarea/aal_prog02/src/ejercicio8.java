import java.util.Scanner;

public class ejercicio8 {
    /**
     * metodo principal
     * @param args argumentos da liña de comandos
     */
    public static void main(String[] args) {
        // leemos os números por teclado
        Scanner teclado = new Scanner( System.in );
        double x, y;
        System.out.print( "Introducir x: " ); 
        x = teclado.nextDouble();
        System.out.print( "Introducir y: " );
        y = teclado.nextDouble(); 
       
        // calculamos e amosamos os resultados das operacións
        System.out.printf("\nx=%.1f  y=%.1f", x,y);
        System.out.printf("\nx+y=%.1f", x+y);
        System.out.printf("\nx-y=%.1f", x-y);
        System.out.printf("\nx*y=%.1f", x*y);
        System.out.printf("\nx^2=%.1f", Math.pow(x, 2));
        System.out.printf("\n√x=%.1f", Math.sqrt(x));
        
    }
}
