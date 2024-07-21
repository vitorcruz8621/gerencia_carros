package org.pitang.gerencia_carros.exceptions.model.usuario;

public class ExistingEmailException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExistingEmailException() {
        super("Email already exists");
    }
}