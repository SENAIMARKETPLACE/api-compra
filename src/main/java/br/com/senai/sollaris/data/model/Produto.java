package br.com.senai.sollaris.data.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.senai.sollaris.data.resources.ReturnProdutoDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "produtos")
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class Produto {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nome;
	private String descricao;
	private Double preco;
	private String img;
	
	@OneToMany(mappedBy = "produto", cascade = CascadeType.ALL)
	private List<Produto_Detalhe> produto_Detalhes = new ArrayList<>();
	
	public Produto(ReturnProdutoDto returnProdutoDetalhe) {
		this.id = returnProdutoDetalhe.getId();
		this.nome = returnProdutoDetalhe.getNome();
		this.descricao = returnProdutoDetalhe.getDescricao();
		this.img = returnProdutoDetalhe.getImg();
		this.preco = returnProdutoDetalhe.getPreco();
	}
}
