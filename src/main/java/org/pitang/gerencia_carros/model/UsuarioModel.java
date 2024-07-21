package org.pitang.gerencia_carros.model;

import java.io.Serializable;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "usuarios", schema = "principal")
@EqualsAndHashCode(of = "id")
@Data
public class UsuarioModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Integer id;

	@NotBlank
	@Column(nullable = false)
	private String nome;

	@NotBlank
	@Column(nullable = false)
	private String sobrenome;

	@Email
	@NotBlank
	@Column(nullable = false, name = "e_mail", unique = true)
	private String email;

	@NotNull
	@Column(nullable = false, name = "data_nascimento")
	private Date dataNascimento;

	@NotBlank
	@Column(nullable = false, unique = true)
	private String login;

	@NotBlank
	@Column(nullable = false)
	private String senha;

	@NotBlank
	@Column(nullable = false)
	private String telefone;
	
	@Lob
	@Column(name = "foto", columnDefinition = "bytea")
    private byte[] foto;
	
	
}