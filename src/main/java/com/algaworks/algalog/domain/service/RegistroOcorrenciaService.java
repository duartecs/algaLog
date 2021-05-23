package com.algaworks.algalog.domain.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algalog.model.Entrega;
import com.algaworks.algalog.model.Ocorrencia;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class RegistroOcorrenciaService {

	private BuscaEntregaService buscaEntregaService;

	@Transactional
	public Ocorrencia registrar(Long entregaId, String descricao) {
		Entrega entrega = buscaEntregaService.buscar(entregaId);

		return entrega.adicionarOcorrencia(descricao);
	}
}
