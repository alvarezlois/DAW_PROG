package com.iesrodeira.domotica;

enum DomError {NOTSUPPORTED,GENERICDRIVER}

public class DomoticaException extends Exception {
	private String[] errmsgs={"Operacion Non Soportada","Dispositivo xenérico: Sen operacións dispoñibles"};
	private DomError errcode;

	public DomoticaException(DomError err) {
		errcode=err;
	}

	public String getMessage() {
		return errmsgs[errcode.ordinal()];
	}
}
