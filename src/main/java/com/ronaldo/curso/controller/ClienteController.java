package com.ronaldo.curso.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	
	@GetMapping(value = "/buscar")
	public ResponseEntity<List<Cliente>> findByNome(@RequestParam(name = "nome") String nome) {
		List<Cliente> cliente = clienteService.findByNome(nome.trim().toUpperCase());
		
		return new ResponseEntity<List<Cliente>>(cliente, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Integer id) {
		Cliente cliente = clienteService.findById(id);
		
		return ResponseEntity.ok().body(cliente);
	}
}