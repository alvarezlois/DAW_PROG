class Impresora extends Producto {
	String tipo;
	int ppm;
	boolean color;
	boolean doble_cara;
	boolean fax;
	boolean red;

	Impresora(int id,String nombre,float precio,String foto,int existencias,
					String tipo,int ppm,boolean color,boolean doble_cara,boolean fax,boolean red) {
		super(id,nombre,precio,foto,existencias);
		this.tipo=tipo;
		this.ppm=ppm;
		this.color=color;
		this.doble_cara=doble_cara;
		this.fax=fax;
		this.red=red;
	}

	public void showFichaProducto() {
		System.out.println("NOMBRE: "+nome);
		System.out.println("PRECIO: "+precio+"€");
		System.out.println("EXISTENCIAS: "+existencias);
		System.out.println("TIPO: "+tipo);
		System.out.println("VELOCIDAD: "+ppm+"ppm");
		System.out.println("COLOR: "+((color?"si":"no")));
		System.out.println("DOBLE CARA: "+((doble_cara?"si":"no")));
		System.out.println("FAX: "+((fax?"si":"no")));
		System.out.println("RED: "+((red?"si":"no")));
	}

	public int getType() {
		return 2;
	}

	public String getTipo() {
		return tipo;
	}

	public int getPpm() {
		return ppm;
	}

	public boolean isColor() {
		return color;
	}

	public boolean isDobleCara() {
		return doble_cara;
	}

	public boolean isFax() {
		return fax;
	}

	public boolean isNetwork() {
		return red;
	}


}
