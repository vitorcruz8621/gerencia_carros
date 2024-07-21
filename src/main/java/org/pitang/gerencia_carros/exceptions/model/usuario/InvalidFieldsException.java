package org.pitang.gerencia_carros.exceptions.model.usuario;

public class InvalidFieldsException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidFieldsException() {
        super("Invalid fields");
    }
}