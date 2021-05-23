package com.algaworks.algalog.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import com.algaworks.algalog.dto.OcorrenciaDTO;
import com.algaworks.algalog.model.Ocorrencia;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class OcorrenciaAssembler {

	private ModelMapper modelMapper;

	public OcorrenciaDTO toDTO(Ocorrencia ocorrencia) {
		return modelMapper.map(ocorrencia, OcorrenciaDTO.class);
	}

	public List<OcorrenciaDTO> toColleciontDTO(List<Ocorrencia> ocorrencias) {
		return ocorrencias.stream().map(this::toDTO).collect(Collectors.toList());
	}

}
