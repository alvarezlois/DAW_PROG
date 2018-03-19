import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Copyfile {
	public static void main(String[] args) throws Exception {
		BufferedInputStream r=null;
		FileOutputStream w=null;
		byte[] data=new byte[4096];
		int nc,nb;

		if (args.length!=2) System.out.println("java Copyfile origen destino");
		else {
			try {
				nb=0;
				r=new BufferedInputStream(new FileInputStream(args[0]));
				w=new FileOutputStream(args[1]);
				while((nc=r.read(data))!=-1) {
					w.write(data,0,nc);
					nb+=nc;
				}
				System.out.println("Copiados "+nb+" bytes");
			} catch (Exception e) {
				System.out.println("Error contando líneas: "+e.getMessage());
			} finally {
				if (r!=null) r.close();
				if (w!=null) w.close();
			}
		}
	}
}
