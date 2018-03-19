class Test {
	public static void main(String[] args) {
		Producto impresora=new Impresora(1,"HP",100,"",10,"Laser",20,true,false,false,true);
		Producto ordenador=new Ordenador(2,"SONY Vaio",320,"",4,"Intel i7",16,170);
		Producto componente=new Componente(3,"nVidia GeForce 740H",80,"",2,"Tarjeta Gráfica nVidia GeForce 740H");

		impresora.showFichaProducto(); System.out.println();
		ordenador.showFichaProducto(); System.out.println();
		componente.showFichaProducto(); System.out.println();
	}
}
