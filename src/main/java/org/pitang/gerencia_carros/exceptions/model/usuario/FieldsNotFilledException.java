package org.pitang.gerencia_carros.exceptions.model.usuario;

public class FieldsNotFilledException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FieldsNotFilledException() {
        super("Missing fields");
    }
}