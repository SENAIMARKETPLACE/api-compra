package br.com.senai.sollaris.domain.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.senai.sollaris.domain.resources.dtos.input.PostPedidoDto;
import br.com.senai.sollaris.domain.resources.dtos.output.ReturnPedidoDto;
import br.com.senai.sollaris.domain.resources.services.PedidoService;

@RestController
@RequestMapping(path = "api/orders")
public class PedidoResources {
	
	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping
	public ResponseEntity<Page<ReturnPedidoDto>> listarPedidos(Pageable pageable) {
		return pedidoService.listarPedidos(pageable);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<ReturnPedidoDto> listarPedido(@PathVariable Integer id) {
		return pedidoService.listarPedido(id);
	}
	
	@PostMapping
	public void cadastrarPedido(PostPedidoDto pedidoDto, UriComponentsBuilder uriBuilder) {
		pedidoService.cadastrarPedido(pedidoDto, uriBuilder);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<Object> deletarPedido(@PathVariable Integer id) {
		return pedidoService.deletarPedido(id);
	}
}
