package hotel.errors;

public class ClienteException extends Exception {
	public static final int NOTFOUND=1;

	int errcode=0; // Quero distinguir uns erros de outros. De momento so NOTFOUND para cliente non atopado

	public ClienteException(String msg) {
		super(msg);
	}

	public ClienteException(String msg,int code) {
		super(msg);
		errcode=code;
	}
	
	public int getCode() {
		return errcode;
	}
}
