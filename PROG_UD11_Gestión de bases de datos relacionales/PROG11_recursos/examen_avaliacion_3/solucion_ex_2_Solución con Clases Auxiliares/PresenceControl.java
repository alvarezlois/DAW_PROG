package TecnoWorkDriver;

class PresenceControl implements TecnoWorkControlDriver {
	private TecnoWorkControlManager driver=null;

	PresenceControl(TecnoWorkControlManager mgr) {
		driver=mgr;
	}

	public void inButtonPressed() {
		System.out.println("Registering In");
		driver.ledOn(1);
		driver.active();
	}

	public void outButtonPressed() {
		System.out.println("Registering Out");
		driver.ledOn(4);
		driver.active();
	}

	public void identificationRead(String id) {
		System.out.println("Identification "+id+" read ");
		driver.ledOn(5);
		driver.passive();
		driver.openDoor();
		driver.ledOff();
	}
}

