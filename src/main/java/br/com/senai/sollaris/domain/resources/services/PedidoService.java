package br.com.senai.sollaris.domain.resources.services;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
import br.com.senai.sollaris.data.resources.ReturnUsuarioDto2;
import br.com.senai.sollaris.domain.Pagamento;
import br.com.senai.sollaris.domain.Pedido;
import br.com.senai.sollaris.domain.Pedido_Itens;
import br.com.senai.sollaris.domain.repositories.PedidoRepository;
import br.com.senai.sollaris.domain.repositories.Pedido_ItensRepository;
import br.com.senai.sollaris.domain.resources.dtos.input.Pedido_ItensDto;
import br.com.senai.sollaris.domain.resources.dtos.input.PostPedidoDto;
import br.com.senai.sollaris.domain.resources.dtos.output.OutputPedidoDto;
import br.com.senai.sollaris.domain.resources.dtos.output.OutputUsuarioDto;
import br.com.senai.sollaris.domain.resources.services.exceptions.DadosInvalidosException;
import br.com.senai.sollaris.domain.resources.services.exceptions.ObjetoNaoEncontradoException;
import br.com.senai.sollaris.domain.resources.services.exceptions.PedidoNaoEncontradoException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Service
public class PedidoService {
	private final Environment env;
	
	private final PedidoRepository pedidoRepository;
	private final Pedido_ItensRepository pedido_ItensRepository;
	private final UsuarioFeign usuarioFeign;
	private final ProdutoFeign produtoFeign;
	
	private final PagamentoService pagamentoService;
	
	//Eu quero retornar um pedido com seus respectivos produtos
	public ResponseEntity<Page<OutputPedidoDto>> listarPedidos(Pageable pageable) {
		return ResponseEntity.ok(pedidoRepository.findAll(pageable).map(pedido -> new OutputPedidoDto(pedido)));
		
	}
	
	public ResponseEntity<OutputPedidoDto> listarPedido(Integer id) {
		return ResponseEntity.ok(pedidoRepository.findById(id)
				.map(OutputPedidoDto::new)
				.orElseThrow(() -> new ObjetoNaoEncontradoException("Pedido não localizado no sistema!")));		
	}
	
	@Transactional
	public ResponseEntity<OutputPedidoDto> cadastrarPedido(PostPedidoDto pedidoDto, UriComponentsBuilder uriBuilder) {
		log.info("PEDIDO_SERVICE ::: GET REQUEST ON: " + env.getProperty("local.server.port") + " port");
		
		try {
			//Ele vai validar o Usuario e o Endereço
			ReturnUsuarioDto2 usuarioDto = usuarioFeign.validarUsuario_Endereco(pedidoDto.getUsuario_id(), 
					pedidoDto.getEndereco_id()).getBody();
			
			Usuario usuario = new Usuario(usuarioDto);
			
			//Valida o endereço do usuário
			Endereco endereco = usuario.getEnderecos().get(0);
			
			//Valida se o Pagamento existe
			Pagamento pagamento = pagamentoService.buscarPagamento(pedidoDto.getPagamento_id());
			
			//Cadastra o pagamento sem pedido_itens
			Pedido pedido = new Pedido(usuario, endereco, pagamento);
			pedidoRepository.save(pedido);
			
			//Lista de Produtos para retorno
			List<Pedido_Itens> listaDeProdutos = new ArrayList<>();
			
			for (Pedido_ItensDto pedido_Itens : pedidoDto.getProdutos_selecionados()) {
				
				//Ele busca o produto na API de Produto
				ReturnProdutoDto returnProdutoDto = produtoFeign
						.listarProduto_DetalhePorId(pedido_Itens.getProduto_detalhe_id()).getBody();
			
			//Crio as Instâncias para gerar um Produto por completo
			Produto produto = new Produto(returnProdutoDto);
			Produto_Detalhe produto_Detalhe = new Produto_Detalhe(produto, returnProdutoDto);
			
			//Vou salvando cada produto já relacionado
			pedido_ItensRepository.save(new Pedido_Itens(pedido_Itens, pedido, produto_Detalhe));
			}
			
			//Pego os produtos que foram cadastrado
			listaDeProdutos =  pedido_ItensRepository.findByPedido_id(pedido.getId()).get();
			//Adiciono todo os produtos que peguei de acordo com o pedido
			pedido.salvarProdutos(listaDeProdutos);
			
			URI uri = uriBuilder.path("api/orders/{id}").buildAndExpand(pedido.getId()).toUri();
			return ResponseEntity.created(uri).body(new OutputPedidoDto(pedido));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return ResponseEntity.internalServerError().build();
	}
	
	@Transactional
	public ResponseEntity<Object> deletarPedido(Integer id) {
		if (pedidoRepository.existsById(id)) {
			pedidoRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
	}

	public ResponseEntity<List<OutputPedidoDto>> listarPedidoPorUsuario(Integer id) {
		
		List<OutputPedidoDto> pedidoDto = pedidoRepository.findByUsuario_Id(id)
				.map(pedidos -> pedidos.stream()
					.map(pedido -> {
						OutputPedidoDto outputPedidoDto = new OutputPedidoDto(pedido);
						// Realize qualquer transformação adicional no objeto "outputPedidoDto" se necessário
						return outputPedidoDto;
					})
					.collect(Collectors.toList())
				)
				.orElse(Collections.emptyList());
		
		if (pedidoDto.isEmpty())
			throw new PedidoNaoEncontradoException("Este pedido não foi encontrado com sucesso");
		
		return ResponseEntity.ok(pedidoDto);
		
	}
}
