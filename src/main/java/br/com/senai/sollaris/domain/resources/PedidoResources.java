package br.com.senai.sollaris.domain.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.sollaris.domain.resources.dtos.output.ReturnPedidoDto;
import br.com.senai.sollaris.domain.resources.services.PedidoService;

@RestController
@RequestMapping(path = "api/orders")
public class PedidoResources {
	
	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping
	public ResponseEntity<Page<ReturnPedidoDto>> listarTodos(Pageable pageable) {
		return pedidoService.listarPedidos(pageable);
	}
}
