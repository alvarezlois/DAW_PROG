import java.net.*; 
import java.io.*; 

/*
	En este exemplo se lee a páxina indicada na liña de comandos nun fío de execución distinto ao principal
	Aquí non sería necesario empregar máis de un único fío, xa que a aplicación non fai ningunha outra cousa, pero
	normalmente sí é necesario xa que o fío principal debe seguir encargándose da xestión dos eventos do usuario para
	o manexo do GUI (entorno gráfico de usuario) e que sexa posible pulsar botóns, maximizar/minimizar/pechar fiestras, desplegar
	menús... etc.
*/

public class Leeweb implements Runnable { 
	URL paxweb; 
	String pax;
 
	public Leeweb(String url) { 
		System.out.println("Exemplo de conexión cun servidor WEB"); 
		try { 
			paxweb=new URL(url); 
		} catch (MalformedURLException e) { 
			System.out.println("A páxina "+url+" non existe"); System.exit(0); 
		} 
	} 

	public void run() { 
		URLConnection conn=null; 
		InputStreamReader in; 
		BufferedReader data; 
		String line; 
		StringBuffer buf=new StringBuffer(); 
		try { 
			conn=paxweb.openConnection(); 
			conn.connect(); 
			System.out.println("Conexión establecida");
			in=new InputStreamReader(conn.getInputStream()); 
			data=new BufferedReader(in);	
			System.out.println("Recibindo datos...");
			while((line=data.readLine())!=null) { 
				buf.append(line+"\n"); 
			} 
			System.out.println(buf.toString()); 
		} catch (IOException e) { 
			System.out.println("IO Error: "+e.getMessage()); 
		} 
	}
 
	/* Para executar a aplicación escribir:

			Leeweb http://url
	*/
	public static void main(String[] args) { 
		Leeweb w=new Leeweb(args[0]); 
		Thread th=new Thread(w).start(); 	// Creamos e lanzamos o fío
		th.join();							// Esperamos a que o fío finalice. Antes poderíamos facer calqueira cousa de xeito simultáneo á descarga da informacion.
	} 
} 

