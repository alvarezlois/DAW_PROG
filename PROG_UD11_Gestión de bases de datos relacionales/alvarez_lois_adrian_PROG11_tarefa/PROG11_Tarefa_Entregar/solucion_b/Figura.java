import java.util.ArrayList;

enum Types { PUNTO, RECTANGULO, TRIANGULO, POLIGONO };

abstract class Figura {
	Types type;
	int id=0;

	abstract void paint(Debuxable d) throws Exception;

	public int getID() {
		return id;
	}

	public void setID(int id) {
		this.id=id;
	}
}
