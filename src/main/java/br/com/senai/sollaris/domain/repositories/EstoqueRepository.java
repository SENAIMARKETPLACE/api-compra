package br.com.senai.sollaris.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senai.sollaris.domain.Estoque;

public interface EstoqueRepository extends JpaRepository<Integer, Estoque> {

}
