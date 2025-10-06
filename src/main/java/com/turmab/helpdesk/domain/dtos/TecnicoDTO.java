package com.turmab.helpdesk.domain.dtos;

import com.turmab.helpdesk.domain.enums.Perfil;

import java.io.Serializable;
import java.util.Set;
import java.util.stream.Collectors;


import com.turmab.helpdesk.domain.Tecnico;

/**
 * Data Transfer Object (DTO) para a entidade Tecnico.
 * Permite transferir dados do técnico sem expor a entidade completa.
 * Contém informações básicas, perfis e data de criação.
 * 
 * @author: Gabriel Samilo
 */
public class TecnicoDTO implements Serializable {
    
	private static final long serialVersionUID = 1L;

    /** Identificador do técnico */
    private Integer id;
    
    /** Nome do técnico */
    private String nome;
    
    /** CPF do técnico */
    private String cpf;
    
    /** Email do técnico */
    private String email;
    
    /** Conjunto de códigos dos perfis do técnico */
    private Set<Integer> perfis;
    
    /** Data de criação do técnico em formato String */
    private String dataCriacao;

    /** Construtor padrão */
    public TecnicoDTO() {
        super();
    }

    /**
     * Construtor que converte uma entidade Tecnico em DTO.
     *
     * @param obj Entidade Tecnico a ser convertida
     */
    public TecnicoDTO(Tecnico obj) {
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.perfis = obj.getPerfis().stream()
                         .map(Perfil::getCodigo)
                         .collect(Collectors.toSet());
        this.dataCriacao = (obj.getDataCriacao() != null) ? obj.getDataCriacao().toString() : null;
    }

    /** Getters e Setters */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Integer> getPerfis() {
        return perfis;
    }

    public void setPerfis(Set<Integer> perfis) {
        this.perfis = perfis;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}
