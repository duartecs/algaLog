package com.algaworks.algalog.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.algaworks.algalog.dto.EntregaDTO;
import com.algaworks.algalog.dto.input.EntregaInput;
import com.algaworks.algalog.model.Entrega;

@Component
public class EntregaAssembler {

	@Autowired
	private ModelMapper modelMapper;

	public EntregaDTO toDTO(Entrega entrega) {
		return modelMapper.map(entrega, EntregaDTO.class);
	}

	public List<EntregaDTO> toCollectionDTO(List<Entrega> entregas) {
		return entregas.stream().map(this::toDTO).collect(Collectors.toList());
	}

	public Entrega toEntity(EntregaInput entregaInput) {
		return modelMapper.map(entregaInput, Entrega.class);
	}
}
