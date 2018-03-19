class Juguete {
	protected String codigo;
	private String nombre;
	private float preciocompra;
	private int existencias;
	private String seccion;
	private int clasificacion;
	public static int totaljuguetes=0;

	private void compruebaCodigoProducto(String c) throws JugueteException {
		try {
			Integer.parseInt(c);
		} catch (NumberFormatException e) {
			throw new JugueteException("Codigo de Producto Erróneo");
		}
	}

	private void compruebaCodigo() throws JugueteException  {
		if (codigo.length()!=10) throw new JugueteException("El Código de Juguete es Erróneo");
		compruebaCodigoProducto(getCodigo());
		_getSeccion();
		_getClasificacion();
	}

	private void _getSeccion() throws JugueteException {
		String se=codigo.substring(0,4);
		if ((se.equals("JMES"))||(se.equals("MYPE"))||(se.equals("CONS"))||(se.equals("DEPO"))||(se.equals("EDUC"))||(se.equals("OTRO")))
			seccion=se;
		else	throw new JugueteException("El Código de Sección es Erróneo");
	}

	private void _getClasificacion() throws JugueteException {
		String cl=codigo.substring(4,6);
		try {
			int n=Integer.parseInt(cl);
			switch(n) {
				case 0: case 6: case 12: case 16: case 18: 
						clasificacion=n;
						break;
				default: throw new JugueteException("El Código de Edad es erróneo");
			}
		} catch(NumberFormatException e) {
			throw new JugueteException("El Código de Edad es erróneo");
		}
	}

	public void setExistencias(int n) {
		existencias=n;
	}

	public int getExistencias() {
		return existencias;
	}

	public void setCodigo(String codigo) throws JugueteException {
		this.codigo=codigo;
		compruebaCodigo();
	}

	public String getCodigo() {
		return codigo.substring(6);
	}

	public String getSeccion() {
		return seccion;
	}

	private int getClasificacion() {
		return clasificacion;
	}

	public Juguete(String codigo,String nombre) throws JugueteException {
		this.codigo=codigo;
		compruebaCodigo();
		totaljuguetes++;
		this.nombre=nombre;
		this.preciocompra=0f;
		this.existencias=0;
	}

	public Juguete(String codigo,String nombre,float preciocompra)  throws JugueteException {
		this.codigo=codigo;
		compruebaCodigo();
		totaljuguetes++;
		this.nombre=nombre;
		this.preciocompra=preciocompra;
		this.existencias=0;
	}


	/**
		Para probar a clase
	*/
	public static void main(String[] args) {
		try  {
			new Juguete("JMES128989","DC-9");
			System.out.println("Creado "+Juguete.totaljuguetes+" Objeto Juguete");
		} catch (JugueteException e) {
			System.out.println("ERROR "+e.getMessage());
		}
	}
}
