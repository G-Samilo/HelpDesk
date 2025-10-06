package com.turmab.helpdesk.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.net.URI;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.turmab.helpdesk.domain.Cliente;
import com.turmab.helpdesk.domain.dtos.ClienteCreateDTO;
import com.turmab.helpdesk.domain.dtos.ClienteDTO;
import com.turmab.helpdesk.service.ClienteService;

/**
 * Recurso REST para gerenciar clientes no sistema HelpDesk.
 * Fornece endpoints para operações CRUD sobre a entidade Cliente.
 * 
 * Exemplo de URL base: localhost:8080/clientes
 * 
 * @author Gabriel Samilo
 */
@RestController
@RequestMapping(value = "/clientes") // Exemplo: localhost:8080/clientes
public class ClienteResource {

    @Autowired
    private ClienteService service;

    /**
     * Busca um cliente pelo seu ID.
     * 
     * @param id ID do cliente a ser buscado.
     * @return ResponseEntity contendo um ClienteDTO caso encontrado.
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> findById(@PathVariable Integer id) {
        Cliente obj = service.findById(id);
        return ResponseEntity.ok().body(new ClienteDTO(obj));
    }

    /**
     * Lista todos os clientes cadastrados.
     * 
     * @return ResponseEntity contendo uma lista de ClienteDTO.
     */
    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<ClienteDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }
    
    /**
     * Cria um novo cliente.
     * 
     * @param objDTO DTO contendo os dados do cliente a ser criado.
     * @return ResponseEntity com status 201 (Created) e o ClienteDTO criado.
     */
    @PostMapping
    public ResponseEntity<ClienteDTO> create(@Valid @RequestBody ClienteCreateDTO objDTO) {
        Cliente newObj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).body(new ClienteDTO(newObj));
    }

    /**
     * Atualiza os dados de um cliente existente.
     * 
     * @param id ID do cliente a ser atualizado.
     * @param objDTO DTO contendo os novos dados do cliente.
     * @return ResponseEntity contendo o ClienteDTO atualizado.
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<ClienteDTO> update(@PathVariable Integer id, @Valid @RequestBody ClienteCreateDTO objDTO) {
        Cliente obj = service.update(id, objDTO);
        return ResponseEntity.ok().body(new ClienteDTO(obj));
    }

    /**
     * Remove um cliente pelo seu ID.
     * 
     * @param id ID do cliente a ser removido.
     * @return ResponseEntity com status 204 (No Content).
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
