/**
	O uso dunha clase Database para realizar as operacións relacionadas co ficheiro fai que tanto as
	clases Cliente como ClientesEmpresa sexan completamente independentes do tipo de almacenamento que 
	se utilice. O almacenamento se pode realizar cun ObjectInputStream/ObjectOutputStream, 
   con DataOutputStream, DataInputStream, RandomAccessFile, a través dun Socket de rede... etc
   simplemente escribindo a clase Database correspondente cos métodos que utiliza a
   clase ClientesEmpresa (add, search, delete, getClientes e close)
*/

import java.io.*;
import java.util.ArrayList;
import TU6.*;

public class ClientesEmpresa {
   private static BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
   private static Database db=null; // Obxecto Database para gardar e leer datos.

	/**
		Visualiza o Menú
	*/
   static int menu() {
         int op=0;

         System.out.println("ELIXE OPCIÓN: ");
         System.out.println("1.- Engadir Cliente ");
         System.out.println("2.- Listar Cliente ");
         System.out.println("3.- Buscar Cliente ");
         System.out.println("4.- Borrar Cliente ");
         System.out.println("5.- Eliminar Datos ");
         System.out.println("6.- Saír ");
         try {
            op=Integer.parseInt(in.readLine());
         } catch(Exception e) {
            System.out.println("Opción errónea");
         }
         return op;
   }

	/**
		Pide datos do novo cliente e os garda na BBDD
	*/
   public static void novoCliente() {
      String dni;
      String nome;
      String direccion;
      String telefono;
      Cliente cl;

      try {
         System.out.println("NOVO CLIENTE:");
         System.out.println("DNI? :"); dni=in.readLine();
			// Esto únicamente sirve para evitar pedir o resto de datos si o dni xa existe...
         try {
            db.search(dni); // O método search de Database debe retornar o Cliente co dni, ou lanzar unha excepción con código 1 si non existe.
            System.out.println("O Cliente xa existe");
         } catch(DatabaseException e) {
            if (e.getCode()==1) { // getCode é un método de DatabaseException que devolve o código da excepción. 1 indica DNI Erróneo
               System.out.println("Nome? :"); nome=in.readLine();
               System.out.println("Dirección? :"); direccion=in.readLine();
               System.out.println("Teléfono? :"); telefono=in.readLine();
               cl=new Cliente(dni,nome,direccion,telefono); 
               db.add(cl);
            } else throw e;
         }
      } catch (ClienteException e) {
         System.out.println("Cliente Erróneo: "+e.getMessage());
      } catch (IOException e) {
         System.out.println("Erro na entrada de datos");
      } catch (DatabaseException e) {
         System.out.println("Erro gardando datos: "+e.getMessage());
      } 
   }

	/**
			Lista os clientes
	*/
   public static void listadoClientes() {
      ArrayList <Cliente> cl;

      try {
         cl=db.getClientes(); // Obten un ArrayList con todos os clientes, e os visualiza
         for(Cliente c: cl) {
           System.out.println(c);
         }
      } catch(Exception e) {
         System.out.println("Erro Listando ("+e.getMessage()+")");
      }
   }

	/**
		Busca un cliente
	*/
   public static void buscaCliente() {
       String dni;
       Cliente c;

       try {
         System.out.println("BUSQUEDA CLIENTE:");
         System.out.println("Introduce DNI :"); dni=in.readLine();
         c=db.search(dni); // Database fai a busca...
         System.out.println("Encontrado: "+c); // A visualización así é algo pobre,se debería traballar algo mais.... (un printCliente que visualice os datos ben)
       } catch(IOException e) {
         System.out.println("Erro na entrada de datos"); 
       } catch (DatabaseException e) {
         System.out.println("ERRO: "+e.getMessage());
       }         
   }

	/**
		Elimina un cliente
	*/
   public static void borraCliente() {
       String dni;
       Cliente c;

       try {
         System.out.println("BUSQUEDA CLIENTE:");
         System.out.println("Introduce DNI :"); dni=in.readLine();
         db.delete(dni); // Database se encarga...
         System.out.println("Cliente Borrado");
       } catch(IOException e) {
         System.out.println("Erro na entrada de datos"); 
       } catch (DatabaseException e) {
         System.out.println("ERRO: "+e.getMessage());
       }        
   }

	/**
		Borra o ficheiro de datos
	*/
   public static void clean() {
      try {
         if (!new File("clientes.dat").delete()) throw new Exception("ErroR");
      } catch (Exception e) {
         System.out.println("ERRO eliminando ficheiro: "+e.getMessage());
      }
   }


	/**
		Programa principal: Visualiza o menú e leva a cabo as accións requeridas
	*/
   public static void main(String[] args) {
      int op;
      try {
         db=new Database(); // Creamos o obxecto Database para acceder ós datos
         do {
            op=menu();
            switch(op) {
               case 1:
                  novoCliente();
                  break;
               case 2:
                  listadoClientes();
                  break;
               case 3:
                  buscaCliente();
                  break;
               case 4:
                  borraCliente();
                  break;
               case 5:
                  clean();
                  break;
            }
         } while(op!=6);
      } catch (Exception e) {
         System.out.println("ERRO: "+e.getMessage());
      } finally {
         try {
            if (db!=null) db.close(); // Pechamos o obxecto Database
         } catch (Exception e) {
            System.out.println("Erro pechando ficheiro ("+e.getMessage()+")");
         }
      }
   }
}
