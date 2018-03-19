class Ordenador extends Producto {
	private String CPU;
	private int RAM;
	private int HDD;

	Ordenador(int id,String nombre,float precio,String foto,int existencias,String CPU,int RAM,int HDD) {
		super(id,nombre,precio,foto,existencias);
		this.CPU=CPU;
		this.RAM=RAM;
		this.HDD=HDD;
	}

	void showFichaProducto() {
		System.out.println("NOMBRE: "+nome);
		System.out.println("PRECIO: "+precio+"€");
		System.out.println("EXISTENCIAS: "+existencias);
		System.out.println("CPU: "+CPU);
		System.out.println("RAM: "+RAM+"GB");
		System.out.println("HDD: "+HDD+"GB");
	}

	int getType() {
		return 0;
	}

	public String getCPU() {
		return CPU;
	}

	public int getRAM() {
		return RAM;
	}

	public int getHDD() {
		return HDD;
	}
}
