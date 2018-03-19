package com.iesrodeira.domotica;

import java.util.Scanner;

public class DomoticaLampColor extends DomoticaLampGeneric {
	public DomoticaLampColor(String nome,String ip) {
		super(nome,ip);
	}

	public void control() throws DomoticaException {
		try {
			Scanner scn=new Scanner(System.in);
			System.out.println("Operacións para "+nome+": ");
			System.out.println("1.- Encender");
			System.out.println("2.- Apagar");
			System.out.println("3.- Subir Intensidade");
			System.out.println("4.- Baixar Intensidade");
			System.out.println("5.- Cambiar Color");
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
				case 5: 
					try {
						System.out.println("Cor Desexada? : ");
						int value=Integer.parseInt(scn.nextLine());
						color(value);
					} catch (NumberFormatException e) {
						System.out.println("Debes introducir un número enteiro");
					}
					break;
				default: throw new DomoticaException(DomError.NOTSUPPORTED);
			}
		}	catch (NumberFormatException e) {
			throw new DomoticaException(DomError.NOTSUPPORTED);
		}
	}
}
