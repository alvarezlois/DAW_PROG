import daw.rodeira.monedas.*;

class Monedas {
	public static void main(String[] args) {
		Euro e=new Euro(100000f);
		e.setTipoCambio(1.2750f);
		System.out.println(e.getImporte()+"€ son "+e.getImporteEnDolaresEEUU()+"$ EEUU");

		DolarEEUU d=e.getDolaresEEUU();
		System.out.println("O Importe en Dólares EEUU é "+d.getImporte());

		Euro e1=new Euro(523f,1.2750f);
		System.out.println(e1.getImporte()+" Euros son "+e1.getImporteEnDolaresEEUU()+" Dolares");
		DolarEEUU d1=new DolarEEUU(523f,1.2750f);
		System.out.println(d1.getImporte()+"$ son "+d1.getImporteEnEuros()+"€");
	}
}
