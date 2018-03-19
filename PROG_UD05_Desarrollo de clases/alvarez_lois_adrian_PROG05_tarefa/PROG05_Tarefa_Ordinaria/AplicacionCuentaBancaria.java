import java.util.Scanner;

public class AplicacionCuentaBancaria {

	/**
		Visualiza as opcións do menú, acepta a elección de usuario e insiste mentras a opción non sexa válida

		@param in - Obxecto da clase Scanner para realizar a entrada por teclado
		@returns  - Opción elexida
	*/
	private static int opciones(Scanner in) {
		int op=-1;

		do {
			System.out.println("\n---------- Menú ------------");
			System.out.println("1 - Ver el número de cuenta completo (CCC - Código Cuenta Cliente).");
			System.out.println("2 - Ver el titular de la cuenta.");
			System.out.println("3 - Ver el código de la entidad.");
			System.out.println("4 - Ver el código de la oficina.");
			System.out.println("5 - Ver el número de la cuenta.");
			System.out.println("6 - Ver los dígitos de control de la cuenta.");
			System.out.println("7 - Realizar un ingreso. ");
			System.out.println("8 - Retirar efectivo.");
			System.out.println("9 - Consultar saldo.");
			System.out.println("10 - Salir de la aplicación.");
			try {
				System.out.println();
				System.out.println("Elixe Opcion: ");
				op=in.nextInt();
			} catch (Exception e) {
				System.out.println("Opcion Errónea");
			}
		} while ((op<1) && (op>10));
		return op;
	}

	/**
		=================== Método Principal ============================
	*/
	public static void main(String[] args) {
		Scanner input=null;
		CuentaBancaria cb=null; // Variable para garda-lo obxecto CuentaBancaria sobre o que imos traballar 
		boolean ok=false;	 // Variable que indica cando os datos son correctos
		int op;				 

		// Pedimos datos da conta bancaria a crear
		do {
			try {
				input=new Scanner(System.in);
				System.out.println("Nome do Depositante: ");
				String nome=input.nextLine();
				System.out.println("Número CCC:");
				String ccc=input.nextLine();
				cb=new CuentaBancaria(ccc,nome);		
				ok=true;
			} catch(CuentaBancariaException e) {
				System.out.println("El número CCC no es Correcto: "+e.getMessage());
			}
			catch(Exception e) {
				System.out.println("Error leyendo datos: "+e.getMessage());
			}
		} while(!ok);
		
		// Solicitamos a opción, e realizamos a acción correspondente mentras non elixamos a opción de saír.
		do {
			op=AplicacionCuentaBancaria.opciones(input);
			switch(op) {
				case 1: 	System.out.println("\nEl número CCC es "+cb.getCCC());
							break;
				case 2:	System.out.println("El titular de la cuenta es "+cb.getTitular());
							break;
				case 3: 	System.out.printf("El Código de Entidad es %d (%s) ",cb.getCodigoEntidad(),cb.getEntidad());
							break;
				case 4:	System.out.println("El codigo de oficina es "+cb.getOficina());
							break;	
				case 5:	System.out.println("El Número de Cuenta es "+cb.getCuenta());
							break;	
				case 6:	System.out.println("Los dígitos de control son "+cb.getDigitosControl());
							break;
				case 7:	try {
								System.out.println("Introduce Importe:");
								double importe=input.nextDouble();
								cb.ingreso(importe);
								System.out.println("Ingreso efectuado, tu nuevo saldo es de "+cb.getSaldo());
							} catch (Exception e) {
								System.out.println("Error en el Importe: "+e.getMessage());
							}
							break;
				case 8:	try {
								System.out.println("Importe a Retirar:");
								double importe=input.nextDouble();
								cb.retirada(importe);
								System.out.println("Retirada efectuada, tu nuevo saldo es de "+cb.getSaldo());
							} catch (CuentaBancariaException e) {
								System.out.println("Error Retirando Importe: "+e.getMessage());
							}
							catch (Exception e) {
								System.out.println("Error en el Importe: "+e.getMessage());
							}
							break;
				case 9:	System.out.println("El saldo de la cuenta es "+cb.getSaldo());
							break;
			}
		} while(op!=10);
	}
}
