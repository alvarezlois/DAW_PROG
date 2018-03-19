
public class ejercicio6 {
    // creamos o tipo enumerado
    enum mes { XANEIRO, FEBREIRO, MARZO, ABRIL, MAIO, XUÑO, XULLO, AGOSTO, 
               SETEMBRO, OUTUBRO, NOVEMBRO, DECEMBRO }
    
    /*
     * metodo main
     * @param args argumentos da liña de comandos
     */
    public static void main(String[] args) {
        // creamos variable do tipo mes
        mes m = mes.MARZO;
        System.out.println("O valor da variable m é " + m );
    }
}
