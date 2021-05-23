package com.algaworks.algalog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.assembler.EntregaAssembler;
import com.algaworks.algalog.domain.repository.EntregaRepository;
import com.algaworks.algalog.domain.service.FinalizacaoEntregService;
import com.algaworks.algalog.domain.service.SolicitacaoEntregaService;
import com.algaworks.algalog.dto.EntregaDTO;
import com.algaworks.algalog.dto.input.EntregaInput;
import com.algaworks.algalog.model.Entrega;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

	@Autowired
	private EntregaRepository entregaRepository;

	@Autowired
	private FinalizacaoEntregService finalizacaoEntregService;

	@Autowired
	private SolicitacaoEntregaService solicitacaoEntregaService;

	@Autowired
	private EntregaAssembler entregaAssembler;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaDTO solicitar(@Valid @RequestBody EntregaInput entregaInput) {
		Entrega novaEntrega = entregaAssembler.toEntity(entregaInput);
		Entrega entregaSolicitada = solicitacaoEntregaService.solicitar(novaEntrega);

		return entregaAssembler.toDTO(entregaSolicitada);
	}

	@GetMapping
	public List<EntregaDTO> listar() {
		return entregaAssembler.toCollectionDTO(entregaRepository.findAll());
	}

	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaDTO> buscar(@PathVariable Long entregaId) {
		return entregaRepository.findById(entregaId).map(entrega -> ResponseEntity.ok(entregaAssembler.toDTO(entrega)))
				.orElse(ResponseEntity.notFound().build());

	}

	@PutMapping("/{entregaId}/finalizacao")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void finalizar(@PathVariable Long entregaId) {
		finalizacaoEntregService.finalizar(entregaId);
	}

}
