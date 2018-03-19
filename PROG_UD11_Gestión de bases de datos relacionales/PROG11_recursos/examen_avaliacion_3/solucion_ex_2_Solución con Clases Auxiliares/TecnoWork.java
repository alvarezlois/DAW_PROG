package TecnoWorkDriver;

import java.util.Scanner;

class TecnoWork {
	private int led;
	private boolean mode;

	private TecnoWorkControlDriver driver=null;
	private TecnoWorkControlManager system=null;

	public static void main(String[] args) {
		System.out.println("Initializing... wait a moment.");
		TecnoWork app=new TecnoWork();	
		app.runDevice();
	}

	TecnoWork() {
		system=new TWDriver(this);
		driver=Manager.initPresenceControl(system);
	}

	void setLed(int c) {
		led=c;
	}

	void setMode(boolean mode) {
		this.mode=mode;
	}

	String color() {
		String[] colors={"Off","Blue","Red","Yellow","Green","Red Blink"};
		return colors[led];
	}

	private void showInterface() {
		System.out.println("Tecno Work System Presence Control");
		System.out.println("----------------------------------");
		System.out.println("Led Color: "+color());
		System.out.println("Read mode: "+mode);
		if (!mode) {
			System.out.println("(I)n   (O)out");
		} else {
			System.out.print("User ID: ");
		}
	}

	private void getAction() {
		Scanner in=new Scanner(System.in);
		String cmd=in.nextLine();
		if (mode) {
			driver.identificationRead(cmd);
		} else {
			switch(cmd.charAt(0)) {
				case 'I':	driver.inButtonPressed();
							break;
				case 'O':   driver.outButtonPressed();
							break;
			}
		}
	}

	private void runDevice() {
		System.out.println("Device Ready...");
		do {
			showInterface();
			getAction();
		} while(true);
	}
}
