package io.github.gilmardev.registrousuario.controller;

import io.github.gilmardev.registrousuario.dto.*;
import io.github.gilmardev.registrousuario.exception.TipoErroPersonalizado;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.support.CustomSQLErrorCodesTranslation;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import io.github.gilmardev.registrousuario.repository.UsuarioRepository;

@RestController
@RequestMapping("/api/usuario")
public class UsuarioRegistroRestController {

	public static final Logger logger = LoggerFactory.getLogger(UsuarioRegistroRestController.class);

	private UsuarioRepository usuarioRepository;

	@Autowired
	public void setUsuarioRepository(UsuarioRepository usuarioRepository) {
		this.usuarioRepository = usuarioRepository;
	}

	@GetMapping("/")
	public ResponseEntity<List<UsuarioDTO>> listarAllUsuario() {
		List<UsuarioDTO> usuarios = usuarioRepository.findAll();
		if(usuarios.isEmpty()) {
			return new ResponseEntity<List<UsuarioDTO>>(HttpStatus.NO_CONTENT);
		}
		
		return new ResponseEntity<List<UsuarioDTO>>(usuarios, HttpStatus.OK);

	}
	
	//método para obter o usuário por id
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> buscarUsuarioById(@PathVariable("id") final Long id) {
		UsuarioDTO usuario = usuarioRepository.findById(id);
		if (usuario == null) {
				return new ResponseEntity<UsuarioDTO>(
					new TipoErroPersonalizado("Usuário com id " 
					+ id + " não encontrado"),HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UsuarioDTO>(usuario, HttpStatus.OK);
	}
	
	// método para criar um usuário
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsuarioDTO> criarUsuario(
					@Valid @RequestBody final UsuarioDTO usuario) {
			logger.info("criando usuário: {}", usuario );
			if(usuarioRepository.findByNome(usuario.getNome())!= null) {
			logger.error("Não foi possível criar usuário com nome {} já existe",
					                      usuario.getNome());
				    return new ResponseEntity<UsuarioDTO>(
				    		new TipoErroPersonalizado(
					"Não foi possível criar um novo usuário. Um usuário com nome"
					+ usuario.getNome() + " já existe."), HttpStatus.CONFLICT);
				
			}
		
			usuarioRepository.save(usuario);
			return new ResponseEntity<UsuarioDTO>(usuario, HttpStatus.CREATED);
	}


	// método para atualizar um usuário existente
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsuarioDTO> atualizarUsuario(
			@PathVariable("id") final Long id, @RequestBody UsuarioDTO usuario) {

		// buscar o usuário com base no id e configurá-lo para o objeto atualizarUsuario do tipo UsuarioDTO
		UsuarioDTO atualizarUsuario = usuarioRepository.findById(id);
		if(atualizarUsuario == null) {
			 return new ResponseEntity<UsuarioDTO>(
					 new TipoErroPersonalizado("Não foi possível atualizar o usuario pelo id"
							+ id + "não encontrado. "), HttpStatus.NOT_FOUND);
		}

		// atualizar os dados do objeto atualizarUsuario com os dados do objeto do usuário
		atualizarUsuario.setNome(usuario.getNome());
		atualizarUsuario.setEndereco(usuario.getEndereco());
		atualizarUsuario.setEmail(usuario.getEmail());

		// salvar objeto do usuário atual
		usuarioRepository.saveAndFlush(atualizarUsuario);

		// retornar ResponseEntity objeto
		return new ResponseEntity<UsuarioDTO>(atualizarUsuario, HttpStatus.OK);
	}

	// deleta um usuário existente
	@DeleteMapping("/{id}")
	public ResponseEntity<UsuarioDTO> excluirUsuario(@PathVariable("id") final Long id) {
		UsuarioDTO usuario = usuarioRepository.findById(id);
		if(usuario == null) {
				return new ResponseEntity<UsuarioDTO>(
						new TipoErroPersonalizado("Não foi possivel excluir usuario com id "
								+ id + "não econtrado "), HttpStatus.NOT_FOUND);
		}
		usuarioRepository.deleteById(id);
		return new ResponseEntity<UsuarioDTO>(
				new TipoErroPersonalizado("Usuario excluido com id"
				+ id + "."), HttpStatus.NO_CONTENT);
	}

}
