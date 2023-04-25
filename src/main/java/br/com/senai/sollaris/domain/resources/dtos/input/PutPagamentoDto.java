package br.com.senai.sollaris.domain.resources.dtos.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class PutPagamentoDto {
	@NotBlank
	private String nome;
	@NotBlank
	private String descricao;
	@NotNull
	private Long tempoEmHoras;
}
