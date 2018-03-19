package TU6;
/**
  Xestión de erros de Cliente
*/

public class ClienteException extends Exception {
  public ClienteException(String errmsg) {
      super(errmsg);
   }
}
