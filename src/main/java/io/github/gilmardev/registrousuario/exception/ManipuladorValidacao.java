package io.github.gilmardev.registrousuario.exception;

import java.awt.TrayIcon.MessageType;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ManipuladorValidacao {
	
	private MessageSource messageSource;
	
	@Autowired
	public ManipuladorValidacao(MessageSource messageSource) {
		this.messageSource = messageSource;
	}
	
	//método para lidar com o erro de validação
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ErroDetalheCampo> erroValidacao(
			MethodArgumentNotValidException mExcecaoNaoValida, HttpServletRequest request){
			ErroDetalheCampo fErroDetalhes = new ErroDetalheCampo();
			fErroDetalhes.setErroTimeStamp(new Date().getTime());
			fErroDetalhes.setErroStatus(HttpStatus.BAD_REQUEST.value());
			fErroDetalhes.setErroTitulo("Erro de validação de campo");
			fErroDetalhes.setErroDetalhe("Falha na validação do campo entrada");
			fErroDetalhes.setErroMensagemDesenvolvedor(mExcecaoNaoValida.getClass().getName());
			fErroDetalhes.setErroPath(request.getRequestURI());
			
			BindingResult resultado = mExcecaoNaoValida.getBindingResult();
			List<FieldError> errosCampo = resultado.getFieldErrors();
			for (FieldError erro : errosCampo) {
				ErroValidacaoCampo fErro = erroProcessarCampo(erro);
				List<ErroValidacaoCampo> fValidacaoErrosLista = fErroDetalhes.getErros().get(erro.getField());
				if(fValidacaoErrosLista == null) {
				   fValidacaoErrosLista = new ArrayList<ErroValidacaoCampo>();
					
				}
				
				fValidacaoErrosLista.add(fErro);
				fErroDetalhes.getErros().put(erro.getField(),fValidacaoErrosLista);
				
				}
				return new ResponseEntity<ErroDetalheCampo>(fErroDetalhes,HttpStatus.BAD_REQUEST);
			}
	
		//método para processar erro de campo
		private ErroValidacaoCampo erroProcessarCampo(final FieldError erro) {
			ErroValidacaoCampo erroValidacaoCampo = new ErroValidacaoCampo();
			if(erro != null) {
				Locale localidadeAtual = LocaleContextHolder.getLocale();
				String msg = messageSource.getMessage(erro.getDefaultMessage(), null, localidadeAtual);
				erroValidacaoCampo.setArquivado(erro.getField());
				erroValidacaoCampo.setTipo(MessageType.ERROR);
				erroValidacaoCampo.setMensagem(msg);
			}
			return erroValidacaoCampo;
		}

}
