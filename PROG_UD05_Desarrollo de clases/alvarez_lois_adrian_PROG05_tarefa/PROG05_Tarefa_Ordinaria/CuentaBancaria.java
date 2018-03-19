public class CuentaBancaria {
	private double saldo;
	private String impositor;
	private String ccc;
	private int control;
	private int cuenta;
	private int entidad;
	private int oficina;


	/**
		Constructor
		@param cuenta - String, número de conta
		@param impositor - String, nome do impositor
	*/
	public CuentaBancaria(String cuenta,String impositor) throws CuentaBancariaException {
		verificaCuenta(cuenta);
		this.ccc=cuenta;
		this.impositor=impositor;
	}

	/**
		Calcula o Díxito de Control dun grupo de 10 díxitos. É estática porque así se poden calcular 
		díxitos de control sin necesidade de instanciar un obxecto CuentaBancaria

		@param num - Número de conta bancaria
		@returns - Número de control do grupo de 10 díxitos
	*/
	public static int calculaDixito(int num) {
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
		Calcula o Díxito de Control. É estática porque así se poden calcular díxitos de control e verificar números 
		de conta sin necesidade de instanciar un obxecto CuentaBancaria. Si o número de conta é incorrecto lanza
		unha excepción CuentaBancariaException

		@param num - Número de conta bancaria completo en formato String
		@returns - Número de control da conta
	*/
	public static int calculaControl(String numcuenta) throws CuentaBancariaException {
		try {
			int control=Integer.parseInt(numcuenta.substring(8,10));
			int cuenta=Integer.parseInt(numcuenta.substring(10));
			int entidadoficina=Integer.parseInt(numcuenta.substring(0,8));
			int control_calculado=calculaDixito(entidadoficina)*10+calculaDixito(cuenta);		
			if (control_calculado!=control) throw new CuentaBancariaException("La cuenta no es correcta");
			return control;			
		} catch (CuentaBancariaException e) {
			throw e;
		} catch (Exception e) {
			throw new CuentaBancariaException("La cuente debe ser numérica");
		}
	}


	/**
		Verifíca si unha conta é correcta. 

		@param numcuenta - Número de conta bancaria completo en formato String
	*/
	private void verificaCuenta(String numcuenta) throws CuentaBancariaException {
		try {
			if (numcuenta.length()!=20) 
				throw new CuentaBancariaException("El CCC debe tene 20 dígitos ");
			control=CuentaBancaria.calculaControl(numcuenta);
			entidad=Integer.parseInt(numcuenta.substring(0,4));
			oficina=Integer.parseInt(numcuenta.substring(4,8));
			cuenta=Integer.parseInt(numcuenta.substring(10));
		} catch(CuentaBancariaException e) {
			throw e;
		}
		catch(Exception e) {
			throw new CuentaBancariaException("Debes escribir números");
		}
	}

	/** 
		Retira diñeiro da conta. No caso de que o saldo non chegue lanza unha excepción
		
		@param importe - Importe a retirar
		@returns - Saldo da conta
	*/
	public double retirada(double importe) throws CuentaBancariaException {
		if (saldo<importe) throw new CuentaBancariaException("Saldo Insuficiente");
		saldo-=importe;
		return saldo;
	}

	/**
		Ingresa diñeiro na conta

		@param impore - Importe a ingresar
	*/
	public double ingreso(double importe) {
		if (saldo<0) throw new CuentaBancariaException("No se permiten ingresos negativos");
		saldo+=importe;
		return saldo;
	}

	/**
		Obten o saldo

		@returns  Saldo da conta
	*/
	public double getSaldo() {
		return saldo;
	}

	/**
		Obten o titular da conta

		@returns  Titular da conta
	*/
	public String getTitular() {
		return impositor;
	}

	/**
		Obten o número de conta (sen entidade nen oficina)

		@returns  Número de a conta
	*/
	public int getCuenta() {
		return cuenta;
	}
	
	/**
		Obten o CCC completo

		@returns  CCC
	*/
	public String getCCC() {
		return ccc;
	}

	/**
		Obten o código de entidade

		@returns  Código de entidade
	*/
	public int getCodigoEntidad() {
		return entidad;
	}

	/**
		Obten o número de oficina

		@returns número de oficina (sucursal)
	*/
	public int getOficina() {
		return oficina;
	}

	/**
		Obten os díxitos de control

		@returns  Díxitos de control
	*/
	public int getDigitosControl() {
		return control;
	}

	/**
		Obten o nome da entidade

		@returns  Nome da entidade
	*/
	public String getEntidad() {
		switch(entidad) {
			case 2080: 	return "Anova";
			case 1465:	return "ING Direct";
		}
		return "Entidad Desconocida";
	}
}
