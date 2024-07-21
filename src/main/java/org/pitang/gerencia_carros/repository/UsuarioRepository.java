package org.pitang.gerencia_carros.repository;

import java.util.List;

import org.pitang.gerencia_carros.model.UsuarioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UsuarioRepository  extends JpaRepository<UsuarioModel, Integer>{
	public List<UsuarioModel> findAllByEmail(String email);
	public List<UsuarioModel> findAllByLogin(String login);
	
	@Modifying
	@Query("UPDATE UsuarioModel u SET u.foto = :foto WHERE u.id = :id")
	void updateFoto(@Param("id") Integer id, @Param("foto") byte[] foto);
}