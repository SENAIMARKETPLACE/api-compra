package br.com.senai.sollaris.domain.resources.dtos.output;

import java.util.ArrayList;
import java.util.List;

import br.com.senai.sollaris.domain.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ReturnPedidoDto {
	
	private Integer pedido_id;
	private UsuarioDto usuario;
	private EnderecoDto endereco;
	private List<ReturnPedido_ItensDto> produtos_selecionados = new ArrayList<>();
	
	public ReturnPedidoDto(Pedido pedido) {
		// TODO Auto-generated constructor stub
	}
	
}
