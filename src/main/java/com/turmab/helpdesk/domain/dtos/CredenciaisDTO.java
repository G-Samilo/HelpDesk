package com.turmab.helpdesk.domain.dtos;

/**
 * Data Transfer Object (DTO) utilizado para enviar credenciais de autenticação.
 * Contém email e senha do usuário.
 * 
 * @author: Gabriel Samilo
 */
public class CredenciaisDTO {

	/** Email do usuário */
    private String email;
    
    /** Senha do usuário */
    private String senha;

    /** Construtor padrão */
    public CredenciaisDTO() {
        super();
    }

    /**
     * Construtor com parâmetros para criar um DTO de credenciais.
     *
     * @param email Email do usuário
     * @param senha Senha do usuário
     */
    public CredenciaisDTO(String email, String senha) {
        super();
        this.email = email;
        this.senha = senha;
    }

    /** @return o email do usuário */
    public String getEmail() {
        return email;
    }

    /** @param email define o email do usuário */
    public void setEmail(String email) {
        this.email = email;
    }

    /** @return a senha do usuário */
    public String getSenha() {
        return senha;
    }

    /** @param senha define a senha do usuário */
    public void setSenha(String senha) {
        this.senha = senha;
    }
}
