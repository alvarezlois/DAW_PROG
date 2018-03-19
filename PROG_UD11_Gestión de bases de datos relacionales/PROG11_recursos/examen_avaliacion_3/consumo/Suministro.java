package com.iesrodeira.consumo;

import java.util.Calendar;

public class Suministro {
	private Cliente cliente;
	private String codigoSuministro;
	protected String tarifa;
	protected float potencia;
	float ultimaLectura;
	public float lecturaActual;
	private String password;
	private float preciokw;
	
	public Suministro(Cliente cl,String tarifa,float potencia,String passwd) throws SuministroException {
		Calendar cal=Calendar.getInstance();
		int ano=cal.get(Calendar.YEAR);
		int mes=cal.get(Calendar.MONTH);
		int dia=cal.get(Calendar.DAY_OF_MONTH);	
		String strmes;
		String strdia;
		
		strmes=String.valueOf(mes+1);
		strdia=String.valueOf(dia);
		if (mes<10) strmes="0"+strmes;
		if (dia<10) strdia="0"+dia;
		try {
			this.cliente=cl; // OLLO!!! Non fai unha copia......
			this.password=passwd;
			this.potencia=potencia;
			this.codigoSuministro=ano+strmes+strdia+cl.getDNI()+tarifa+getLetraPotencia();
			this.tarifa=tarifa;
			preciokw=getPrecioKw();
			ultimaLectura=getUltimoConsumo();
			lecturaActual=ultimaLectura;
			SuministroDB db=new SuministroDB();
			db.saveSuministro(this,this.password);
		} catch (DBException e) {
			throw new SuministroException(e.getMessage());
		}
	}

	public static Suministro getInstance(String codSuministro,String passwd) throws SuministroException {
		Suministro s=null;
		try {
			SuministroDB db=new SuministroDB();
			s=db.loadSuministro(codSuministro,passwd);
		} catch (DBException e) {
			throw new SuministroException(e.getMessage());
		}
		return s;
	}

	public void procesaFactura() throws SuministroException {
		try {
			SuministroDB db=new SuministroDB();
			getUltimoConsumo();
			ultimaLectura=lecturaActual;
			db.saveSuministro(this,this.password);		
		} catch(DBException e) {
			throw new SuministroException(e.getMessage());
		}
	}

	public float getImporteFactura() {
		return getFactorPotencia()+getConsumo()*preciokw;
	}
	
	public float getUltimoConsumo() {
		Contador c=new Contador(getCodigo());
		lecturaActual=c.getLectura();
		return lecturaActual;
	}

	public float getConsumo() {
		getUltimoConsumo();
		return (lecturaActual-ultimaLectura);
	}

	public float getPotencia() {
		return potencia;
	}

	public String getTarifa() {
		return tarifa;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public String getCodigo() {
		return codigoSuministro;
	}

	protected String getPassword() {
		return password;
	}

	/** Devolve o precio por KW según a tarifa
	*/
	private float getPrecioKw() throws SuministroException {
		float preciokw=0.0f;

		if (tarifa.equals("TUR")) preciokw=0.007343f;
		else if (tarifa.equals("2TR")) preciokw=0.008123f;
		else if (tarifa.equals("")) preciokw=0.011210f;
		else throw new SuministroException("A tarifa non existe");		
		return preciokw;
	}

	/** Devolve a letra correspondente á potencia contratada
	*/
	private char getLetraPotencia() {
		char letraPotencia='D';

		if (potencia <= 2.3) letraPotencia='A';
		else if (potencia <= 3.5) letraPotencia='B';
		else if (potencia <= 4.6) letraPotencia='C';
		return letraPotencia;
	}

	/**
		Devolve o factor de potencia a aplicar á factura según a potencia contratada
	*/
	private float getFactorPotencia() {
		float factor=0.0f;
		char letra=getLetraPotencia();
		switch(letra) {
			case 'A': factor=3.17f; break;
			case 'B': factor=4.23f; break;
			case 'C': factor=5.12f; break;
			case 'D': factor=7.06f; break;
		}
		return factor;
	}
}
