package org.pitang.gerencia_carros.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Optional;

import org.pitang.gerencia_carros.exceptions.model.excecoes_padrao.RegisterNotFoundException;
import org.pitang.gerencia_carros.exceptions.model.usuario.ExistingEmailException;
import org.pitang.gerencia_carros.exceptions.model.usuario.ExistingLoginException;
import org.pitang.gerencia_carros.exceptions.model.usuario.FieldsNotFilledException;
import org.pitang.gerencia_carros.exceptions.model.usuario.InvalidFieldsException;
import org.pitang.gerencia_carros.model.UsuarioModel;
import org.pitang.gerencia_carros.service.UsuarioService;
import org.pitang.gerencia_carros.utilitario.Utilitarios;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {
	private final UsuarioService usuarioService;

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@GetMapping
	public ResponseEntity<List<UsuarioModel>> getAllUsuarios() throws Exception {
		List<UsuarioModel> listaUsuarios = usuarioService.getAllUsuarios();
		
		if (!listaUsuarios.isEmpty()) {
			return ResponseEntity.ofNullable(listaUsuarios);
		}
		
		throw new RegisterNotFoundException();
		//return ResponseEntity.ok(usuarioService.getAllUsuarios());
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioModel> getUsuarioById(@PathVariable Integer id) throws Exception {
		Optional<UsuarioModel> opUsuario = usuarioService.getUsuarioById(id);
		
		if (!opUsuario.isEmpty()) {
			return ResponseEntity.ofNullable(opUsuario.get());
		}
		
		throw new RegisterNotFoundException();
		
		/*return usuarioService.getUsuarioById(id).map(entity -> ResponseEntity.ok(entity))
				.orElse(ResponseEntity.notFound().build());*/
	}

	@PostMapping
	public ResponseEntity<UsuarioModel> createUsuario(@RequestBody @Valid String usuarioModel, BindingResult result)
			// public ResponseEntity<UsuarioModel> createUsuario(@RequestBody @Valid String
			// usuarioModel, BindingResult result)
			throws FieldsNotFilledException, InvalidFieldsException, ExistingEmailException, ExistingLoginException,
			JsonMappingException, JsonProcessingException {
		Optional<UsuarioModel> opUsuario;
		ObjectMapper objectMapper = new ObjectMapper();
		UsuarioModel usuario;

		try {
			usuario = objectMapper.readValue(usuarioModel, UsuarioModel.class);
		} catch (JsonMappingException e) {
			throw new InvalidFieldsException();
		}

		opUsuario = Optional.of(usuario);

		if (!opUsuario.isEmpty()) {
			Utilitarios.validarCamposObrigatorios(opUsuario.get());

			var emailDuplicavel = usuarioService.getAllUsuariosPorEmail(opUsuario.get().getEmail());

			if (emailDuplicavel.size() > 0) {
				throw new ExistingEmailException();
			}

			// var loginDuplicavel =
			// usuarioService.getAllUsuariosPorEmail(opUsuario.get().getLogin());
			var loginDuplicavel = usuarioService.getAllUsuariosPorLogin(opUsuario.get().getLogin());

			if (loginDuplicavel.size() > 0) {
				throw new ExistingLoginException();
			}

			return ResponseEntity.ok(usuarioService.saveUsuario(opUsuario.get()));
		}

		throw new RegisterNotFoundException();
	}

	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<UsuarioModel> updateUsuario(@PathVariable Integer id, @RequestBody @Valid String usuarioModel)
			throws FieldsNotFilledException, InvalidFieldsException, ExistingEmailException, ExistingLoginException,
			Exception {
		Optional<UsuarioModel> opUsuario;
		ObjectMapper objectMapper = new ObjectMapper();
		UsuarioModel usuario;

		try {
			usuario = objectMapper.readValue(usuarioModel, UsuarioModel.class);
			Utilitarios.validarCamposObrigatorios(usuario);
		} catch (JsonMappingException | FieldsNotFilledException e) {
			throw new InvalidFieldsException();
		}

		opUsuario = usuarioService.getUsuarioById(id);

		if (!opUsuario.isEmpty()) {
			usuario.setId(opUsuario.get().getId());
			return ResponseEntity.ok(usuario);
		}

		throw new RegisterNotFoundException();

	}

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<Void> deleteUsuario(@PathVariable Integer id) throws Exception {
		Optional<UsuarioModel> opUsuario = usuarioService.getUsuarioById(id);

		if (!opUsuario.isEmpty()) {
			usuarioService.deleteUsuario(id);
			return ResponseEntity.noContent().build();
		}

		throw new RegisterNotFoundException();
	}
	
	/*
	@PostMapping("/{id}/foto")
    public ResponseEntity<Void> salvarFoto(@PathVariable Integer id, @RequestParam("foto") MultipartFile foto) throws IOException {
        //byte[] bytes = foto.getBytes();
		//usuarioService.salvarFoto(id, bytes);
		usuarioService.salvarFoto(id, foto);
		return ResponseEntity.ok().build();
    }*/
	@PostMapping("/{id}/foto")
	public ResponseEntity<Void> salvarFoto(@PathVariable Integer id, @RequestParam("foto") MultipartFile foto) {
	    try (InputStream inputStream = foto.getInputStream()) {
	        usuarioService.salvarFoto(id, inputStream);
	        return ResponseEntity.ok().build();
	    } catch (IOException e) {
	        return ResponseEntity.badRequest().build();
	    }
	}

    

    @GetMapping("/{id}/foto")
    public ResponseEntity<byte[]> recuperarFoto(@PathVariable Integer id) {
        byte[] foto = usuarioService.recuperarFoto(id);
        if (foto != null) {
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(foto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
