import java.util.ArrayList;

class Pintura {
	private ArrayList <Figura> l;
	private String nome;

	Pintura(String nome) {
		this.nome=nome;
		l=new ArrayList <Figura>();
	}

	String getName() {
		return nome;
	}

	void setName(String n) {
		nome=n;
	}

	void add(Figura f) {
		l.add(f);
	}

	void paint(Debuxable d) throws Exception {
		int y=d.getHeight();
		int x=d.getWidth();

		d.writeText(y-1,x-nome.length()-1,nome);
		for(Figura f: l) f.paint(d);
	}

	public String toString() {
		return nome;
	}
}
