package UD6T1;

public class Persoa {
	private String dni;
	private String nome;
	private String direccion;
	private String telefono;
	private String email;

	public Persoa(String dni,String nome,String direccion,String telefono,String email) throws PersoaException {
		this.dni=Persoa.verificaDNI(dni);
		this.nome=nome;
		this.direccion=direccion;
		this.telefono=telefono;
		this.email=email;
	}

	public String getDni() {
		return dni;
	}

	public String getNome() {
		return nome;
	}

	public String getDireccion() {
		return direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setDni(String dni) throws PersoaException {
		this.dni=Persoa.verificaDNI(dni);
	}

	public void setNome(String nome) {
		this.nome=nome;
	}

	public void setDireccion(String direccion) {
		this.direccion=direccion;
	}

	public void setTelefono(String telefono) {
		this.telefono=telefono;
	}

	public void setEmail(String email) {
		this.email=email;
	}

	public static String verificaDNI(String dni) throws PersoaException {
		String letras="TRWAGMYFPDXBNJZSQVHLCKE";
		if (dni.length()!=9) throw new PersoaException(ErrPersoa.ERRDNI);
		int dninum=Integer.parseInt(dni.substring(0,8));
		int modulo= dninum % 23;
    	if (letras.charAt(modulo) != dni.charAt(8)) throw new PersoaException(ErrPersoa.ERRDNI);
    	return dni; 
	}

	public String toString() {
		return dni+" - "+nome;
	}
}
