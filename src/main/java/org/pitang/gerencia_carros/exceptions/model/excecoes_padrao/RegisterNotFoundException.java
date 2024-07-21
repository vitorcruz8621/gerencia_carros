package org.pitang.gerencia_carros.exceptions.model.excecoes_padrao;

public class RegisterNotFoundException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;

	public RegisterNotFoundException() {
        super("Register not found");
    }

}
