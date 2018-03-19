package com.iesrodeira.consumo;

import java.util.Random;

class Contador {
	private static float last=0.0f;
	private String codigo;
	private float lectura;

	/**
		Crea o obxecto Contador asociado con codSuministro
	*/
	Contador(String codSuministro) {
		System.out.println("Conectando con el Contador...");
		codigo=codSuministro;
		Random rnd=new Random();
		lectura=Contador.last+Math.abs(rnd.nextFloat()*700);
		Contador.last=lectura;
	}
	
	/**
		Devolve o consumo total en Kw do contador
	*/
	float getLectura() {
		return lectura;
	}
}
