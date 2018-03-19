package com.iesrodeira.consumo;

import java.util.Scanner;

public class Cliente {
	private String dni;
	private String nome;
	private String direccion;
	private String telefono;
	private String email;
	private String ccc;

	public String getDNI() { return dni;	}
	public String getNome() { return nome; }
	public String getDireccion() { return direccion; }
	public String getTelefono() { return telefono; }
	public String getEmail() { return email; }
	public String getCCC() { return ccc; }
	public String toString() { return dni+" - "+nome; }


	public Cliente(String dni,String nome,String direccion,String telefono,String email,String ccc) throws CCCException,DNIException {
		Cliente.verificaDNI(dni);
		CuentaCorriente.verificaCCC(ccc);
		this.dni=dni;
		this.nome=nome;
		this.direccion=direccion;
		this.telefono=telefono;
		this.email=email;
		this.ccc=ccc;
	}

	/**
		Solicita os datos do novo cliente por consola e devolve o obxecto Cliente correspondente.
	*/
	public static Cliente newClienteConsole() throws CCCException, DNIException {
		Scanner scn=new Scanner(System.in);
	
		System.out.println("Dni Cliente: ");
		String dni=scn.nextLine();
		verificaDNI(dni);
		System.out.println("Nome Cliente: ");
		String nome=scn.nextLine();
		System.out.println("Direccion Cliente: ");
		String direccion=scn.nextLine();
		System.out.println("Teléfono Cliente: ");
		String telefono=scn.nextLine();
		System.out.println("Email Cliente: ");
		String email=scn.nextLine();
		System.out.println("Nº de Cuenta: ");
		String ccc=scn.nextLine();
		return new Cliente(dni,nome,direccion,telefono,email,ccc);
	}

	public static void verificaDNI(String dni) throws DNIException {
		char[] control={'T','R','W','A','G','M','Y','F','P','D','X','B','N','J','Z','S','Q','V','H','L','C','K','E'};
		int num,resto;
		char letra;		
		
		if (dni.length() != 9) throw new DNIException("Número de DNI Erróneo (8 números + 1 letra)");
		try {
			num=Integer.parseInt(dni.substring(0,8)); 
			letra=dni.charAt(8);
			resto=num%23;
			if (control[resto] != letra) throw new DNIException("Número de DNI Erróneo (A letra non coincide)");
		} catch (NumberFormatException e) {
			throw new DNIException("Número de DNI Erróneo (8 números + 1 letra)");
		}
				
	}
}

