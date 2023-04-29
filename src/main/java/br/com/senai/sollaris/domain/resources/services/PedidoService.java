package br.com.senai.sollaris.domain.resources.services;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.senai.sollaris.domain.repositories.PedidoRepository;
import br.com.senai.sollaris.domain.resources.dtos.input.PostPedidoDto;
import br.com.senai.sollaris.domain.resources.dtos.output.ReturnPedidoDto;
import br.com.senai.sollaris.domain.resources.services.exceptions.ObjetoNaoEncontradoException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PedidoService {
	
	private final PedidoRepository pedidoRepository;
	
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
	public void cadastrarPedido(PostPedidoDto pedidoDto, UriComponentsBuilder uriBuilder) {
		
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
