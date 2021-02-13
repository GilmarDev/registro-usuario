package io.github.gilmardev.registrousuario.controller;

import io.github.gilmardev.registrousuario.dto.*;

import java.util.List;
import java.util.Optional;

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
	public void setUsuarioRepository(UsuarioRepository usuarioRepositoy) {
			this.usuarioRepository = usuarioRepositoy;
		}
	
	@GetMapping("/")
	public ResponseEntity<List<UsuarioDTO>> listAllUsuario(){
		List<UsuarioDTO> usuarios = usuarioRepository.findAll();
		return new ResponseEntity<List<UsuarioDTO>>(usuarios, HttpStatus.OK);
		
		
	}
	
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsuarioDTO> criarUsuario(@RequestBody final UsuarioDTO usuario){
		usuarioRepository.save(usuario);
		return new ResponseEntity<UsuarioDTO>(usuario, HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDTO> buscarUsuarioById(@PathVariable("id") final Long id) {
		UsuarioDTO usuario = usuarioRepository.findById(id);
			if(usuario == null) {
					return new ResponseEntity<UsuarioDTO>(
						new CustomErrorType("Usuário com id "
						+ id + " não encontrado"), HttpStatus.NOT_FOUND);
	}
			return new ResponseEntity<UsuarioDTO>(usuario, HttpStatus.OK);
	}
	
	@PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UsuarioDTO> atualizarUsuario(
			@PathVariable("id") final long id, @RequestBody UsuarioDTO usuario){
		
		//buscar o usuário com base no id e configurá-lo para o objeto atualUsuario do tipo UsuarioDTO
		UsuarioDTO atualizarUsuario = usuarioRepository.findById(id);
		
		//atualizar os dados do objeto atualizarUsuario com os dados do objeto do usuário
		atualizarUsuario .setNome(usuario.getNome());
		atualizarUsuario .setEndereco(usuario.getEndereco());
		atualizarUsuario .setEmail(usuario.getEmail());
		
		//salvar objeto do usuário atual
		usuarioRepository.saveAndFlush(atualizarUsuario);
		
		//retornar ResponseEntity objeto
		return new ResponseEntity<UsuarioDTO>(atualizarUsuario, HttpStatus.OK);
	}
	
	
		@DeleteMapping("/{id}")
		public ResponseEntity<UsuarioDTO> excluirUsuario(@PathVariable("id") final Long id){
			usuarioRepository.deleteById(id);
			return new ResponseEntity<UsuarioDTO>(HttpStatus.NO_CONTENT);
		}
	
	}


