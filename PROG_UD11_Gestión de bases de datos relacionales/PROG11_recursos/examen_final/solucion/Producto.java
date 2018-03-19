abstract class Producto {
	protected int id_producto;
	protected String nome;
	protected float precio;
	protected String foto;
	protected int existencias;

	Producto(int id,String nome,float precio,String foto,int existencias) {
		this.id_producto=id;
		this.nome=nome;
		this.precio=precio;
		this.foto=foto;
		this.existencias=existencias;	
	}
	
	int getId() {
		return id_producto;
	}
	
	public String getNome() {
		return nome;
	}

	public float getPrecio() {
		return precio;
	}

	public String getFoto() {
		return foto;
	}

	public int getExistencias() {
		return existencias;
	}

	public boolean equals(Producto p) {
		return (id_producto==p.id_producto);
	}

	// Visualiza na pantalla a ficha do producto	
	abstract void showFichaProducto();

	// Devolve o tipo de producto
	abstract int getType();
}

