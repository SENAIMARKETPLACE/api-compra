package br.com.senai.sollaris.domain.resources.services;

import java.net.URI;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.senai.sollaris.domain.Pagamento;
import br.com.senai.sollaris.domain.repositories.PagamentoRepository;
import br.com.senai.sollaris.domain.resources.dtos.input.PostPagamentoDto;
import br.com.senai.sollaris.domain.resources.dtos.output.ReturnPagamentoDto;
import br.com.senai.sollaris.domain.resources.services.exceptions.ObjetoNaoEncontradoException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PagamentoService {
	
	private final PagamentoRepository pagamentoRepository;

	public ResponseEntity<Page<ReturnPagamentoDto>> listarPagamentos(Pageable pageable) {
		return ResponseEntity.ok(pagamentoRepository.findAll(pageable).map(ReturnPagamentoDto::new));
		
	}

	public ResponseEntity<ReturnPagamentoDto> listarPagamento(Integer id) {
		return ResponseEntity.ok(pagamentoRepository.findById(id)
				.map(ReturnPagamentoDto::new)
				.orElseThrow(() -> new ObjetoNaoEncontradoException("Método de pagamento escolhido não encontrado no sistema")));
	}
	
	@Transactional
	public ResponseEntity<ReturnPagamentoDto> cadastrarPagamento(@Valid PostPagamentoDto pagamentoDto, UriComponentsBuilder uriBuilder) {
		Pagamento pagamento = new Pagamento(pagamentoDto);
		pagamentoRepository.save(pagamento);
		
		URI uri = uriBuilder.path("api/payments/{id}").buildAndExpand(pagamento.getId()).toUri();
		return ResponseEntity.created(uri).body(new ReturnPagamentoDto(pagamento));
		
	}
	
	


}
