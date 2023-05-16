package br.com.senai.sollaris.domain.resources.handleExceptions;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@JsonInclude(value = Include.NON_NULL)
public class RespostaException {
	
	private String titulo;
	private StringBuffer url;
	private Integer status;
	private String recurso;
	private String data_requisicao;
	private List<Campo> campos;
	
	public RespostaException(Integer value, List<Campo> listaDeErros) {
		this.titulo = "Um ou mais campos estão inválidos ou não foram preenchidas corretamente, tente novamente!";
		this.status = value;
		this.data_requisicao = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
		this.campos = listaDeErros;
	}

	public RespostaException(String message, int value, HttpServletRequest httpServletRequest) {
		this.titulo = message;
		this.status = value;
		this.recurso = httpServletRequest.getRequestURI();
		this.url = httpServletRequest.getRequestURL();
		this.data_requisicao = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
	}
}
