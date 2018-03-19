package TU6;

import java.io.Serializable;

public class Cliente implements Serializable {
   private String dni;
   private String nome;
   private String direccion;
   private String telefono;
   private double deuda;

   public Cliente(String dni,String nome,String direccion,String telefono) throws ClienteException {
      this.dni=Cliente.verificaDNI(dni);
      this.nome=nome;
      this.direccion=direccion;
      this.telefono=telefono;
      deuda=0.0;
   }

   public Cliente(String dni,String nome,String direccion,String telefono,double deuda) 
     throws ClienteException {
      this.dni=Cliente.verificaDNI(dni);
      this.nome=nome;
      this.direccion=direccion;
      this.telefono=telefono;
      this.deuda=deuda;
   }

   public String toString() {
      return dni+" - "+nome+" - "+direccion+" - "+telefono+" - "+deuda;
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
   
   public double getDeuda() {
      return deuda;
   }

	/**
		Verifica DNI/NIE. E estática para poderse utilizar para comprobar a validez de dni/nie sin
		instanciar un obxecto Cliente
		En lugar de un código de erro, devolve o DNI/NIE si e correcto e LANZA UNHA ClienteException
		si é erróneo.
	*/
   public static String verificaDNI(String dni) throws ClienteException {
      String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
      int numdni;
      String nif=dni;

      if (nif.toUpperCase().startsWith("X")||nif.toUpperCase().startsWith("Y")||nif.toUpperCase().startsWith("Z")) nif = nif.substring(1);
      if (nif.length()!= 9) throw new ClienteException("DNI Erróneo");
      char letra=nif.toUpperCase().charAt(8);
      try {
         numdni=Integer.parseInt(nif.substring(0,8));
      } catch(NumberFormatException e) {
         throw new ClienteException("DNI Erroneo");
      }
      numdni = numdni % 23;
      char l= letras.charAt(numdni);
      if (l!=letra) throw new ClienteException("DNI Erroneo");
      return dni;
   }
}
