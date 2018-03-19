import java.util.ArrayList;

class Poligono extends Figura {
	private ArrayList <Punto> vertices=null;
	protected boolean relleno;

	// Non quero que se instancie nin use un polígono directamente
	protected Poligono() {
		vertices=null;
		relleno=false;
	}

	protected void setVertices(ArrayList <Punto> v) throws Exception {
		switch(v.size()) {
			case 0: 
			case 1: 
			case 2: 
				throw new Exception("No es un Polígono"); 
			case 3:
				this.type=Types.TRIANGULO;
				break;
			case 4:
				this.type=Types.RECTANGULO;
				break;
			default:
				this.type=Types.POLIGONO;
				break;
		}
		this.vertices=v;
		this.relleno=false;
	}

	// OLLO!!!!!! - Si alteran este vértice, alteran o polígono xa que é unha referencia
	// si queremos que non sexa así, deberíamos voltar unha copia (por exemplo cun constructor de copia: return new Punto(vertices.get(idx));
	public Punto getVertice(int idx) {
		return vertices.get(idx);
	}

	public void setRelleno(boolean relleno) {
		this.relleno=relleno;
	}

	// Suponse que os puntos están correctamente ordenados...
	// Non permitimos pintar directamente un polígono
	protected void paint(Debuxable d) throws Exception {
		if (vertices==null) throw new Exception("Polígono desconocido");
		int idx=0;
		if (!relleno) {
			while(idx<vertices.size()-1) {
				d.linea(vertices.get(idx).getY(),vertices.get(idx).getX(),vertices.get(idx+1).getY(),vertices.get(idx+1).getX());
				d.show();
				idx++;
			}
			d.linea(vertices.get(idx).getY(),vertices.get(idx).getX(),vertices.get(0).getY(),vertices.get(0).getX());
		} else throw new UnsupportedOperationException();
	}
}
