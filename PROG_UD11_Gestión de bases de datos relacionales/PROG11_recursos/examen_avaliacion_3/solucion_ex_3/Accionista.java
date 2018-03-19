package ControlPresencial;

import java.util.Date;
import java.util.Calendar;

class Accionista extends Persoal {
	
	Accionista(String id,String nome,String telefono,String email) {
		super(id,nome,telefono,email,2);
	}

	public void controlHorario(int inorout,long time) throws Exception {
		Calendar c=Calendar.getInstance();
		Date t=new Date(time);
		c.set(Calendar.HOUR_OF_DAY,14);
		c.set(Calendar.MINUTE,0);
		Date l=new Date(c.getTimeInMillis());
		if (t.compareTo(l) > 0) throw new Exception("Not Authorized!!");
	}
}
