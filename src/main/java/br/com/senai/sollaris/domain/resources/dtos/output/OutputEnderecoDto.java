package br.com.senai.sollaris.domain.resources.dtos.output;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import br.com.senai.sollaris.data.model.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@JsonInclude(value = Include.NON_NULL)
public class OutputEnderecoDto {
	
	private Integer id;
	private String cep;
	private String logradouro;
	private String numero;
	private String estado;
	private String bairro;
	private String cidade;
	@JsonInclude(value = Include.NON_EMPTY)
	private String complemento;
	
	public OutputEnderecoDto(Endereco endereco) {
		this.id = endereco.getId();
		this.cep = endereco.getCep();
		this.logradouro = endereco.getLogradouro();
		this.numero = endereco.getNumero();
		this.estado = endereco.getEstado();
		this.bairro = endereco.getBairro();
		this.cidade = endereco.getCidade();
		this.complemento = endereco.getComplemento();
	}
}
