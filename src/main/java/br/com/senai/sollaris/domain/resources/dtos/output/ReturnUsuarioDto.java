package br.com.senai.sollaris.domain.resources.dtos.output;

import br.com.senai.sollaris.data.model.Usuario;
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
	
	public ReturnUsuarioDto(Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.cpf = usuario.getCpf();
	}
}
