package com.turmab.helpdesk.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import javax.validation.Valid;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.turmab.helpdesk.domain.Tecnico;
import com.turmab.helpdesk.domain.dtos.TecnicoCreateDTO;
import com.turmab.helpdesk.domain.dtos.TecnicoDTO;
import com.turmab.helpdesk.service.TecnicoService;

/**
 * Recurso REST para gerenciar técnicos no sistema HelpDesk.
 * Fornece endpoints para operações CRUD sobre a entidade Tecnico.
 * 
 * Exemplo de URL base: localhost:8080/tecnicos
 * 
 * @Author: Gabriel Samilo
 */
@RestController // indica que a classe é um controlador rest
@RequestMapping(value = "/tecnicos") // indica que esta classe é um endpont e já indica o seu caminho de acesso na
										// URL, ex: 'localhost:8080/tecnicos'
public class TecnicoResource {

	@Autowired
	private TecnicoService service; // considerando que a classe de serviço e os métodos já estão criados.

	/**
     * Busca um cliente pelo seu ID.
     * 
     * @param id ID do cliente a ser buscado.
     * @return ResponseEntity contendo um ClienteDTO caso encontrado.
     */
	@GetMapping(value = "/{id}") // estou informando que estou recebendo uma variável de path
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {

		Tecnico obj = service.findById(id);

		return ResponseEntity.ok().body(new TecnicoDTO(obj));

	}
	
	/**
     * Lista todos os clientes cadastrados.
     * 
     * @return ResponseEntity contendo uma lista de ClienteDTO.
     */
	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll() {
	    List<TecnicoDTO> list = service.findAll();
	    return ResponseEntity.ok().body(list);
	}

	/**
     * Cria um novo cliente.
     * 
     * @param objDTO DTO contendo os dados do cliente a ser criado.
     * @return ResponseEntity com status 201 (Created) e o ClienteDTO criado.
     */
	@PostMapping
	public ResponseEntity<TecnicoDTO> create(@Valid @RequestBody TecnicoCreateDTO objDTO) {
	    Tecnico newObj = service.create(objDTO);
	    URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
	            .buildAndExpand(newObj.getId()).toUri();
	    return ResponseEntity.created(uri).body(new TecnicoDTO(newObj));
	}

	/**
     * Atualiza os dados de um cliente existente.
     * 
     * @param id ID do cliente a ser atualizado.
     * @param objDTO DTO contendo os novos dados do cliente.
     * @return ResponseEntity contendo o ClienteDTO atualizado.
     */
	@PutMapping(value = "/{id}")
	public ResponseEntity<TecnicoDTO> update(@PathVariable Integer id, @Valid @RequestBody TecnicoCreateDTO objDTO) {
	    Tecnico obj = service.update(id, objDTO);
	    return ResponseEntity.ok().body(new TecnicoDTO(obj));
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