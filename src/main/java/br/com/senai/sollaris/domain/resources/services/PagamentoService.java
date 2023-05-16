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
import br.com.senai.sollaris.domain.resources.dtos.input.PutPagamentoDto;
import br.com.senai.sollaris.domain.resources.dtos.output.OutputPagamentoDto;
import br.com.senai.sollaris.domain.resources.services.exceptions.PagamentoNaoEncontradoException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class PagamentoService {
	private final PagamentoRepository pagamentoRepository;

	public ResponseEntity<Page<OutputPagamentoDto>> listarPagamentos(Pageable pageable) {
		return ResponseEntity.ok(pagamentoRepository.findAll(pageable).map(OutputPagamentoDto::new));
		
	}

	public ResponseEntity<OutputPagamentoDto> listarPagamento(Integer id) {
		return ResponseEntity.ok(pagamentoRepository.findById(id)
				.map(OutputPagamentoDto::new)
				.orElseThrow(() -> new PagamentoNaoEncontradoException("Método de pagamento escolhido não encontrado no sistema")));
	}
	
	//Utilizado pelo PedidoService
	public Pagamento buscarPagamento(Integer id) {
		
		return pagamentoRepository.findById(id)
				.orElseThrow(() -> new PagamentoNaoEncontradoException("Método de pagamento escolhido inválido!"));
	}
	
	@Transactional
	public ResponseEntity<OutputPagamentoDto> cadastrarPagamento(@Valid PostPagamentoDto pagamentoDto, UriComponentsBuilder uriBuilder) {
		Pagamento pagamento = new Pagamento(pagamentoDto);
		pagamentoRepository.save(pagamento);
		
		URI uri = uriBuilder.path("api/payments/{id}").buildAndExpand(pagamento.getId()).toUri();
		return ResponseEntity.created(uri).body(new OutputPagamentoDto(pagamento));
		
	}
	
	@Transactional
	public ResponseEntity<OutputPagamentoDto> alterarPagamento(Integer id, PutPagamentoDto pagamentoDto) {
		Pagamento pagamento = pagamentoRepository.findById(id)
				.orElseThrow(() -> new PagamentoNaoEncontradoException("Método de pagamento não encontrado no sistema"));
		
		pagamento.alterarMetodoPagamento(pagamentoDto);
		
		return ResponseEntity.ok(new OutputPagamentoDto(pagamento));
		
	}
	
	@Transactional
	public ResponseEntity<Object> deletarPagamento(Integer id) {
		if (pagamentoRepository.existsById(id)) {
			pagamentoRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		
		return ResponseEntity.notFound().build();
		
	}

}
