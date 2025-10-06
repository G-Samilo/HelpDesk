package com.turmab.helpdesk.domain.dtos;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class TecnicoCreateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    @NotNull(message = "Nome é obrigatório")
    private String nome;
    
    @NotNull(message = "CPF é obrigatório")
    private String cpf;
    
    @NotNull(message = "Email é obrigatório")
    private String email;
    
    @NotNull(message = "Senha é obrigatória")
    private String senha;

    // Construtores
    public TecnicoCreateDTO() {
    }

    public TecnicoCreateDTO(Integer id, String nome, String cpf, String email, String senha) {
    	this.id = id;
    	this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }

    // Getters e Setters
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