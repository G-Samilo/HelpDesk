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

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;
    
    @Autowired
    private BCryptPasswordEncoder encoder;

    public Cliente findById(Integer id) {
        Optional<Cliente> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! id: " + id));
    }

    public List<ClienteDTO> findAll() {
        List<Cliente> list = repository.findAll();
        return list.stream()
                .map(ClienteDTO::new)
                .collect(Collectors.toList()); // Java 11
    }

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


    // Criar cliente
    public Cliente create(ClienteCreateDTO objDTO) {
        objDTO.setId(null);
        validaPorCpfEEmail(objDTO);
        Cliente newObj = new Cliente(null, objDTO.getNome(), objDTO.getCpf(),
                                     objDTO.getEmail(), encoder.encode(objDTO.getSenha())); // Criptografa
        return repository.save(newObj);
    }

    // Atualizar cliente
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

    // Deletar cliente
    public void delete(Integer id) {
        Cliente obj = findById(id);
        if (obj.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("Cliente possui chamados e não pode ser deletado!");
        }
        repository.deleteById(id);
    }
}
