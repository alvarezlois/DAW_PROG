package daw.rodeira.monedas;

	public class DolarEEUU {
		float tipocambio_euro;
		float cantidad;

		public DolarEEUU(float c) {	
			cantidad=c;
		}

		public DolarEEUU(float c,float tc) {
			cantidad=c;
			tipocambio_euro=tc;
		}

		public float getImporte() {
			return cantidad;
		}

		public void setTipoCambio(float tc) {
			tipocambio_euro=tc;
		}

		public float getImporteEnEuros() {
			return tipocambio_euro * cantidad;
		}

		public Euro getEuros() {
			Euro e=new Euro(getImporteEnEuros());
			e.setTipoCambio(1/tipocambio_euro);
			return e;
		}
	}
