package org.pitang.gerencia_carros.exceptions.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public record UsuarioErroMessageDTO(String message, int errorCode) {}