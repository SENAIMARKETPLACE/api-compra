package br.com.senai.sollaris.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senai.sollaris.domain.Pedido_Itens;

public interface Pedido_ItensRepository extends JpaRepository<Pedido_Itens, Integer>{

	Optional<List<Pedido_Itens>> findByPedido_id(Integer id);

}
