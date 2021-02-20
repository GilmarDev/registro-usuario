package io.github.gilmardev.registrousuario.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ErroDetalheCampo {

	private String erroTitulo;
	private int erroStatus;
	private String erroDetalhe;
	private long erroTimeStamp;
	private String erroPath;
	private String erroMensagemDesenvolvedor;
	private Map<String, List<ErroDetalheCampo>> erros = new HashMap<String, List<ErroDetalheCampo>>();

	public String getErroTitulo() {
		return erroTitulo;
	}

	public void setErroTitulo(String erroTitulo) {
		this.erroTitulo = erroTitulo;
	}

	public int getErroStatus() {
		return erroStatus;
	}

	public void setErroStatus(int erroStatus) {
		this.erroStatus = erroStatus;
	}

	public String getErroDetalhe() {
		return erroDetalhe;
	}

	public void setErroDetalhe(String erroDetalhe) {
		this.erroDetalhe = erroDetalhe;
	}

	public long getErroTimeStamp() {
		return erroTimeStamp;
	}

	public void setErroTimeStamp(long erroTimeStamp) {
		this.erroTimeStamp = erroTimeStamp;
	}

	public String getErroPath() {
		return erroPath;
	}

	public void setErroPath(String erroPath) {
		this.erroPath = erroPath;
	}

	public String getErroMensagemDesenvolvedor() {
		return erroMensagemDesenvolvedor;
	}

	public void setErroMensagemDesenvolvedor(String erroMensagemDesenvolvedor) {
		this.erroMensagemDesenvolvedor = erroMensagemDesenvolvedor;
	}

	public Map<String, List<ErroDetalheCampo>> getErros() {
		return erros;
	}

	public void setErros(Map<String, List<ErroDetalheCampo>> erros) {
		this.erros = erros;
	}

}
