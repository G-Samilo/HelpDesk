package com.turmab.helpdesk.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turmab.helpdesk.domain.Pessoa;
import com.turmab.helpdesk.domain.Tecnico;
import com.turmab.helpdesk.domain.dtos.TecnicoDTO;
import com.turmab.helpdesk.repositories.PessoaRepository;
import com.turmab.helpdesk.repositories.TecnicoRepository;
import com.turmab.helpdesk.service.exceptions.DataIntegrityViolationException;
import com.turmab.helpdesk.service.exceptions.ObjectNotFoundException;

@Service
public class TecnicoService {

	@Autowired
	private TecnicoRepository repository; // como estende do JpaRepository já vão ter métodos quepoderemos utilizar
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public Tecnico findById(Integer id) {
		
		Optional<Tecnico> obj = repository.findById(id);
			
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: "+ id));
		
	}

	public List<TecnicoDTO> findAll() {
	    List<Tecnico> list = repository.findAll();
	    return list.stream()
	               .map(TecnicoDTO::new)  
	               .collect(Collectors.toList());
	}


	private void validaPorCpfEEmail(TecnicoDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
		}
		
		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if(obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
		}
	}




	
}