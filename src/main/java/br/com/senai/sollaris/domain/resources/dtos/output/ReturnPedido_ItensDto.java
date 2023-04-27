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
	private Double valorUnitario;
	
	public ReturnPedido_ItensDto(Pedido_Itens pedido_item) {
		// TODO Auto-generated constructor stub
	}
}
