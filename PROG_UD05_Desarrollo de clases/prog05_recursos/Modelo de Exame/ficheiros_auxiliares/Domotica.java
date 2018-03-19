package com.iesrodeira.domotica;

import java.util.Random;

public class Domotica {
	protected enum Command { GETSTATUS,ON,OFF,UP,DN,SETCOLOR,GETCOLOR,GETVALUE }
	protected String nome;
	protected String ip;
	protected boolean status;
	protected int color;
	protected int value;

	public Domotica(String nome,String ip) {
		this.nome=nome;
		this.ip=ip;
		this.status=false;
	}

	protected Connection getConnection() throws DomoticaException {
		throw new DomoticaException(DomError.NOTSUPPORTED);
	}
	
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

	private void sendCmd(Command cmd,int value) throws DomoticaException {
		Connection cnx=getConnection();
		System.out.println("Levando a cabo comando en "+nome+" ("+ip+")");
		switch(cmd) {
			case UP: 	
					for(int idx=0;idx<value;idx++) cnx.up();
				 	break;
			case DN:		
					for(int idx=0;idx<value;idx++) cnx.dn();
				 	break;
			case SETCOLOR:		
					cnx.setColor(value);
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

	public boolean getStatus() throws DomoticaException {
		sendCmd(Command.GETSTATUS);
		return status;
	}

	public int getColor() throws DomoticaException {
		sendCmd(Command.GETCOLOR);
		return color;
	}

	public int getValue() throws DomoticaException {
		sendCmd(Command.GETVALUE);
		return value;
	}

	public void up() throws DomoticaException {
		sendCmd(Command.UP);
	}

	public void dn() throws DomoticaException {
		sendCmd(Command.DN);
	}

	public void up(int n) throws DomoticaException {
		sendCmd(Command.UP,n);
	}

	public void dn(int n) throws DomoticaException {
		sendCmd(Command.DN,n);
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
