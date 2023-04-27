package br.com.senai.sollaris.domain.resources.dtos.input;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class PostPedidoDto {
	private Integer usuario_id;
	private Integer endereco_id;
	private Integer pagamento_id;
	private List<Pedido_ItensDto> produtos_selecionados = new ArrayList<>();
}
