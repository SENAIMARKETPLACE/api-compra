package br.com.senai.sollaris.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.senai.sollaris.domain.resources.dtos.input.PostPagamentoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pagamentos")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Pagamento {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String descricao;
	private Long tempoEmHoras;
	
	//Muitos pagamentos podem estar em um pedido
	@OneToMany(mappedBy = "pagamento", cascade = CascadeType.PERSIST)
	List<Pedido> pedido_pagamento = new ArrayList<>();
	
	public Pagamento(PostPagamentoDto pagamentoDto) {
		this.nome = pagamentoDto.getNome();
		this.descricao = pagamentoDto.getDescricao();
		this.tempoEmHoras = pagamentoDto.getTempoEmHoras();
	}
}
