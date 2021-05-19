package com.algaworks.algalog.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.model.Cliente;

@RestController
public class ClienteController {
	
	@GetMapping("/clientes")
	public List<Cliente> listar() {
		var cliente1 = new Cliente();
		cliente1.setId(1L);
		cliente1.setNome("Jaum2");
		cliente1.setTelefone("9999-9999");
		cliente1.setEmail("teste@teste");
		
		var cliente2 = new Cliente();
		cliente2.setId(2L);
		cliente2.setNome("Marie");
		cliente2.setTelefone("8888-8888");
		cliente2.setEmail("teste2@teste");
		return Arrays.asList(cliente1, cliente2);
	}

}
