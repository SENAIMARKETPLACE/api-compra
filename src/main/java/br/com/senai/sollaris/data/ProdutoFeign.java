package br.com.senai.sollaris.data;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.senai.sollaris.data.resources.ReturnProdutoDto;

@FeignClient(name = "api-produtos", url = "http://localhost:8100/")
public interface ProdutoFeign {
		
	@GetMapping("api/products/detailed_product/{id}")
	public ResponseEntity<ReturnProdutoDto> listarProduto_DetalhePorId(@PathVariable Integer id);
}
