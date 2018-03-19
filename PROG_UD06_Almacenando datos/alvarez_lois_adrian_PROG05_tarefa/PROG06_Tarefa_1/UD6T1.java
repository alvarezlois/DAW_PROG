import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.IOException;
import UD6T1.Persoa;
import UD6T1.PersoaException;

public class UD6T1 {
	public static void main(String[] args) {
		String dni,nome,direccion,telefono,email;
		Persoa p;
		BufferedReader input=new BufferedReader(new InputStreamReader(System.in));

		try {
			System.out.print("DNI: "); dni=input.readLine();
			Persoa.verificaDNI(dni);
			System.out.print("Nome: "); nome=input.readLine();
			System.out.print("Dirección: "); direccion=input.readLine();
			System.out.print("Teléfono: "); telefono=input.readLine();
			System.out.print("E-mail: "); email=input.readLine();
			p=new Persoa(dni,nome,direccion,telefono,email);
			System.out.println("Creado: "+p);
		} catch (IOException e) {
			System.out.println("Erro na entrada de datos ("+e.getMessage()+")");
		} catch (PersoaException e) {
			System.out.println("Erro creando Persoa: "+e.getMessage());
		}
	}
}
