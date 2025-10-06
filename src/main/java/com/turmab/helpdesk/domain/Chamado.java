package com.turmab.helpdesk.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.turmab.helpdesk.domain.enums.Prioridade;
import com.turmab.helpdesk.domain.enums.Status;

/**
 * Entidade Chamado que representa um chamado do sistema HelpDesk.
 * Contém informações sobre o chamado, como prioridade, status, título,
 * observações, técnico responsável e cliente solicitante.
 * 
 * @author: Gabriel Samilo
 */
@Entity
public class Chamado {

	/** Identificador único do chamado */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Data de abertura do chamado */
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura = LocalDate.now();

    /** Data de fechamento do chamado */
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataFechamento;

    /** Código da prioridade do chamado (usando enum Prioridade) */
    private Integer prioridade;
    
    /** Código do status do chamado (usando enum Status) */
    private Integer status;
    
    /** Título do chamado */
    private String titulo;
    
    /** Observações adicionais sobre o chamado */
    private String observacoes;

    /** Técnico responsável pelo chamado */
    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private Tecnico tecnico;

    /** Cliente solicitante do chamado */
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    /** Construtor padrão */
    public Chamado() {
        super();
    }

    /**
     * Construtor com parâmetros para criar um chamado.
     * 
     * @param id Identificador do chamado
     * @param prioridade Prioridade do chamado (enum Prioridade)
     * @param status Status do chamado (enum Status)
     * @param titulo Título do chamado
     * @param observacoes Observações sobre o chamado
     * @param tecnico Técnico responsável pelo chamado
     * @param cliente Cliente solicitante do chamado
     */
    public Chamado(Integer id, Prioridade prioridade, Status status, String titulo,
                   String observacoes, Tecnico tecnico, Cliente cliente) {
        this.id = id;
        this.prioridade = prioridade.getCodigo();
        this.status = status.getCodigo();
        this.titulo = titulo;
        this.observacoes = observacoes;
        this.tecnico = tecnico;
        this.cliente = cliente;
    }

    /** @return o ID do chamado */
    public Integer getId() {
        return id;
    }

    /** @param id define o ID do chamado */
    public void setId(Integer id) {
        this.id = id;
    }

    /** @return a data de abertura do chamado */
    public LocalDate getDataAbertura() {
        return dataAbertura;
    }
    
    /** @param dataAbertura define a data de abertura do chamado */
    public void setDataAbertura(LocalDate dataAbertura) {
        this.dataAbertura = dataAbertura;
    }

    /** @return a data de fechamento do chamado */
    public LocalDate getDataFechamento() {
        return dataFechamento;
    }

    /** @param dataFechamento define a data de fechamento do chamado */
    public void setDataFechamento(LocalDate dataFechamento) {
        this.dataFechamento = dataFechamento;
    }

    /** @return o código da prioridade */
    public Integer getPrioridade() {
        return prioridade;
    }

    /** @param prioridade define o código da prioridade */
    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }

    /** @return o código do status */
    public Integer getStatus() {
        return status;
    }

    /** @param status define o código do status */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /** @return o título do chamado */
    public String getTitulo() {
        return titulo;
    }

    /** @param titulo define o título do chamado */
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    /** @return as observações do chamado */
    public String getObservacoes() {
        return observacoes;
    }

    /** @param observacoes define as observações do chamado */
    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    /** @return o técnico responsável pelo chamado */
    public Tecnico getTecnico() {
        return tecnico;
    }

    /** @param tecnico define o técnico responsável pelo chamado */
    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    /** @return o cliente solicitante do chamado */
    public Cliente getCliente() {
        return cliente;
    }

    /** @param cliente define o cliente solicitante do chamado */
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
