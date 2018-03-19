

import java.util.Scanner;

public class ejercicio5 {

    /**
     * metodo principal
     * @param args argumentos da liña de comandos
     */
    public static void main(String[] args) {
        Scanner teclado = new Scanner( System.in );
        int x, y;
        String mensaje;
        System.out.print( "Introducir primer número enteiro : " ); 
        x = teclado.nextInt();
        System.out.print( "Introducir segundo número enteiro: " );
        y = teclado.nextInt(); 
        // construimos mensaxe de saida en función de si é multiplo ou non
        mensaje = x%y == 0 ? " é múltiplo de " : " non é múltiplo de ";
        System.out.println(x+mensaje+y);
    }
}
