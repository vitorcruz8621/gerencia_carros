package org.pitang.gerencia_carros.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.pitang.gerencia_carros.model.UsuarioModel;
import org.pitang.gerencia_carros.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import jakarta.transaction.Transactional;

@Service
public class UsuarioService {

    // Declare the repository as final to ensure its immutability
    private final UsuarioRepository usuarioRepository;

    // Use constructor-based dependency injection
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
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
    
    //public void salvarFoto(Integer id, byte[] foto) {
    @Transactional
    public void salvarFoto(Integer id, MultipartFile foto) throws IOException {
        Optional<UsuarioModel> optionalUsuario = usuarioRepository.findById(id);
        byte[] bytes = foto.getBytes();
        //usuarioService.salvarFoto(id, bytes);
        
        optionalUsuario.ifPresent(usuario -> {
            usuario.setFoto(bytes);
            //usuarioRepository.save(usuario);
            usuarioRepository.updateFoto(id, bytes);
        });
    }

    public byte[] recuperarFoto(Integer id) {
        Optional<UsuarioModel> optionalUsuario = usuarioRepository.findById(id);
        return optionalUsuario.map(UsuarioModel::getFoto).orElse(null);
    }
    
    /*@Transactional
    public void salvarFoto(Integer id, byte[] foto) {
        usuarioRepository.updateFoto(id, foto);
    }*/
}
    