import java.util.ArrayList;

class Rectangulo extends Poligono {
	Rectangulo(Punto a,Punto c) throws Exception {
		ArrayList <Punto> v=new ArrayList <Punto>();
		this.type=Types.RECTANGULO;
		Punto b=new Punto(a.getY(),c.getX());
		Punto d=new Punto(c.getY(),a.getX());
		v.add(a); v.add(b); v.add(c); v.add(d);	
		setVertices(v);
	}

	public void paint(Debuxable d) throws Exception {
		if (!relleno) super.paint(d);
		else {
			Punto a=getVertice(0);
			Punto c=getVertice(2);
			int y=a.getY(); int y1=c.getY();
			if (y>y1) {
				y=c.getY(); 
				y1=a.getY();
			}
			for(int i=y;i<=y1;i++) 	d.linea(i,a.getX(),i,c.getX());
		} 		
	}
}
