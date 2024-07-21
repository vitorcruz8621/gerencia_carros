package org.pitang.gerencia_carros.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "carros", schema = "principal")
@EqualsAndHashCode(of = "id")
@Data
public class CarroModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false)
	private Integer id;

	@Column(nullable = false, name = "ano_fabricacao")
	private Integer anoFabricacao;

	@Column(nullable = false, unique = true)
	private String placa;

	@Column(nullable = false)
	private String modelo;

	@Column(nullable = false)
	private String cor;

	@ManyToOne
	@JoinColumn(name = "id_usuario")
	private UsuarioModel usuario;
}
