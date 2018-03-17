import java.util.Scanner;
import com.iesrodeira.consumo.Cliente;
import com.iesrodeira.consumo.Suministro;
import com.iesrodeira.consumo.SuministroException;
import com.iesrodeira.consumo.CCCException;
import com.iesrodeira.consumo.DNIException;

class InformacionCliente {
	Scanner scn=null;

	public InformacionCliente() {
		scn=new Scanner(System.in);
	}

	public int menu() {
		int op;

		try {
			System.out.println("1.- Alta de Cliente");
			System.out.println("2.- Acceso ós datos de consumo");
			System.out.println("3.- Saír");
			String line=scn.nextLine();
			op=Integer.parseInt(line);			
		} catch (NumberFormatException e) {
			op=4;
		}
		return op;
	}

	public void altaCliente() {
		String password;
		String tarifa;
		float potencia;

		try {
			Cliente cl=Cliente.newClienteConsole();
			System.out.println("Tarifa?: ");
			tarifa=scn.nextLine();
			System.out.println("Potencia Contratada?: ");
			potencia=Float.parseFloat(scn.nextLine());
			System.out.println("Introduce a password: ");
			password=scn.nextLine();
			Suministro sum=new Suministro(cl,tarifa,potencia,password);
			System.out.println("Cliente dado de alta : "+cl+" (Código de Suministro: "+sum.getCodigo()+")");
		} 	catch (NumberFormatException e) {
			System.out.println("A potencia debe ser un número decimal en KW");
		}	catch (CCCException e) {
			System.out.println(e.getMessage());
		} catch (DNIException e) {
			System.out.println(e.getMessage());
		} catch (SuministroException e) {
			System.out.println(e.getMessage());
		}
	}

	public void datosConsumo() {
		String nums,password;
		Suministro sum;

		try {
			System.out.println("Introduce o código de Suministro: ");
			nums=scn.nextLine();
			System.out.println("Introduce a password: ");
			password=scn.nextLine();
			sum=Suministro.getInstance(nums,password);
			System.out.println("Datos do Suministro: "+sum.getCliente());
			System.out.println("Nº Suministro: "+sum.getCodigo());
			System.out.println("Potencia Contratada: "+sum.getPotencia());
			System.out.println("Tipo de Tarifa: "+sum.getTarifa());
			System.out.println("Consumo en KW/h: "+sum.getConsumo());
			System.out.println("Importe Factura: "+sum.getImporteFactura());
		} catch(SuministroException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {
		int op;
		InformacionCliente ic;

		ic=new InformacionCliente();
		do {
			op=ic.menu();
			switch(op) {
				case 1: ic.altaCliente();
						  break;
				case 2: ic.datosConsumo();
						  break;
			}			
		} while(op!=3);
	}
}
