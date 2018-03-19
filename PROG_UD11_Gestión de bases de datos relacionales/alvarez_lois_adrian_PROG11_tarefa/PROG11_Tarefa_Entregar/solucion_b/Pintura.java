import java.util.ArrayList;

class Pintura {
	private ArrayList <Figura> l;
	private int id;
	private String nome;

	Pintura(String nome) {
		this.id=0;
		this.nome=nome;
		l=new ArrayList <Figura>();
	}

	Pintura(int id,String nome) {
		this.id=id;
		this.nome=nome;
		l=new ArrayList <Figura>();
	}

	String getName() {
		return nome;
	}

	void setFiguras(ArrayList <Figura> f) {
		l=f;
	}

	ArrayList <Figura> getFiguras() {
		return l;
	}

	int getID() {
		return id;
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
