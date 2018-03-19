package TecnoWorkDriver;

class TWDriver implements TecnoWorkControlManager {
	private TecnoWork tw=null;

	TWDriver(TecnoWork tw) {
		this.tw=tw;
	}

	public void ledOn(int color) {
		tw.setLed(color);
		System.out.println("Led en "+tw.color());
	}

	public void ledOff() {
		tw.setLed(0);
		System.out.println("Led apagado");
	}

	public void passive() {
		tw.setMode(false);
		System.out.println("Modo pasivo");
	}

	public void active() {
		tw.setMode(true);
		System.out.println("Modo activo");
	}
	
	public void openDoor() {
		System.out.println("Abrindo a porta. Se pechará en 10 segundos.!!");
	}
}
