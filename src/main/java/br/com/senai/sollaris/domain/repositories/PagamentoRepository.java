package br.com.senai.sollaris.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senai.sollaris.domain.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
