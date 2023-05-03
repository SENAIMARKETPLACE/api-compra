package br.com.senai.sollaris.data.resources;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ReturnUsuarioDto2 {
	private Integer id;
	private String nome;
	private String cpf;
	private ReturnEnderecoDto endereco;
}
