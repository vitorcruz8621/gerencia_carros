package org.pitang.gerencia_carros.exceptions;

import org.pitang.gerencia_carros.exceptions.dto.UsuarioErroMessageDTO;
import org.pitang.gerencia_carros.exceptions.model.excecoes_padrao.RegisterNotFoundException;
import org.pitang.gerencia_carros.exceptions.model.usuario.ExistingEmailException;
import org.pitang.gerencia_carros.exceptions.model.usuario.ExistingLoginException;
import org.pitang.gerencia_carros.exceptions.model.usuario.FieldsNotFilledException;
import org.pitang.gerencia_carros.exceptions.model.usuario.InexistentLoginOrInvalidPasswordException;
import org.pitang.gerencia_carros.exceptions.model.usuario.InvalidFieldsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InexistentLoginOrInvalidPasswordException.class)
    public ResponseEntity<UsuarioErroMessageDTO> handleInexistentLoginOrInvalidPassword(InexistentLoginOrInvalidPasswordException ex) {
        UsuarioErroMessageDTO usuarioErroMessageDTO = new UsuarioErroMessageDTO(ex.getMessage(), HttpStatus.UNAUTHORIZED.value());
        return new ResponseEntity<>(usuarioErroMessageDTO, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(ExistingEmailException.class)
    public ResponseEntity<UsuarioErroMessageDTO> handleExistingEmail(ExistingEmailException ex) {
        UsuarioErroMessageDTO usuarioErroMessageDTO = new UsuarioErroMessageDTO(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(usuarioErroMessageDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ExistingLoginException.class)
    public ResponseEntity<UsuarioErroMessageDTO> handleExistingLogin(ExistingLoginException ex) {
        UsuarioErroMessageDTO usuarioErroMessageDTO = new UsuarioErroMessageDTO(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(usuarioErroMessageDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidFieldsException.class)
    public ResponseEntity<UsuarioErroMessageDTO> handleInvalidFields(InvalidFieldsException ex) {
        UsuarioErroMessageDTO usuarioErroMessageDTO = new UsuarioErroMessageDTO(ex.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY.value());
        return new ResponseEntity<>(usuarioErroMessageDTO, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(FieldsNotFilledException.class)
    public ResponseEntity<UsuarioErroMessageDTO> handleFieldsNotFilled(FieldsNotFilledException ex) {
        UsuarioErroMessageDTO usuarioErroMessageDTO = new UsuarioErroMessageDTO(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(usuarioErroMessageDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RegisterNotFoundException.class)
    public ResponseEntity<UsuarioErroMessageDTO> handleRegisterNotFoundException(RegisterNotFoundException ex) {
        UsuarioErroMessageDTO usuarioErroMessageDTO = new UsuarioErroMessageDTO("Error message: " + ex.getMessage(), HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(usuarioErroMessageDTO, HttpStatus.NOT_FOUND);
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<UsuarioErroMessageDTO> handleGenericException(Exception ex) {
        UsuarioErroMessageDTO usuarioErroMessageDTO = new UsuarioErroMessageDTO("Error message: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(usuarioErroMessageDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}