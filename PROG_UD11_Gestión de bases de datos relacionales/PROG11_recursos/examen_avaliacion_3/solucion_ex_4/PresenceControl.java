package TecnoWorkDriver;

import java.util.Calendar;
import ControlPresencial.*;

class PresenceControl implements TecnoWorkControlDriver {
	private TecnoWorkControlManager driver=null;
	private KeepPresence db=new MySQLPresence();
	private int status;

	PresenceControl(TecnoWorkControlManager mgr) {
		driver=mgr;
		status=0;
	}

	public void inButtonPressed() {
		System.out.println("Registering In");
		driver.ledOn(1);
		driver.active();
		status=1;
	}

	public void outButtonPressed() {
		System.out.println("Registering Out");
		driver.ledOn(4);
		driver.active();
		status=2;
	}

	public void identificationRead(String id) {
		try {
			System.out.println(status+" Searching "+id);
			Persoal p=db.getPersonal(id);	
			p.controlHorario(status-1,time);
			driver.ledOn(5);
			driver.passive();
			driver.openDoor();
			driver.ledOff();
			long time=Calendar.getInstance().getTimeInMillis();
			db.registerPresence(p,time,status-1);
		} catch(Exception e) {
			System.out.println("Not Authorized!!");
		}
		status=0;
	}
}

