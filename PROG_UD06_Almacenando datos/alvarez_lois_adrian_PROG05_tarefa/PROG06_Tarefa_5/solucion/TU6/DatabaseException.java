/**
	Xestión dos erros de Database. Se pode indicar un codigo de error.
	O código 1, por exemplo, indica rexistro non atopado.
	O resto non se usan (de momento)
*/

package TU6;

public class DatabaseException extends Exception {
  int code=0;

  public DatabaseException (String errmsg) {
      super(errmsg);
  }

  public DatabaseException(int code,String errmsg) {
      super(errmsg);
      this.code=code;
  }

  public int getCode() {
      return code;
  }
}
