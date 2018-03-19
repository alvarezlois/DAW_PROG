enum Types { PUNTO, RECTANGULO, TRIANGULO };

abstract class Figura {
	Types type;

	abstract void paint(Debuxable d);
}
