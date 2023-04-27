package br.com.senai.sollaris.domain.resources.dtos.output;

import br.com.senai.sollaris.data.model.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ReturnEnderecoDto {
	
	private Integer id;
	private String cep;
	private String logradouro;
	private String numero;
	private String estado;
	private String bairro;
	private String cidade;
	private String complemento;
	
	public ReturnEnderecoDto(Endereco endereco) {
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
