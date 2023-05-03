package br.com.senai.sollaris.data.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.senai.sollaris.data.resources.ReturnUsuarioDto2;
import br.com.senai.sollaris.domain.Pedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Usuario")
@Table(name = "usuarios")
public class Usuario {
	

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private String cpf;
	
	//Um usuario pode fazer varios pedidos
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.PERSIST)
	List<Pedido> listaPedidos = new ArrayList<>();
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Endereco> enderecos = new ArrayList<>();
	
	public Usuario(ReturnUsuarioDto2 usuarioDto) {
		this.id = usuarioDto.getId();
		this.nome = usuarioDto.getNome();
		this.cpf = usuarioDto.getCpf();
		this.enderecos.add(new Endereco(usuarioDto.getEndereco()));
				
	}
}
