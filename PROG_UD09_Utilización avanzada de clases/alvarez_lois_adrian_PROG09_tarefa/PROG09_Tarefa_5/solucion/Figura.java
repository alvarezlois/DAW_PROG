enum Types { PUNTO, RECTANGULO, TRIANGULO, POLIGONO };

abstract class Figura {
	Types type;

	abstract void paint(Debuxable d) throws Exception;
}
