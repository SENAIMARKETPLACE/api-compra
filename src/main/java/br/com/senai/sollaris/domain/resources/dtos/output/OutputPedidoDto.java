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
public class OutputPedidoDto {
	
	private Integer pedido_id;
	private OutputUsuarioDto usuario;
	private OutputEnderecoDto endereco;
	private OutputPagamentoDto metodo_de_pagamento;
	private List<OutputPedido_ItensDto> produtos = new ArrayList<>();
	private Double valorTotal;
	
	public OutputPedidoDto(Pedido pedido) {
		this.pedido_id = pedido.getId();
		this.usuario = new OutputUsuarioDto(pedido.getUsuario());
		this.endereco = new OutputEnderecoDto(pedido.getEndereco());
		this.metodo_de_pagamento = new OutputPagamentoDto(pedido.getPagamento());
		this.produtos = pedido.getPedido_Itens().stream()
				.map(pedido_item -> new OutputPedido_ItensDto(pedido_item)).toList();
		this.valorTotal = calcularValorTotal(produtos);
	}
	
	public Double calcularValorTotal(List<OutputPedido_ItensDto> list) {
		Double valorTotal;
		double valor = 0;
		
		for (OutputPedido_ItensDto outputPedido_ItensDto : list) {
			valor += (outputPedido_ItensDto.getValorUnitario() * outputPedido_ItensDto.getQuantidade());
		}
		valorTotal = valor;
		
		return valorTotal;
	}



	
}
