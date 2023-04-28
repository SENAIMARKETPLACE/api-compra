package br.com.senai.sollaris.domain.resources.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.senai.sollaris.domain.repositories.PedidoRepository;
import br.com.senai.sollaris.domain.resources.dtos.output.ReturnPedidoDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PedidoService {
	
	private final PedidoRepository pedidoRepository;
	
	//Eu quero retornar um pedido com seus respectivos produtos
	public ResponseEntity<Page<ReturnPedidoDto>> listarPedidos(Pageable pageable) {
		return ResponseEntity.ok(pedidoRepository.findAll(pageable).map(pedido -> new ReturnPedidoDto(pedido)));
		
	}
}
