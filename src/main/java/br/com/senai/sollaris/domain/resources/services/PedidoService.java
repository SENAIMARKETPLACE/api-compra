package br.com.senai.sollaris.domain.resources.services;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.senai.sollaris.data.ProdutoFeign;
import br.com.senai.sollaris.data.UsuarioFeign;
import br.com.senai.sollaris.data.model.Endereco;
import br.com.senai.sollaris.data.model.Produto;
import br.com.senai.sollaris.data.model.Produto_Detalhe;
import br.com.senai.sollaris.data.model.Usuario;
import br.com.senai.sollaris.data.resources.ReturnProdutoDto;
import br.com.senai.sollaris.data.resources.ReturnUsuarioDto;
import br.com.senai.sollaris.domain.Pagamento;
import br.com.senai.sollaris.domain.Pedido;
import br.com.senai.sollaris.domain.Pedido_Itens;
import br.com.senai.sollaris.domain.repositories.PedidoRepository;
import br.com.senai.sollaris.domain.resources.dtos.input.Pedido_ItensDto;
import br.com.senai.sollaris.domain.resources.dtos.input.PostPedidoDto;
import br.com.senai.sollaris.domain.resources.dtos.output.ReturnPedidoDto;
import br.com.senai.sollaris.domain.resources.services.exceptions.DadosInvalidosException;
import br.com.senai.sollaris.domain.resources.services.exceptions.ObjetoNaoEncontradoException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class PedidoService {
	private final Environment env;
	
	private final PedidoRepository pedidoRepository;
	private final UsuarioFeign usuarioFeign;
	private final ProdutoFeign produtoFeign;
	
	private final PagamentoService pagamentoService;
	
	//Eu quero retornar um pedido com seus respectivos produtos
	public ResponseEntity<Page<ReturnPedidoDto>> listarPedidos(Pageable pageable) {
		return ResponseEntity.ok(pedidoRepository.findAll(pageable).map(pedido -> new ReturnPedidoDto(pedido)));
		
	}
	
	public ResponseEntity<ReturnPedidoDto> listarPedido(Integer id) {
		return ResponseEntity.ok(pedidoRepository.findById(id)
				.map(ReturnPedidoDto::new)
				.orElseThrow(() -> new ObjetoNaoEncontradoException("Pedido não localizado no sistema!")));		
	}
	
	@Transactional
	public ResponseEntity<ReturnPedidoDto> cadastrarPedido(PostPedidoDto pedidoDto, UriComponentsBuilder uriBuilder) {
		log.info("PEDIDO_SERVICE ::: GET REQUEST ON: " + env.getProperty("local.server.port") + " port");
		
		try {
			//Ele vai validar o Usuario e o Endereço
			ReturnUsuarioDto usuarioDto = usuarioFeign.validarUsuario_Endereco(pedidoDto.getUsuario_id(), 
					pedidoDto.getEndereco_id()).getBody();
			
			Usuario usuario = new Usuario(usuarioDto);
			
			//Valida o endereço do usuário
			Endereco endereco = usuario.getEnderecos().stream()
					.filter(enderecoAux -> enderecoAux.getId() == pedidoDto.getEndereco_id())
					.findFirst().orElseThrow();
			
			//Valida se o Pagamento existe
			Pagamento pagamento = pagamentoService.buscarPagamento(pedidoDto.getPagamento_id());
			
			Pedido pedido = new Pedido(usuario, endereco, pagamento);
			pedidoRepository.save(pedido);
			
			//Validar se os Produtos existe
			List<Pedido_Itens> listaDeProdutos = new ArrayList<>();
			
			for (Pedido_ItensDto pedido_Itens : pedidoDto.getProdutos_selecionados()) {
				ReturnProdutoDto returnProdutoDto = produtoFeign
						.listarProduto_DetalhePorId(pedido_Itens.getProduto_detalhe_id()).getBody();
			
			Produto produto = new Produto(returnProdutoDto);
			Produto_Detalhe produto_Detalhe = new Produto_Detalhe(produto, returnProdutoDto);
			
			listaDeProdutos.add(new Pedido_Itens(pedido_Itens, pedido, produto_Detalhe));
			}
			
//			pedidoDto.getProdutos_selecionados().forEach(produtos_selecionados -> {
				
//				ReturnProdutoDto returnProdutoDto = produtoFeign
//							.listarProduto_DetalhePorId(produtos_selecionados.getProduto_detalhe_id()).getBody();
//				
//				Produto produto = new Produto(returnProdutoDto);
//				Produto_Detalhe produto_Detalhe = new Produto_Detalhe(produto, returnProdutoDto);
//				
//				listaDeProdutos.add(new Pedido_Itens(produtos_selecionados, pedido, produto_Detalhe));
//			});
			
			pedido.salvarProdutos(listaDeProdutos);
			
			URI uri = uriBuilder.path("api/orders/{id}").buildAndExpand(pedido.getId()).toUri();
			return ResponseEntity.created(uri).body(new ReturnPedidoDto(pedido));
			
		} catch (FeignException e) {
			throw new DadosInvalidosException("Usuário e/ou Produto estão inválido, tente novamente!");
		}
		
	}
	
	@Transactional
	public ResponseEntity<Object> deletarPedido(Integer id) {
		if (pedidoRepository.existsById(id)) {
			pedidoRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}
