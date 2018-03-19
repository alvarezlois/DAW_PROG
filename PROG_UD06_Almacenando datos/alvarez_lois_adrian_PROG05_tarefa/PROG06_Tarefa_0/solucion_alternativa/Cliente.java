package unit06b;

import java.io.Serializable;

public class Cliente implements Serializable {
	private String nif=null;
	private String nombre=null;
	private String telefono=null;
	private String direccion=null;
	private double deuda=0.0d;

	/**
		Constructor
	*/
	Cliente(String nif,String nombre,String telefono,String direccion,double deuda) throws DNIException {
		Cliente.checkDNI(nif);
		this.nif=nif;
		this.nombre=nombre;
		this.telefono=telefono;
		this.direccion=direccion;
		this.deuda=deuda;
	}

	/**
		Devolve o NIF
	*/
	public String getNif() {
		return nif;
	}

	/**
		Establece cando dous clientes son iguais
	*/
	public boolean equals(Cliente c) {
		return nif.equals(c.getNif());
	}
	

	/**
		Comproba que un DNI sexa correcto
	*/
	public static void checkDNI(String dni) throws DNIException {
		String letra="TRWAGMYFPDXBNJZSQVHLCKE";
		try {
			int num=Integer.parseInt(dni.substring(0,8));
			char check=dni.charAt(8); 
			int r=num%23;
			if (letra.charAt(r)==check) return;
			throw new Exception();
		} catch (Exception e) {
			throw new DNIException("DNI erróneo");
		}
	}

	/**
		Devolve a representación de un cliente coma String
	*/
	public String toString() {
		String result=	"DNI: "+this.nif+"\n"+
						"Nombre: "+this.nombre+"\n"+
						"Dirección: "+this.direccion+"\n"+
						"Teléfono: "+this.telefono+"\n"+
						"Deuda: "+this.deuda+"\n";
		return result;
	}
}

/**
	Erro para xestionar os DNI incorrectos
*/
class DNIException extends Exception {
	DNIException(String msg) {
		super(msg);
	}
}
