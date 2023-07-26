package com.ronaldo.curso.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.ronaldo.curso.domain.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

	@Transactional(readOnly = true)
	@Query(value = "select c from Cliente c where upper(trim(c.nome)) like %?1%")
	List<Cliente> findByNome(String nome);
}
