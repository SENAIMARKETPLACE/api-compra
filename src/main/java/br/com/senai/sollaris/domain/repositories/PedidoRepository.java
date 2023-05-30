package br.com.senai.sollaris.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senai.sollaris.domain.Pedido;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

	Optional<List<Pedido>> findByUsuario_Id(Integer id);

}
