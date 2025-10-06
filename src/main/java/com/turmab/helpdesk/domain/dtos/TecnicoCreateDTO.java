package com.turmab.helpdesk.domain.dtos;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Data Transfer Object (DTO) utilizado para criar ou atualizar um técnico.
 * Contém validações básicas para os campos obrigatórios.
 * 
 * @author: Gabriel Samilo
 */
public class TecnicoCreateDTO implements Serializable {
    
	private static final long serialVersionUID = 1L;

    /** Identificador do técnico (opcional, usado em atualizações) */
    private Integer id;
    
    /** Nome do técnico (obrigatório) */
    @NotNull(message = "Nome é obrigatório")
    private String nome;
    
    /** CPF do técnico (obrigatório) */
    @NotNull(message = "CPF é obrigatório")
    private String cpf;
    
    /** Email do técnico (obrigatório) */
    @NotNull(message = "Email é obrigatório")
    private String email;
    
    /** Senha do técnico (obrigatório) */
    @NotNull(message = "Senha é obrigatória")
    private String senha;

    /** Construtor padrão */
    public TecnicoCreateDTO() {
    }

    /**
     * Construtor com parâmetros para criar ou atualizar um técnico.
     *
     * @param id Identificador do técnico
     * @param nome Nome do técnico
     * @param cpf CPF do técnico
     * @param email Email do técnico
     * @param senha Senha do técnico
     */
    public TecnicoCreateDTO(Integer id, String nome, String cpf, String email, String senha) {
    	this.id = id;
    	this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
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

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}