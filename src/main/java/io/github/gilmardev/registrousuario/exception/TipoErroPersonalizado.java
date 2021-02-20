package io.github.gilmardev.registrousuario.exception;

import io.github.gilmardev.registrousuario.dto.UsuarioDTO;

public class TipoErroPersonalizado extends UsuarioDTO {
	
	private String erroMensagem;
	
	public TipoErroPersonalizado(final String erroMensagem) {
		this.erroMensagem = erroMensagem;
	}
	
	
	public String getErroMensagem() {
		return erroMensagem;
	}
	
	

}
