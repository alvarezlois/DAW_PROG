public class Localidade {
	private String poboacion;
	private int codpos;
	private String provincia;
	private int zona;

	public Localidade(int codpos) throws LocalidadeException {
		verificaCodigoPostal(codpos);
	}

	/**
		Comprueba si o código postal é válido (si a empresa traballa en ese código postal), e obten da BBDD
		o nome da poboación e a provincia. Tamén calcula en que zona está.
	*/
	private void verificaCodigoPostal(int cp) throws LocalidadeException {
		//
		// Fake code - Este código non vale, so é a proposito de proba
		//
		switch(cp) {
			case 36940:
				codpos=cp;
				provincia="Pontevedra";
				poboacion="Cangas";
				zona=2;
				break;
			case 36201:
				codpos=cp;
				provincia="Pontevedra";
				poboacion="Vigo";
				zona=1;
				break;

			default: throw new LocalidadeException("Non traballamos nesa zona");
		}
	}

	public int getCodigoPostal() {
		return codpos;
	}

	public String getPoboacion() {
		return poboacion;
	}

	public String getProvincia() {
		return provincia;
	}

	public int getSobrecusto() {
		switch(zona) {
			case 2: return 5;
			case 3: return 8;
		}
		return 0;
	}

	public int getZone() {
		return zona;
	}
}

/**
	Excepcion de Localidade
*/
class LocalidadeException extends Exception {
	public LocalidadeException(String msg) {
		super(msg);
	}
}
