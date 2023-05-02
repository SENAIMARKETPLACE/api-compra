package br.com.senai.sollaris.domain.resources.dtos.input;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class PostPedidoDto {
	@NotNull
	private Integer usuario_id;
	@NotNull
	private Integer endereco_id;
	@NotNull
	private Integer pagamento_id;
	@NotNull
	private List<Pedido_ItensDto> produtos_selecionados = new ArrayList<>();
}
