package br.com.dhentech.cm.exception;

public class ExplosionException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public ExplosionException() {
		
	}

	public ExplosionException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public ExplosionException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}
