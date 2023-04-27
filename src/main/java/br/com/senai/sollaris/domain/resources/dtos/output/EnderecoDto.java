package br.com.senai.sollaris.domain.resources.dtos.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class EnderecoDto {
	
	private Integer id;
	private String cep;
	private String logradouro;
	private String numero;
	private String estado;
	private String bairro;
	private String cidade;
	private String complemento;
}
