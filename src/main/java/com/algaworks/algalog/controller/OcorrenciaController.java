package com.algaworks.algalog.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algalog.assembler.OcorrenciaAssembler;
import com.algaworks.algalog.domain.service.BuscaEntregaService;
import com.algaworks.algalog.domain.service.RegistroOcorrenciaService;
import com.algaworks.algalog.dto.OcorrenciaDTO;
import com.algaworks.algalog.dto.input.OcorrenciaInput;
import com.algaworks.algalog.model.Entrega;
import com.algaworks.algalog.model.Ocorrencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/entregas/{entregaId}/ocorrencias")
public class OcorrenciaController {

	private BuscaEntregaService buscaEntregaService;

	private OcorrenciaAssembler ocorrenciaAssembler;

	private RegistroOcorrenciaService registroOcorrenciaService;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public OcorrenciaDTO resgistrar(@PathVariable Long entregaId, @Valid @RequestBody OcorrenciaInput ocorrenciaInput) {
		Ocorrencia ocorrenciaRegistrada = registroOcorrenciaService.registrar(entregaId,
				ocorrenciaInput.getDescricao());

		return ocorrenciaAssembler.toDTO(ocorrenciaRegistrada);
	}

	@GetMapping
	public List<OcorrenciaDTO> listar(@PathVariable Long entregaId) {
		Entrega entrega = buscaEntregaService.buscar(entregaId);

		return ocorrenciaAssembler.toColleciontDTO(entrega.getOcorrencias());
	}
}
