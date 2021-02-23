package io.github.gilmardev.registrousuario.exception;

import java.awt.TrayIcon.MessageType;

//Este DTO contém as informações de um único erro de validação.
public class ErroValidacaoCampo {

	private String arquivado;
	private String mensagem;
	private MessageType tipo;

	public String getArquivado() {
		return arquivado;
	}

	public void setArquivado(String arquivado) {
		this.arquivado = arquivado;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public MessageType getTipo() {
		return tipo;
	}

	public void setTipo(MessageType tipo) {
		this.tipo = tipo;
	}

}

/*
 * public enum MessageType { SUCCESS, INFO, WARNING, ERROR }
 */
