package br.com.senai.sollaris.data.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.senai.sollaris.data.resources.ReturnProdutoDto;
import br.com.senai.sollaris.domain.Pedido_Itens;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "produtos_detalhes")
public class Produto_Detalhe {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String tamanho;
	private String peso;
	private String cor;
	private Long quantidade;
	
	@ManyToOne
	private Produto produto;
	
	//um produto detalhe vai fazer parte de muitos pedidos
	@OneToMany(mappedBy = "produto_Detalhe")
	List<Pedido_Itens> pedido_Itens = new ArrayList<>();

	public Produto_Detalhe(Produto produto, ReturnProdutoDto returnProdutoDto) {
		this.produto = produto;
		
		this.id = returnProdutoDto.getDetalhes_do_produto().getId();
		this.tamanho = returnProdutoDto.getDetalhes_do_produto().getTamanho();
		this.peso = returnProdutoDto.getDetalhes_do_produto().getPeso();
		this.cor = returnProdutoDto.getDetalhes_do_produto().getCor();
		this.quantidade = returnProdutoDto.getDetalhes_do_produto().getQuantidade();
	}
}
