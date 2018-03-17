package com.iesrodeira.consumo;

enum ErrCode { ERRNOTFOUND, ERRPASSWD };

class DBException extends Exception {
	private static final String[] MSG= {
		"Subministro non Atopado",
		"O Subministro xa existe, pero a Password non é válida ",
	};
	ErrCode code;

	DBException(ErrCode code) {
		this.code=code;
	}

	public String getMessage() {
		return DBException.MSG[code.ordinal()];
	}

	ErrCode getError() {
		return code;
	}
}
