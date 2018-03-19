package ControlPresencial;

import java.util.Date;

public class DropBoxPresence implements KeepPresence {
	String[] status={"Entrada","Saida"};

	public void registerPresence(Persoal p,long time, int type) {
		System.out.println("Recording presence "+status[type]+" of "+p+" at "+new Date(time));
	}

	public Persoal getPersonal(String id) throws Exception {
		if (id.equals("test")) return new Traballador(id,"TEST","","",0);
		throw new Exception(id+" Not Found!!");
	}
}
