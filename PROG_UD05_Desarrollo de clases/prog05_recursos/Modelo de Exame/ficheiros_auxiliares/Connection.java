package com.iesrodeira.domotica;

class Connection  {
	private String name="Generic Rodeira Domotics Device";
	private boolean status;
	private String ip;
	

	/**
			Nun caso real, este constructor conectaría co dispositivo ip e obtería o seu estado actual
	*/
	Connection(String ip) {
		System.out.println("Abrindo conexión con "+name+" e obtendo estado");
		this.ip=ip;
		status=false;
		
	}

	boolean getStatus() throws DomoticaException {
		return status;
	}

	/**
			Nun caso real, este método conectaría co dispositivo e o prendería
	*/
	void on() throws DomoticaException {
		System.out.println("Encendendo Dispositivo "+name);
		status=true;
	}

	/**
			Nun caso real, este método conectaría co dispositivo e o apagaría
	*/
	void off() throws DomoticaException {
		System.out.println("Apagando Dispositivo "+name);
		status=false;
	}

	void up() throws DomoticaException {
		throw new DomoticaException(DomError.NOTSUPPORTED);
	}

	void dn() throws DomoticaException {
		throw new DomoticaException(DomError.NOTSUPPORTED);
	}

	void setColor(int value) throws DomoticaException {
		throw new DomoticaException(DomError.NOTSUPPORTED);
	}

	int getColor() throws DomoticaException {
		throw new DomoticaException(DomError.NOTSUPPORTED);
	}

	int getValue() throws DomoticaException {
		throw new DomoticaException(DomError.NOTSUPPORTED);
	}

	void close() throws DomoticaException {
		System.out.println("Pechando conexión con "+name);
	}
	
}
