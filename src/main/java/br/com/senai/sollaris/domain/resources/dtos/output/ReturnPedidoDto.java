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
	private ReturnUsuarioDto usuario;
	private ReturnEnderecoDto endereco;
	private List<ReturnPedido_ItensDto> produtos = new ArrayList<>();
	
	public ReturnPedidoDto(Pedido pedido) {
		this.pedido_id = pedido.getId();
		this.usuario = new ReturnUsuarioDto(pedido.getUsuario());
		this.endereco = new ReturnEnderecoDto(pedido.getEndereco());
		this.produtos = pedido.getPedido_Itens().stream()
				.map(pedido_item -> new ReturnPedido_ItensDto(pedido_item)).toList();
	}
	
}
