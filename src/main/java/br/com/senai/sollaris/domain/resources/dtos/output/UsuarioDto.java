package br.com.senai.sollaris.domain.resources.dtos.output;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class UsuarioDto {
	
	private Integer id;
	private String nome;
}
