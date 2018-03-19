/**
	Database

	Versión con RandomAccessFile
*/

package TU6;
import java.io.*;
import java.util.ArrayList;

public class Database {
   private final int SIZERECORD=296; // Número de bytes que ocupa 1 rexistro (Cliente)
   RandomAccessData f;	// Utilizamos RandomAccessData que nos proporciona funcións útiles...

	/**
		Constructor, abre o ficheiro
	*/
   public Database() throws DatabaseException {
      try {
         f=new RandomAccessData(new File("clientes.dat"),"rw");
      } catch (FileNotFoundException e) {       
         throw new DatabaseException("Erro creando ficheiro ("+e.getMessage()+")");
      } catch (SecurityException e) {
         throw new DatabaseException("Non tes permisos");
      } 
   }

	/**
		Método de uso privado, lee un Cliente
	*/
   private Cliente readCliente() throws DatabaseException, ClienteException, EOFException {
      try {
         Cliente cl=null;
         do {
            String dni=f.readString(9);
            String nome=f.readString(80);
            String dir=f.readString(40);
            String tel=f.readString(15);
            double deuda=f.readDouble();
            if (dni.charAt(0)!='*') cl=new Cliente(dni,nome,dir,tel,deuda);
         } while(cl==null);
         return cl;
      } catch (EOFException e) {
           throw e;
      } catch (IOException e) {
         throw new DatabaseException("Erro lendo cliente ("+e.getMessage()+")");
      }
   }
   
	/**
		Método de uso privado, garda un Cliente
	*/
   private void saveCliente(Cliente cl) throws DatabaseException {
      try {
         f.writeString(cl.getDni(),9);
         f.writeString(cl.getNome(),80);
         f.writeString(cl.getDireccion(),40);
         f.writeString(cl.getTelefono(),15);
         f.writeDouble(cl.getDeuda());
      } catch (IOException e) {
         throw new DatabaseException("Erro escribindo ("+e.getMessage()+")");
      }
   }

	/**
		Método de uso privado. Busca unha posición libre (rexistro borrado, ou final do arquivo) para 
		gardar un novo cliente
	*/
   private void posicionaInsercion() throws DatabaseException {
      String dni;
      String nome;
      String dir;
      String tel;
      double deuda;

      try {
         f.seek(0);
         do {
            dni=f.readString(9);
            nome=f.readString(80);
            dir=f.readString(40);
            tel=f.readString(15);
            deuda=f.readDouble();
         } while(dni.charAt(0)!='*');
         f.lseek(-1,SIZERECORD);
      } catch (EOFException e) {
      } catch (IOException e) {
         throw new DatabaseException("Erro Obtendo Posición de Inserción ("+e.getMessage()+")");
      }
   }

	/**
		Garda o cliente cl no ficheiro
	*/
   public void add(Cliente cl) throws DatabaseException {
      try {
         search(cl.getDni());
         throw new DatabaseException("DNI Duplicado");
      } catch (DatabaseException e) {
         if (e.getCode() == 1) {
            posicionaInsercion();
            saveCliente(cl);
         }
         else throw e;
      }
   }

	/**
		Busca no ficheiro o cliente co dni. Si non existe, lanza unha DatabaseException con código 1
		A posición no ficheiro será xusto ao comenzo do rexistro buscado si se atopa, e o final do
		arquivo si non se atopa
	*/
   public Cliente search(String dni) throws DatabaseException {
      Cliente c;

      try {
         f.seek(0);
         while(true) {
            c=readCliente();
            if (c.getDni().equals(dni)) {
               f.lseek(-1,SIZERECORD); // Atopado!!. Retrocedo 1 rexistro (estou ao final do rexistro buscado)
               return c;
            }
         }
      } catch (EOFException e) {
         throw new DatabaseException(1,"O Cliente "+dni+" non existe");
      } catch (IOException e) {
         throw new DatabaseException("Erro de lectura ("+e.getMessage()+")");
      } catch (ClienteException e) {
         throw new DatabaseException("A BBDD contén clientes erróneos");
      }
   }

	/**
		Borra o rexistro indicado por dni, escribindo un * encima do primeiro díxito do DNI
	*/
   public void delete(String dni) throws DatabaseException {
      try  {
         Cliente cl=search(dni);
         f.writeChars("*");
      } catch(IOException e) {
         throw new DatabaseException("Erro Borrando Rexistro ("+e.getMessage()+")");
      }
   }

   /**
		Devolve un ArrayList coa lista de todos os clientes
	*/
   public ArrayList <Cliente> getClientes() throws DatabaseException {
      ArrayList <Cliente> lc=new ArrayList <Cliente>();
      Cliente c;

      try {
         f.seek(0); // Empezamos dende o principio do arquivo
         while(true) {
            c=readCliente();
            lc.add(c);
         }
      } catch (EOFException e) {
         return lc;
      } catch (IOException e) {
         throw new DatabaseException("Erro de lectura ("+e.getMessage()+")");
      } catch (ClienteException e) {
         throw new DatabaseException("A BBDD contén clientes erróneos");
      }
   }

	/**
		Pecha o arquivo
	*/
   public void close() throws DatabaseException {
      try {
         if (f!=null) f.close();
      } catch(IOException e) { 
         throw new DatabaseException("Erro pechando ficheiro ("+e.getMessage()+")");
      }
   }
}
