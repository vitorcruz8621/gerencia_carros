package org.pitang.gerencia_carros.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.pitang.gerencia_carros.exceptions.model.excecoes_padrao.RegisterNotFoundException;
import org.pitang.gerencia_carros.model.UsuarioModel;
import org.pitang.gerencia_carros.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UsuarioService {
	private final UsuarioRepository usuarioRepository;
	private final ResourceLoader resourceLoader;

	@Value("${app.upload.dir}")
	private String uploadDir;

	@Autowired
	public UsuarioService(UsuarioRepository usuarioRepository, ResourceLoader resourceLoader) {
		this.usuarioRepository = usuarioRepository;
		this.resourceLoader = resourceLoader;
	}

	public List<UsuarioModel> getAllUsuarios() {
		return usuarioRepository.findAll();
	}

	public List<UsuarioModel> getAllUsuariosPorEmail(String email) {
		return usuarioRepository.findAllByEmail(email);
	}

	public List<UsuarioModel> getAllUsuariosPorLogin(String login) {
		return usuarioRepository.findAllByLogin(login);
	}

	public Optional<UsuarioModel> getUsuarioById(Integer id) {
		return usuarioRepository.findById(id);
	}

	public UsuarioModel saveUsuario(UsuarioModel user) {
		return usuarioRepository.save(user);
	}

	public void deleteUsuario(Integer id) {
		usuarioRepository.deleteById(id);
	}

	public Resource loadUserImage(String imagePath) {
		return resourceLoader.getResource("classpath:static/images/usuarios/" + imagePath);
	}

	public UsuarioModel saveUserImage(Integer userId, MultipartFile file) throws IOException {
		Optional<UsuarioModel> usuarioOpt = usuarioRepository.findById(userId);

		if (usuarioOpt.isPresent()) {
			UsuarioModel usuario = usuarioOpt.get();
			//String imagePath = "src/main/resources/static/images/usuarios/" + file.getOriginalFilename();
			String imagePath = uploadDir + file.getOriginalFilename();
			File dest = new File(imagePath);
			file.transferTo(dest);
			usuario.setFotoPath(file.getOriginalFilename());
			return usuarioRepository.save(usuario);
		} else {
			throw new RegisterNotFoundException();
		}
	}
}
