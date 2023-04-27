package br.com.senai.sollaris.domain.resources.services;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.senai.sollaris.domain.repositories.PedidoRepository;
import br.com.senai.sollaris.domain.resources.dtos.output.ReturnPedidoDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PedidoService {
	
	private final PedidoRepository pedidoRepository;
	
	//Eu quero retornar um pedido com seus respectivos produtos
	public void listarPedidos(Pageable pageable) {
		pedidoRepository.findAll(pageable).map(pedido -> new ReturnPedidoDto(pedido));
	}
}
