package com.ronaldo.curso.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ronaldo.curso.domain.Cliente;
import com.ronaldo.curso.services.ClienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@PostMapping
	public ResponseEntity<Void> insert(@Valid @RequestBody Cliente obj){
		Cliente newCliente = clienteService.insert(obj);
		/**
		 * Ao inserir um novo dado é criado uma nova URI com um novo ID
		 * fromCurrentRequest => pega a URI
		 * path("/{id}") => acrescenta o ID na URI
		 * buildAndExpand(obj.getId()) => pega o ID do objeto para colocar no path
		 * .toUri() => converte para URI
		 */
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				  .path("/{id}").buildAndExpand(newCliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	@PostMapping(value = "/buscar")
	public ResponseEntity<Cliente> findByNome(@RequestBody String nome) {
		Cliente cliente = clienteService.findByNome(nome);
		
		return ResponseEntity.ok().body(cliente);
	}
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Integer id) {
		Cliente cliente = clienteService.findById(id);
		
		return ResponseEntity.ok().body(cliente);
	}
}