package com.iesrodeira.domotica;

import java.util.Scanner;

public class DomoticaEnchufe extends Domotica {

	public DomoticaEnchufe(String nome,String ip) {
		super(nome,ip);
	}

	protected Connection getConnection() throws DomoticaException {
		return new Connection(ip);
	}

	public void control() throws DomoticaException {
		try {
			Scanner scn=new Scanner(System.in);
			System.out.println("Operacións para "+nome+": ");
			System.out.println("1.- Encender");
			System.out.println("2.- Apagar");
			String opc=scn.nextLine();
			int op=Integer.parseInt(opc);
			switch(op) {
				case 1: on(); 	break;
				case 2: off(); break;
				default: throw new DomoticaException(DomError.NOTSUPPORTED);
			}
		}	catch (NumberFormatException e) {
			throw new DomoticaException(DomError.NOTSUPPORTED);
		}
	}
}
