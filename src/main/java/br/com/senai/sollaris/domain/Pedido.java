package br.com.senai.sollaris.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	
	private Usuario usuario;
	
	private Endereco endereco;
	
	private Pagamento pagamento;
	
	private LocalDateTime data_pedido;
	private LocalDateTime data_expiracao;
	
	@Enumerated(EnumType.STRING)
	private PedidoStatus status;
}
