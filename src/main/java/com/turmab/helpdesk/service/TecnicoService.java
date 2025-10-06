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

/**
 * Classe de serviço responsável por conter as regras de negócio relacionadas à
 * entidade {@link Tecnico}. É responsável por realizar operações de CRUD,
 * validações e interações entre os repositórios.
 * 
 * <p>Esta classe segue o padrão de camada de serviço no modelo MVC, mantendo
 * a lógica de negócios separada dos controladores REST e dos repositórios.</p>
 * 
 * @author Gabriel Samilo
 * @version 1.0
 */
@Service
public class TecnicoService {
	
	/** Repositório responsável pelas operações de persistência de {@link Tecnico}. */
	@Autowired
	private TecnicoRepository repository; // como estende do JpaRepository já vão ter métodos que poderemos utilizar
	
	/** Repositório auxiliar para validação de CPF e e-mail. */
	@Autowired
	private PessoaRepository pessoaRepository;
	
	/** Responsável pela criptografia das senhas antes do armazenamento. */
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	/**
     * Busca um técnico pelo seu identificador único (ID).
     * 
     * @param id Identificador do técnico.
     * @return Um objeto {@link Tecnico} correspondente ao ID informado.
     * @throws ObjectNotFoundException Caso o técnico não seja encontrado no banco.
     */
	public Tecnico findById(Integer id) {
		
		Optional<Tecnico> obj = repository.findById(id);
			
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: "+ id));
		
	}
	
	/**
     * Retorna uma lista de todos os técnicos cadastrados no sistema.
     * 
     * @return Uma lista de {@link TecnicoDTO} contendo todos os técnicos.
     */
	public List<TecnicoDTO> findAll() {
	    List<Tecnico> list = repository.findAll();
	    return list.stream()
	               .map(TecnicoDTO::new)  
	               .collect(Collectors.toList());
	}

	/**
     * Valida se o CPF e o e-mail informados já estão cadastrados no sistema.
     * 
     * <p>Esta validação impede a duplicação de dados sensíveis como CPF e e-mail
     * entre diferentes usuários do sistema.</p>
     * 
     * @param objDTO Objeto {@link TecnicoCreateDTO} contendo os dados a validar.
     * @throws DataIntegrityViolationException Caso CPF ou e-mail já estejam em uso.
     */
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

	/**
     * Cria um novo registro de técnico no sistema.
     * 
     * <p>Antes de salvar, os dados são validados e a senha é criptografada usando
     * {@link BCryptPasswordEncoder}.</p>
     * 
     * @param objDTO Objeto {@link TecnicoCreateDTO} contendo os dados do novo técnico.
     * @return O técnico recém-criado.
     * @throws DataIntegrityViolationException Caso CPF ou e-mail já estejam cadastrados.
     */
	public Tecnico create(TecnicoCreateDTO objDTO) {
	    objDTO.setId(null);
	    validaPorCpfEEmail(objDTO);
	    
	    Tecnico newObj = new Tecnico(
	        null, 
	        objDTO.getNome(), 
	        objDTO.getCpf(), 
	        objDTO.getEmail(), 
	        encoder.encode(objDTO.getSenha()) 
	    );
	    
	    return repository.save(newObj);
	}

	/**
     * Atualiza os dados de um técnico existente.
     * 
     * <p>O método valida CPF e e-mail antes de salvar, e criptografa a senha caso
     * uma nova tenha sido informada.</p>
     * 
     * @param id     Identificador do técnico a ser atualizado.
     * @param objDTO Objeto {@link TecnicoCreateDTO} com os novos dados.
     * @return O técnico atualizado.
     * @throws ObjectNotFoundException Caso o técnico não exista.
     * @throws DataIntegrityViolationException Caso CPF ou e-mail entrem em conflito.
     */
	public Tecnico update(Integer id, TecnicoCreateDTO objDTO) {
	    objDTO.setId(id);
	    Tecnico oldObj = findById(id);
	    validaPorCpfEEmail(objDTO);
	    
	    oldObj.setNome(objDTO.getNome());
	    oldObj.setCpf(objDTO.getCpf());
	    oldObj.setEmail(objDTO.getEmail());
	    
	    if (objDTO.getSenha() != null && !objDTO.getSenha().isEmpty()) {
	        oldObj.setSenha(encoder.encode(objDTO.getSenha()));
	    }
	    
	    return repository.save(oldObj);
	}
	
	/**
     * Remove um técnico do sistema com base no ID informado.
     * 
     * <p>Antes da exclusão, é verificado se o técnico possui chamados associados.
     * Caso possua, a exclusão é bloqueada.</p>
     * 
     * @param id Identificador do técnico a ser removido.
     * @throws DataIntegrityViolationException Caso o técnico possua chamados vinculados.
     * @throws ObjectNotFoundException Caso o técnico não seja encontrado.
     */
	public void delete(Integer id) {
	    Tecnico obj = findById(id);
	    
	    if (obj.getChamados().size() > 0) {
	        throw new DataIntegrityViolationException("Técnico possui chamados e não pode ser deletado!");
	    }
	    
	    repository.deleteById(id);
	}


	
}