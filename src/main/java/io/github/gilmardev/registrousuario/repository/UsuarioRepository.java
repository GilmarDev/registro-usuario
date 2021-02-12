package io.github.gilmardev.registrousuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.gilmardev.registrousuario.dto.UsuarioDTO;

public interface UsuarioRepository extends JpaRepository<UsuarioDTO, Long> {
	
	UsuarioDTO findByNome(String nome);

}
