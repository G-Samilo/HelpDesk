package com.turmab.helpdesk.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.turmab.helpdesk.domain.Cliente;
import com.turmab.helpdesk.domain.Pessoa;
import com.turmab.helpdesk.domain.dtos.ClienteCreateDTO;
import com.turmab.helpdesk.domain.dtos.ClienteDTO;
import com.turmab.helpdesk.domain.dtos.CredenciaisDTO;
import com.turmab.helpdesk.repositories.ClienteRepository;
import com.turmab.helpdesk.repositories.PessoaRepository;
import com.turmab.helpdesk.service.exceptions.DataIntegrityViolationException;
import com.turmab.helpdesk.service.exceptions.ObjectNotFoundException;

/**
 * Classe de serviço responsável pelas regras de negócio relacionadas
 * à entidade {@link Cliente}.
 * 
 * <p>Realiza operações de CRUD, validações de CPF e e-mail, e
 * aplica criptografia nas senhas antes de salvar no banco de dados.</p>
 * 
 * <p>Segue o padrão de arquitetura em camadas, servindo como intermediária
 * entre os controladores REST (Resources) e os repositórios (Repositories).</p>
 * 
 * @author Gabriel Samilo
 * @version 1.0
 */
@Service
public class ClienteService {

	/** Repositório responsável pelas operações de persistência de {@link Cliente}. */
    @Autowired
    private ClienteRepository repository;

    /** Repositório auxiliar usado para validação de CPF e e-mail em {@link Pessoa}. */
    @Autowired
    private PessoaRepository pessoaRepository;
    
    /** Utilitário de criptografia utilizado para codificar as senhas. */
    @Autowired
    private BCryptPasswordEncoder encoder;

    /**
     * Busca um cliente pelo seu identificador único (ID).
     * 
     * @param id Identificador do cliente.
     * @return O objeto {@link Cliente} correspondente ao ID informado.
     * @throws ObjectNotFoundException Caso o cliente não seja encontrado no banco de dados.
     */
    public Cliente findById(Integer id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! id: " + id));
    }

    /**
     * Retorna uma lista contendo todos os clientes cadastrados no sistema.
     * 
     * @return Lista de {@link ClienteDTO} representando os clientes cadastrados.
     */
    public List<ClienteDTO> findAll() {
        List<Cliente> list = repository.findAll();
        return list.stream()
                .map(ClienteDTO::new)
                .collect(Collectors.toList()); // Java 11
    }

    /**
     * Valida se o CPF e o e-mail informados já estão cadastrados no sistema.
     * 
     * <p>Essa validação evita duplicidade de informações sensíveis
     * entre os registros de usuários.</p>
     * 
     * @param objDTO Objeto {@link ClienteCreateDTO} com os dados a validar.
     * @throws DataIntegrityViolationException Caso o CPF ou e-mail já estejam em uso.
     */
    private void validaPorCpfEEmail(ClienteCreateDTO objDTO) {
        Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }

        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
            throw new DataIntegrityViolationException("E-mail já cadastrado no sistema!");
        }
    }


    /**
     * Cria um novo registro de cliente no sistema.
     * 
     * <p>Antes de salvar, a senha é criptografada com {@link BCryptPasswordEncoder}
     * e é realizada a validação de CPF e e-mail.</p>
     * 
     * @param objDTO Objeto {@link ClienteCreateDTO} contendo os dados do novo cliente.
     * @return O {@link Cliente} recém-criado e persistido no banco de dados.
     * @throws DataIntegrityViolationException Caso CPF ou e-mail já existam no sistema.
     */
    public Cliente create(ClienteCreateDTO objDTO) {
        objDTO.setId(null);
        validaPorCpfEEmail(objDTO);
        Cliente newObj = new Cliente(null, objDTO.getNome(), objDTO.getCpf(),
                                     objDTO.getEmail(), encoder.encode(objDTO.getSenha())); // Criptografa
        return repository.save(newObj);
    }

    /**
     * Atualiza os dados de um cliente existente no sistema.
     * 
     * <p>O método valida CPF e e-mail, criptografa a nova senha e
     * persiste as alterações no banco.</p>
     * 
     * @param id Identificador do cliente a ser atualizado.
     * @param objDTO Objeto {@link ClienteCreateDTO} com os novos dados.
     * @return O {@link Cliente} atualizado.
     * @throws ObjectNotFoundException Caso o cliente não seja encontrado.
     * @throws DataIntegrityViolationException Caso CPF ou e-mail estejam em conflito.
     */
    public Cliente update(Integer id, ClienteCreateDTO objDTO) {
        objDTO.setId(id);
        Cliente oldObj = findById(id);
        validaPorCpfEEmail(objDTO);
        oldObj.setNome(objDTO.getNome());
        oldObj.setCpf(objDTO.getCpf());
        oldObj.setEmail(objDTO.getEmail());
        oldObj.setSenha(encoder.encode(objDTO.getSenha())); // Criptografa
        return repository.save(oldObj);
    }

    /**
     * Exclui um cliente do sistema com base em seu ID.
     * 
     * <p>Antes da exclusão, é verificado se o cliente possui chamados associados.
     * Caso possua, a exclusão é bloqueada para preservar a integridade dos dados.</p>
     * 
     * @param id Identificador do cliente a ser removido.
     * @throws DataIntegrityViolationException Caso o cliente possua chamados vinculados.
     * @throws ObjectNotFoundException Caso o cliente não seja encontrado.
     */
    public void delete(Integer id) {
        Cliente obj = findById(id);
        if (obj.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("Cliente possui chamados e não pode ser deletado!");
        }
        repository.deleteById(id);
    }
}
