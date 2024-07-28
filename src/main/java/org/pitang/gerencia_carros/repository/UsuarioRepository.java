package org.pitang.gerencia_carros.repository;

import java.util.List;

import org.pitang.gerencia_carros.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository  extends JpaRepository<UsuarioModel, Integer>{
	public List<UsuarioModel> findAllByEmail(String email);
	public List<UsuarioModel> findAllByLogin(String login);
}