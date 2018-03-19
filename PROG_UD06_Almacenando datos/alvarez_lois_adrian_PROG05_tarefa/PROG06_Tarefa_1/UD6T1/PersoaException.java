package UD6T1;

enum ErrPersoa {ERRDNI}

public class PersoaException extends Exception {
	private static final String[] errmsgs={"DNI Erróneo"};

	public PersoaException(ErrPersoa err) {
		super(PersoaException.errmsgs[err.ordinal()]);
	}
}
