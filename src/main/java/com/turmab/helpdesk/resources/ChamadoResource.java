package com.turmab.helpdesk.resources;

import java.net.URI;
import java.util.List;

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

import com.turmab.helpdesk.domain.Chamado;
import com.turmab.helpdesk.domain.dtos.ChamadoDTO;
import com.turmab.helpdesk.service.ChamadoService;

/**
 * Controlador REST responsável por gerenciar os endpoints relacionados à entidade {@link Chamado}.
 *
 * <p>
 * Esta classe faz a comunicação entre o cliente (frontend ou API externa) e a camada de serviço,
 * permitindo realizar operações CRUD sobre os chamados.
 * </p>
 *
 * <p>
 * Todos os endpoints retornam objetos {@link ChamadoDTO}, garantindo que apenas os dados
 * necessários sejam expostos na API.
 * </p>
 *
 * <p>
 * Exemplo de acesso: <b>http://localhost:8080/chamados</b>
 * </p>
 * 
 * @author Gabriel Samilo
 * @version 1.0
 */
@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {

    @Autowired
    private ChamadoService service;

    /**
     * Busca um chamado específico pelo seu identificador.
     *
     * @param id identificador do chamado
     * @return um {@link ChamadoDTO} correspondente ao ID informado
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id) {
        Chamado obj = service.findById(id);
        return ResponseEntity.ok().body(new ChamadoDTO(obj));
    }

    /**
     * Retorna uma lista com todos os chamados cadastrados.
     *
     * @return uma lista de {@link ChamadoDTO} representando os chamados existentes
     */
    @GetMapping
    public ResponseEntity<List<ChamadoDTO>> findAll() {
        List<ChamadoDTO> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    /**
     * Cria um novo chamado no sistema.
     *
     * <p>
     * O objeto {@link ChamadoDTO} é validado com {@code @Valid} antes de ser persistido.
     * Após a criação, é retornado o objeto criado juntamente com o cabeçalho de localização (URI).
     * </p>
     *
     * @param objDTO dados do chamado a ser criado
     * @return resposta HTTP 201 (Created) com o novo chamado
     */
    @PostMapping
    public ResponseEntity<ChamadoDTO> create(@Valid @RequestBody ChamadoDTO objDTO) {
        Chamado obj = service.create(objDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(new ChamadoDTO(obj));
    }

    /**
     * Atualiza os dados de um chamado existente.
     *
     * @param id identificador do chamado a ser atualizado
     * @param objDTO novos dados do chamado
     * @return o {@link ChamadoDTO} atualizado
     */
    @PutMapping(value = "/{id}")
    public ResponseEntity<ChamadoDTO> update(@PathVariable Integer id, @Valid @RequestBody ChamadoDTO objDTO) {
        Chamado obj = service.update(id, objDTO);
        return ResponseEntity.ok().body(new ChamadoDTO(obj));
    }

    /**
     * Exclui um chamado com base em seu identificador.
     *
     * @param id identificador do chamado a ser removido
     * @return resposta HTTP 204 (No Content) em caso de sucesso
     */
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
