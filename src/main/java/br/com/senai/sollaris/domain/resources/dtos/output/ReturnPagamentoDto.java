package br.com.senai.sollaris.domain.resources.dtos.output;

import br.com.senai.sollaris.domain.Pagamento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class ReturnPagamentoDto {
	private Integer id;
	private String nome;
	private String descricao;
	private Long tempoEmHoras;
	
	public ReturnPagamentoDto (Pagamento pagamento) {
		this.id = pagamento.getId();
		this.nome = pagamento.getNome();
		this.descricao = pagamento.getDescricao();
		this.tempoEmHoras = pagamento.getTempoEmHoras();
	}
}
