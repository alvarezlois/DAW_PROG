package daw.rodeira.monedas;

public class Euro {
		float tipocambio_dolar;
		float cantidad;

		public Euro(float c) {	
			cantidad=c;
		}

		public Euro(float c,float tc) {	
			cantidad=c;
			tipocambio_dolar=tc;
		}

		public float getImporteEnDolaresEEUU() {
			return tipocambio_dolar * cantidad;
		}

		public float getImporte() {
			return cantidad;
		}

		public void setTipoCambio(float tc) {
			tipocambio_dolar=tc;
		}

		public DolarEEUU getDolaresEEUU() {
			DolarEEUU d=new DolarEEUU(getImporteEnDolaresEEUU());
			d.setTipoCambio(1/tipocambio_dolar);
			return d;
		}
	}
