package org.pitang.gerencia_carros.exceptions.model.usuario;

public class InexistentLoginOrInvalidPasswordException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InexistentLoginOrInvalidPasswordException() {
        super("Invalid login or password");
    }
}