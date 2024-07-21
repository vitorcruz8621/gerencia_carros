package org.pitang.gerencia_carros.exceptions.model.usuario;

public class ExistingLoginException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExistingLoginException() {
        super("Login already exists");
    }
}