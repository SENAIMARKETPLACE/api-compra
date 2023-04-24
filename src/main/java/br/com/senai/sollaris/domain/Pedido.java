package br.com.senai.sollaris.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.senai.sollaris.data.model.Endereco;
import br.com.senai.sollaris.data.model.Usuario;
import br.com.senai.sollaris.domain.enums.PedidoStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "pedidos")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Pedido {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne
	private Usuario usuario;
	
	@ManyToOne
	private Endereco endereco;
	
	@ManyToOne
	private Pagamento pagamento;
	
	private LocalDateTime data_pedido;
	
	@Enumerated(EnumType.STRING)
	private PedidoStatus status;
	
	@OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL)
	private List<Pedido_Itens> pedido_Itens = new ArrayList<>();

	public LocalDateTime getDataExpiracao() {
		return data_pedido.plusHours(pagamento.getTempoEmHoras());
	}
}
