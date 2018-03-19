package com.iesrodeira.domotica;

import java.util.Scanner;

public class DomoticaLampGeneric extends Domotica {
	public DomoticaLampGeneric(String nome,String ip) {
		super(nome,ip);
	}

	protected Connection getConnection() throws DomoticaException {
		return new ConnectionLamp(ip);
	}

	public void control() throws DomoticaException {
		try {
			Scanner scn=new Scanner(System.in);
			System.out.println("Operacións para "+nome+": ");
			System.out.println("1.- Encender");
			System.out.println("2.- Apagar");
			System.out.println("3.- Subir Intensidade");
			System.out.println("4.- Baixar Intensidade");
			String opc=scn.nextLine();
			int op=Integer.parseInt(opc);
			switch(op) {
				case 1: 
					on(); 	
					break;
				case 2: 
					off(); 
					break;
				case 3:
					up();
					break;
				case 4:
					dn();
					break;
				default: throw new DomoticaException(DomError.NOTSUPPORTED);
			}
		}	catch (NumberFormatException e) {
			throw new DomoticaException(DomError.NOTSUPPORTED);
		}
	}
}
