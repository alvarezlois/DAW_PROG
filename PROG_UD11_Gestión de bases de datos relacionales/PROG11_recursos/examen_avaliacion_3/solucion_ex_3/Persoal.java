package ControlPresencial;

public class Persoal {
	String id;
	String nome;
	String telefono;
	String email;
	int 	 tipo;
	int 	bonus;

   Persoal(String id,String nome,String telefono,String email,int tipo) {
		this.id=id;
		this.nome=nome;
		this.telefono=telefono;
		this.email=email;
		this.tipo=tipo;
		this.bonus=0;
	}

	public void controlHorario(int inorout,long time) throws Exception {
	}

	public String getID() {
		return id;
	}

	public int getBonus() {
		return bonus;
	}

	public String toString() {
		return nome+"   Bonificación: "+bonus+"€";
	}
}
