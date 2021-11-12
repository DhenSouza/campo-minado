package br.com.dhentech.cm.exception;

public class ExitException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	

	public ExitException(String message, Throwable cause) {
		super(message, cause);
	}

	public ExitException(String message) {
		super(message);
	}
	

}
