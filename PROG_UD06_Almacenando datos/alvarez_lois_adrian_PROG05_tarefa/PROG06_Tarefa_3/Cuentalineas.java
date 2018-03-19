import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;

public class Cuentalineas {
	public static void main(String[] args) throws Exception {
		BufferedReader r=null;
		int nl=0;
		if (args.length!=1) System.out.println("java Cuentalineas fichero");
		else {
			try {
				r=new BufferedReader(new InputStreamReader(new FileInputStream(args[0])));
				while(r.readLine()!=null) nl++;
				System.out.println(nl+" líneas");
			} catch (Exception e) {
				System.out.println("Error contando líneas: "+e.getMessage());
			} finally {
				if (r!=null) r.close();
			}
		}
	}
}
