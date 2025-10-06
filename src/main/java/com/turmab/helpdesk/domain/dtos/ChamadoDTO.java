package com.turmab.helpdesk.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.turmab.helpdesk.domain.Chamado;
import com.turmab.helpdesk.domain.enums.Prioridade;
import com.turmab.helpdesk.domain.enums.Status;

/**
 * Data Transfer Object (DTO) para a entidade Chamado.
 * Permite transferir dados entre as camadas sem expor a entidade completa.
 * Contém informações do chamado, incluindo IDs e nomes do técnico e cliente.
 * 
 * @author: Gabriel Samilo
 */
public class ChamadoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /** Identificador do chamado */
    private Integer id;

    /** Data de abertura do chamado */
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura;

    /** Data de fechamento do chamado */
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFechamento;

    /** Código da prioridade do chamado */
    private Integer prioridade;
    
    /** Código do status do chamado */
    private Integer status;
    
    /** Título do chamado */
    private String titulo;
    
    /** Observações do chamado */
    private String observacoes;
    
    /** ID do técnico responsável pelo chamado */
    private Integer tecnico;
    /** ID do cliente solicitante do chamado */
    private Integer cliente;
    
    /** Nome do técnico */
    private String nomeTecnico;
    
    /** Nome do cliente */
    private String nomeCliente;

    /** Construtor padrão */
    public ChamadoDTO() {
        super();
    }

    /**
     * Construtor que converte uma entidade Chamado em DTO.
     * 
     * @param obj Entidade Chamado a ser convertida
     */
    public ChamadoDTO(Chamado obj) {
        this.id = obj.getId();
        this.dataAbertura = obj.getDataAbertura();
        this.dataFechamento = obj.getDataFechamento();
        this.prioridade = obj.getPrioridade();
        this.status = obj.getStatus();
        this.titulo = obj.getTitulo();
        this.observacoes = obj.getObservacoes();
        this.tecnico = (obj.getTecnico() != null) ? obj.getTecnico().getId() : null;
        this.cliente = (obj.getCliente() != null) ? obj.getCliente().getId() : null;
        this.nomeTecnico = (obj.getTecnico() != null) ? obj.getTecnico().getNome() : null;
        this.nomeCliente = (obj.getCliente() != null) ? obj.getCliente().getNome() : null;
    }

    /** Getters e Setters */
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public LocalDate getDataAbertura() { return dataAbertura; }
    public void setDataAbertura(LocalDate dataAbertura) { this.dataAbertura = dataAbertura; }

    public LocalDate getDataFechamento() { return dataFechamento; }
    public void setDataFechamento(LocalDate dataFechamento) { this.dataFechamento = dataFechamento; }

    public Integer getPrioridade() { return prioridade; }
    public void setPrioridade(Integer prioridade) { this.prioridade = prioridade; }

    public Integer getStatus() { return status; }
    public void setStatus(Integer status) { this.status = status; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getObservacoes() { return observacoes; }
    public void setObservacoes(String observacoes) { this.observacoes = observacoes; }

    public Integer getTecnico() { return tecnico; }
    public void setTecnico(Integer tecnico) { this.tecnico = tecnico; }

    public Integer getCliente() { return cliente; }
    public void setCliente(Integer cliente) { this.cliente = cliente; }

    public String getNomeTecnico() { return nomeTecnico; }
    public void setNomeTecnico(String nomeTecnico) { this.nomeTecnico = nomeTecnico; }

    public String getNomeCliente() { return nomeCliente; }
    public void setNomeCliente(String nomeCliente) { this.nomeCliente = nomeCliente; }
}
