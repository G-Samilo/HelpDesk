package com.turmab.helpdesk.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.turmab.helpdesk.domain.enums.Perfil;

/**
 * Entidade Cliente que representa um cliente do sistema HelpDesk.
 * Herda atributos da classe Pessoa e mantém a lista de chamados do cliente.
 * 
 * Cada cliente possui automaticamente o perfil CLIENTE.
 * 
 * @author: Gabriel Samilo
 */
@Entity
public class Cliente extends Pessoa {


	private static final long serialVersionUID = 1L;

	 /** Lista de chamados associados ao cliente */
	@OneToMany(mappedBy = "cliente")
	private List<Chamado> chamados = new ArrayList<>();

	/** Construtor padrão */
	public Cliente() {
		super();
		addPerfil(Perfil.CLIENTE);
	}

	/**
     * Construtor com parâmetros para criar um cliente.
     * 
     * @param id Identificador do cliente
     * @param nome Nome do cliente
     * @param cpf CPF do cliente
     * @param email Email do cliente
     * @param senha Senha do cliente
     */
	public Cliente(Integer id, String nome, String cpf, String email, String senha) {
	super(id, nome, cpf, email, senha);
	addPerfil(Perfil.CLIENTE);
	}
	
	/**
     * Retorna a lista de chamados do cliente.
     * 
     * @return Lista de Chamado associados ao cliente
     */
	public List<Chamado> getChamados() {
	return chamados;
	}
	
	/**
     * Define a lista de chamados do cliente.
     * 
     * @param chamados Lista de Chamado a ser associada ao cliente
     */
	public void setChamados(List<Chamado> chamados) {
	this.chamados = chamados;
	}

}