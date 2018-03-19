class Componente extends Producto {
	String descripcion;

	Componente(int id,String nombre,float precio,String foto,int existencias,String descripcion) {
		super(id,nombre,precio,foto,existencias);
		this.descripcion=descripcion;
	}

	void showFichaProducto() {
		System.out.println("NOMBRE: "+nome);
		System.out.println("PRECIO: "+precio+"€");
		System.out.println("EXISTENCIAS: "+existencias);
		System.out.println("DESCRIPCION: "+descripcion);
	}

	int getType() {
		return 1;
	}

	public String getDescripcion() {
		return descripcion;
	}
}
