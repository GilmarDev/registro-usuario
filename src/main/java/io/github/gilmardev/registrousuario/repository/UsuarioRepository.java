package io.github.gilmardev.registrousuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.github.gilmardev.registrousuario.dto.UsuarioDTO;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioDTO, Long>{
	
	UsuarioDTO findByNome(String nome);
	UsuarioDTO findById(Long id);

}
