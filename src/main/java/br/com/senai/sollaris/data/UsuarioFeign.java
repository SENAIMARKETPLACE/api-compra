package br.com.senai.sollaris.data;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.senai.sollaris.data.resources.ReturnUsuarioDto;

@FeignClient(name = "api-usuario", url = "http://localhost:8200/")
public interface UsuarioFeign {
	
	@GetMapping("api/users/validate")
	public ResponseEntity<ReturnUsuarioDto> validarUsuario_Endereco(
			@RequestParam(name = "usuario", required = true) Integer usuarioId,
			@RequestParam(name = "endereco", required = true) Integer empresaId);
}
