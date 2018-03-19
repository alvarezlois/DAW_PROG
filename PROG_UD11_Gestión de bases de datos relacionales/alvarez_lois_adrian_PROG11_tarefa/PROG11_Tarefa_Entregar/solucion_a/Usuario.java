public class Usuario {
	private int id=0;
	private String nick;
	private String nome;
	private String apelidos;
	private String pass;

	public Usuario(String nick,String nome,String apelidos,String pass) {
		this.nick=nick;
		this.nome=nome;
		this.apelidos=apelidos;
		this.pass=pass;
	}

	public void setID(int id) {
		this.id=id;
	}

	public int getID() {
		return id;
	}

	public String getNick() {
		return nick;
	}

	public String getPass() {
		return pass;
	}

	public String toString() {
		return nome+" "+apelidos;
	}
}
