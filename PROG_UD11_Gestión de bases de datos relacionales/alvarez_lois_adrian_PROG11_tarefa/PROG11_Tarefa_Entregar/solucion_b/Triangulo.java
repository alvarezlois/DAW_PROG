import java.util.ArrayList;

class Triangulo extends Poligono {

	Triangulo(Punto a,Punto b,Punto c) throws Exception {
		ArrayList <Punto> v=new ArrayList <Punto>();
		this.type=Types.TRIANGULO;
		v.add(a); v.add(b); v.add(c);
		setVertices(v);
	}
}
