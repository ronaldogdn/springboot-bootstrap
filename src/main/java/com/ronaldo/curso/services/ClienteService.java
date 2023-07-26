package com.ronaldo.curso.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ronaldo.curso.domain.Cliente;
import com.ronaldo.curso.repositories.ClienteRepository;
import com.ronaldo.curso.services.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Transactional
	public Cliente insert(Cliente cliente) {
		//se não tem ID no objeto o JPA faz uma inserção nova
		//obj.setId(null);
		cliente = clienteRepository.save(cliente);
		return cliente;
	}
	
	public List<Cliente> findByNome(String nome) {		
		List<Cliente> cliente = clienteRepository.findByNome(nome);
		if(cliente == null) {
			return null;
		}
		return cliente;
	}

	public Cliente findById(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! id: " + id + ", Tipo: " + Cliente.class.getName()));
	}
}