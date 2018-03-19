import java.util.ArrayList;

class Pintura {
	ArrayList <Figura> l;
	String nome;

	Pintura(String nome) {
		this.nome=nome;
		l=new ArrayList <Figura>();
	}

	void add(Figura f) {
		l.add(f);
	}

	void paint(Debuxable d) {
		int y=d.getHeight();
		int x=d.getWidth();

		d.writeText(y-1,x-nome.length()-1,nome);
		for(Figura f: l) f.paint(d);
	}
}
