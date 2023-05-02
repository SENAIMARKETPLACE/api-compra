package br.com.senai.sollaris.data.resources;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ReturnUsuarioDto {
	private Integer id;
	private String nome;
	private String cpf;
	private List<ReturnEnderecoDto> enderecos;
}
