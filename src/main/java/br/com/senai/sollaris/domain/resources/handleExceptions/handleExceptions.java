package br.com.senai.sollaris.domain.resources.handleExceptions;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.senai.sollaris.domain.resources.services.exceptions.DadosInvalidosException;
import br.com.senai.sollaris.domain.resources.services.exceptions.ObjetoNaoEncontradoException;
import br.com.senai.sollaris.domain.resources.services.exceptions.PagamentoNaoEncontradoException;
import br.com.senai.sollaris.domain.resources.services.exceptions.PedidoNaoEncontradoException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@ControllerAdvice
public class handleExceptions extends ResponseEntityExceptionHandler {
	
	private final MessageSource messageSource;
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		List<Campo> listaDeErros = new ArrayList<>();
		
		List<FieldError> allErrors = ex.getBindingResult().getFieldErrors();
		
		allErrors.forEach(error -> {
			String nome = error.getField();
			String campo = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			listaDeErros.add(new Campo(nome, campo));
		});
		
		RespostaException respostaException = new RespostaException(status.value(), listaDeErros);
		
		return handleExceptionInternal(ex, respostaException, headers, status, request);
	}
	
	@ExceptionHandler(ObjetoNaoEncontradoException.class)
	public ResponseEntity<RespostaException> ObjetoNaoEncontrado(ObjetoNaoEncontradoException ex, HttpServletRequest httpServletRequest) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		RespostaException respostaException = new RespostaException(ex.getMessage(), status.value(), httpServletRequest);
		
		return ResponseEntity.status(status).body(respostaException);
	}
	
	@ExceptionHandler(DadosInvalidosException.class)
	public ResponseEntity<RespostaException> DadosInvalidos(DadosInvalidosException ex, HttpServletRequest httpServletRequest) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		RespostaException respostaException = new RespostaException(ex.getMessage(), status.value(), httpServletRequest);
		
		return ResponseEntity.status(status).body(respostaException);
	}
	
	@ExceptionHandler(PagamentoNaoEncontradoException.class)
	public ResponseEntity<RespostaException> PagamentoNaoEncontrado(PagamentoNaoEncontradoException ex, HttpServletRequest httpServletRequest) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		RespostaException respostaException = new RespostaException(ex.getMessage(), status.value(), httpServletRequest);
		
		return ResponseEntity.status(status).body(respostaException);
	}
	
	@ExceptionHandler(PedidoNaoEncontradoException.class)
	public ResponseEntity<RespostaException> PagamentoNaoEncontrado(PedidoNaoEncontradoException ex, HttpServletRequest httpServletRequest) {
		HttpStatus status = HttpStatus.NOT_FOUND;
		
		RespostaException respostaException = new RespostaException(ex.getMessage(), status.value(), httpServletRequest);
		
		return ResponseEntity.status(status).body(respostaException);
	}

}
