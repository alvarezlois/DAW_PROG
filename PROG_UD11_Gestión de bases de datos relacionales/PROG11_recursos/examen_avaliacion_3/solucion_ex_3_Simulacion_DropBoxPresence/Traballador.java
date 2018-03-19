package ControlPresencial;

import java.util.Date;
import java.util.Calendar;

class Traballador extends Persoal {
	
	Traballador(String id,String nome,String telefono,String email) {
		super(id,nome,telefono,email,0);
	}

	public void controlHorario(int inorout,long time) throws Exception {
		Calendar c=Calendar.getInstance();
		Date t=new Date(time);

		if (inorout==1) {
			c.set(Calendar.HOUR_OF_DAY,19);
			c.set(Calendar.MINUTE,0);
			Date l=new Date(c.getTimeInMillis());
			if (t.compareTo(l) > 0) bonus=5;
		} else if (inorout==0) {
			c.set(Calendar.HOUR_OF_DAY,10);
			c.set(Calendar.MINUTE,0);
			Date l=new Date(c.getTimeInMillis());
			if (t.compareTo(l) > 0) bonus=-5;
			System.out.println("Comparando "+t+" con "+l);
		}
		System.out.println("BONO: "+bonus);
	}
}
