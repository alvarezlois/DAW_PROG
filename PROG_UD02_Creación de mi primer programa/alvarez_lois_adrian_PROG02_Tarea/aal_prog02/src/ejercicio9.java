import java.util.Scanner;

public class ejercicio9 {

    /**
     * metodo principal
     * @param args argumentos da liña de comandos
     */
    public static void main(String[] args) {
        // leemos o número por teclado
        Scanner teclado = new Scanner( System.in );
        int x;
        // gardaremos cada díxito nun byte
        byte dm, um, c, d, u;
        System.out.print( "Introducir un número de cinco cifras: " );
        x = teclado.nextInt();
        
        dm = (byte)(x / 10000);  // calculamos as decenas de millar
        x = x -10000*dm;         // e nos quedamos so con catro cifras
        
        um = (byte)(x / 1000);   // calculamos as unidades de millar
        x = x -1000*um;          // e nos quedamos so con tres cifras
        
        c = (byte)(x / 100);     // calculamos as centenas
        x = x -100*c;            // e nos quedamos so con duas cifras
        
        d = (byte)(x / 10);      // calculamos as centenas
        u = (byte)(x -10*d);     // e nos quedamos so con unha cifra
        // amosamos os dixitos obtidos
        System.out.printf("\n%d  %d  %d  %d  %d", dm, um, c, d, u);       
    }
}
