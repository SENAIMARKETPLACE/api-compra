package br.com.senai.sollaris.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.senai.sollaris.data.model.Produto_Detalhe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pedidos_itens")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Pedido_Itens {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne()
	private Pedido pedido;
	
	@ManyToOne()
	private Produto_Detalhe produto_Detalhe;
	
	@Column(name = "qtd")
	private Integer quantidade;
}
