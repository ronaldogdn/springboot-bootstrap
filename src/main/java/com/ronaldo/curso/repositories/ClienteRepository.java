package com.ronaldo.curso.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.ronaldo.curso.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	@Transactional(readOnly = true)
	Optional<Cliente> findByNome(String nome);
}
