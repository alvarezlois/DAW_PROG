package com.iesrodeira.domotica;

public class Domotica {
	protected enum Command { GETSTATUS,ON,OFF,UP,DN,SETCOLOR,GETCOLOR,GETVALUE }
	protected String nome;
	protected String ip;
	protected boolean status;
	protected int color;
	protected int value;

	/** Constructor. Crea un obxecto domótica co nome indicado e coa ip indicada
	*/
	public Domotica(String nome,String ip) {
		this.nome=nome;
		this.ip=ip;
		this.status=false;
	}

	/**	Obten o driver apropiado para o manexo do obxecto domótico. 
	*/
	protected Connection getConnection() throws DomoticaException {
		throw new DomoticaException(DomError.NOTSUPPORTED);
	}

	/**  Realiza unha acción co obxecto domótico
	*/
	private void sendCmd(Command cmd) throws DomoticaException {
		System.out.println("Conectando con "+nome+" ("+ip+")");
		Connection cnx=getConnection();
		switch(cmd) {
			case GETSTATUS: status=cnx.getStatus(); 	break;
			case ON:			 cnx.on(); 						break;
			case OFF:		 cnx.off(); 					break;
			case GETCOLOR:	 color=cnx.getColor();		break;
			case GETVALUE:	 value=cnx.getValue();		break;
			case UP:			 cnx.up();						break;
			case DN:			 cnx.dn();						break;
		}
		cnx.close();
	}

	/** Versión sobrecargada. Realiza unha acción co obxecto domótico que precisa o paso dun valor
	*/
	private void sendCmd(Command cmd,int value) throws DomoticaException {
		Connection cnx=getConnection();
		System.out.println("Levando a cabo comando en "+nome+" ("+ip+")");
		switch(cmd) {
			case UP: 	
					for(int idx=0;idx<value;idx++) cnx.up();
					this.value=cnx.getValue();
				 	break;
			case DN:		
					for(int idx=0;idx<value;idx++) cnx.dn();
					this.value=cnx.getValue();
				 	break;
			case SETCOLOR:		
					cnx.setColor(value);
					this.value=cnx.getColor();
					break;
		}
		cnx.close();
	}

   /**
		Este método debe visualizar un menú de opcións e levar a cabo a acción solicitada.
	*/
	public void control() throws DomoticaException {
		throw new DomoticaException(DomError.GENERICDRIVER);
	}

	/** Devolve o estado do obxecto domótico (true = on, false = off)
	*/
	public boolean getStatus() throws DomoticaException {
		sendCmd(Command.GETSTATUS);
		return status;
	}

	/** Devolve a cor que ten o obxecto domótico
	*/
	public int getColor() throws DomoticaException {
		sendCmd(Command.GETCOLOR);
		return color;
	}

	/** Devolve o valor interno do obxecto domótico (como a intensidade de luz, por exemplo, ou a temperatura...)
	*/
	public int getValue() throws DomoticaException {
		sendCmd(Command.GETVALUE);
		return value;
	}

	/** Sube o valor interno dun obxecto domótico en 1
	*/
	public void up() throws DomoticaException {
		sendCmd(Command.UP);
	}

	/** Baixa o valor interno dun obxecto domótico en 1
	*/
	public void dn() throws DomoticaException {
		sendCmd(Command.DN);
	}

	public void color(int c) throws DomoticaException {
		sendCmd(Command.SETCOLOR,c);
	}	

	public void on() throws DomoticaException {
		sendCmd(Command.ON);
	}

	public void off() throws DomoticaException {
		sendCmd(Command.OFF);
	}
	
}
