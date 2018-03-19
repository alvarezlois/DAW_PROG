package com.iesrodeira.domotica;

class ConnectionLamp extends Connection  {
	private final int MAXVALUE=10;
	private final int MINVALUE=0;
	private int value;
	private int color;

	/**
			Nun caso real, este constructor conectaría co dispositivo ip e obtería o seu estado actual
	*/
	ConnectionLamp(String ip) {
		super(ip);
		this.name="Lamp Rodeira Domotics Device";
		this.value=0; 	// Nun caso real obtería o valor do dispositivo....
		this.color=0;	// Nun caso real obtería o valor do dispositivo...
		System.out.println("Conectado a "+this.name);
	}

	void up() throws DomoticaException {
		if (this.value < MAXVALUE) this.value++;
		else throw new DomoticaException(DomError.ERRVALUE);
		System.out.println("Subida a intensidade a "+this.value);
	}

	void dn() throws DomoticaException {
		if (this.value > MINVALUE) this.value--;
		else throw new DomoticaException(DomError.ERRVALUE);
		System.out.println("Baixada a intensidade a "+this.value);
	}

	void setColor(int value) throws DomoticaException {
		this.color=value; 	// Nun caso real se conectaría co dispositivo e configuraría a súa cor
		System.out.println("Color posto a "+value);
	}

	int getColor() throws DomoticaException {
		return this.color;
	}

	int getValue() throws DomoticaException {
		return this.value;
	}

}
