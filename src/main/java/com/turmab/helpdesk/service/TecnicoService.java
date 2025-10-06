package com.turmab.helpdesk.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.turmab.helpdesk.domain.Pessoa;
import com.turmab.helpdesk.domain.Tecnico;
import com.turmab.helpdesk.domain.dtos.TecnicoCreateDTO;
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
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
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


	private void validaPorCpfEEmail(TecnicoCreateDTO objDTO) {
	    Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
	    if (obj.isPresent() && !obj.get().getId().equals(objDTO.getId())) {
	        throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
	    }

	    obj = pessoaRepository.findByEmail(objDTO.getEmail());
	    if (obj.isPresent() && !obj.get().getId().equals(objDTO.getId())) {
	        throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
	    }
	}

	public Tecnico create(TecnicoCreateDTO objDTO) {
	    objDTO.setId(null); // Garante novo cadastro
	    validaPorCpfEEmail(objDTO);
	    
	    Tecnico newObj = new Tecnico(
	        null, 
	        objDTO.getNome(), 
	        objDTO.getCpf(), 
	        objDTO.getEmail(), 
	        encoder.encode(objDTO.getSenha()) // Criptografa
	    );
	    
	    return repository.save(newObj);
	}

	public Tecnico update(Integer id, TecnicoCreateDTO objDTO) {
	    objDTO.setId(id); // Define o ID para update
	    Tecnico oldObj = findById(id);
	    validaPorCpfEEmail(objDTO);
	    
	    // Atualiza os campos
	    oldObj.setNome(objDTO.getNome());
	    oldObj.setCpf(objDTO.getCpf());
	    oldObj.setEmail(objDTO.getEmail());
	    
	    // Só atualiza senha se foi enviada
	    if (objDTO.getSenha() != null && !objDTO.getSenha().isEmpty()) {
	        oldObj.setSenha(encoder.encode(objDTO.getSenha()));
	    }
	    
	    return repository.save(oldObj);
	}
	public void delete(Integer id) {
	    Tecnico obj = findById(id);
	    
	    // Verifica se o técnico possui chamados antes de deletar
	    if (obj.getChamados().size() > 0) {
	        throw new DataIntegrityViolationException("Técnico possui chamados e não pode ser deletado!");
	    }
	    
	    repository.deleteById(id);
	}


	
}