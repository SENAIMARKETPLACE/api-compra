package br.com.senai.sollaris.domain.resources.dtos.input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Pedido_ItensDto {
	private Integer produto_detalhe_id;
	private Integer quantidade;
}
