package org.pitang.gerencia_carros.utilitario;
import java.lang.reflect.Field;

import org.pitang.gerencia_carros.exceptions.model.usuario.FieldsNotFilledException;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class Utilitarios {

    public static <T> void validarCamposObrigatorios(T objeto) {
        Class<?> clazz = objeto.getClass();
        Field[] campos = clazz.getDeclaredFields();
        
        for (Field campo : campos) {
            campo.setAccessible(true);

            try {
            	if (campo.isAnnotationPresent(NotNull.class)) {
                    if (campo.get(objeto) == null) {
                    	throw new FieldsNotFilledException();
                    }
                } else if (campo.isAnnotationPresent(NotBlank.class)) {
                    if (campo.get(objeto) == null || ((String) campo.get(objeto)).isEmpty()) {
                        throw new FieldsNotFilledException();
                    }
                }
                // Adicione outras validações conforme necessário para outras anotações
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
