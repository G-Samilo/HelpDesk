package com.turmab.helpdesk.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.turmab.helpdesk.domain.Chamado;
import com.turmab.helpdesk.domain.Cliente;
import com.turmab.helpdesk.domain.Tecnico;
import com.turmab.helpdesk.domain.dtos.ChamadoDTO;
import com.turmab.helpdesk.domain.enums.Prioridade;
import com.turmab.helpdesk.domain.enums.Status;
import com.turmab.helpdesk.repositories.ChamadoRepository;
import com.turmab.helpdesk.service.exceptions.ObjectNotFoundException;


/**
 * Classe de serviço responsável por conter as regras de negócio
 * relacionadas à entidade {@link Chamado}.
 * 
 * <p>Realiza operações de CRUD, conversões entre DTOs e entidades, e
 * validações necessárias para manter a consistência dos dados.</p>
 * 
 * <p>Esta classe atua como intermediária entre a camada de controle
 * (Recursos/Resources) e a camada de persistência (Repositories).</p>
 * 
 * @author Gabriel Samilo
 * @version 1.0
 */
@Service
public class ChamadoService {

	/** Repositório responsável pelas operações de persistência de {@link Chamado}. */
    @Autowired
    private ChamadoRepository repository;

    /** Serviço responsável pela manipulação de técnicos. */
    @Autowired
    private TecnicoService tecnicoService;

    /** Serviço responsável pela manipulação de clientes. */
    @Autowired
    private ClienteService clienteService;

    /**
     * Busca um chamado pelo seu identificador único (ID).
     * 
     * @param id Identificador do chamado.
     * @return O objeto {@link Chamado} correspondente ao ID informado.
     * @throws ObjectNotFoundException Caso o chamado não seja encontrado no banco.
     */
    public Chamado findById(Integer id) {
        Optional<Chamado> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Chamado não encontrado! id: " + id));
    }

    /**
     * Retorna uma lista com todos os chamados cadastrados no sistema.
     * 
     * @return Uma lista de {@link ChamadoDTO} representando os chamados existentes.
     */
    public List<ChamadoDTO> findAll() {
        return repository.findAll().stream()
                .map(ChamadoDTO::new)
                .collect(Collectors.toList());
    }

    /**
     * Cria um novo chamado no sistema.
     * 
     * <p>Os dados são convertidos a partir de um objeto {@link ChamadoDTO} e salvos
     * no banco de dados. A associação com técnico e cliente é resolvida a partir
     * dos serviços correspondentes.</p>
     * 
     * @param objDTO Objeto {@link ChamadoDTO} com os dados do chamado a ser criado.
     * @return O {@link Chamado} recém-criado e persistido.
     */
    public Chamado create(ChamadoDTO objDTO) {
        return repository.save(newChamado(objDTO));
    }

    /**
     * Atualiza os dados de um chamado existente.
     * 
     * <p>O método busca o chamado atual, substitui os valores pelos informados no DTO,
     * e realiza a atualização no banco de dados.</p>
     * 
     * @param id     Identificador do chamado a ser atualizado.
     * @param objDTO Objeto {@link ChamadoDTO} contendo os novos dados.
     * @return O objeto {@link Chamado} atualizado.
     * @throws ObjectNotFoundException Caso o chamado não seja encontrado.
     */
    public Chamado update(Integer id, ChamadoDTO objDTO) {
        objDTO.setId(id);
        Chamado oldObj = findById(id);
        oldObj = newChamado(objDTO);
        return repository.save(oldObj);
    }

    /**
     * Converte um objeto {@link ChamadoDTO} em uma entidade {@link Chamado}.
     * 
     * <p>Durante a conversão, são resolvidos os relacionamentos de {@link Tecnico}
     * e {@link Cliente}, além da definição de prioridade e status com base nos
     * valores enumerados.</p>
     * 
     * @param obj Objeto {@link ChamadoDTO} a ser convertido.
     * @return Um novo objeto {@link Chamado} pronto para persistência.
     */
    private Chamado newChamado(ChamadoDTO obj) {
        Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
        Cliente cliente = clienteService.findById(obj.getCliente());

        Chamado chamado = new Chamado();
        if (obj.getId() != null) {
            chamado.setId(obj.getId());
        }
        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()).getCodigo());
        chamado.setStatus(Status.toEnum(obj.getStatus()).getCodigo());
        chamado.setTitulo(obj.getTitulo());
        chamado.setObservacoes(obj.getObservacoes());

        return chamado;
    }

    /**
     * Remove um chamado do sistema com base em seu ID.
     * 
     * <p>Antes da exclusão, o chamado é verificado para garantir sua existência.
     * Caso não seja encontrado, uma exceção é lançada.</p>
     * 
     * @param id Identificador do chamado a ser removido.
     * @throws ObjectNotFoundException Caso o chamado não exista no banco de dados.
     */
    public void delete(Integer id) {
        Chamado obj = findById(id);
        repository.deleteById(obj.getId());
    }
}
