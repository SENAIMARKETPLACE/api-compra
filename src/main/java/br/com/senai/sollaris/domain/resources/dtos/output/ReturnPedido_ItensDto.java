package br.com.senai.sollaris.domain.resources.dtos.output;

import br.com.senai.sollaris.domain.Pedido_Itens;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ReturnPedido_ItensDto {

	private Integer id;
	
	private Integer produto_detalhado_id;
	private String nome;
	private String descricao;
	private String img;
	private String tamanho;
	private String peso;
	private String cor;
	private Integer quantidade;
	private Double valorTotal;
	
	public ReturnPedido_ItensDto(Pedido_Itens pedido_item) {
		this.id = pedido_item.getId();
		this.produto_detalhado_id = pedido_item.getProduto_Detalhe().getId();
		this.nome = pedido_item.getProduto_Detalhe().getProduto().getNome();
		this.descricao = pedido_item.getProduto_Detalhe().getProduto().getNome();
		this.img = pedido_item.getProduto_Detalhe().getProduto().getImg();
		this.tamanho = pedido_item.getProduto_Detalhe().getTamanho();
		this.peso = pedido_item.getProduto_Detalhe().getPeso();
		this.cor = pedido_item.getProduto_Detalhe().getCor();
		this.quantidade = pedido_item.getQuantidade();
		this.valorTotal = pedido_item.getQuantidade() * pedido_item.getProduto_Detalhe().getProduto().getPreco();
	}
}
