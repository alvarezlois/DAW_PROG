package com.iesrodeira.consumo;

public class CuentaCorriente {

	/**
		Calcula o Díxito de Control dun grupo de 10 díxitos. 

		@param num - Número de conta bancaria
		@returns - Número de control do grupo de 10 díxitos
	*/
	private static int calculaDixito(int num) {
		int[] coeficientes={1, 2, 4, 8, 5, 10, 9, 7, 3, 6};
		int factor=1000000000;
		int sum=0;
		int d;
		int idx=0;

		while(idx<10) {
			d=num/factor;
			sum=sum+d*coeficientes[idx];
			num=num-d*factor;
			factor/=10;
			idx++;
		}
		sum=11-(sum%11);
		if (sum==10) return 1;
		if (sum==11) return 0;
		return sum;
	}


	/**
		Verifica e calcula o Díxito de Control dunha CCC. Si o número de conta é incorrecto lanza
		unha excepción CuentaBancariaException

		@param ccc - Número de conta bancaria completo en formato String
		@returns - Número de control da conta
	*/
	public static int verificaCCC(String ccc) throws CCCException {
		try {
			if (ccc.length()!=20) throw new CCCException("O CCC debe ter 20 díxitos ");
			int control=Integer.parseInt(ccc.substring(8,10));
			int cuenta=Integer.parseInt(ccc.substring(10));
			int entidadoficina=Integer.parseInt(ccc.substring(0,8));
			int control_calculado=calculaDixito(entidadoficina)*10+calculaDixito(cuenta);		
			if (control_calculado!=control) throw new CCCException("O Número de Conta non é correcto");
			return control;			
		} catch (NumberFormatException e) {
			throw new CCCException("O número de conta debe ser numérico");
		}
	}

}
