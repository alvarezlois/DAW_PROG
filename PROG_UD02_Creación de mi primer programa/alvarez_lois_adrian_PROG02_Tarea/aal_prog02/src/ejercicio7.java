
import java.util.Scanner;


public class ejercicio7 {
     /*
     * metodo main
     * @param args argumentos da liña de comandos
     */
    public static void main(String[] args) {
        // leemos coeficientes por teclado
        Scanner teclado = new Scanner( System.in );
        // Utilizaremos variables double para poder entrar números reales
        double c1, c2;
        System.out.print( "Introducir coeficiente c1: " ); 
        c1 = teclado.nextDouble();
        System.out.print( "Introducir coeficiente c2: " );
        c2 = teclado.nextDouble(); 
        // mostramos la solución
        System.out.println("X = " + (double)(-c2/c1) );
    }
}
