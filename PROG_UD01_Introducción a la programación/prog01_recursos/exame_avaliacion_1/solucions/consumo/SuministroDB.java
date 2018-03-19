package com.iesrodeira.consumo;

/**
		SuministroDB - Debería traballar contra unha BBDD. Pero a simulo mediante 
		variables estáticas para non necesitar adiantar o acceso á BBDD que se
		verá máis adiante....

		Repasade o código e preguntade as dúbidas. O único novo, pero sencillo de
		comprender son as táboas (String[], int[] ....)
*/
class SuministroDB {
	private static final int SIZE=10;
	private static Suministro[] sum=null;
	private static int nums;

	SuministroDB() {
		System.out.println("Conectado á DB....");
		if (SuministroDB.sum==null) {
			SuministroDB.nums=0;
			SuministroDB.sum=new Suministro[SuministroDB.SIZE];
		}
	}

	private int search(String codsuministro) throws DBException {
		for(int idx=0;idx<SuministroDB.nums;idx++) {
			String code=SuministroDB.sum[idx].getCodigo();
			System.out.println("Comprobando "+code);
			if (codsuministro.equals(code)) return idx;
		}
		throw	new DBException(ErrCode.ERRNOTFOUND);
	}

	/**
		Carga o suministro identificado por codSuministro da BBDD, devolvendo o obxecto Suministro 
		correspondente, co atributo lecturaActual a 0, e co atributo ultimaLectura co seu valor correcto.
	*/
	Suministro loadSuministro(String codsum,String password) throws DBException {
		System.out.println("Cargando Subministro "+codsum);
		int idx=search(codsum);
		if (!password.equals(SuministroDB.sum[idx].getPassword())) throw new DBException(ErrCode.ERRPASSWD);
		return SuministroDB.sum[idx];
	}

	/**
		Garda na BBDD o obxecto suministro protexido pola password password. Si o suministro xa existe, o modifica
	*/
	void saveSuministro(Suministro suministro,String password) throws DBException {
		System.out.println("Gardando Subministro "+suministro.getCodigo());
		try {
			int idx=search(suministro.getCodigo());
			if (password.equals(SuministroDB.sum[idx].getPassword())) 	SuministroDB.sum[idx]=suministro;
			else 																			throw new DBException(ErrCode.ERRPASSWD);
		}
		catch (DBException e) {
			if (e.getError()==ErrCode.ERRNOTFOUND) {
				System.out.println("Novo Subministro");
				SuministroDB.sum[SuministroDB.nums]=suministro; // OLLO!!! non garda unha copia....
				SuministroDB.nums++;			
				System.out.println("Xestionando un total de "+SuministroDB.nums+" subministros");
			}
			else throw e;
		}
	}
}
